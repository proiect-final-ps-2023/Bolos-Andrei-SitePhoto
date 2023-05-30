package com.example.sitephoto.controller;

import com.example.sitephoto.DTO.PhotoDTO;
import com.example.sitephoto.mapper.PhotoMapper;
import com.example.sitephoto.model.Photo;
import com.example.sitephoto.model.User;
import com.example.sitephoto.repository.PhotoRepository;
import com.example.sitephoto.repository.UserRepository;
import com.example.sitephoto.service.PhotoService;
import com.example.sitephoto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private UserRepository userRepository;

    public PhotoController(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<PhotoDTO> all(){
        return photoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> getPhotoById(@PathVariable Long id) {
        PhotoDTO photoDTO= photoService.findFirstById(id);
        return ResponseEntity.ok(photoDTO);
    }
    @RequestMapping(value = "/findAllFiltered",method = RequestMethod.GET)
    public List<PhotoDTO> findAllFiltered(@RequestParam Long threshold){
        return photoService.findAllFiltered(threshold);
    }

    @RequestMapping(value = "/allOfUser",method = RequestMethod.GET)
    public List<PhotoDTO> getAllOfUser(@RequestParam User user){
        return photoService.findAllOfUser(user);
    }
    @RequestMapping(value = "/oneOfUser", method = RequestMethod.GET)
    public PhotoDTO getOneOfUser(@RequestParam User user){
        return photoService.findFirstByUser(user);
    }
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public PhotoDTO getName(@RequestParam String name){
        return photoService.findFirstByName(name);
    }
    @RequestMapping(value="/updateDescription", method = RequestMethod.POST)
    public void updateDescription(@RequestParam Long id, String description)
    {
        photoService.updateDescription(photoMapper.mapDtoToModel(photoService.findFirstById(id)),description);
    }
    @RequestMapping(value="/updateName", method = RequestMethod.POST)
    public void updateName(@RequestParam Long id, String name)
    {
        photoService.updateName(photoMapper.mapDtoToModel(photoService.findFirstById(id)),name);
    }
    @RequestMapping(value="/updatePath", method = RequestMethod.POST)
    public void updatePath(@RequestParam Long id, String path)
    {
        photoService.updatePath(photoMapper.mapDtoToModel(photoService.findFirstById(id)),path);
    }
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public void updateUser(@RequestParam Long id, Long userId)
    {
        User user = userRepository.findFirstById(userId);
        photoService.updateUser(photoMapper.mapDtoToModel(photoService.findFirstById(id)),user);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        photoService.deleteFirstById(id);
    }

    @RequestMapping(value ="/addPhoto", method = RequestMethod.POST)
    public void addPhoto(@RequestParam Long userId, String name,String path, String description, MultipartFile file) throws IOException {
        photoService.addPhoto(userId, name,path,description,compressBytes(file.getBytes()));
    }

    @PostMapping("/upload")
    public ResponseEntity.BodyBuilder uploadImage(@RequestParam("imagedata") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        Photo img = new Photo();
        img.setImageData( compressBytes(file.getBytes()));
        photoRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
