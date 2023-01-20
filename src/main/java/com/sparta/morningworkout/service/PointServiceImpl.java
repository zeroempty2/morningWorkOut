//package com.sparta.morningworkout.service;
//
//import com.sparta.morningworkout.dto.Point.AdminGivePresentRequest;
//import com.sparta.morningworkout.dto.Point.AdminGivePresentResponse;
//import com.sparta.morningworkout.dto.Point.PointResponse;
//import com.sparta.morningworkout.entity.*;
//import com.sparta.morningworkout.repository.PointRepository;
//import com.sparta.morningworkout.repository.ProductRepository;
//import com.sparta.morningworkout.repository.SellerRegistRepository;
//import com.sparta.morningworkout.repository.UserRepository;
//import com.sparta.morningworkout.service.serviceInterface.PointService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class PointServiceImpl implements PointService {
//    private final PointRepository pointRepository;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public PointResponse getPoint(Long id) {
//        Point pointInfo = pointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저정보가 유효하지 않습니다."));
//        int point = pointInfo.getPoint();
//
//        if (point <= 0) {
//            throw new IllegalArgumentException("포인트가 없습니다");
//        }
//
//        return new PointResponse(pointInfo);
//    }
//
//    @Override
//    public PointResponse pointBuyGoods(Long id, Long productId) {
//        Point pointInfo = pointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저 DB에 정보가 없습니다."));
//        int point = pointInfo.getPoint();
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다"));
//        int productPrice = product.getPrice();
//        Long sellerId = product.getUserId();
//
//
//        if (productPrice > point) { throw new IllegalArgumentException("포인트가 부족하여 구매할 수 없습니다"); }
//
//        point = point - productPrice;
//        Point update = Point.builder()
//                            .userId(id)
//                            .point(point)
//                            .build();
//        pointRepository.save(update);
//
//        User sellerInfo = userRepository.findById(sellerId).orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다"));
//        String sellerName = sellerInfo.getUsername();
//
//        if (username.equals(sellername)) { throw new IllegalArgumentException("자신이 등록한 상품은 구매할 수 없습니다"); }
//
//        Point pointSellerinfo = pointRepository.findByUsername(sellername).orElseThrow(() -> new IllegalArgumentException("Error2"));
//        int sellerPoint = pointSellerinfo.getPoint() + productPrice;
//        Point seller = Point.builder()
//                            .adminname(null)
//                            .username(pointSellerinfo.getUsername())
//                            .point(sellerPoint)
//                            .build();
//        pointRepository.save(seller);
//
////        productRepository.delete(product);
//
//        return new PointResponse(update);
//    }
//
//    @Override
//    public AdminGivePresentResponse adminGivePresent(AdminGivePresentRequest request, User admin) {
//        if (!admin.getRole().equals(UserRoleEnum.ADMIN)) { throw new IllegalArgumentException("권한이 없습니다"); }
//
//        User userinfo = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원"));
//        Point pointinfo = pointRepository.findByUsername(userinfo.getUsername()).orElseThrow(() -> new IllegalArgumentException("Error"));
//        int point = pointinfo.getPoint() + request.getGivePresent();
//        Point update = Point.builder()
//                            .adminname(admin.getUsername())
//                            .username(userinfo.getUsername())
//                            .point(point)
//                            .build();
//        pointRepository.save(update);
//        return new AdminGivePresentResponse(update) ;
//    }
//}
