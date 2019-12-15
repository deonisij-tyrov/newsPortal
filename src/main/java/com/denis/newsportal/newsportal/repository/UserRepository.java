package com.denis.newsportal.newsportal.repository;

import com.denis.newsportal.newsportal.entity.User;
import com.denis.newsportal.newsportal.enumeration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String name);
}