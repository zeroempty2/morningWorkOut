//package com.sparta.morningworkout.service;
//
//
//import com.sparta.morningworkout.entity.Product;
//import com.sparta.morningworkout.entity.User;
//import com.sparta.morningworkout.repository.CustomerRequestListRepository;
//import com.sparta.morningworkout.repository.ProductRepository;
//import com.sparta.morningworkout.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CustomerServiceImplTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    CustomerRequestListRepository customerRequestListRepository;
//
//    @Mock
//    ProductRepository productRepository;
//
//    @InjectMocks
//    CustomerServiceImpl customerService;
//
//    @Test
//    void showSellerList() {
//        //given
//        User user = mock(User.class);
//        List<User> sellerList = new ArrayList<>();
//
//        //when
//
//
//        //then
//    }
//
//    @Test
//    void showSellerProfile() {
//    }
//
//    @Test
//    void requestBuyProducts() {
//        Product product = mock(Product.class);
//
//        when(product.getId()).thenReturn(1L);
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//
//    }
//}
