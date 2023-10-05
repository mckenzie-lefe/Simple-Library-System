import org.junit.Test;
import static org.junit.Assert.*;

public class TestBook{

    @Test
    public void testConstructorAndGetterMethods() {
        Book book = new Book(1, "Title", "Author", "Description");

        assertEquals(1, book.getBookId());
        assertEquals("Title", book.getTitle());
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\nRating: %0.00", book.getInfo());
        assertFalse(book.isCheckedOut());
        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testCheckOut() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.checkOut("123");
        assertTrue(book.isCheckedOut());
        assertEquals("123", book.getCheckedOutBy());
    }

    @Test
    public void testCheckOutWhenAlreadyCheckedOut() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.checkOut("123");
        assertThrows(IllegalStateException.class, () -> book.checkOut("456"));
    }

    @Test
    public void testReturnBook() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.checkOut("123");
        book.returnBook();
        assertFalse(book.isCheckedOut());
        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testReturnBookWhenNotCheckedOut() {
        Book book = new Book(1, "Title", "Author", "Description");

        assertThrows(IllegalStateException.class, book::returnBook);
    }

    @Test
    public void testGetCheckedOutBy() {
        Book book = new Book(1, "Title", "Author", "Description");
        book.checkOut("123");

        assertEquals("123", book.getCheckedOutBy());
    }

    @Test
    public void testGetCheckedOutByWhenNotCheckedOut() {
        Book book = new Book(1, "Title", "Author", "Description");

        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testUpdateRatingLiked() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.updateRating(true);
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\nRating: %1.00", book.getInfo());
    }

    @Test
    public void testUpdateRatingNotLiked() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.updateRating(false);
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\nRating: %0.00", book.getInfo());
    }

    @Test
    public void testMultipleRatings() {
        Book book = new Book(1, "Title", "Author", "Description");

        book.updateRating(true);
        book.updateRating(false);
        book.updateRating(true);

        // (1 + 0 + 1) / 3 = 0.6667
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\nRating: %0.67", book.getInfo());
    }

    @Test
    public void testMultipleRatingsNotLiked() {
        Book book = new Book(1, "Title", "Author", "Description");
        book.updateRating(false);
        book.updateRating(false);

        // (0 + 0) / 2 = 0.0
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\nRating: %0.00", book.getInfo());
    }
}