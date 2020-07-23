package lightning.webds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lightning.webds.handler.DriverSocketHandler;
import lightning.webds.handler.WaitingSocketHandler;
 
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
 
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new WaitingSocketHandler(), "/wait");
        webSocketHandlerRegistry.addHandler(new DriverSocketHandler(), "/ds");
    }
}