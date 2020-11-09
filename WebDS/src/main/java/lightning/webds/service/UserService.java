package lightning.webds.service;

import java.util.HashMap;

import lightning.webds.controller.MainController;
import lightning.webds.entity.User;

import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private SimpMessagingTemplate simpMessagingTemplate = new SimpMessagingTemplate(new ExecutorSubscribableChannel());
    //TODO: temporary database. need database on server
    private HashMap<String, User> userMap = new HashMap<String, User>(){
        {
        put("ziming412@gmail.com", new User("Ziming Huang", "ziming412@gmail.com", "USER"));
        put("zhuang412@pccsk12.com", new User("Ziming zzstu Huang", "zhuang412@pccsk12.com", "ADMIN"));
        put("ftgeib640@pcck12.com", new User("Frederick zzstu Geib", "ftgeib640@pcck12.com", "ADMIN"));
        }
    };

    // using temporary database. need database on server
    public User findUserByEmail(String email){
        var user = userMap.get(email);
        if(user == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return user;
    }

    public void addUser(User user){
        userMap.put(user.getEmail(), user);
    }

    public Boolean userExist(String email){
        return userMap.containsKey(email);
    }

    public void connectUserToAdmin(String email, String role){
        simpMessagingTemplate.convertAndSend("/admin/" + MainController.getAdminEmail(),
        "message");
        System.out.println(email);
    }

    public HashMap getUserMap(){
        return userMap;
    }
}