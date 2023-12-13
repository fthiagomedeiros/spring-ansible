package com.spring.ansible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    @GetMapping
    public ResponseEntity<String> fetchHello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
