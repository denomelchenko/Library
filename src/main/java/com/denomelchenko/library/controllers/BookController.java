package com.denomelchenko.library.controllers;

import com.denomelchenko.library.dao.BookDAO;
import com.denomelchenko.library.dao.UserDAO;
import com.denomelchenko.library.models.Book;
import com.denomelchenko.library.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final UserDAO userDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, UserDAO userDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getById(id);
        model.addAttribute("book", book);
        model.addAttribute("users", userDAO.getAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("users", userDAO.getAll());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, Model model) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userDAO.getAll());
            return "books/new";
        }
        bookDAO.add(book);
        return "redirect:/books";
    }

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        bookDAO.delete(id);
//        return "redirect:/books";
//    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.edit(book, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/edit_user")
    public String updateUser(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.setUserId(book.getUserId(), id);
        return "redirect:/books";
    }
}
