package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/library/books")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenderService genderService;

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        return "books";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("genders", genderService.getAllGender());
        return "bookRegister";
    }

    @PostMapping("/register")
    public String createBook(@RequestParam("name") String name,
                                @RequestParam("writer") String writer,
                                @RequestParam("publicationDate") String publicationDate,
                                @RequestParam("idGender") Long id, Model model) {

        Book book = new Book();
        if (name.length() <= 4 || name.length() >= 60) {
            model.addAttribute("errorMessage", "Nombre de Libro deber tener de 4 a 60 caracteres");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookRegister";
        }
        book.name = name;
        if (writer.length() <= 4 || writer.length() >= 60) {
            model.addAttribute("errorMessage", "Nombre del Autor deber tener de 4 a 60 caracteres");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookRegister";
        }
        book.writer = writer;
        if (!publicationDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            // Si no tiene el formato esperado, muestra un mensaje de error y retorna
            model.addAttribute("errorMessage", "La fecha de publicación debe tener el formato dd-MM-yyyy");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookRegister";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            book.publicationDate = format.parse(publicationDate);
        } catch (ParseException e) {
            model.addAttribute("errorMessage", "Fecha de Publicacion deber tener el formato dd-MM-yyyy");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookRegister";
        }

        book.gender = genderService.getGenderByID(id);

        bookService.saveBook(book);

        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        model.addAttribute("genders", genderService.getAllGender());

        return "books";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Book book = bookService.getBookByID(id);

        model.addAttribute("book", book);
        model.addAttribute("genders", genderService.getAllGender());

        return "bookEdit";
    }


    @PostMapping("/edit")
    public String editBook(@RequestParam("idBook") Long idBook,
    					   @RequestParam("name") String name,
                           @RequestParam("writer") String writer,
                           @RequestParam("publicationDate") String publicationDate,
                           @RequestParam("idGender") Long idGender, Model model) {

        Book book = bookService.getBookByID(idBook);
        if (name.length() <= 4 || name.length() >= 60) {
            model.addAttribute("errorMessage", "Nombre de Libro deber tener de 4 a 60 caracteres");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookEdit";
        }
        book.name = name;
        if (writer.length() <= 4 || writer.length() >= 60) {
            model.addAttribute("errorMessage", "Nombre del Autor deber tener de 4 a 60 caracteres");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookEdit";
        }
        book.writer = writer;
        if (!publicationDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            // Si no tiene el formato esperado, muestra un mensaje de error y retorna
            model.addAttribute("errorMessage", "La fecha de publicación debe tener el formato dd-MM-yyyy");
            model.addAttribute("book", book);
            model.addAttribute("genders", genderService.getAllGender());
            return "bookEdit";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            book.publicationDate = format.parse(publicationDate);
        } catch (ParseException e) {
            model.addAttribute("errorMessage", "Fecha de Publicacion deber tener el formato dd-MM-yyyy");
            model.addAttribute("genders", genderService.getAllGender());
            return "bookEdit";
        }

        book.gender = genderService.getGenderByID(idGender);

        bookService.saveBook(book);

        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        model.addAttribute("genders", genderService.getAllGender());

        return "books";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id, Model model) {
        bookService.deleteBook(id);

        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        model.addAttribute("genders", genderService.getAllGender());

        return "books";
    }


}
