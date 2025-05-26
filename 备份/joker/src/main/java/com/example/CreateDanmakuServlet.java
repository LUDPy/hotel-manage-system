package com.example;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/createDanmaku")
public class CreateDanmakuServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASS = "123456";
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String label = request.getParameter("label");

        if (name == null || name.trim().isEmpty()) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "缺少必要的参数：梗的内容");
            return;
        }

        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = prepareInsertStatement(conn, name, description, label)) {
            pstmt.executeUpdate();
            sendSuccessResponse(response, "词条创建成功");
        } catch (SQLException e) {
            System.err.println("数据库操作失败: " + e.getMessage());
            e.printStackTrace();
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "词条创建失败，请稍后重试");
        } catch (Exception e) {
            System.err.println("发生意外错误: " + e.getMessage());
            e.printStackTrace();
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "发生意外错误，请稍后重试");
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

    private PreparedStatement prepareInsertStatement(Connection conn, String name, String description, String label) throws SQLException {
        int id = getNextAvailableId(conn);
        // 使用双引号括起来的 "Description" 和 "Label" 字段名以确保区分大小写
        String sql = "INSERT INTO DANMAKU (ID, NAME, LIKE_COUNT, Description, Label) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setInt(3, 0);
        pstmt.setString(4, description);
        pstmt.setString(5, label);
        return pstmt;
    }

    private int getNextAvailableId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(ID), 0) + 1 FROM DANMAKU";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 1;
    }

    private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", "success");
        responseData.put("message", message);
        String jsonResponse = gson.toJson(responseData);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse);
    }

    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", "error");
        responseData.put("message", message);
        String jsonResponse = gson.toJson(responseData);
        response.setStatus(statusCode);
        response.getWriter().write(jsonResponse);
    }

    public List<String> getDanmakus() {
        List<String> danmakuList = new ArrayList<>();
        try (Connection conn = getDatabaseConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT NAME FROM DANMAKU ORDER BY ID")) {
            while (rs.next()) {
                danmakuList.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("数据库连接失败: " + e.getMessage());
            e.printStackTrace();
        }
        return danmakuList;
    }
}