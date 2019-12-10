package pl.wlodarczyk.bookapp.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;

import java.util.Optional;

@Route("modify-book")
public class BookModifyGui extends VerticalLayout {

    private BookService bookService;


    @Autowired
    public BookModifyGui(BookService bookService) {
        this.bookService = bookService;
        NumberField numberFieldid = new NumberField();
        numberFieldid.setLabel("ID");
        Button buttonAdd = new Button("Search");
        TextField textFieldIsbn = new TextField();
        textFieldIsbn.setLabel("ISBN");
        TextField textFieldTitle = new TextField("Title");
        TextField textFieldAuthor = new TextField("Author");
        Button buttonModify = new Button("Modify");
        Grid<Book> grid = new Grid<>(Book.class);
        grid.setVisible(false);


        buttonAdd.addClickListener(clickEvent -> {
            Optional<Book> optionalBook = bookService.getBookByID(numberFieldid.getValue().longValue());
            Book book = optionalBook.get();
            grid.setItems(book);
            grid.setVisible(true);

            String isbn = textFieldIsbn.getValue();
            String title = textFieldTitle.getValue();
            String author = textFieldAuthor.getValue();
            if (isbn.equals("")) {
                isbn = book.getIsbn();
            }
            if (title.equals("")) {
                title = book.getTitle();
            }
            if (author.equals("")) {
                author = book.getAuthor();
            }

            Book bookUpdate = new Book(numberFieldid.getValue().longValue(), isbn, title, author);
            bookService.modifyAllBookFields(bookUpdate);

        });

        add(numberFieldid, buttonAdd, grid, textFieldIsbn, textFieldTitle, textFieldAuthor, buttonModify);

    }
}
