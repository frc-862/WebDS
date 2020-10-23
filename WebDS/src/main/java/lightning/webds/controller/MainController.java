package lightning.webds.controller;

import lightning.webds.UserInfo;
import lightning.webds.config.HttpSecurityConfig;
import lightning.webds.entity.User;
import lightning.webds.service.UserService;

import java.util.Map;
import java.lang.Object;
import java.lang.invoke.ConstantBootstraps;
import java.util.HashMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "/admin", method = RequestMethod.POST, consumes = {"application/json"})
    public String getUser(@RequestBody User user){
        var userDetails = UserInfo.userService.findUserByEmail(user.getEmail());
        var role = userDetails.getRole();

        if(role ==  HttpSecurityConfig.admin_role){return "admin";}
        else if(qOpen){return "index";}
        return "closed";
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