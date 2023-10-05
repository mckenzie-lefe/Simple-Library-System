public class Book {
    protected int bookId;
    protected String title;
    protected String author;
    protected String description;
    protected Boolean canBorrow;

    public Book(int bookId, String title, String author, String description) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public int getBookId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean canBorrow() {
        return this.canBorrow;
    }

    public String getInfo() {
        return "ID: " + this.bookId + "\nTitle: " + this.title + "\nAuthor: " + 
            this.author + "\nDescription: " + this.description;
    }
}
