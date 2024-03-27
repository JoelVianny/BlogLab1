package com.example.blogpost.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Entity
@Data
@Table(name = "newsInfo", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class  NewsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description ;
    @Column(name = "photo" )
    private String photos;

}
