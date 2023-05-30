package com.example.sitephoto.mapper;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO mapModelToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAdmin(user.getAdmin());
        userDTO.setIsloggedin(user.getIsloggedin());
        return userDTO;
    }
    public static User mapDtoToModel(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhotoList(null);
        user.setAdmin(userDTO.getAdmin());
        user.setIsloggedin(userDTO.getIsloggedin());
        return user;
    }
}
