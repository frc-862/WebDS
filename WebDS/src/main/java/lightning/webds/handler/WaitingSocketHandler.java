package lightning.webds.handler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lightning.webds.controller.MainController;
 
public class WaitingSocketHandler extends TextWebSocketHandler {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(WaitingSocketHandler.class);
    
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final ConcurrentHashMap<String,WebSocketSession> sessionMaps = new ConcurrentHashMap<String,WebSocketSession>();

    private WebSocketSession admin = null;
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        super.afterConnectionEstablished(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String msg = message.getPayload();
        String[] msgSplit = msg.split(" ");
        if(msg.equals("IAMADMIN")) {
            admin = session;
            sessions.remove(session);
            //admin.sendMessage(new TextMessage("Admin Configured"));
        } else if(msgSplit[0].equals("ADD")) {
            System.out.println("queue open?" +MainController.isQueueOpen());
            if(admin != null && admin.isOpen() && MainController.isQueueOpen()) {
                // send message to admin to add user to queue
                admin.sendMessage(new TextMessage("+"));
                var email = msgSplit[1];
                sessionMaps.put(email, session);
            }
        } else if(msg.equals("OPENQ")) {
            MainController.openQueue();
            if(admin.isOpen()) admin.sendMessage(new TextMessage("Queue Is Open"));
        } else if(msg.equals("CLOSEQ")) {
            MainController.closeQueue();
            if(admin.isOpen()) admin.sendMessage(new TextMessage("Queue Is Closed"));
        } else if(msg.equals("ADMINDIED")) {
            admin = null;
        } else if(msgSplit[0].equals("USRLEAV")) {
            // send message to admin to allow user to enter and remove user from queue
            if(admin != null && admin.isOpen()) {
                var email = msgSplit[1];
                admin.sendMessage(new TextMessage("- " + email));
            }
        } else if(msgSplit[0].equals("NEXT")) {
            //admin.sendMessage(new TextMessage(""));
            if(!sessionMaps.isEmpty()) {
                // send message to user to confirm activity
                var email = msgSplit[1];
                if(sessionMaps.containsKey(email)){
                    WebSocketSession next = sessionMaps.get(email);
                    next.sendMessage(new TextMessage("URNEXT"));
                    sessionMaps.remove(email);
                }
            } else {
                if(admin != null) {
                    admin.sendMessage(new TextMessage("Queue Is Empty"));
                } else {
                    throw new Exception("ADMIN NULL");
                }
            }
        } else {
            sessions.forEach(webSocketSession -> {
                try {
                    webSocketSession.sendMessage(message);
                } catch (IOException e) {
                    LOGGER.error("Error occurred.", e);
                }
            });            
        }
    }
    
}