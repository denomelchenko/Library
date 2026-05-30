package com.denomelchenko.library.LibraryBoot.services;

import com.denomelchenko.library.LibraryBoot.models.Book;
import com.denomelchenko.library.LibraryBoot.models.User;
import com.denomelchenko.library.LibraryBoot.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Value("${library.book.expiration-days:10}")
    private int expirationDays;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Book> findBooksByUserId(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Hibernate.initialize(user.get().getBooks());
            user.get().getBooks().forEach(book -> {
                if (book.getWasTakenAt() != null &&
                        book.getWasTakenAt().plusDays(expirationDays).isBefore(LocalDateTime.now()))
                    book.setExpired(true);
            });
            return user.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    public Optional<User> findUserByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void add(User user) {
        userRepository.save(user);
    }
}
