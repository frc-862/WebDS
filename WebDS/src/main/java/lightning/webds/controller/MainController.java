package lightning.webds.controller;

import lightning.webds.UserInfo;
import lightning.webds.config.HttpSecurityConfig;
import lightning.webds.entity.User;
import lightning.webds.service.UserService;

import java.util.Map;
import java.lang.Object;
import java.lang.invoke.ConstantBootstraps;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String getMainPage(){
        return "twitch";
    }
    @RequestMapping("/user/{email}")
    public String getUserPage(@PathVariable String email) {
        if(UserInfo.userService.userExist(email)){
            if(qOpen) return "index.html";
            return "closed.html";
        }
        return "thanks";
    }

    @RequestMapping(value = "/admin/{email}")
    public String getAdminPage(@PathVariable String email){
        if(UserInfo.userService.userExist(email)){
            return "admin";
        }
        return "thanks";
    }
    @RequestMapping("/twitch")
    public String getTwitchPage(){
        return "twitch";
    }

    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    private User userDetails;
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String getRedirectPage(){
    System.out.print("page redirected");
    var role = userDetails.getRole();
    var email = userDetails.getEmail();
    if(role ==  HttpSecurityConfig.admin_role){    System.out.println("admin redirected"); return "redirect:/admin/" + email;}
    else if(qOpen){   System.out.println("open redirected"); return "redirect:/user/" + email;}
    System.out.println("closed redirected"); 
    return "redirect:/user/" + email;
    }
    @RequestMapping(value = "/redirect", method = RequestMethod.POST, consumes = {"application/json"})
    public String getRedirectJSON(@RequestBody User user){
        userDetails = UserInfo.userService.findUserByEmail(user.getEmail());
       return "thanks";
    }

    @RequestMapping("/run")
    public String getRunPage() {
        return "run";
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