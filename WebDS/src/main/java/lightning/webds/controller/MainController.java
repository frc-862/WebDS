package lightning.webds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static boolean qOpen = false;

    @RequestMapping("/")
    public String getMainPage() {
        if(qOpen) return "index";
        return "closed";
    }

    @RequestMapping("/twitch")
    public String getTwitchPage(){
        return "twitch";
    }
    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
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

    public static void openQueue() {
        qOpen = true;
    }

    public static void closeQueue() {
        qOpen = false;
    }

    public static boolean isQueueOpen() {
        return qOpen;
    }
    
}