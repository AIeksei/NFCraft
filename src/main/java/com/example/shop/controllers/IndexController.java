package com.example.shop.controllers;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class IndexController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        System.out.println(response.getStatus());
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            System.out.println(2);
            //modelAndView.setViewName("error-404");
            modelAndView.setViewName("error");
        } else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            System.out.println(3);
            //modelAndView.setViewName("error-403");
            modelAndView.setViewName("error");
        } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            System.out.println(4);
            //modelAndView.setViewName("error-500");
            modelAndView.setViewName("error");
        } else {
            System.out.println(5);
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
    @ResponseBody
    public String getErrorPath() {
        System.out.println(6);
        return "/error";
    }
}
