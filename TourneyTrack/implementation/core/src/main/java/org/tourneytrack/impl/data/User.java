package org.tourneytrack.impl.data;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String password; // később majd hash
    private UserType type;
}