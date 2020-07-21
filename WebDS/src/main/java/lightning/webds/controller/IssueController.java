package lightning.webds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IssueController {
    
    @RequestMapping("/error")
    public String getError() {
        return "error";
    }

}