package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryUserRepository;
import mk.finki.ukim.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository userRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(RuntimeException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty()||surname==null||surname.isEmpty() ) {
            throw new RuntimeException();

        }
        if(!password.equals(repeatPassword)){
            throw new RuntimeException();
        }
        return userRepository.saveOrUpdate(new User(username,password,name,surname));
    }
}
