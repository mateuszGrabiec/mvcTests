package pl.wlodarczyk.bookapp.Service;

import org.springframework.stereotype.Service;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private List<Book> bookList;

    public BookServiceImpl() {

        this.bookList = new ArrayList<>();
        bookList.add(new Book(1, "978-83-8119-572-0", "Bezglosni", "Segiet Eliza"));
        bookList.add(new Book(2, "978-83-7593-387-1", "Debowiec", "Herok Iga"));
        bookList.add(new Book(3, "978-83-8119-576-8", "A co jesli Pytania zmieniające Twoj swiat", "Beata Jusik"));
        bookList.add(new Book(4, "978-83-952496-2-4", "Metoda Teczowki", "Jacek Klosinski"));
        bookList.add(new Book(5, "978-83-953342-4-5", "Marysia na biegunach", "Krzysiek Syput"));
        bookList.add(new Book(6, "978-83-954366-0-4", "Turysci na Wenus", "Aneta Jablonowska"));
        bookList.add(new Book(7, "978-83-7502-720-4", "Zimowa przygoda aniolka", "Maria Przybylska"));
        bookList.add(new Book(8, "978-83-948093-1-7", "Krwawa sroda", "Wojciech lukaszewski"));
        bookList.add(new Book(9, "978-83-955834-2-1", "Rozmowa o prace - angielskie slownictwo i zwroty", "Slawomir Maczka"));
        bookList.add(new Book(10, "978-83-61706-52-6", "100 polskich artystww wspolczesnych", "Kama Zboralskaa"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookList;
    }

    @Override
    public Optional<Book> getBookByID(long id)
    {
        return bookList.stream().filter(book -> book.getId() == id).findFirst();
    }

    @Override
    public Optional<Book> getBookByTitle(String title)
    {
        return bookList.stream().filter(book -> book.getTitle().equals(title)).findFirst();
    }

    @Override
    public boolean addBook(Book book)
    {
        return bookList.add(book);
    }
    @Override
    public boolean modifyChoiseBookAuthor(long id, String author)
    {
        Optional<Book> bookOptional =  bookList.stream().filter(book -> book.getId() == id).findFirst();
        if (bookOptional.isPresent())
        {
            bookOptional.get().setAuthor(author);
            return true;
        }
        return false;
    }

@Override
    public boolean deleteBook(long id)
{
    Optional<Book> first = bookList.stream().filter(book -> book.getId() == id).findFirst();

    if (first.isPresent())
    {
        bookList.remove(first.get());
        return true;
    }
    else {
        return false;
    }
}

@Override

    public boolean modifyAllBookFields (Book bookUpdate) {
    Optional<Book> first = bookList.stream().filter(book -> book.getId() == bookUpdate.getId()).findFirst();
    if (first.isPresent())
    {
        bookList.remove(first.get());
        return bookList.add(bookUpdate);
    }

        return false;
    }

    @Override
    public void set(List<Book> bookList) {
        this.bookList=bookList;
    }

    @Override
    public void clear() {
        this.bookList=new ArrayList<>();
    }
}

