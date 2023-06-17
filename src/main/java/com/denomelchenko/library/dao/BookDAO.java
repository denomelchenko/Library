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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT id, title, author, year FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void edit(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET author=?, title=?, year=? WHERE id=?",
                book.getAuthor(), book.getTitle(), book.getYear(), id);
    }

    public Optional<User> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT User.* FROM Book Join User ON Book.user_id=User.id WHERE Book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET user_id=NULL WHERE id=?", id);
    }

    public void assign(int id, User user) {
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE id=?", user.getId(), id);
    }
}
