# Kaptcha

kaptcha - A kaptcha generation engine clone of http://code.google.com/p/kaptcha/. Make it maven based.

Now this repository is a clone of https://gitlab.com/axet/kaptcha. Aim at supporting Spring Framework 6 and Spring Boot 3.

## Goals

  - Make it Maven based
  - Allow use it from Spring
  - Support Jakarta Servlet 6.0
  - Support Spring Framework 6
  - Support Spring Boot 3
  - Support Java 17

## Central Maven Repo
```xml
  <dependencies>
    <dependency>
      <groupId>io.github.timbotetsu</groupId>
      <artifactId>kaptcha-java-17</artifactId>
      <version>0.0.10</version>
    </dependency>
  </dependencies>
```

## Example

```java
package com.example.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.servlet.KaptchaExtend;

@Controller
public class RegisterKaptchaController extends KaptchaExtend {

    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET)
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        super.captcha(req, resp);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerGet(@RequestParam(value = "error", required = false) boolean failed,
            HttpServletRequest request) {
        ModelAndView model = new ModelAndView("register-get");
        
        //
        // model MUST contain HTML with <img src="/captcha.jpg" /> tag
        //
        
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("register-post");

        if (email.isEmpty())
            throw new RuntimeException("email empty");

        if (password.isEmpty())
            throw new RuntimeException("empty password");

        String captcha = request.getParameter("captcha");

        if (!captcha.equals(getGeneratedKey(request)))
            throw new RuntimeException("bad captcha");

        //
        // eveyting is ok. proceed with your user registration / login process.
        //

        return model;
    }

}
```
