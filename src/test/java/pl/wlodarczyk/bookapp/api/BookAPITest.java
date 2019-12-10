package pl.wlodarczyk.bookapp.api;


import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.wlodarczyk.bookapp.Model.Book;


import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookAPITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookAPI bookAPI;

    @BeforeEach
    private void setList() {
        ArrayList<Book> bookList = new ArrayList<>();
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
        bookAPI.setBookList(bookList);
    }

    @AfterEach
    private void after() {
        bookAPI.deleteBookList();
    }

    @Test
    void shouldGetBookList() throws Exception {
        mockMvc.perform(get("/books", 10))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(10)));
    }

    @Test
    void shouldGetBookByID() throws Exception {
        mockMvc.perform(get("/books/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Is.is("978-83-7593-387-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Debowiec")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Is.is("Herok Iga")));
    }

    @Test
    void shouldGetBookByTitle() throws Exception {
        mockMvc.perform(get("/books/title/{title}", "Debowiec"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Is.is("978-83-7593-387-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Debowiec")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Is.is("Herok Iga")));
    }

    @Test
    void shouldAddBook() throws Exception {
        mockMvc.perform(post("/books/").contentType(MediaType.APPLICATION_JSON).content(
                new Book(12, "131-2313-1313", "Title", "Author").toString()
        ))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldModBook() throws Exception {

        mockMvc.perform(put("/books/").contentType(MediaType.APPLICATION_JSON).content(
                new Book(1, "131-2313-1313", "Title", "Author").toString()
        ))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelBook() throws Exception {

        mockMvc.perform(delete("/books/{id}", 8))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldUpdateBookFields() throws Exception {
        mockMvc.perform(patch("/books/{id}", 6)//requestAttr("author","sadasads"))
                .param("author", "asddsasda"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldNotGetBookList() throws Exception {
        mockMvc.perform(get("/books/all"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotGetBookByID() throws Exception {
        mockMvc.perform(get("/books/{id}", 17))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotGetBookByTitle() throws Exception {
        mockMvc.perform(get("/books/title/{title}", "nonExistingTitle"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotAddBook() throws Exception {

        mockMvc.perform(post("/books/").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                new Book(12, "131-2313-1313", "Title", "Author").toString()
        ))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotModBook() throws Exception {

        mockMvc.perform(put("/books/").contentType(MediaType.APPLICATION_JSON).content(
                new Book(12, "131-2313-1313", "Title", "Author").toString()
        ))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldNotDelBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", 17))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldNotUpdateBookFields() throws Exception {
        mockMvc.perform(patch("/books/{id}", 111)//requestAttr("author","sadasads"))
                .param("author", "asddsasda"))
                .andExpect(status().is4xxClientError());
    }
}