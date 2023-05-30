package com.example.sitephoto.repository;

import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

    Photo findFirstById (Long id);
    Photo findFirstByName(String name);
    Photo findFirstByUser(User user);
    Photo findFirstByDescription(String description);
    List<Photo> findAllByUser(User user);
//    void addPhoto(String name,String path,String description,Boolean admin, User user);
//    Photo updatePhoto(Photo photo);
//    void deleteFirstById(Long id);
}
