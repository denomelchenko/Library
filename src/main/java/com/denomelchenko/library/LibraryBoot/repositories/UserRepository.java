package com.denomelchenko.library.LibraryBoot.repositories;

import com.denomelchenko.library.LibraryBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFullName(String fullName);
}
