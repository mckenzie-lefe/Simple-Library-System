import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Map;

public class TestStudentMember {

    @Test 
    public void testCalcDaysOverDueCharge() {
        LibraryMember member = new StudentMember("123", "John", "555-555-5555");
        
        // 20 - 7 = 13
        assertEquals(13, member.calcDaysOverDue(LocalDate.now().minusDays(20)));
    }

    @Test 
    public void testCalcOverDueCharge() {
        LibraryMember member = new StudentMember("123", "John", "555-555-5555");
        
        // 3 * 3.00 = 9.00
        assertEquals(9.00, member.calcOverDueCharge(3), 0.00001);
    }
}
