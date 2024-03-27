package com.example.blogpost.service;



import com.example.blogpost.entities.Post;
import com.example.blogpost.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;


import static com.example.blogpost.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private  final PostRepository postRepository;

    public List<Post> getAllPost(){
        return  postRepository.findAll();
    }

    public Page<Post> getAllPostPage(int page, int size){
        return   postRepository.findAll(PageRequest.of(page,size, Sort.by("title")));
    }

    public  Post getPost(Long id){
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

   public String uploadPhoto(Long id , MultipartFile file){
        log.info("Saving picture for user ID:{}", id);
        Post post = getPost(id);
        String photoUrl = photoFunction.apply(id.toString(),file);
        post.setPhotos(photoUrl);
       postRepository.save(post);
       return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/post/image/" + filename).toUriString();
        }catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };
}
