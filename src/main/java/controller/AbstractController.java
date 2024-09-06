package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbstractController {

    @RequestMapping(value = "/default")
    public String handleError(@RequestParam(value = "error",required = true) String error){
        return error;
    }
}