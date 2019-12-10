package pl.wlodarczyk.bookapp.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wlodarczyk.bookapp.Model.Book;
import pl.wlodarczyk.bookapp.Service.BookService;

@Route("add-book")
public class BookAddGui extends VerticalLayout {

    private BookService bookService;

    @Autowired
    public BookAddGui(BookService bookService) {
    this.bookService = bookService;
        NumberField numberField = new NumberField("ID");
        TextField textFieldIsbn = new TextField();
        textFieldIsbn.setLabel("ISBN");
        TextField textFieldTitle = new TextField("Title");
        TextField textFieldAuthor = new TextField("Author");
        Button buttonAdd = new Button("Add book");

        buttonAdd.addClickListener(clickEvent -> {
            Book book = new Book(numberField.getValue().longValue(), textFieldIsbn.getValue(),textFieldTitle.getValue(),textFieldAuthor.getValue());
            bookService.addBook(book);

        });

        add(numberField, textFieldIsbn,textFieldTitle,textFieldAuthor, buttonAdd);
    }

}
