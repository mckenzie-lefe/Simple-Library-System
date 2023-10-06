import java.io.IOException;
import java.util.Scanner;

public class LibraryUI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static LibrarySystem library = new LibrarySystem();
 
    public static String getUsersMemberId() throws IOException {
        System.out.print("Enter your library member id: ");
        String memberId = SCANNER.nextLine();
        if (library.isLibraryMember(memberId)) {
            System.out.println("Invalid library member id.");
            throw new IOException();
        } 
        return memberId;
    }

    public static int getBookIdFromUser() throws IOException {
        System.out.print("Enter the book id: ");
        int bookId = SCANNER.nextInt();
        SCANNER.nextLine();
        if (!library.isLibraryBook(bookId)) {
            System.out.println("Invalid book id.");
            throw new IOException();
        }
        return bookId;
    }
    
    public static void main(String[] args) {
        String memberId;
        int bookId;
        
        library.addLibraryMember("123", new StudentMember("123", "John Student", "555-555-5555"));
        library.addLibraryMember("456", new TeacherMember("456", "Jane Teacher", "555-555-5556"));
        Book book1 = new Book(1, "Book 1", "Author 1", "Description 1");
        library.addBook(1, book1);
        Book book2 = new Book(2, "Book 2", "Author 2", "Description 2");
        library.addBook(2, book2);

        while (true) {
            System.out.println("\n1. Check out a book");
            System.out.println("2. Return a book");
            System.out.println("3. View & pay over due fees");
            System.out.println("4. View over due books");
            System.out.println("5. Look up a book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = SCANNER.nextInt();
            SCANNER.nextLine();
            System.out.println();
            try {
                switch (choice) {
                    case 1:
                        memberId = getUsersMemberId();
                        bookId = getBookIdFromUser();

                        if (library.checkOutBook(memberId, bookId)) {
                            System.out.println("Book successfully checked out.");
                        } else {
                            System.out.println("The book is already checked out.");
                        }
                        break;

                    case 2:
                        memberId = getUsersMemberId();
                        bookId = getBookIdFromUser();

                        System.out.print("Did you like the book? (yes/no): ");
                        String likeInput = SCANNER.nextLine();
                        boolean liked = likeInput.equalsIgnoreCase("yes");
            
                        if(library.returnBook(memberId, bookId, liked)) {
                            System.out.println("Book returned successfully.");
                        } else {
                            System.out.println("Unable to return book.");
                            System.out.println("Please ensure you have entered the correct book id and that your over due fees are paid before returning any books");   
                        }
                        break;

                    case 3:
                        memberId = getUsersMemberId();
                        double overDueCharge = library.getMembersOverDueCharges(memberId);

                        if (overDueCharge == 0.0) {
                            System.out.println("You have not over due charges.");
                        } else {
                            System.out.println("You own $" + overDueCharge);
                            System.out.println("Would you like to pay now?(yes/no)");
                            String payNowInput = SCANNER.nextLine();
                            boolean payNow = payNowInput.equalsIgnoreCase("yes");  
                            if (payNow) {
                                library.updateMembersLastPayment(memberId);
                                System.out.println("Over due fees paid.");
                            }
                        }
                        break;

                    case 4:
                        memberId = getUsersMemberId();
                        System.out.println(library.getMembersOverDueBooks(memberId));
                        break;

                    case 5:
                        memberId = getUsersMemberId();

                        System.out.print("Enter the book title to look up: ");
                        String bookTitle = SCANNER.nextLine();
                        String bookInfo = library.getBookInfo(bookTitle);
                        if (bookInfo == null) {
                            System.out.println("Invalid book Title.");
                            continue;
                        }

                        System.out.println(bookInfo);
                        break;

                    case 6:
                        System.out.println("Exiting the library system.");
                        SCANNER.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e){
                continue;
            }
        }
    }
}