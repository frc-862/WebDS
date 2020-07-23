package lightning.webds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }

    @RequestMapping("/run")
    public String getRunPage() {
        return "run";
    }

    @RequestMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @RequestMapping("/thx")
    public String getEndPage() {
        return "thanks";
    }
    
}