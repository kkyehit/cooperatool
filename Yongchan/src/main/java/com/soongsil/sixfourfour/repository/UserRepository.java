package com.soongsil.sixfourfour.repository;

import com.soongsil.sixfourfour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
