package com.denomelchenko.library.dao;

import com.denomelchenko.library.models.Book;
import com.denomelchenko.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM User", new BeanPropertyRowMapper<>(User.class));
    }

    public User getById(int id) {
        return jdbcTemplate.query("SELECT * FROM User WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void add(User user) {
        jdbcTemplate.update("INSERT INTO User(full_name, year_of_birthday) VALUES (?, ?)",
                user.getFullName(), user.getYearOfBirth());
    }

    public void update(User user, int id) {
        jdbcTemplate.update("UPDATE User SET full_name=?, year_of_birthday=? WHERE id=?",
                user.getFullName(), user.getYearOfBirth(), id);
    }

    public Optional<User> getUserByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM User WHERE full_name=?",
                new Object[]{fullName}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    public List<Book> getBooksById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE user_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
