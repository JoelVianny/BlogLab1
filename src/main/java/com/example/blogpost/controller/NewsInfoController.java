package com.example.blogpost.controller;


import com.example.blogpost.entities.NewsInfo;
import com.example.blogpost.service.NewsInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.example.blogpost.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class NewsInfoController {

    private  final NewsInfoService   newsInfoService;


   @PostMapping
   public ResponseEntity<NewsInfo> createPost(@RequestBody NewsInfo newsInfo){
       return  ResponseEntity.created(URI.create("/newsInfo/userID")).body(newsInfoService.createNewsInfo(newsInfo));
   }


   @GetMapping("/{id}")
    public ResponseEntity<NewsInfo> getPost(@PathVariable(value = "id") Long id){
       return ResponseEntity.ok().body(newsInfoService.getNewsInfo(id));
   }

   @GetMapping

   public  ResponseEntity<List<NewsInfo>> getAllNewsInfo(){
       return ResponseEntity.ok().body(newsInfoService.getAllNews());
   }


   @PutMapping("/photo")
    public  ResponseEntity<String> uploadPhoto(@RequestParam("id") long id , @RequestParam("file") MultipartFile file){
       return ResponseEntity.ok().body(newsInfoService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}
