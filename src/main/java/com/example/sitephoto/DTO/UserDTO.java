package com.example.sitephoto.DTO;

import com.example.sitephoto.model.Photo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean admin;
    private Boolean isloggedin;
//    private List<Photo> photoList;
}
