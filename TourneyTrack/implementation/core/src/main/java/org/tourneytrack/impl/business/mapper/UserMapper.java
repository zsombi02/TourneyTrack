package org.tourneytrack.impl.business.mapper;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.User;
import org.tourneytrack.impl.data.UserType;
import org.tourneytrack.intf.dto.data.UserDto;
import org.tourneytrack.intf.dto.data.UserTypeDto;

@Component
public class UserMapper {

    public UserDto toDto(User model) {
        if (model == null) return null;

        UserDto dto = new UserDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setType(model.getType() != null ? model.getType().name() : null);
        return dto;
    }

    public User toModel(UserDto dto) {
        if (dto == null) return null;

        User model = new User();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        model.setType(dto.getType() != null ? UserType.valueOf(dto.getType()) : null);
        return model;
    }

    public UserTypeDto toDto(UserType type) {
        if (type == null) return null;
        return UserTypeDto.valueOf(type.name());
    }

    public UserType toModel(UserTypeDto dto) {
        if (dto == null) return null;
        return UserType.valueOf(dto.name());
    }
}
