package com.springio.springtest.repository;

import com.springio.springtest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {
    //JpaRepository<Entity, PK 자료형>
    //인터페이스만 있는 상태에서 JPA 리포지토리를 받고 있으면
    //구현체를 JPA가 자동으로 만들어준다.
    //스프링 빈에 자동으로 등록한다.


    //JPQL select m from Member as m where m.name =?
    Optional<Member> findByName(String name);
    //Optional<Member> findByNameAndId(String name, Long id);



}
