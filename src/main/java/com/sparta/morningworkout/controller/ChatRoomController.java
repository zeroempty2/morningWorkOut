package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.ChatService;
import com.sparta.morningworkout.service.serviceInterface.ProductService;
import com.sparta.morningworkout.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ws/chat")
public class ChatRoomController {

    private final ChatService chatService;

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/room")
    public ModelAndView rooms(Model model) {
        return new ModelAndView("/chat/room");
    }


    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    @PostMapping("/room")
    public ChatRoom createRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long productId){
        User customer = userDetails.getUser();
        Product product = productService.findProduct(productId);
        User seller = userService.findUser(product.getUserId());
        return chatService.createRoom(customer, seller, product);
    }
}
