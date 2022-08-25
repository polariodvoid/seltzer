package com.polariodvoid.one_eighty.Service;
import com.polariodvoid.one_eighty.Model.User;
import com.polariodvoid.one_eighty.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);

}
