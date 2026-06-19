package com.denisar5.perfumehub.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            PerfumeNotFoundException.class,
            OrderNotFoundException.class,
            UserNotFoundException.class
    })
    public String handleNotFound(RuntimeException exception, Model model) {
        model.addAttribute("errorTitle", "Resource not found");
        model.addAttribute("errorMessage", exception.getMessage());

        return "error";
    }

    @ExceptionHandler({
            InsufficientStockException.class,
            UnauthorizedActionException.class,
            DuplicateUserException.class,
            IllegalStateException.class,
            IllegalArgumentException.class
    })
    public String handleBusinessError(RuntimeException exception, Model model) {
        model.addAttribute("errorTitle", "Action cannot be completed");
        model.addAttribute("errorMessage", exception.getMessage());

        return "error";
    }
}
