package com.test.search.textme.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.search.textme.models.LiveChat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class FileToPojoConverter {

    private final ObjectMapper jacksonObjectMapper;

    public List<LiveChat> convert(MultipartFile file) {
        try {
            LiveChat[] liveChats = jacksonObjectMapper.readValue(file.getBytes(), LiveChat[].class);
            return Arrays.asList(liveChats);
        } catch (Exception e) {
            log.error("Data conversion error", e);
            throw new IllegalArgumentException("File is not valid");
        }
    }
}
