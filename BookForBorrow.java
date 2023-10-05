public class BookForBorrow extends Book {
    private boolean checkedOut;
    private String checkedOutBy;

    public BookForBorrow(int bookId, String title, String author, String description) {
        super(bookId, title, author, description);
        this.canBorrow = true;
        this.checkedOut = false;
        this.checkedOutBy = null;
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
