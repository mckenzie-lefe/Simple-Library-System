import org.junit.Test;
import static org.junit.Assert.*;

public class TestBookForSale {
    @Test
    public void testConstructorAndGetterMethods() {
        BookForSale book = new BookForSale(1, "Title", "Author", "Description", 20.0);

        assertEquals(1, book.getBookId());
        assertEquals("Title", book.getTitle());
        assertEquals(20.0, book.getPrice(), 0.00001);
        assertTrue(book.canBorrow());
    }
}
