package com.ohgiraffers.userservice.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@ToString
public class UserImpl extends User {
    private int no;
    private String tempPwdYn;
    private java.sql.Date pwdChangedDatetime;
    private String pwdExpDate;
    private String name;
    private java.sql.Date registDatetime;
    private int accumLoginCount;
    private int loginFailedCount;
    private String accLockYn;
    private String accInactiveYn;
    private String accExpDate;
    private String accExpYn;
    private java.sql.Date accSecessionDatetime;
    private String accSecessionYn;

    /* 설명. username, password, authorities 필드 대신 생성자로 대체 */
    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setDetails(MemberDTO member) {
        this.no = member.getNo();
        // username, password, authorities는 생성자에서 이미 설정됨
        this.tempPwdYn = member.getTempPwdYn();
        this.pwdChangedDatetime = member.getPwdChangedDatetime();
        this.pwdExpDate = member.getPwdExpDate();
        this.name = member.getName();
        this.registDatetime = member.getRegistDatetime();
        this.accumLoginCount = member.getAccumLoginCount();
        this.loginFailedCount = member.getLoginFailedCount();
        this.accLockYn = member.getAccLockYn();
        this.accInactiveYn = member.getAccInactiveYn();
        this.accExpDate = member.getAccExpDate();
        this.accExpYn = member.getAccExpYn();
        this.accSecessionDatetime = member.getAccSecessionDatetime();
        this.accSecessionYn = member.getAccSecessionYn();
    }
}
