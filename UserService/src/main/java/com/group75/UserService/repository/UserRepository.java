package com.group75.UserService.repository;

import com.group75.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role); // Find users by role (student, staff, faculty)
    List<User> findByUserType(String userType); // Find users by userType (e.g., "student", "staff")
}
