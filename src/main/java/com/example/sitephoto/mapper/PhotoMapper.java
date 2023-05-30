package com.example.sitephoto.mapper;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {
    private final UserRepository userRepository;

    public PhotoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static PhotoDTO mapModelToDto (Photo photo){
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setId(photo.getId());
        photoDTO.setDescription(photo.getDescription());
        photoDTO.setName(photo.getName());
        photoDTO.setPath(photo.getPath());
//        photoDTO.setUserId(5L);
        if(photo.getUser()!=null){
            photoDTO.setUserid(photo.getUser().getId());
        }
        else {
            photoDTO.setUserid(0L);
        }
        if(photo.getImageData()!=null){
            photoDTO.setImagedata(photoDTO.getImagedata());
        }
        else {
            photoDTO.setImagedata(null);
        }
        return photoDTO;
    }

    public Photo mapDtoToModel(PhotoDTO photoDTO) {
        Photo photo = new Photo();
        photo.setId(photoDTO.getId());
        photo.setName(photoDTO.getName());
        photo.setDescription(photo.getDescription());
        photo.setPath(photoDTO.getPath());
        User user = userRepository.findFirstById(photoDTO.getUserid());
        photo.setUser(user);
        return photo;
    }
}
