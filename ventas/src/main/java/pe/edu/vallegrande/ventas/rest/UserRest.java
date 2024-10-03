package pe.edu.vallegrande.ventas.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.vallegrande.ventas.model.Users;
import pe.edu.vallegrande.ventas.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.findUsersAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
public ResponseEntity<Users> createUser(@RequestBody Users user) {
    Users createdUser = Users.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .first_name(user.getFirst_name())
            .last_name(user.getLast_name())
            .address(user.getAddress())
            .phone(user.getPhone())
            .email(user.getEmail())
            .document_type(user.getDocument_type())
            .document_number(user.getDocument_number())
            .user_type(user.getUser_type())
            .status("A") 
            .build();

    Users savedUser = userService.createUsers(createdUser);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
}

@PutMapping("/update/{id}")
public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
    Optional<Users> existingUserOptional = userService.findUsersById(id);
    
    if (!existingUserOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Users existingUser = existingUserOptional.get();

    existingUser.setUsername(userDetails.getUsername());
    existingUser.setPassword(userDetails.getPassword());
    existingUser.setFirst_name(userDetails.getFirst_name());
    existingUser.setLast_name(userDetails.getLast_name());
    existingUser.setAddress(userDetails.getAddress());
    existingUser.setPhone(userDetails.getPhone());
    existingUser.setEmail(userDetails.getEmail());
    existingUser.setDocument_type(userDetails.getDocument_type());
    existingUser.setDocument_number(userDetails.getDocument_number());
    existingUser.setUser_type(userDetails.getUser_type());

    Users updatedUser = userService.updateUsers(existingUser);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}



    @PutMapping("/{id}/inactivar")
    public ResponseEntity<Void> inactivateUser(@PathVariable Long id) {
        userService.inactivarUsers(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/restaurar")
    public ResponseEntity<Void> restoreUser(@PathVariable Long id) {
        userService.restaurarUsers(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return userService.findUsersAll().stream()
                .filter(user -> user.getId_user().equals(id))
                .findFirst()
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Users>> listarUsersActivos() {
        List<Users> userActivos = userService.UserState("A");
        return new ResponseEntity<>(userActivos, HttpStatus.OK);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<List<Users>> listarUsersInactivos() {
        List<Users> userInactivos = userService.UserState("I");
        return new ResponseEntity<>(userInactivos, HttpStatus.OK);
    }

}
