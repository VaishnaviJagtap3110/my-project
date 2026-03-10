package com.wheelshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheelshare.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
