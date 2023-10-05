import org.junit.Test;
import static org.junit.Assert.*;

public class TestBookForBorrow {
    @Test
    public void testConstructorAndGetterMethods() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        assertEquals(1, book.getBookId());
        assertEquals("Title", book.getTitle());
        assertFalse(book.isCheckedOut());
        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testCheckOut() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        book.checkOut("123");
        assertTrue(book.isCheckedOut());
        assertEquals("123", book.getCheckedOutBy());
    }

    @Test
    public void testCheckOutWhenAlreadyCheckedOut() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        book.checkOut("123");
        assertThrows(IllegalStateException.class, () -> book.checkOut("456"));
    }

    @Test
    public void testReturnBook() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        book.checkOut("123");
        book.returnBook();
        assertFalse(book.isCheckedOut());
        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testReturnBookWhenNotCheckedOut() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        assertThrows(IllegalStateException.class, book::returnBook);
    }

    @Test
    public void testGetCheckedOutBy() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");
        book.checkOut("123");

        assertEquals("123", book.getCheckedOutBy());
    }

    @Test
    public void testGetCheckedOutByWhenNotCheckedOut() {
        BookForBorrow book = new BookForBorrow(1, "Title", "Author", "Description");

        assertNull(book.getCheckedOutBy());
    }
}
