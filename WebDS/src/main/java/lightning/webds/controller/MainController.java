package lightning.webds.controller;

import lightning.webds.UserInfo;
import lightning.webds.config.HttpSecurityConfig;
import lightning.webds.entity.User;
import lightning.webds.handler.DriverSocketHandler;
import lightning.webds.service.UserService;

import java.util.Map;
import java.lang.Object;
import java.lang.invoke.ConstantBootstraps;
import java.util.concurrent.ExecutionException;
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
    private static boolean adminExists = false;
    private static String adminID = null;

    private User userDetails;

    @RequestMapping("/")
    public String getMainPage(){
        return "twitch";
    }

    @RequestMapping("/user/{email}")
    public String getUserPage(@PathVariable String email)  throws Exception {
        // if user exists, direct to queue
        if(UserInfo.userService.userExist(email)){
            if(qOpen){
                DriverSocketHandler.sendMessageToAdmin(email, "USER", "WAITING");
                return "wait.html";
            }
            return "closed.html";
        }
        return "redirect:/error";
    }

    @RequestMapping(value = "/admin/{email}")
    public String getAdminPage(@PathVariable String email){
        if(UserInfo.userService.userExist(email)){
            // allow only 1 admin
            if(!adminExists || adminID.equals(email)){
                adminExists = true;
                adminID = email;
                return "admin";
            } 
            // if an admin exists, this admin becomes user
            return "redirect:/user/" + email;
        }
        return "redirect:/error";
    }

    @RequestMapping("/twitch")
    public String getTwitchPage(){
        return "twitch";
    }

    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String getRedirectPage(){
        var role = userDetails.getRole().stripTrailing().stripLeading();
        var email = userDetails.getEmail();

        // redirect user based on role
        if(role.equals(HttpSecurityConfig.admin_role)){ return "redirect:/admin/" + email;}
        else if(qOpen){
         return "redirect:/user/" + email;}
        
        return "redirect:/user/" + email;
    }

    @RequestMapping(value = "/redirect/info", method = RequestMethod.POST, consumes = {"application/json"})
    public String getRedirectJSON(@RequestBody User user){
        try{
        userDetails = UserInfo.userService.getUserDetails(user.getEmail());
        }
        catch(InterruptedException in){}
        catch(ExecutionException ex){}
       return "thanks";
    }

    @RequestMapping("/run/*")
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
    
    public static void setAdmin(){
        adminExists = true;
    }
    
    public static void removeAdmin(){
        adminExists = false;
    }

    public static Boolean hasAdmin(){
        return adminExists;
    }

    public static String getAdminEmail(){
        return adminID;
    }
}