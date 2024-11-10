package com.group75.UserService.repository;

import com.group75.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role);
    List<User> findByUserType(String userType);
}
