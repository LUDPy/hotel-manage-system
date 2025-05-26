package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/danmaku")
public class DanmakuServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASS = "123456";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> danmakuList = getDanmakus();
        int nextId = getNextDanmakuId(request);

        if (!danmakuList.isEmpty()) {
            String danmakuContent = danmakuList.get(nextId - 1);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"content\": \"" + danmakuContent + "\", \"nextId\": " + nextId + "}");
        }
    }

    private List<String> getDanmakus() {
        List<String> danmakuList = new ArrayList<>();
        try {
            // 显式加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name FROM danmaku ORDER BY id")) {
                while (rs.next()) {
                    danmakuList.add(rs.getString("name"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("数据库连接失败: " + e.getMessage());
            e.printStackTrace();
        }
        return danmakuList;
    }

    private int getNextDanmakuId(HttpServletRequest request) {
        Integer currentId = (Integer) request.getSession().getAttribute("currentDanmakuId");
        List<String> danmakuList = getDanmakus();
        if (currentId == null || currentId >= danmakuList.size()) {
            currentId = 1;
        } else {
            currentId++;
        }
        request.getSession().setAttribute("currentDanmakuId", currentId);
        return currentId;
    }
}    