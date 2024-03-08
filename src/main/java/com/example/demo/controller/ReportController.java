package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Book;
import com.example.demo.model.BookReport;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller 
@RequestMapping("/library/books")
public class ReportController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/report")
	public void report (HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/reports/ReportLibrary.jasper");
	    Map<String, Object> params = new HashMap<>();
	    
	    List<Book> books = bookService.getAllLastSixMonths();
	    List<BookReport> bookReports = new ArrayList<>();
	    
	    for (Book book : books) {
	    	bookReports.add(new BookReport(book.id, book.name, book.writer, book.publicationDate, book.gender.name));
	    }
	    
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookReports);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "filename=report_Book.pdf");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
