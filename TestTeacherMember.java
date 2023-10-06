import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Map;

public class TestTeacherMember {
    @Test 
    public void testCalcDaysOverDueCharge() {
        LibraryMember member = new TeacherMember("123", "John", "555-555-5555");
        
        // 20 - 14 = 6
        assertEquals(6, member.calcDaysOverDue(LocalDate.now().minusDays(20)));
    }

    @Test 
    public void testCalcOverDueCharge() {
        LibraryMember member = new TeacherMember("123", "John", "555-555-5555");
        
        // 3 * 2.00 = 6.00
        assertEquals(6.00, member.calcOverDueCharge(3), 0.00001);
    }
}
