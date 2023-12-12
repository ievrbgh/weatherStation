package ru.ievrb.weatherStation.utill;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorUtill {
    public static String getErrorMessageString(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()){
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        return errorMessage.toString();
    }

}
