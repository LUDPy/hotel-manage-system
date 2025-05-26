package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hotJokes")
public class HotJokesServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASS = "123456";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // 若解析失败，使用默认值
        }

        int pageSize = 4; // 每页显示的梗数量
        List<Joke> jokes = new ArrayList<>();
        int totalCount = 0;

        try (Connection conn = getDatabaseConnection()) {
            // 查询总记录数
            String countSql = "SELECT COUNT(*) FROM DANMAKU";
            try (PreparedStatement countStmt = conn.prepareStatement(countSql);
                 ResultSet countRs = countStmt.executeQuery()) {
                if (countRs.next()) {
                    totalCount = countRs.getInt(1);
                }
            }

            // 计算总页数
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);

            // 三层子查询，确保分页后排名从 1 开始
            String sql = "SELECT * FROM ( " +
                    "    SELECT sub.*, ROW_NUMBER() OVER (ORDER BY LIKE_COUNT DESC) AS page_rank " +
                    "    FROM ( " +
                    "        SELECT t.*, ROW_NUMBER() OVER (ORDER BY LIKE_COUNT DESC) as rn " +
                    "        FROM DANMAKU t " +
                    "    ) sub WHERE sub.rn BETWEEN ? AND ? " +
                    ") WHERE page_rank BETWEEN 1 AND ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                int startRow = (pageNumber - 1) * pageSize + 1;
                int endRow = pageNumber * pageSize;
                pstmt.setInt(1, startRow);
                pstmt.setInt(2, endRow);
                pstmt.setInt(3, pageSize); // 确保分页后排名从 1 开始

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("NAME");
                        int likeCount = rs.getInt("LIKE_COUNT");
                        String description = rs.getString("DESCRIPTION");
                        String label = rs.getString("LABEL");
                        int rank = rs.getInt("page_rank"); // 确保当前页内排名从 1 递增
                        jokes.add(new Joke(id, name, likeCount, description, label, rank));
                    }
                }
            }

            // 返回 JSON 响应
            PrintWriter out = response.getWriter();
            out.print("{");
            out.print("\"totalPages\": " + totalPages + ",");
            out.print("\"currentPage\": " + pageNumber + ",");
            out.print("\"data\": [");
            for (int i = 0; i < jokes.size(); i++) {
                Joke joke = jokes.get(i);
                out.print("{");
                out.print("\"rank\": " + joke.getRank() + ",");
                out.print("\"id\": " + joke.getId() + ",");
                out.print("\"name\": \"" + escapeJson(joke.getContent()) + "\",");
                out.print("\"likeCount\": " + joke.getLikeCount() + ",");
                out.print("\"description\": \"" + escapeJson(joke.getDescription()) + "\",");
                out.print("\"label\": \"" + escapeJson(joke.getLabel()) + "\"");
                out.print("}");
                if (i < jokes.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]}");

        } catch (SQLException e) {
            System.err.println("数据库操作失败: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("无法加载数据库驱动", e);
        }
    }

    private String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    static class Joke {
        private int id;
        private String name;
        private int likeCount;
        private String description;
        private String label;
        private int rank;

        public Joke(int id, String name, int likeCount, String description, String label, int rank) {
            this.id = id;
            this.name = name;
            this.likeCount = likeCount;
            this.description = description;
            this.label = label;
            this.rank = rank;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return name;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public String getDescription() {
            return description;
        }

        public String getLabel() {
            return label;
        }

        public int getRank() {
            return rank;
        }
    }
}
