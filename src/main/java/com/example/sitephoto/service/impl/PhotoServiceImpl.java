package com.example.sitephoto.service.impl;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.mapper.PhotoMapper;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.PhotoRepository;
import com.example.sitephoto.repository.UserRepository;
import com.example.sitephoto.service.PhotoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    //@Autowired
    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;
    private final UserRepository userRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, PhotoMapper photoMapper,
                            UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
        this.userRepository = userRepository;
    }

    @Override

    public List<PhotoDTO> findAll() {
        List<Photo> photoList = (List<Photo>) photoRepository.findAll();
        List<PhotoDTO> photoDTOList = new ArrayList<PhotoDTO>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoMapper.mapModelToDto(photo));
        }
        return photoDTOList;
    }

    @Override
    public List<PhotoDTO> findAllOfUser(User user) {
        List<Photo> photoList = (List<Photo>) photoRepository.findAll();
        List<PhotoDTO> photoDTOList = new ArrayList<PhotoDTO>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoMapper.mapModelToDto(photo));
        }
        return photoDTOList;
    }

    //    @Override
    public PhotoDTO findFirstById(Long id) {
        return PhotoMapper.mapModelToDto(photoRepository.findFirstById(id));
    }

    @Override
    public PhotoDTO findFirstByName(String name) {
        return PhotoMapper.mapModelToDto(photoRepository.findFirstByName(name));
    }

    @Override
    public PhotoDTO findFirstByUser(User user) {
        return PhotoMapper.mapModelToDto(photoRepository.findFirstByUser(user));
    }

    @Override
    public PhotoDTO findFirstByDescription(String description) {
        return PhotoMapper.mapModelToDto(photoRepository.findFirstByDescription(description));
    }

    @Override
    public List<PhotoDTO> findAllFiltered(Long threshold) {
        List<Photo> photoList = (List<Photo>) photoRepository.findAll();
        List<PhotoDTO> photoDTOList = new ArrayList<PhotoDTO>();
        for (Photo photo : photoList) {
            if (photo.getUser() != null) {
                if (photo.getUser().getId().equals(threshold)) {
                    photoDTOList.add(PhotoMapper.mapModelToDto(photo));
                }
            }
        }
        return photoDTOList;
    }

    @Override
    public void addPhoto(Long userId, String name, String path, String description, byte[] imagedata) {
        Photo x = new Photo();
        User user = userRepository.findFirstById(userId);
        x.setUser(user);
        x.setName(name);
        x.setPath(path);
        x.setDescription(description);
        x.setImageData(imagedata);
        photoRepository.save(x);
    }

    @Override
    public Photo updatePhoto(Photo photo, Long id) {
        //se se actualizeaza campurile pozei cu id-ul is cu valorile campurilor pozei photo
        Photo updatePhoto = photoRepository.findFirstById(id);// de implementat exceptie
        updatePhoto.setUser(photo.getUser());
        updatePhoto.setName(photo.getName());
        updatePhoto.setPath(photo.getPath());
        updatePhoto.setDescription(photo.getDescription());
        photoRepository.save(updatePhoto);
        return updatePhoto;
    }

    @Override
    public Photo updateName(Photo photo, String name) {
        Photo updatePhoto = photoRepository.findFirstById(photo.getId());// de implementat exceptie
        if (name != null) {
            updatePhoto.setName(name);
        }
        photoRepository.save(updatePhoto);
        return updatePhoto;
    }

    @Override
    public Photo updatePath(Photo photo, String path) {
        Photo updatePhoto = photoRepository.findFirstById(photo.getId());// de implementat exceptie
        if (path != null) {
            updatePhoto.setPath(path);
        }
        photoRepository.save(updatePhoto);
        return updatePhoto;
    }

    @Override
    public Photo updateDescription(Photo photo, String description) {
        Photo updatePhoto = photoRepository.findFirstById(photo.getId());// de implementat exceptie
        if (description != null) {
            updatePhoto.setDescription(description);
        }
        photoRepository.save(updatePhoto);
        return updatePhoto;
    }

    @Override
    public Photo updateUser(Photo photo, User user) {
        Photo updatePhoto = photoRepository.findFirstById(photo.getId());// de implementat exceptie
        if (user != null) {
            updatePhoto.setUser(user);
        }
        photoRepository.save(updatePhoto);
        return updatePhoto;
    }

    public void deleteFirstById(Long id) {
        Photo p = photoRepository.findFirstById(id);
        photoRepository.delete(p);
    }
}
