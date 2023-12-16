package com.kpi.taskmanager.mapper;


import com.kpi.taskmanager.model.auth.Authorities;
import com.kpi.taskmanager.model.dto.UserDto;
import com.kpi.taskmanager.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        imports = Authorities.class
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User dtoToUser(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto userToDto(User user);

    default org.springframework.security.core.userdetails.User entityToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                Authorities.getAllAuthorities());
    }
}
