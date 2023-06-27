package com.denomelchenko.library.controllers;

import com.denomelchenko.library.models.Book;
import com.denomelchenko.library.models.User;
import com.denomelchenko.library.services.BookService;
import com.denomelchenko.library.services.UserService;
import com.denomelchenko.library.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, UserService userService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.userService = userService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        User bookOwner = bookService.getBookOwner(id);
        if (bookOwner != null) {
            model.addAttribute("owner", bookOwner);
        } else {
            Optional<User> users = userService.getAll().stream().findAny();
            if (users.isPresent()) {
                model.addAttribute("users", userService.getAll());
            } else {
                model.addAttribute("empty", "Library without users");
            }
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("users", userService.getAll());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, Model model) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAll());
            return "books/new";
        }
        bookService.add(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.getById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        bookService.assign(id, user);
        return "redirect:/books/" + id;
    }
}
