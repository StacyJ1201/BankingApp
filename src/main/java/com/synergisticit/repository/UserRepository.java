package com.synergisticit.repository;

import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT Max(u.userId) FROM User u")
    Long findMaxUserId();

    Optional<User> findUserByUsername(String username) throws UsernameNotFoundException;

}
