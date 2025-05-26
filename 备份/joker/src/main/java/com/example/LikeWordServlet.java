package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/likeWord")
public class LikeWordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "123456";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id parameter is required");
            return;
        }
        int id = Integer.parseInt(idStr);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateSql = "UPDATE DANMAKU SET LIKE_COUNT = LIKE_COUNT + 1 WHERE ID = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSql)) {
                pstmtUpdate.setInt(1, id);
                int rowsUpdated = pstmtUpdate.executeUpdate();

                if (rowsUpdated > 0) {
                    String selectSql = "SELECT LIKE_COUNT FROM DANMAKU WHERE ID = ?";
                    try (PreparedStatement pstmtSelect = conn.prepareStatement(selectSql)) {
                        pstmtSelect.setInt(1, id);
                        ResultSet rs = pstmtSelect.executeQuery();
                        if (rs.next()) {
                            int like_count = rs.getInt("like_count");
                            response.getWriter().write("{\"success\":true,\"like_count\":" + like_count + "}");
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Hot word not found");
                        }
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Hot word not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}