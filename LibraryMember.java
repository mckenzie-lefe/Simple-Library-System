import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class LibraryMember {
    private String memberId;
    private String name;
    private String phoneNumber;
    private int memberType;
    private Map<Integer, LocalDate> checkedOutBooks;
    private LocalDate lastPayment;
    static final int STUDENT = 0;
    static final int TEACHER = 1;

    public LibraryMember(String memberId, String name, String phoneNumber, int typeCode)  {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.memberType = typeCode;
        this.checkedOutBooks = new HashMap<>();
        this.lastPayment = LocalDate.now().minusDays(100);
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void updateLastPayment() {
        this.lastPayment = LocalDate.now();
    }

    public LocalDate getLastPayment() {
        return this.lastPayment;
    }

    public Map<Integer, LocalDate> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }

    public void checkOutBook(Book book, LocalDate checkOutDate) {      
        this.checkedOutBooks.put(book.getBookId(), checkOutDate);
    }

    public void returnBook(int bookID) {
        this.checkedOutBooks.remove(bookID);
    }

    public Map<Integer, Integer> getOverDueBooks() {
        Map<Integer, Integer> overDueBooks = new HashMap<>();

        if (this.memberType == STUDENT) {
            for (Map.Entry<Integer, LocalDate> checkedOutBook : this.checkedOutBooks.entrySet()) {
                int daysOverDue = (int) ChronoUnit.DAYS.between(checkedOutBook.getValue() , LocalDate.now()) - 7;
                if (daysOverDue > 0) {
                    overDueBooks.put(checkedOutBook.getKey(), daysOverDue);
                }
            }
        } else if(this.memberType == TEACHER) {
            for (Map.Entry<Integer, LocalDate> checkedOutBook : this.checkedOutBooks.entrySet()) {
                int daysOverDue = (int) ChronoUnit.DAYS.between(checkedOutBook.getValue() , LocalDate.now()) - 14;
                if (daysOverDue > 0) {
                    overDueBooks.put(checkedOutBook.getKey(), daysOverDue);
                }
            }
        } 

        return overDueBooks;
    }

    public double getOverDueCharges() {
        Map<Integer, Integer> overDueBooks = getOverDueBooks();
        int daysUnpaid = 0;
        double charge = 0.0;

        if (this.memberType == STUDENT) {
            for (Map.Entry<Integer, Integer> overDueBook : overDueBooks.entrySet()) {
                LocalDate dateCheckedOut = this.checkedOutBooks.get(overDueBook.getKey());
                if (this.lastPayment.isBefore(dateCheckedOut)) {
                    charge = charge + (overDueBook.getValue() * 3.00);       
                } else {
                    daysUnpaid = (int) ChronoUnit.DAYS.between(this.lastPayment , LocalDate.now());
                    charge = charge + (daysUnpaid * 3.00);
                }
            }
        } else if (this.memberType == TEACHER) {
            for (Map.Entry<Integer, Integer> overDueBook : overDueBooks.entrySet()) {
                LocalDate dateCheckedOut = this.checkedOutBooks.get(overDueBook.getKey());
                if (this.lastPayment.isBefore(dateCheckedOut)) {
                    charge = charge + (overDueBook.getValue() * 2.00);       
                } else {
                    daysUnpaid = (int) ChronoUnit.DAYS.between(this.lastPayment , LocalDate.now());
                    charge = charge + (daysUnpaid * 2.00);
                }
            }
        }

        return charge;
    }
}
