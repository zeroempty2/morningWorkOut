// package com.sparta.morningworkout.entity.chat;
//
//
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.sparta.morningworkout.entity.Product;
// import com.sparta.morningworkout.entity.Profile;
// import com.sparta.morningworkout.entity.User;
// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// import java.util.List;
//
//
// @Entity
// @Getter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Table(name = "chat_room")
// public class ChatRoom {
//
//
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long roomId;
//
// //    @ManyToOne
// //    @JoinColumn(name = "SELLER_ID", nullable = false)
// //    private User seller;
// //
// //    @ManyToOne
// //    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
// //    private User customer;
//
// //    @JsonProperty("collection")
//     @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.REMOVE)
//     private List<ChatParticipant> chatParticipants;
//
//     @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.REMOVE)
//     private List<Message> messages;
//
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn
//     private Product product;
//
//
// //    public void handActions
//
//
// }
