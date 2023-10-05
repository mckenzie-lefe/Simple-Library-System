public class Book {
    private int bookId;
    private String title;
    private String author;
    private String description;
    private boolean checkedOut;
    private String checkedOutBy;
    private int ratingsCount;
    private double averageRating;

    public Book(int bookId, String title, String author, String description) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.checkedOut = false;
        this.checkedOutBy = null;
        this.ratingsCount = 0;
        this.averageRating = 0.00;
    }

    public int getBookId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateRating(boolean liked) {
        this.ratingsCount++;
        if (liked) {
            this.averageRating = (this.averageRating * (this.ratingsCount - 1) + 1) / this.ratingsCount;
        } else {
            this.averageRating = (this.averageRating * (this.ratingsCount - 1)) / this.ratingsCount;
        }
    }

    public String getInfo() {
        return "ID: " + this.bookId + "\nTitle: " + this.title + "\nAuthor: " + 
            this.author + "\nDescription: " + this.description + "\nRating: %" + String.format("%.2f",this.averageRating);
    }

    public boolean isCheckedOut() {
        return this.checkedOut;
    }

    public String getCheckedOutBy() {
        return this.checkedOutBy;
    }

    public void checkOut(String memberId) {
        if (!this.checkedOut) {
            this.checkedOut = true;
            this.checkedOutBy = memberId;
        } else {
            throw new IllegalStateException("Cannot check out book which has already been checked out.");
        }
    }

    public void returnBook() {
        if (this.checkedOut) {
            this.checkedOut = false;
            this.checkedOutBy = null;
        } else {
            throw new IllegalStateException("Cannot return book which is not checked out.");
        }
    }
}