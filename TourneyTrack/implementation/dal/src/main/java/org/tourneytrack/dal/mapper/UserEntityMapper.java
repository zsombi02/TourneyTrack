package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.tourneytrack.dal.entity.UserEntity;
import org.tourneytrack.impl.data.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toModel(UserEntity entity);
    UserEntity toEntity(User model);
}
