package com.example.blogpost.repositories;

import com.example.blogpost.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
