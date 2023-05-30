package com.example.sitephoto.service.impl;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.mapper.PhotoMapper;
import com.example.sitephoto.mapper.UserMapper;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.PhotoRepository;
import com.example.sitephoto.repository.UserRepository;
import com.example.sitephoto.service.PhotoService;
import com.example.sitephoto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //    @Autowired
    private final UserRepository userRepository;

    //    @Autowired
    private final UserMapper userMapper;

    //    @Autowired
    private final PhotoRepository photoRepository;

    private final PhotoMapper photoMapper;

//    PhotoService photoService = new PhotoServiceImpl(photoRepository);


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PhotoRepository photoRepository, PhotoMapper photoMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }


    public List<UserDTO> findAll() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (User user : userList) {
            userDTOList.add(UserMapper.mapModelToDto(user));
        }
        return userDTOList;
    }

    @Override
    public Boolean login(String email, String password) {
        UserDTO userDTO = this.findFirstByEmail(email);
        userDTO.setIsloggedin(Boolean.TRUE);
        User user = UserMapper.mapDtoToModel(userDTO);
        userRepository.save(user);
        if (userDTO.getPassword().equals(passEncryption(password)))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @Override
    public Boolean logoff(String email) {
        UserDTO userDTO = this.findFirstByEmail(email);
        userDTO.setIsloggedin(Boolean.FALSE);
        User user = UserMapper.mapDtoToModel(userDTO);
        userRepository.save(user);
        return user.getIsloggedin();
    }

    @Override
    public UserDTO findFirstById(Long id) {
        return UserMapper.mapModelToDto(userRepository.findFirstById(id));
    }

    @Override
    public UserDTO findFirstByName(String name) {
        return UserMapper.mapModelToDto(userRepository.findFirstByName(name));
    }

    @Override
    public UserDTO findFirstByEmail(String email) {
        return UserMapper.mapModelToDto(userRepository.findFirstByEmail(email));
    }

    @Override
    public void addUser(String name, String email, String password, Boolean admin, ArrayList<Photo> photoList, Boolean isloggedin) {
        User u = new User();
        u.setPassword(passEncryption(password));
        u.setName(name);
        u.setEmail(email);
        u.setAdmin(admin);
        u.setPhotoList(photoList);
        u.setIsloggedin(isloggedin);
        userRepository.save(u);
    }

    @Override
    public void addPhoto(User user, Photo photo) {
        User u = userRepository.findFirstById(user.getId());
        u.getPhotoList().add(photo);
        Photo p = photoRepository.findFirstById(photo.getId());
        p.setUser(user);
        photoRepository.save(p);
        userRepository.save(u);
    }

    @Override
    public void deletePhoto(User user, Photo photo) {
        User u = userRepository.findFirstById(user.getId());
        u.getPhotoList().remove(photo);
        userRepository.save(u);
        Photo p = photoRepository.findFirstById(photo.getId());
        p.setUser(null);
        photoRepository.delete(p);
    }

    public void deleteAllPhotos(User user) {
        user.setPhotoList(null);
    }

    @Override
    public void updateName(Long id, User newUser) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        u.setName(newUser.getName());
        userRepository.save(u);
    }

    @Override
    public void updateEmail(Long id, User newUser) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        u.setEmail(newUser.getEmail());
        userRepository.save(u);
    }

    @Override
    public void updatePassword(Long id, User newUser) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        u.setPassword(passEncryption(newUser.getPassword()));
        userRepository.save(u);
    }

    @Override
    public void updateAdmin(Long id) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        if (u.getAdmin().equals(Boolean.TRUE)) {
            u.setAdmin(Boolean.FALSE);
        } else {//toggle
            u.setAdmin(Boolean.TRUE);
        }
        userRepository.save(u);
    }

    @Override
    public void updatePhotoList(Long id, User newUser) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        u.setPhotoList(newUser.getPhotoList());
        userRepository.save(u);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        User u = userRepository.findFirstById(id);// de implementat exceptie
        u.setName(newUser.getName());
        u.setEmail(newUser.getEmail());
        u.setPassword(passEncryption(newUser.getPassword()));
        u.setAdmin(newUser.getAdmin());
        u.setPhotoList(newUser.getPhotoList());
        u.setIsloggedin(newUser.getIsloggedin());
        userRepository.save(u);
        return u;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteFirstByIdIfUserAdmin(User userAdmin, Long id) {
        if (userAdmin.getAdmin()) {
            User u = userRepository.findFirstById(id);
            UserService userService = new UserServiceImpl(userRepository, userMapper, photoRepository, photoMapper);
            userService.deleteAllPhotos(u);
            userRepository.deleteById(id);
        }
    }

    private static String passEncryption(String password) {
        try {
            // Get the key to use for encrypting the data
            byte[] key = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return new String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
