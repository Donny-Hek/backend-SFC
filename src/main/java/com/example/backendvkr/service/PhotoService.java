package com.example.backendvkr.service;

import com.example.backendvkr.model.Examination;
import com.example.backendvkr.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PhotoService {

    private static Set<Photo> getPhotos(MultipartFile[] photos, Examination examination) throws IOException {
        Set<Photo> photosToRecognize = new LinkedHashSet<>();
        for (MultipartFile file : photos) {
            if (!file.isEmpty()) {
                Photo ph = new Photo(
                        file.getOriginalFilename(), (int) file.getSize(),
                        file.getBytes(), examination);
                photosToRecognize.add(ph);
            }
        }
        return photosToRecognize;
    }
}
