package com.payconiq.mvp;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MvpApplication {

  public static void main(String[] args) {
    SpringApplication.run(MvpApplication.class, args);
  }

  @GetMapping("/")
  public void home(final HttpServletResponse response) throws IOException {
    response.sendRedirect("/h2-console");
  }
}
