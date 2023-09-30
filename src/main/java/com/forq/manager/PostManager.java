package com.forq.manager;

import com.forq.db.DBConnectionProvider;
import com.forq.model.Post;
import com.forq.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    UserManager userManager = new UserManager();

    public void addPost(Post post) {
        String query = "INSERT INTO post (post_title, post_description, created_date, post_upvotes, post_downvotes, user_id)" +
                " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getDescription());
            java.util.Date currentDate = new java.util.Date();
            preparedStatement.setDate(3, new java.sql.Date(currentDate.getTime()));
            preparedStatement.setInt(4, post.getUpvotes());
            preparedStatement.setInt(5, post.getDownvotes());
            preparedStatement.setInt(6, post.getUser().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * from post";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("post_title"));
                post.setDescription(resultSet.getString("post_description"));
                int userId = resultSet.getInt("user_id");
                User user = userManager.getUserById(userId);
                post.setUser(user);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }


    public List<Post> getAllPostsByUser(int userId) {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * from post WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setTitle(resultSet.getString("post_title"));
                post.setDescription(resultSet.getString("post_description"));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }


    public Post getPostById(int postId) {
        String query = "SELECT * from post WHERE post_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("post_title"));
                post.setDescription(resultSet.getString("post_description"));
                int userId = resultSet.getInt("user_id");
                User user = userManager.getUserById(userId);
                post.setUser(user);
                return post;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public void changePostTitle(String newTitle, int postId) {
        String query = "UPDATE post SET post_title = ? WHERE post_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, postId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void changePostDescription(String newDescription, int postId) {
        String query = "UPDATE post SET post_description = ? WHERE post_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, postId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
