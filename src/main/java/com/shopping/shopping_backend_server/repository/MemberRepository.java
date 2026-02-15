package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받으면 기본적인 저장, 조회 기능이 자동으로 만들어집니다.
public interface MemberRepository extends JpaRepository<Member, Long> {
}