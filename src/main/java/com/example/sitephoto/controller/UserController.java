package com.example.sitephoto.controller;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.model.User;
import com.example.sitephoto.service.UserService;
import com.example.sitephoto.service.impl.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailSenderService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getPhotoById(@PathVariable Long id) {
        UserDTO userDTO= userService.findFirstById(id);
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<UserDTO> all(){
        return userService.findAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam User user, @RequestParam Long Id) {
        userService.deleteFirstByIdIfUserAdmin(user, Id);
    }

    @RequestMapping(value = "/login")
    public Boolean login(@RequestParam String email, String password){
        return userService.login(email, password);
    }

    @RequestMapping(value = "/logoff")
    public Boolean login(@RequestParam String email)  {
        userService.logoff(email);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public UserDTO getName(@RequestParam String name) {
        return userService.findFirstByName(name);
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public void addUser(@RequestParam String name,String email, String password) throws MessagingException {
        System.out.println(email);
        emailSenderService.sendMailWithAttachment(email,"Email Verification",
                "Let's gooo ☺");
        userService.addUser(name, email, password, Boolean.FALSE, null,Boolean.FALSE);
    }
    @RequestMapping(value="/findUserByEmail",method = RequestMethod.GET)
    public UserDTO findUserByEmail(@RequestParam String email){
        return userService.findFirstByEmail(email);
    }
    @RequestMapping(value="/updateAdmin",method = RequestMethod.POST) //toggle
    public void updateAdmin(@RequestParam Long id){
        userService.updateAdmin(id);
    }
}
