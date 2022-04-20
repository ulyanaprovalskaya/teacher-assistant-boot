package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.service.api.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String getStudentImagePath(String cardUid) {
        String basePath = "/photo/students/" + cardUid + ".jpg";
        String path = "/static" + basePath;
        URL resource = ImageServiceImpl.class.getResource(path);

        try {
            if (Files.exists(Paths.get(resource.toURI()))) {
                log.info("Image was found for student with cardUid={}", cardUid);
                return basePath;
            }
        } catch (URISyntaxException e) {
            log.warn(e.getMessage());
        }

        log.warn("Image for student with cardUid={} wasn't found. Using default image instead", cardUid);
        return "/images/noavatar.png";
    }
}
