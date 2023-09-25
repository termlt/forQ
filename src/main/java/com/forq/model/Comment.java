package com.forq.model;

import lombok.Data;

@Data
public class Comment {
    int id;
    String text;
    Post post;
    User user;
}
