package com.example.usermanagementapp.repository;

import com.example.usermanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
