package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Image;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ErrorException("İmage not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Image save(Image ımage) {
        return imageRepository.save(ımage);
    }

    @Override
    public Image delete(Long id) {
        Image image = findById(id);
        imageRepository.delete(image);
        return image;
    }
}
