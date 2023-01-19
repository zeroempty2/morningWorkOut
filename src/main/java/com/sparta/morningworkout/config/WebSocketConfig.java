package com.sparta.morningworkout.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지 처리 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // Stomp 사용

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);
    private final StompHandler stompHandler; // jwt 인증

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { // broker 설정 부분
        config.enableSimpleBroker("/topic/chat"); // 메시지 받을 때, 내장 브로커 사용, prefix 붙은 메시지를 발행 시 브로커가 처리
        config.setApplicationDestinationPrefixes("/app"); // 메시지를 보내는 과정, 메시지 핸들러로 라우팅되는 prefix, 즉 해당 "/app"이라는 경로를 처리하는 핸들러, controller로 전달
    }

    // 메세지 도착 지점 url로 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //
        registry.addEndpoint("/ws") // 웹소켓 연결을 위한 주소 배정
                .setAllowedOriginPatterns("*") // url 요청이 다른 경우 cors로 인한 연결 x 상태 방지?
                .withSockJS() // socketJs로 연결한다는 설정
                .setHeartbeatTime(60_000); // HTTP header 통해 연결 상태 주기적으로 확인하는데, 그 주기를 설정
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}
