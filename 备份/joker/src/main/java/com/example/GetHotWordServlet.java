package com.example;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/GetHotWordServlet")
public class GetHotWordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "123456";
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            out.println("{}");
            return;
        }
        int id = Integer.parseInt(idStr);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM DANMAKU WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    HotWord hotWord = new HotWord();
                    hotWord.setId(rs.getInt("id"));
                    hotWord.setName(rs.getString("name"));
                    hotWord.setDescription(rs.getString("description"));
                    hotWord.setCategoryId(rs.getString("Label"));
                    hotWord.setLikes(rs.getInt("like_count"));
                    out.println(gson.toJson(hotWord));
                } else {
                    out.println("{}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("{}");
        }
    }
}

class HotWord {
    private int id;
    private String name;
    private String description;
    private String Label;
    private int like_count;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return Label;
    }

    public void setCategoryId(String Label) {
        this.Label = Label;
    }

    public int getLikes() {
        return like_count;
    }

    public void setLikes(int like_count) {
        this.like_count = like_count;
    }
}