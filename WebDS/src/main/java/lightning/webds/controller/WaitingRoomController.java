package lightning.webds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lightning.webds.entity.User;
import lightning.webds.service.WaitingRoomService;

@Controller
public class WaitingRoomController {

    @Autowired
    private WaitingRoomService wrs;
    
    @RequestMapping("/wait/{name}/{email}")
    public String initializeWaitingRoom(@PathVariable("name") String name, @PathVariable("email") String email) {
        User curr = new User(name, email);
        wrs.addUser(curr);
        return "wait";
    }

    // @RequestMapping("/line")
    // public String line() {
    //     return wrs.getUsersInLine().toString();//not a template . . . how to get spring to recognize??
    // }

}