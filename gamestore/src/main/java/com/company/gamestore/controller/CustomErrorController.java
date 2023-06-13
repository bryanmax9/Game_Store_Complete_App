package com.company.gamestore.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public String handleError(HttpServletRequest request, Model model) {
        // Get the error status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Get the error message
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        // Set the error message in the model
        model.addAttribute("errorMsg", errorMessage);

        return "/ErrorHandling/error";
    }
}

