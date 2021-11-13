package com.test.search.textme.controllers;

import com.test.search.textme.services.DataStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class DataController {

    private final DataStorageService dataStorageService;

    @PostMapping("/initDb")
    public ResponseEntity<String> initDb(@RequestParam("file") MultipartFile file) {
        try {
            dataStorageService.initDb(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchChats")
    public ResponseEntity<String> searchChats(@RequestParam String text) {
        try {
            dataStorageService.searchChats(text);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
