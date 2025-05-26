package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

@WebServlet("/hotWordDetail")
public class HotWordDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wordName = request.getParameter("name");
        String name = null;
        String description = null;
        int like_count = 0;
        Date createdAt = null;
        int id = 0;

        try {
            // 加载数据库驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 建立数据库连接
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "123456");
            // 准备 SQL 查询语句，增加 id 字段
            String sql = "SELECT ID, NAME, Description, LIKE_COUNT, created_at FROM DANMAKU WHERE NAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wordName);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("NAME");
                description = rs.getString("Description");
                like_count = rs.getInt("LIKE_COUNT");
                createdAt = rs.getDate("created_at");
            }
            // 关闭资源
            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (name != null) {
            json.append("\"id\": ").append(id).append(",");
            json.append("\"name\": \"").append(name).append("\",");
            json.append("\"description\": \"").append(description).append("\",");
            json.append("\"like_count\": ").append(like_count).append(",");
            json.append("\"createdAt\": \"").append(createdAt == null ? "" : createdAt.toString()).append("\"");
        } else {
            json.append("\"error\": \"未找到该热梗\"");
        }
        json.append("}");
        out.println(json.toString());
    }
}