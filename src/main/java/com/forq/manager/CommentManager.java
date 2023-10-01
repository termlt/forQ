package com.forq.manager;

import com.forq.db.DBConnectionProvider;
import com.forq.model.Comment;
import com.forq.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    UserManager userManager = new UserManager();

    public void addComment(Comment comment) {
        String query = "INSERT INTO comment (text, post_id, user_id) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setInt(3, comment.getUser().getId());

            preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public List<Comment> getAllCommentsOfPost(int postId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE post_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("comment_id"));
                comment.setText(resultSet.getString("text"));
                int userId = resultSet.getInt("user_id");
                User user = userManager.getUserById(userId);
                comment.setUser(user);
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }



    public List<Comment> getAllCommentsByUser(int userId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("comment_id"));
                comment.setText(resultSet.getString("text"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }


    public void deleteComment(int commentId) {
        String query = "DELETE FROM comment WHERE comment_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commentId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeCommentText(String text, int commentId) {
        String query = "UPDATE comment SET text = ? WHERE comment_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, commentId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
