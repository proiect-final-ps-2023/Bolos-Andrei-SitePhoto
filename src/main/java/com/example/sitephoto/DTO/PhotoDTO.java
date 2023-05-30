package com.example.sitephoto.DTO;

import com.example.sitephoto.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoDTO {
    private Long id;
    private String name;
    private String path;
    private String description;
    private Long userid;
    private byte[] imagedata;
}
