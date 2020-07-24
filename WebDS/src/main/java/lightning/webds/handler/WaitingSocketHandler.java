package lightning.webds.handler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
        if(msg.equals("IAMADMIN")) {
            admin = session;
            sessions.remove(session);
            //admin.sendMessage(new TextMessage("Admin Configured"));
        } else if(msg.equals("OPENQ")) {
            MainController.openQueue();
            if(admin.isOpen()) admin.sendMessage(new TextMessage("Queue Is Open"));
        } else if(msg.equals("CLOSEQ")) {
            MainController.closeQueue();
            if(admin.isOpen()) admin.sendMessage(new TextMessage("Queue Is Closed"));
        } else if(msg.equals("ADMINDIED")) {
            admin = null;
        } else if(msg.equals("USRLEAV")) {
            if(admin != null) {
                admin.sendMessage(new TextMessage("User Exited Queue For DS"));
            } else {
                throw new Exception("ADMIN NULL");
            }
        } else if(msg.equals("NEXT")) {
            //admin.sendMessage(new TextMessage(""));
            if(!sessions.isEmpty()) {
                WebSocketSession next = sessions.remove(0);
                next.sendMessage(new TextMessage("URNEXT"));
                next.close();
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