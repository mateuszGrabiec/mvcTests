package pl.wlodarczyk.bookapp.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookAPI {
    private BookService bookService;

    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBookList() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Book>> getBookByID(@PathVariable long id) {
        Optional<Book> first = bookService.getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        Optional<Book> first = bookService.getAllBooks().stream().filter(book -> book.getTitle().equals(title)).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book) {
        boolean add = bookService.addBook(book);
        if (add) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modBook(@RequestBody Book newBook) {
        Optional<Book> first = bookService.getAllBooks().stream().filter(video -> video.getId() == newBook.getId()).findFirst();
        if (first.isPresent()) {
            bookService.deleteBook(first.get().getId());
            bookService.addBook(newBook);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delBook(@PathVariable long id) {
        Optional<Book> first = bookService.getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
        if (first.isPresent()) {
            bookService.deleteBook(first.get().getId());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateBookFields(@PathVariable long id, @RequestParam String author) {
        Optional<Book> bookOptional = bookService.getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
        if (bookOptional.isPresent()) {
            bookOptional.get().setAuthor(author);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void setBookList(List<Book> bookList) {
        this.bookService.set(bookList);
    }

    public void deleteBookList() {
        this.bookService.clear();
    }
}



