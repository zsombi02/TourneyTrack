package org.tourneytrack.intf.dto.data;

import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password; // később majd hash
    private String type;
}
