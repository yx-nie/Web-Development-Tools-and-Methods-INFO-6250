package com.example.mosaicticket.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {
    public Map<String, String> ExceptionHandler(UserNotFoundException exception){
        HashMap<String, String> map= new HashMap<>();
        map.put("errormsg", exception.getMessage());
        return map;
    }
}
