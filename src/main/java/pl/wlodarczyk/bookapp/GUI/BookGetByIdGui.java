package pl.wlodarczyk.bookapp.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;

import java.util.Optional;


@Route("show-id")
public class BookGetByIdGui extends VerticalLayout {
    private BookService bookService;

    @Autowired
    public BookGetByIdGui(BookService bookService) {
        this.bookService = bookService;
        NumberField numberFieldid = new NumberField();
        numberFieldid.setLabel("ID");
        Button buttonAdd = new Button("Search");
        Grid<Book> grid = new Grid<>(Book.class);
        grid.setVisible(false);

        buttonAdd.addClickListener(clickEvent -> {
            Optional<Book> optionalBook = bookService.getBookByID(numberFieldid.getValue().longValue());
            Book book = optionalBook.get();
            grid.setItems(book);
            grid.setVisible(true);
        });

        add(numberFieldid, buttonAdd, grid);
    }


}
