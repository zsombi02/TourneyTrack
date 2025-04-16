package org.tourneytrack.data;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private UserType type;
}