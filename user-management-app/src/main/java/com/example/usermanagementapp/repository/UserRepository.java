package com.example.usermanagementapp.repository;

import com.example.usermanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE " +
            "u.firstName LIKE CONCAT('%',:searchVariable, '%')" +
            "Or u.lastName LIKE CONCAT('%', :searchVariable, '%')")
    List<User> searchUsers(String searchVariable);



}
