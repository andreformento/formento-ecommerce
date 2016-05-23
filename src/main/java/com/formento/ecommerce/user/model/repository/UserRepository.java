package com.formento.ecommerce.user.model.repository;

import com.formento.ecommerce.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    @Query(" select user " +
            " from User user " +
            " where user.email = ?1")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(user) from User user WHERE user.email = ?1")
    Integer countByEmail(@Param("email") String email);

    @Query(" select user " +
            " from User user " +
            " where user.email = ?1" +
            "   and user.password = ?2")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
