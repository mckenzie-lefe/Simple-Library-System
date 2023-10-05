public class BookForSale extends Book {
    private double price;

    public BookForSale(int bookId, String title, String author, String description, double price) {
        super( bookId, title, author, description);
        this.canBorrow = true;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
