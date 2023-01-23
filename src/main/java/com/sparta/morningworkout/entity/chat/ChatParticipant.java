// package com.sparta.morningworkout.entity.chat;
//
//
// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Getter
// @Builder
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// public class ChatParticipant {
//
//     @Id
//     private Long id;
//
//     @Column
//     private String nickName;
//
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "room_id")
//     private ChatRoom chatRoom;
// }
