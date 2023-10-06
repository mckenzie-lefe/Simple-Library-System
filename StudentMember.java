import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StudentMember extends LibraryMember {
    
    public StudentMember(String memberId, String name, String phoneNumber) {
        super(memberId, name, phoneNumber);
    }

    public int calcDaysOverDue(LocalDate dayCheckedOut) {
        return (int) ChronoUnit.DAYS.between(dayCheckedOut, LocalDate.now()) - 7;
    }

    public double calcOverDueCharge(int days) {
        return days * 3.00;
    }
}
