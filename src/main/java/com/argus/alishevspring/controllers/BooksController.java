package com.argus.alishevspring.controllers;

import com.argus.alishevspring.dao.BookDAO;
import com.argus.alishevspring.dao.PersonDAO;
import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import com.argus.alishevspring.services.BooksService;
import com.argus.alishevspring.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookDAO bookDAO, PersonDAO personDAO) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(@RequestParam(required = false, name = "page") Integer page,
                        @RequestParam(required = false, name = "books_per_page") Integer booksPerPage,
                        @RequestParam(required = false, name = "sort_by_year") boolean sortByYear,
                        Model model) {
        if (page!= null && booksPerPage != null && sortByYear) {
            Page<Book> bookPage = booksService.index(PageRequest.of(page, booksPerPage, Sort.by("age_of_publishment")));
            model.addAttribute("totalPages", bookPage.getTotalPages());
            model.addAttribute("books", bookPage.getContent());
        } else if (page!= null && booksPerPage != null) {
            Page<Book> bookPage = booksService.index(PageRequest.of(page, booksPerPage));
            model.addAttribute("totalPages", bookPage.getTotalPages());
            model.addAttribute("books", bookPage.getContent());
        } else if (sortByYear) {
            model.addAttribute("totalPages", 1);
            model.addAttribute("books", booksService.indexSorted());
        } else {
            model.addAttribute("totalPages", 1);
            model.addAttribute("books", booksService.index());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.show(id));
        model.addAttribute("reader", booksService.showPerson(id));
        model.addAttribute("people", peopleService.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int bookId, Person person) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.releasePerson(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        booksService.assignPerson(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @GetMapping("/find")
    public String find(@RequestParam(required = false, name = "key") String key, Model model) {
        if (key != null) {
            if (key.equals("")) {
                return "books/search";
            }
            model.addAttribute("book", booksService.find(key));
        }
        return "books/search";
    }
}
