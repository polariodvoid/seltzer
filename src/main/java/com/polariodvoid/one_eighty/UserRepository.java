package com.polariodvoid.one_eighty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polariodvoid.one_eighty.Model.User;
@Repository

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByEmail(String email);

}
