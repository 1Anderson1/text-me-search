package com.test.search.textme.services;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface DataStorageService {

    void initDb(@NonNull MultipartFile file);

    void searchChats(@NonNull String text);
}
