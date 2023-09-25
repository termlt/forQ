package com.forq.model;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    int id;
    String username;
    String password;
    Date creationDate;
}
