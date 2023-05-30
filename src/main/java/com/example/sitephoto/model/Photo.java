package com.example.sitephoto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String path;
    private String description;
//    private Date data;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
}
