package pl.wlodarczyk.bookapp.GUI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;


@Route("show-book")
public class BookShowGui extends VerticalLayout {

    private BookService bookService;

    @Autowired
    public BookShowGui(BookService bookService) {
        this.bookService = bookService;
        Grid<Book> grid = new Grid<>(Book.class);
        grid.setItems(bookService.getAllBooks());

        add(grid);
    }

}
