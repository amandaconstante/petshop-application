package com.app.petshop.repository;

import com.app.petshop.domain.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);

    UserDetails findByLogin(String login);

//    @Query(nativeQuery = true, value = """
//            SELECT user_tb.id, user_tb.name, user_tb.login, user_tb.email, user_tb.birth_date, user_tb.permission
//            FROM user_tb
//            WHERE user_tb.name LIKE CONCAT('%', :name, '%')
//            """)
//    Optional<Object> findByName(@Param("name") String name);

    // JPQL, sem nativeQuery
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByName(@Param("name") String name);
}
