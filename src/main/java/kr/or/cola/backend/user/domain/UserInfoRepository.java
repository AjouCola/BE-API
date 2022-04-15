package kr.or.cola.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
