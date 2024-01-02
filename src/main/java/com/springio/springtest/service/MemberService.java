package com.springio.springtest.service;

import com.springio.springtest.domain.Member;
import com.springio.springtest.repository.MemberRepository;
import com.springio.springtest.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //테스트 코드 작성 후 설명
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository ;
        //MemberService 입장에서 보면 직접 new 하지 않고 외부에서 넣어준다
        //이런걸 DI (의존성 주입)이라고 한다
    }


    /* 회원 가입*/

    public Long join(Member member){
        //같은 이름이 있는 중복회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //메소드 추출 Crtl + Alt + M
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);

        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
