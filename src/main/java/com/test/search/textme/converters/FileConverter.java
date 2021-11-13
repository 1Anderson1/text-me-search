package com.test.search.textme.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.search.textme.models.ChatLogModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class FileConverter {

    private final ObjectMapper jacksonObjectMapper;

    public List<ChatLogModel> convert(MultipartFile file) {
        try {
            ChatLogModel[] chatLogModels = jacksonObjectMapper.readValue(file.getBytes(), ChatLogModel[].class);
            return Arrays.asList(chatLogModels);
        } catch (Exception e) {
            log.error("Data conversion error", e);
            throw new IllegalArgumentException("File is not valid");
        }
    }
}
