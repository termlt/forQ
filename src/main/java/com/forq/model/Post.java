package com.forq.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Post {
    int id;
    String title;
    String description;
    Date createdDate;
    int upvotes;
    int downvotes;
    User user;
}
