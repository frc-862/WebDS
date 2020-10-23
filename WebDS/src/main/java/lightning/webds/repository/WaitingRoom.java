package lightning.webds.repository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.springframework.stereotype.Repository;

import lightning.webds.entity.User;

@Repository
public class WaitingRoom {

    private ArrayDeque<User> line;

    public WaitingRoom() {
        line = new ArrayDeque<>();
    }

    public void add(User usr) {
        line.add(usr);
    }

    public User getNext() {
        if(!line.isEmpty()) return line.poll();
        return new User("No Users In Waiting Room", ":(","X");
    }

    public int getSize() {
        return line.size();
    }

    public List<String> getUsersInLine() {
        List<String> list = new ArrayList<>();
        Deque<User> cp = line.clone();
        while(!cp.isEmpty()) list.add(cp.poll().getName());
        return list;
    }
    
}