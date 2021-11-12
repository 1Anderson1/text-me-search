package com.test.search.textme.services;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void initDb(@NonNull MultipartFile file);
}
