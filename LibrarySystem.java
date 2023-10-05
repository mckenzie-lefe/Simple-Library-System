import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LibrarySystem {
    private Map<String, LibraryMember> libraryMembers;
    private Map<Integer, Book> libraryBooks;

    public LibrarySystem() {
        this.libraryMembers = new HashMap<>();
        this.libraryBooks = new HashMap<>();
    }

    public boolean isLibraryMember(String id) {
        if (this.libraryMembers.get(id) == null) {
            return false;
        }
        return true;
    }

    public boolean isLibraryBook(int id) {
        if(this.libraryBooks.get(id) == null) {
            return false;
        }
        return true;
    }

    public void addBook(int id, Book book) {
        this.libraryBooks.put(id, book);
    }

    public void addLibraryMember(String id, LibraryMember member) {
        this.libraryMembers.put(id, member);
    }

    public String getBookInfo(String bookTitle) {
        for (Book book : this.libraryBooks.values()){
            if (book.getTitle().equals(bookTitle)){
                return book.getInfo();
            }
        }
        return null;
    }

    public boolean checkOutBook(String memberId, int bookId) {
        LibraryMember member = this.libraryMembers.get(memberId);
        Book book = this.libraryBooks.get(bookId);
        
        if (book.isCheckedOut()) {
            return false;
        } 

        book.checkOut(member.getMemberId());
        member.checkOutBook(book, LocalDate.now());
        return true;
    }

    public double getMembersOverDueCharges(String memberId) {
        return this.libraryMembers.get(memberId).getOverDueCharges();
    }

    public void updateMembersLastPayment(String memberId) {
        this.libraryMembers.get(memberId).updateLastPayment();
    }

    public String getMembersOverDueBooks(String memberId) {
        Map<Integer, Integer> overDueBooks = this.libraryMembers.get(memberId).getOverDueBooks();

        if (overDueBooks.isEmpty()) {
            return "";
        }
        String overDueBooksStr = "Over due books:\n";
        for (Map.Entry<Integer, Integer> overDueBook : overDueBooks.entrySet()) {
            overDueBooksStr = overDueBooksStr + this.libraryBooks.get(overDueBook.getKey()).getTitle() + 
                "\tDays Over Due: " + overDueBook.getValue() + "\n";
        }
        return overDueBooksStr;
    }

    public boolean returnBook(String memberId, int bookId, boolean liked) {
        LibraryMember member = this.libraryMembers.get(memberId);
        Book book = this.libraryBooks.get(bookId);

        if (book.getCheckedOutBy() != null && book.getCheckedOutBy().equals(member.getMemberId())) {
            if (member.getOverDueCharges() != 0.0)  {
                return false;
            }
            book.returnBook();
            member.returnBook(book.getBookId());
            book.updateRating(liked);
            return true;

        } 
        return false;
    }
}