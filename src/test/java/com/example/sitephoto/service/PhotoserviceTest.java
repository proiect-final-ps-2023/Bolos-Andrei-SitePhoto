package com.example.sitephoto.service;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.mapper.PhotoMapper;
import com.example.sitephoto.mapper.UserMapper;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.PhotoRepository;
import com.example.sitephoto.repository.UserRepository;
import com.example.sitephoto.service.impl.PhotoServiceImpl;
import com.example.sitephoto.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhotoserviceTest {
    private final Long idP = 1L;
    private final String nameP = "Photo1";
    private final String path = "B:\\Poze\\Poze Aparat edit\\Lightroom exports\\BOL_4929-16x9.jpg";
    private final String description = "First Photo";

    private final Long unexistentIdP = 46L;
    private final String unexistentNameP = "200th Photo";
    private final String unexistentPath = "Dorel";
    private final String unexistentDescription = "La o bericica cu Dorel";

    private final Long idU = 1L;
    private final String nameU = "User1";
    private final String email = "Email1";
    private final String password = "pass1";
    private final Boolean admin = Boolean.TRUE;
    private ArrayList<Photo> photoList;
    private final Boolean isloggedin = Boolean.FALSE;

    private final Long unexistentIdU = 31L;
    private final String unexistentNameU = "User321";
    private final String unexistentEmail = "Email13";
    private final String unexistentPassword = "pass24";
    private final Boolean unexistentAdmin = Boolean.FALSE;
    private final Boolean unexistentIsloggedin = Boolean.FALSE;
    private ArrayList<Photo> unexistentPhotoList;

    UserDTO user1DTO = new UserDTO(idU, nameU, email, password,admin,isloggedin);;
    User fakeUser = new User(unexistentIdU, unexistentNameU, unexistentEmail, unexistentPassword,unexistentAdmin,unexistentPhotoList,unexistentIsloggedin);
    UserDTO fakeUserDTO = new UserDTO(unexistentIdU, unexistentNameU, unexistentEmail, unexistentPassword,unexistentAdmin,unexistentIsloggedin);

    private UserService userService;
    private PhotoService photoService;
    @Mock
    private UserRepository userRepository ;
    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private PhotoMapper photoMapper;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void init(){
        initMocks(this);

        User user = new User(idU, nameU, email, password,admin,photoList,isloggedin);
        Photo photo = new Photo(idP,nameP,path,description,user,null);
        when(this.userRepository.findFirstByName(nameU)).thenReturn(user);
        when(this.userRepository.findFirstById(idU)).thenReturn(user);
        when(this.photoRepository.findFirstByName(nameP)).thenReturn(photo);
        when(this.photoRepository.findFirstByUser(user)).thenReturn(photo);
        when(this.photoRepository.findFirstById(idP)).thenReturn(photo);
    }
    @Test
    void givenExistingUser_whenFindByUser_thenFindOne()
    {
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        User user1 = new User(idU, nameU, email, password,admin,photoList,isloggedin);
        photoService = new PhotoServiceImpl(photoRepository,photoMapper,userRepository);
        photoService.addPhoto(user1.getId(), nameP, path, description,null);
        PhotoDTO photo= photoService.findFirstByUser(user1);
        assertNotNull(photo);
        assertEquals(user1.getId(), photo.getUserid());
    }
    @Test
    void givenNonExistingUser_whenFindByUser_thenThrowException() {
        when(photoRepository.findFirstByUser(fakeUser)).thenReturn(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            photoService.findFirstByUser(fakeUser);
        });
    }

    @Test
    void givenExistingName_whenFindByName_thenFindOne()
    {
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        User user1 = new User(idU, nameU, email, password,admin,photoList,isloggedin);
        photoService = new PhotoServiceImpl(photoRepository,photoMapper,userRepository);
        photoService.addPhoto(user1.getId(), nameP, path, description,null);
        PhotoDTO photo= photoService.findFirstByUser(user1);//photoRepository.findbyname
        assertNotNull(photo);
        assertEquals(nameP, photo.getName());
    }
    @Test
    void givenNonExistingName_whenFindByName_thenThrowException() {
        when(photoRepository.findFirstByName(unexistentNameP)).thenReturn(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            photoService.findFirstByName(unexistentNameP);
        });
    }


}
