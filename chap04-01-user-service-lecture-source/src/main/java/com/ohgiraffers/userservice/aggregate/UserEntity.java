package com.ohgiraffers.userservice.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_member")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

//    @Column(name = "pwd", nullable = false)
//    private String pwd;

    @Column(name="encrypt_pwd", nullable = false)
    private String encryptPwd;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "userId", nullable = false, unique = true)
    private String userId;      //회원 가입 시 생성 될 고유 아이디(닉네임 개념)

}
