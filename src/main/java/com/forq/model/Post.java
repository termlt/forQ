package com.forq.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Post {
    int postId;
    String postTitle;
    String postDescription;
    Date createdDate;
    int postUpvotes;
    int postDownvotes;
}
