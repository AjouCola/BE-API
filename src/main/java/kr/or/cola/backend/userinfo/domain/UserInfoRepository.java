package kr.or.cola.backend.userinfo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
