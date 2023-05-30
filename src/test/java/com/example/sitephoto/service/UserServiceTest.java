package com.example.sitephoto.service;

import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.mapper.PhotoMapper;
import com.example.sitephoto.mapper.UserMapper;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.PhotoRepository;
import com.example.sitephoto.repository.UserRepository;
import com.example.sitephoto.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
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

    private final Long idU2 = 2L;
    private final String nameU2 = "User2";
    private final String email2 = "Email2";
    private final String password2 = "pass2";
    private final Boolean admin2 = Boolean.FALSE;
    private ArrayList<Photo> photoList2;
    private final Boolean isloggedin2 = Boolean.FALSE;

    private final Long unexistentIdU = 31L;
    private final String unexistentNameU = "User321";
    private final String unexistentEmail = "Email13";
    private final String unexistentPassword = "pass24";
    private final Boolean unexistentAdmin = Boolean.FALSE;
    private ArrayList<Photo> unexistentPhotoList;
    private final Boolean unexistentIsloggedin = Boolean.FALSE;


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

        User user1 = new User(idU, nameU, email, password,admin,photoList,isloggedin);
        User user2 = new User(idU2, nameU2, email2, password2,admin2,photoList2,isloggedin2);
        Photo photo = new Photo(idP,nameP,path,description,user1,null);
        when(this.userRepository.findFirstByName(nameU)).thenReturn(user1);
//        when(this.userService.deleteFirstByIdIfUserAdmin(user1,this.idU2)).thenReturn(user1);
        when(this.userRepository.findFirstByName(nameU2)).thenReturn(user2);
        when(this.userRepository.findFirstByEmail(email)).thenReturn(user1);
        when(this.userRepository.findFirstByEmail(email2)).thenReturn(user2);
        when(this.userRepository.findFirstById(idU)).thenReturn(user1);
        when(this.userRepository.findFirstById(idU2)).thenReturn(user2);
        when(this.photoRepository.findFirstByName(nameP)).thenReturn(photo);
        when(this.photoRepository.findFirstById(idP)).thenReturn(photo);
    }
    @Test
    void testAddUser(){// CREATE
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        UserDTO user1= userService.findFirstByName(nameU);
        assertNotNull(user1);
        assertEquals(nameU,user1.getName());
    }

    @Test
    void givenExistingNameU_whenFindByName_thenFindOne() {// READ
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        UserDTO user1= userService.findFirstByName(nameU);
        assertNotNull(user1);
        assertEquals(nameU, user1.getName());
    }
    @Test
    void givenNonExistingNameU_whenFindByName_thenThrowException() {// READ
        when(userRepository.findFirstByName(unexistentNameU)).thenReturn(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.findFirstByName(unexistentNameU);
        });
    }
    @Test
    void givenExistingEmail_whenFindByEmail_thenFindOne(){// READ
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        UserDTO user1= userService.findFirstByEmail(email);
        assertNotNull(user1);
        assertEquals(email, user1.getEmail());
    }
    @Test
    void givenNonExistingEmail_whenFindByEmail_thenThrowException() {// READ
        when(userRepository.findFirstByEmail(unexistentEmail)).thenReturn(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.findFirstByEmail(unexistentEmail);
        });
    }

    @Test
    void testUpdateName(){// UPDATE
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        UserDTO user1= userService.findFirstByName(nameU);
        assertNotNull(user1);
        userService.addUser(nameU2, email2, password2,admin2,photoList2,isloggedin);
        UserDTO user2= userService.findFirstByName(nameU2);
        assertNotNull(user2);
        User user = new User(idU2, nameU2, email2, password2,admin2,photoList2,isloggedin);
        userService.updateName(user1.getId(), user);
//        user1.setName(user2.getName());
        assertEquals(user1.getName(), user2.getName());
    }

    @Test
    void testDeleteUserIfAdmin(){// DELETE
        userService = new UserServiceImpl(userRepository,userMapper,photoRepository,photoMapper);
        userService.addUser(nameU, email, password,admin,photoList,isloggedin);
        UserDTO user1= userService.findFirstByName(nameU);
        assertNotNull(user1);// acest user are setat Admin pe TRUE
        userService.addUser(nameU2, email2, password2,admin2,photoList2,isloggedin2);
        UserDTO user2= userService.findFirstByName(nameU2);
        assertNotNull(user2);// acest user are setat Admin pe FALSE
//        userService.deleteFirstByIdIfUserAdmin(user1, user2.getId());
        if(user1.getAdmin()){ userRepository.deleteById(user2.getId());}
        UserDTO user3= userService.findFirstById(user2.getId());
        assertNull(user3);// ar trebui sa dea null, pt ca l am sters pe user2 prin metoda stas deleteById(user2.getId());
    }

}
