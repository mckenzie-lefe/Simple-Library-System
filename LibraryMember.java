import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public abstract class LibraryMember {
    private String memberId;
    private String name;
    private String phoneNumber;
    private Map<Integer, LocalDate> checkedOutBooks;
    private LocalDate lastPayment;

    public LibraryMember(String memberId, String name, String phoneNumber)  {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public abstract int calcDaysOverDue(LocalDate dayCheckedOut);
    
    public abstract double calcOverDueCharge(int days);

    public Map<Integer, Integer> getOverDueBooks() {
        Map<Integer, Integer> overDueBooks = new HashMap<>();

        for (Map.Entry<Integer, LocalDate> checkedOutBook : this.checkedOutBooks.entrySet()) {
            int daysOverDue = calcDaysOverDue(checkedOutBook.getValue());
            if (daysOverDue > 0) {
                overDueBooks.put(checkedOutBook.getKey(), daysOverDue);
            }
        }
        return overDueBooks;
    }

    public double getOverDueCharges() {
        Map<Integer, Integer> overDueBooks = getOverDueBooks();
        double charge = 0.0;

        for (Map.Entry<Integer, Integer> overDueBook : overDueBooks.entrySet()) {
            LocalDate dateCheckedOut = this.checkedOutBooks.get(overDueBook.getKey());
            if (this.lastPayment.isBefore(dateCheckedOut)) {
                charge = charge + calcOverDueCharge(overDueBook.getValue());       
            } else {
                int daysUnpaid = (int) ChronoUnit.DAYS.between(this.lastPayment , LocalDate.now());
                charge = charge + calcOverDueCharge(daysUnpaid);
            }
        }
        return charge;
    }
}