import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Map;

public class TestLibraryMember {

    @Test
    public void testGetMemberId() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        assertEquals("123", member.getMemberId());
    }

    @Test
    public void testCheckOutBook() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        member.checkOutBook(book, LocalDate.now());
        assertTrue(member.getCheckedOutBooks().containsKey(1));
    }

    @Test
    public void testReturnBook() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        member.checkOutBook(book, LocalDate.now());
        member.returnBook(1);
        assertFalse(member.getCheckedOutBooks().containsKey(1));
    }

    @Test
    public void testGetCheckedOutBooks() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book book = new Book(1, "Title", "Author", "Description");

        member.checkOutBook(book, LocalDate.now());
        Map<Integer, LocalDate> checkedOutBooks = member.getCheckedOutBooks();
        assertTrue(checkedOutBooks.containsKey(1));
        assertNotNull(checkedOutBooks.get(1));
    }

    @Test
    public void testGetOverDueBooksForStudent() {
        LibraryMember studentMember = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book overdueBook = new Book(1, "Overdue Book", "Author", "Description");
        Book notOverdueBook = new Book(2, "Not Overdue Book", "Author", "Description");

        studentMember.checkOutBook(notOverdueBook, LocalDate.now());
        studentMember.checkOutBook(overdueBook, LocalDate.now().minusDays(8));

        Map<Integer, Integer> overdueBooks = studentMember.getOverDueBooks();
        assertEquals(1, overdueBooks.size());
        assertTrue(overdueBooks.containsKey(1));
    }

    @Test
    public void testGetOverDueBooksForTeacher() {
        LibraryMember teacherMember = new LibraryMember("456", "Jane", "555-555-5556", LibraryMember.TEACHER);
        Book overdueBook = new Book(1, "Overdue Book", "Author", "Description");
        Book notOverdueBook = new Book(2, "Not Overdue Book", "Author", "Description");

        teacherMember.checkOutBook(notOverdueBook, LocalDate.now());
        teacherMember.checkOutBook(overdueBook, LocalDate.now().minusDays(15));

        Map<Integer, Integer> overdueBooks = teacherMember.getOverDueBooks();
        assertEquals(1, overdueBooks.size());
        assertTrue(overdueBooks.containsKey(1));
    }

    @Test
    public void testGetOverDueChargesForStudents() {
        LibraryMember studentMember = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        Book studentBook = new Book(1, "Student Book", "Author", "Description");

        studentMember.checkOutBook(studentBook, LocalDate.now().minusDays(8));
        double studentCharges = studentMember.getOverDueCharges();

        assertEquals(3.0, studentCharges, 0.00001);
    }

    @Test
    public void testGetOverDueChargesForTeacher() {
        LibraryMember teacherMember = new LibraryMember("456", "Jane", "555-555-5556", LibraryMember.TEACHER);
        Book teacherBook = new Book(2, "Teacher Book", "Author", "Description");
        
        teacherMember.checkOutBook(teacherBook, LocalDate.now().minusDays(15));     
        double teacherCharges = teacherMember.getOverDueCharges();

        assertEquals(2.0, teacherCharges, 0.00001);
    }

    @Test
    public void testUpdateLastPayment() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        member.updateLastPayment();

        assertEquals(LocalDate.now(), member.getLastPayment());
    }

    @Test 
    public void testCalcDaysOverDueCharge() {
        LibraryMember member = new LibraryMember("123", "John", "555-555-5555", LibraryMember.STUDENT);
        
        // 20 - 7 = 13
        assertEquals(13, member.calcDaysOverDue(LocalDate.now().minusDays(20)));
    }
}
