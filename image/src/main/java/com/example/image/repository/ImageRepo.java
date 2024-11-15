package com.example.image.repository;

import com.example.image.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface ImageRepo extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String name);
}
