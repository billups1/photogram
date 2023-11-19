package com.gg.photogram.web.dto.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private String caption;
    private MultipartFile file;

}
