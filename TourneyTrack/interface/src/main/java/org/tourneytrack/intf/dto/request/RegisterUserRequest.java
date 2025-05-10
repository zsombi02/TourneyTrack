package org.tourneytrack.intf.dto.request;

import lombok.Data;
import org.tourneytrack.intf.dto.data.UserTypeDto;

@Data
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password; // majd hash-elve
    private UserTypeDto type;
}
