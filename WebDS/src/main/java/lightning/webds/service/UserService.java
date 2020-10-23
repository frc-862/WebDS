package lightning.webds.service;

import java.util.HashMap;
import lightning.webds.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private HashMap<String, User> userMap = new HashMap<String, User>(){
        {
        put("ziming412@gmail.com", new User("Ziming Huang", "ziming412@gmail.com", "USER"));
        put("zhuang412@pccsk12.com", new User("Ziming zzstu Huang", "zhuang412@pccsk12.com", "ADMIN"));
        }
    };

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
}