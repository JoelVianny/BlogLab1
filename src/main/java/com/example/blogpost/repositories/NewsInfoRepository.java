package com.example.blogpost.repositories;

import com.example.blogpost.entities.NewsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsInfoRepository  extends JpaRepository<NewsInfo,Long> {
}
