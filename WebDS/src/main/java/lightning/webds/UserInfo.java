package lightning.webds;

import lightning.webds.entity.User;
import lightning.webds.service.UserService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class UserInfo implements UserDetailsService{

    public static UserService userService = new UserService();

    // called by Spring to get user info
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
            .username(username)
            .password("password")
            .roles(user.getRole())
            .build();
        }
        throw new UsernameNotFoundException("User not found.");
    }

    // find user from UserService class
    private User findUser(String email){
        return userService.findUserByEmail(email);
    }
}