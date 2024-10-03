package pe.edu.vallegrande.ventas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.vallegrande.ventas.model.Users;
import pe.edu.vallegrande.ventas.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> findUsersAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findUsersById(Long id) {
        return userRepository.findById(id);
    }

    public Users updateUsers(Users users) {
        return userRepository.save(users);
    }

    public Users createUsers(Users users) {
        return userRepository.save(users);
    }

    public void inactivarUsers(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        userOptional.ifPresent(users -> {
            users.setStatus("I");
            userRepository.save(users);
        });
    }

    public void restaurarUsers(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        userOptional.ifPresent(users -> {
            users.setStatus("A");
            userRepository.save(users);
        });
    }

    public List<Users> UserState(String status) {
        return userRepository.findByStatus(status);
    }
}
