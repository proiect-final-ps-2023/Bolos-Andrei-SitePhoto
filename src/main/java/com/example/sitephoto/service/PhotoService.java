package com.example.sitephoto.service;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface PhotoService {
    PhotoDTO findFirstByName(String name);
    PhotoDTO findFirstById (Long id);
    PhotoDTO findFirstByUser(User user);
    public List<PhotoDTO> findAll();
    PhotoDTO findFirstByDescription(String description);
    void addPhoto(Long userId, String name,String path,String description,byte[] imagedata);
    Photo updatePhoto(Photo photo,Long id);

    Photo updateName(Photo photo, String name);
    Photo updatePath(Photo photo, String path);
    Photo updateDescription(Photo photo, String description);
    Photo updateUser(Photo photo, User user);
    List<PhotoDTO> findAllOfUser(User user);
    void deleteFirstById(Long id);

    List<PhotoDTO> findAllFiltered(Long threshold);
}
