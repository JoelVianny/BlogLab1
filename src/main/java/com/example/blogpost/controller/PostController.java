package com.example.blogpost.controller;



import com.example.blogpost.entities.Post;
import com.example.blogpost.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
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
public class PostController {

    private  final PostService postService;


   @PostMapping
   public ResponseEntity<Post> createPost(@RequestBody Post post){
       return  ResponseEntity.created(URI.create("/posts/userID")).body(postService.createPost(post));
   }

//    public ResponseEntity<Post> creatPost(@RequestBody Post post){
//       try {
//           postService.createPost(post);
//           return new ResponseEntity<>(HttpStatusCode.valueOf(200));
//       }
//       catch (Exception e){
//           return new ResponseEntity<>(HttpStatusCode.valueOf(500));
//       }
//
//   }

   @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long id){
       return ResponseEntity.ok().body(postService.getPost(id));
   }

   @GetMapping

   public  ResponseEntity<List<Post>> getAllPosts(){
       return ResponseEntity.ok().body(postService.getAllPost());
   }
//   @GetMapping
//    public  ResponseEntity<Page<Post>> getPosts(@RequestParam (value = "page", defaultValue = "0") int page,
//                                                @RequestParam(value = " size", defaultValue = "10") int size){
//       return ResponseEntity.ok().body(postService.getAllPostPage(page, size));
//   }

   @PutMapping("/photo")
    public  ResponseEntity<String> uploadPhoto(@RequestParam("id") long id , @RequestParam("file") MultipartFile file){
       return ResponseEntity.ok().body(postService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}
