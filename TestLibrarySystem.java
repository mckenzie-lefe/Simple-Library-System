import org.junit.Test;
import static org.junit.Assert.*;

public class TestLibrarySystem {

    @Test
    public void testAddBook() {
        LibrarySystem library = new LibrarySystem();
        Book book = new Book(1, "Title", "Author", "Description");

        library.addBook(1, book);
        assertTrue(library.isLibraryBook(1));
    }

    @Test
    public void testAddLibraryMember() {
        LibrarySystem library = new LibrarySystem();
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);

        library.addLibraryMember("123", member);
        assertTrue(library.isLibraryMember("123"));
    }

    @Test
    public void testCheckOutBook() {
        LibrarySystem library = new LibrarySystem();
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        library.addLibraryMember("123", member);
        library.addBook(1, book);

        library.checkOutBook("123", 1);
        assertTrue(book.isCheckedOut());
        assertEquals("123", book.getCheckedOutBy());
    }

    @Test
    public void testReturnBook() {
        LibrarySystem library = new LibrarySystem();
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        library.addLibraryMember("123", member);
        library.addBook(1, book);

        library.checkOutBook("123", 1);
        library.returnBook("123", 1, true);
        assertFalse(book.isCheckedOut());
        assertNull(book.getCheckedOutBy());
    }

    @Test
    public void testGetBookInfo() {
        LibrarySystem library = new LibrarySystem();
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        library.addLibraryMember("123", member);
        library.addBook(1, book);

        String expectedInfo = "ID: 1\nTitle: Title\nAuthor: Author\nDescription: Description\n Rating: %0.0";
        assertEquals(expectedInfo, library.getBookInfo("Title"));
    }
}
