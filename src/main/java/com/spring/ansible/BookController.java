package com.spring.ansible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:application.properties")
@RestController
@RequestMapping("/api/hello")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Value("${api.env}")
    private String env;

    @GetMapping
    public ResponseEntity<String> fetchHello() {
        return new ResponseEntity<>(String.format("Hello World em %s", env), HttpStatus.OK);
    }
}
