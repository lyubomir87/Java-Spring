package com.softuni.springdataautomapping.repositories;

import com.softuni.springdataautomapping.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
User findByEmail(String email);
    User findByEmailAndPassword(String email,String pass);
}
