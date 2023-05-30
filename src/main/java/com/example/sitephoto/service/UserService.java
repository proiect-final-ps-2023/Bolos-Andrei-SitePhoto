package com.example.sitephoto.service;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//@Component
public interface UserService {
    UserDTO findFirstById(Long id);
//    void delete(Long id);
//    UserDTO findById(Long id);
//    List<UserDTO> findAll();
    UserDTO findFirstByName(String name);
    UserDTO findFirstByEmail(String email);
    User updateUser(Long id, User newUser);
    void deleteById(Long id);
    public List<UserDTO> findAll();
    void addPhoto(User user, Photo photo);
    void deletePhoto(User user, Photo photo);
    public void deleteAllPhotos(User user);
    void addUser(String name, String email, String password, Boolean admin, ArrayList<Photo> photoList,Boolean isloggedin);
    void deleteFirstByIdIfUserAdmin(User userAdmin, Long id);
    void updateName(Long id, User newUser);
    public Boolean login(String email, String password);
    public Boolean logoff(String email);
    void updateEmail(Long id, User newUser);
    void updatePassword(Long id, User newUser);
    void updateAdmin(Long id);//toggle
    void updatePhotoList(Long id, User newUser);
}
