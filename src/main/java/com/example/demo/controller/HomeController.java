package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class HomeController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String redirectToBooks(Model model) {
		List<Book> bookList = bookService.getAllBooks();
		model.addAttribute("books", bookList);
		return "redirect:/library/books/all";
	}

	
}