package pl.wlodarczyk.bookapp.Model;

public class Book {

    private long id;
    private String isbn;
    private String title;
    private String author;


    public Book(long id, String isbn, String title, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{" +"\n"+
                "\"id\": " + id +",\n"+
                "\"isbn\": \"" + isbn + "\",\n" +
                "\"title\": \"" + title + "\",\n" +
                "\"author\": \"" + author + "\"\n" +
                "}";
    }
}


