package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getAllLastSixMonths() {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, -6);
		Date sixMonthsAgo = calendar.getTime();
		return bookRepository.findRecordsLastSixMonths(sixMonthsAgo);
	}

	public void saveBook(Book book) {
		bookRepository.save(book);
	}
	
	public Book getBookByID(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
}
