package com.ohgiraffers.userservice.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int no;                           // 회원번호
    private String username;                        // 회원아이디
    private String password;                        // 회원비밀번호
    private String tempPwdYn;                  // 임시비밀번호여부
    private java.sql.Date pwdChangedDatetime;      // 회원비밀번호변경일시
    private String pwdExpDate;                  // 회원비밀번호만료일자
    private String name;                     // 회원이름
    private java.sql.Date registDatetime;         // 회원가입일시
    private int accumLoginCount;               // 누적로그인횟수
    private int loginFailedCount;               // 로그인연속실패횟수
    private String accLockYn;                  // 계정잠금여부
    private String accInactiveYn;               // 계정비활성여부
    private String accExpDate;                  // 계정만료일자
    private String accExpYn;                  // 계정만료여부
    private java.sql.Date accSecessionDatetime;      // 계정탈퇴일시
    private String accSecessionYn;               // 계정탈퇴여부

    private List<String> authorities;      // 회원별권한리스트
}
