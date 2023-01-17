package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스를 상속
//제네릭타입으로 첫번쨰로 CRUD할 엔터티클래스의 타입, 두번째로 해당 엔터티의 ID의 타입
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    //쿼리메서드
    List<MemberEntity> findByGender(Gender gender);

    List<MemberEntity> findByAccountAndGender(String account,Gender gender);


    List<MemberEntity> findByNickNameContaining(String nickName);


    //JPQL 사용
    //select 별칭 from 엔터티클래스명 as 별칭 where 별칭.필드명
    //native-sql: select m.user_code from tbl_member as m
    //jpql: select m.userId from MemberEntity as m
    //계정명으로 회원조회
    //@Query("select m from MemberEntity as m where m.account=?1") //?1은 매개변수의 첫번째 파라미터
    @Query("select m from MemberEntity as m where m.account=:acc")
    MemberEntity getMemberByAccount(String acc);

    //닉네임과 성별 동시만족 조건으로 회원조회
    //@Query("select m from MemberEntity m where m.nickName=?1 and m.gender=?2") //숫자 1,2는 위치 기반 의미
    @Query("select m from MemberEntity m where m.nickName=:nick and m.gender=:gen") //숫자 1,2는 위치 기반 의미
    List<MemberEntity> getMembersByNickAndGender(String nick,Gender gen);


    @Query("select m from MemberEntity m where m.nickName like %:nick%")
    List<MemberEntity> getMembersByNickName(String nick);


}
