package com.example.apibasic.jpabasic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter @NoArgsConstructor
@ToString @AllArgsConstructor
@EqualsAndHashCode(of = "userId")//userId만 보고 중복인지 확인할 수 있도록
@Builder

//JPA
@Entity //jpa의 entity 객체
@Table(name = "tbl_member")
public class MemberEntity {

    @Id //기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성 전략
    @Column(name="user_code")
    private Long userId;//회원 식별 코드(기본키)

    @Column(nullable = false, unique = true, length = 30)//Not Null 제약조건 , unique 중복 제거
    private String account;//계정명

    @Column(nullable = false)
    private String password;//패스워드

    @Column(name = "user_nick",nullable = false)
    private String nickName;//닉네임

    @Enumerated(EnumType.STRING)//Ordinal : 순번(0부터 시작), Spring: 순수문자열
    private Gender gender;//성별

    @CreationTimestamp // insert 시점에 서버시간을 자동으로 입력
    private LocalDateTime joinDate;//가입일자와시간

    @UpdateTimestamp // update 시점에 서버시간을 자동으로 입력
    private LocalDateTime modifyDate;//정보 수정 시간
}
