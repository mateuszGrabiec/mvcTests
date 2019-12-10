package pl.wlodarczyk.bookapp.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import pl.wlodarczyk.bookapp.Service.BookService;


@Route("delete-book")
public class BookRemoveGui extends VerticalLayout {

    private BookService bookService;

    @Autowired
    public BookRemoveGui(BookService bookService) {
        this.bookService = bookService;
        NumberField numberFieldid = new NumberField();
        numberFieldid.setLabel("ID");
        Button buttonDel = new Button("Delete");
        buttonDel.addClickListener(clickEvent -> {
            bookService.deleteBook(numberFieldid.getValue().longValue());
        });
        add(numberFieldid,buttonDel);
}
}
