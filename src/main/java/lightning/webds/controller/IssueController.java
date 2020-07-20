package lightning.webds.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IssueController implements ErrorController{
    
    @RequestMapping("/error")
    public String getError() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}