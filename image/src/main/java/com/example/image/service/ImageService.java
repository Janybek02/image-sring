package com.example.image.service;

import com.example.image.entity.ImageData;
import com.example.image.entity.ImageUploadResponse;
import com.example.image.repository.ImageRepo;
import com.example.image.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageDataRepository;

    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {

        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtils.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtils.decompressImage(dbImage.get().getImageData());
        return image;
    }
}