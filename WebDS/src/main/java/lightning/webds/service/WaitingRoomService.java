package lightning.webds.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lightning.webds.entity.User;
import lightning.webds.repository.WaitingRoom;

@Service
public class WaitingRoomService {
    
    @Autowired
    private static WaitingRoom wr = new WaitingRoom();

    public void addUser(User usr) {
        wr.add(usr);
    }

    public User getNextUser() {
        return wr.getNext();
    }

    public int size() {
        return wr.getSize();
    }

    public List<String> getUsersInLine() {
        var list = wr.getUsersInLine();
        Collections.reverse(list);
        return list;
    }

}