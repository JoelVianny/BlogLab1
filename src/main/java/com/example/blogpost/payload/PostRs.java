package com.example.blogpost.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;


@Data
@NoArgsConstructor
public class PostRs {
    private Long id;


    private String title;

    private String author;

    private String  photos;


    public PostRs(Long id, String title, String author, byte[] photosBytes) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.photos = photosBytes != null ? Base64.encodeBase64String(photosBytes) : null;
    }

}
