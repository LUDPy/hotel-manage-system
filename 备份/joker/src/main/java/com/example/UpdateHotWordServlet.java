package com.example;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateHotWordServlet")
public class UpdateHotWordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "123456";
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String categoryIdStr = request.getParameter("Label");
        String likesStr = request.getParameter("like_count");

        if (idStr == null || idStr.isEmpty() || name == null || name.isEmpty() ||
                categoryIdStr == null || categoryIdStr.isEmpty() || likesStr == null || likesStr.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Missing required parameters");
            out.println(gson.toJson(result));
            return;
        }

        int id = Integer.parseInt(idStr);
        String Label = categoryIdStr;
        int like_count = Integer.parseInt(likesStr);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE DANMAKU SET name = ?, description = ?, Label = ?, like_count = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.setString(3, Label);
                pstmt.setInt(4, like_count);
                pstmt.setInt(5, id);

                int rowsUpdated = pstmt.executeUpdate();
                Map<String, Object> result = new HashMap<>();
                if (rowsUpdated > 0) {
                    result.put("success", true);
                } else {
                    result.put("success", false);
                    result.put("message", "Hot word not found or update failed");
                }
                out.println(gson.toJson(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Internal Server Error");
            out.println(gson.toJson(result));
        }
    }
}