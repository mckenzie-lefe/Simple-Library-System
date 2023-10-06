import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TeacherMember extends LibraryMember {

    public TeacherMember(String memberId, String name, String phoneNumber) {
        super(memberId, name, phoneNumber);
    }
    
    public int calcDaysOverDue(LocalDate dayCheckedOut) {
        return (int) ChronoUnit.DAYS.between(dayCheckedOut, LocalDate.now()) - 14;
    }

    public double calcOverDueCharge(int days) {
        return days * 2.00;
    }
}
