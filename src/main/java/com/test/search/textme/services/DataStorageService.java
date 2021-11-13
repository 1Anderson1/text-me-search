package com.test.search.textme.services;

import com.test.search.textme.models.ChatResponse;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataStorageService {

    void initDb(@NonNull MultipartFile file);

    List<ChatResponse> searchChats(@NonNull String text);
}
