package com.formento.ecommerce.user.model.repository;

import com.formento.ecommerce.user.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(@Param("email") String email);

}
