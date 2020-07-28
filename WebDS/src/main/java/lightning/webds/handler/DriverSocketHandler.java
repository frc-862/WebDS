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

public class DriverSocketHandler extends TextWebSocketHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WaitingSocketHandler.class);
    
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private WebSocketSession driver = null;

    private WebSocketSession admin = null;

    private WebSocketSession robot = null;

    private static boolean driverEnabled = true;

    private static boolean notifyAdminJoystickInput = false;
 
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
        if(msg.contains("CMD")) {
            if(driverEnabled) {
                if(robot != null && robot.isOpen()) {
                    robot.sendMessage(message);
                }
                if(notifyAdminJoystickInput && admin != null && admin.isOpen()) {
                    admin.sendMessage(message);
                }
            }
        } else if(msg.equals("ENABLEDRIVER")) {
            driverEnabled = true;
            if(admin != null && admin.isOpen()) {
                admin.sendMessage(new TextMessage("Driver Joysticks Enabled"));
            }
        } else if(msg.equals("DISABLEDRIVER")) {
            driverEnabled = false;
            if(admin != null && admin.isOpen()) {
                admin.sendMessage(new TextMessage("Driver Joysticks Disabled"));
            }
        } else if(msg.equals("LISTEN")) {
            notifyAdminJoystickInput = true;
            if(admin != null && admin.isOpen()) {
                admin.sendMessage(new TextMessage("Displaying Driver Joystick Commands"));
            }
        } else if(msg.equals("IGNORE")) { 
            notifyAdminJoystickInput = false;
            if(admin != null && admin.isOpen()) {
                admin.sendMessage(new TextMessage("Hiding Driver Joystick Commands"));
            }
        } else if(msg.equals("IAMADMIN")) {
            admin = session;
        } else if(msg.equals("ADMINDIED")) {
            //admin.close();
            admin = null;
        } else if(msg.equals("IAMDRIVER")) {
            if(driver == null) {
                driver = session;
            } else {
                if(driver.isOpen()) {
                    driver.sendMessage(new TextMessage("LEAVE"));
                }
                driver.close();
                driver = session;
            }
            admin.sendMessage(new TextMessage("-"));
            admin.sendMessage(new TextMessage("New Driver Connected"));
        } else if(msg.equals("IAMROBOT")) {
            robot = session;
            if(admin != null && admin.isOpen()) admin.sendMessage(new TextMessage("New Robot Connected"));
        } else if(msg.equals("ROBOTDIED")) {
            //robot.close();
            robot = null;
        } else if(msg.equals("DRIVERLEAVE")) {
            if(driver != null && driver.isOpen()) {
                driver.sendMessage(new TextMessage("LEAVE"));
            }
        } else {                                                          //                                          } 
            sessions.forEach(webSocketSession -> {
                try {
                    webSocketSession.sendMessage(message);
                } catch (IOException e) {
                    LOGGER.error("Error occurred.", e);
                }
            });
        } // COMMENT OUT FOR DEBUG
    }

}