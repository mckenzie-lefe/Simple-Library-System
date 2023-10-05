import org.junit.Test;
import static org.junit.Assert.*;

public class TestBook{

    @Test
    public void testConstructorAndGetterMethods() {
        Book book = new Book(1, "Title", "Author", "Description");

        assertEquals(1, book.getBookId());
        assertEquals("Title", book.getTitle());
        assertEquals("ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description", book.getInfo());
    }
}
