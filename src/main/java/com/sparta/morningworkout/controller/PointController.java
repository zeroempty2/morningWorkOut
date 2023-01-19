package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.Point.AdminGivePresentRequest;
import com.sparta.morningworkout.dto.Point.AdminGivePresentResponse;
import com.sparta.morningworkout.dto.Point.PointResponse;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.PointServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/point")
public class PointController {
    private final PointServiceImpl pointService;

    @GetMapping("/inquir")
    public ResponseEntity<PointResponse> getPoint(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        PointResponse response = pointService.getPoint(username);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/buygoods/{productId}")
    public ResponseEntity<PointResponse> BuyGoods(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        PointResponse pointBuyGoods = pointService.pointBuyGoods(username, productId);
        return ResponseEntity.ok().body(pointBuyGoods);
    }

    @PostMapping("/admin")
    public ResponseEntity<AdminGivePresentResponse> adminGivePresent(@RequestBody AdminGivePresentRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        AdminGivePresentResponse adminGivePresentResponse = pointService.adminGivePresent(request, user);
        return ResponseEntity.ok().body(adminGivePresentResponse);
    }
}
