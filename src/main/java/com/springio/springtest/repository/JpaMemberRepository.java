package com.springio.springtest.repository;

import com.springio.springtest.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //JPA 라이브러리를 받으면
    //application.properties 에서 설정한 DB 정보, JPA 설정정보 활용해서
    //스프링 부트가 엔티티매니져를 생성해준다.
    //데이터베이스까지 연결을 해준다.

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //JPQL 객체지향 쿼리언어를 사용해서
        List<Member> result =
            em.createQuery("select m from Member as m where m.name =:name", Member.class)
                    .setParameter("name", name)
                    .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m ", Member.class)
                .getResultList();
    }
}
