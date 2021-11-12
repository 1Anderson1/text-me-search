package com.test.search.textme.services;

import com.test.search.textme.converters.FileToPojoConverter;
import com.test.search.textme.models.LiveChat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileToPojoConverter fileToPojoConverter;

    @Override
    public void initDb(MultipartFile file) {
        List<LiveChat> liveChats = fileToPojoConverter.convert(file);
    }

}
