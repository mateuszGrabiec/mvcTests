package pl.wlodarczyk.bookapp.Service;
import pl.wlodarczyk.bookapp.Model.Book;

import java.util.List;
import java.util.Optional;

public  interface BookService
{

    List<Book> getAllBooks();
    Optional<Book> getBookByID(long id);
    Optional<Book> getBookByTitle(String title);
    boolean addBook(Book book);
    boolean modifyChoiseBookAuthor(long id, String author);
    boolean deleteBook(long id);
    boolean modifyAllBookFields(Book bookUpdate);

    void set(List<Book> bookList);

    void clear();
}
