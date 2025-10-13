package com.example.demo.repo;

import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByFirstName(String firstName); 
	List<User> findByEmailContaining(String keyword);
}
