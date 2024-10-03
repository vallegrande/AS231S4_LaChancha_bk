package pe.edu.vallegrande.ventas.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.vallegrande.ventas.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByStatus(String status);
}
 