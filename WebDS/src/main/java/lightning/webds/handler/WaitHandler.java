package lightning.webds.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lightning.webds.controller.WaitingRoomController;
import lightning.webds.entity.User;

public class WaitHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitHandler.class);

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

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
        if(msg.equals("NEXT")) {
            User next = WaitingRoomController.getWRSInstance().getNextUser();
            // TODO - maybe not this?
        }
    }
    
}