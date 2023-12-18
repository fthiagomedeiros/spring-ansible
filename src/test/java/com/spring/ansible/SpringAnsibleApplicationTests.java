package com.spring.ansible;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;

@WebMvcTest(value = BookController.class)
class SpringAnsibleApplicationTests {


  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private ApplicationContext context;

  @Test
  void contextLoads() {
    assertNotNull(context);
    assertNotNull(mapper);
  }

}
