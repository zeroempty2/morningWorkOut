package com.sparta.morningworkout.zschat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

/***
 * 웹 소켓은 http가 아닌 ws로 시작하는 자체적인 주소체계를 가지고 있음
 * https == wss
 *
 */
@Configuration
@EnableWebSocketMessageBroker // 메시지 처리 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // Stomp 사용

//    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);
    private final StompHandler stompHandler; // jwt 인증


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { // broker 설정 부분

        config.enableSimpleBroker("/topic"); //

        config.setApplicationDestinationPrefixes("/app"); // 컨트롤러 도착지
    }

    // 메세지 도착 지점 url로 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //
        registry.addEndpoint("/chatting") // 웹소켓 연결을 위한 주소 배정
                .setAllowedOriginPatterns("*") // url 요청이 다른 경우 cors로 인한 연결 x 상태 방지?
                .withSockJS() // socketJs로 연결한다는 설정
                .setHeartbeatTime(60_000); // HTTP header 통해 연결 상태 주기적으로 확인하는데, 그 주기를 설정
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler);
//    }
}
