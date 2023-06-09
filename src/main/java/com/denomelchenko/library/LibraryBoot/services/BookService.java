package com.denomelchenko.library.LibraryBoot.services;

import com.denomelchenko.library.LibraryBoot.models.Book;
import com.denomelchenko.library.LibraryBoot.models.User;
import com.denomelchenko.library.LibraryBoot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAll(int page, int itemsPerPage, boolean sorting) {
        if (sorting) {
            return bookRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        } else {
            return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
        }
    }

    public List<Book> getAll(boolean sorting) {
        if (sorting) return bookRepository.findAll(Sort.by("year"));
        return bookRepository.findAll();
    }

    public Book getById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setWasTakenAt(null);
        });
    }

    public User getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void assign(int id, User user) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(user);
            book.setWasTakenAt(new Date());
        });
    }

    public List<Book> findByTitleLike(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
