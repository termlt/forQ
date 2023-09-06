package com.forq.model;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    int userId;
    String username;
    String password;
    Date userCreationDate;
    List<Post> posts = new ArrayList<>();
}
