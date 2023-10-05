import java.util.Scanner;

public class LibraryUI {
    private static final Scanner SCANNER = new Scanner(System.in);
 
    public static void main(String[] args) {
        String memberId;
        int bookId;
        LibrarySystem library = new LibrarySystem();
        library.addLibraryMember("123", new LibraryMember("123", "John Student", "555-555-5555", 0));
        library.addLibraryMember("456", new LibraryMember("456", "Jane Teacher", "555-555-5556", 1));
        BookForBorrow book1 = new BookForBorrow(1, "Book 1", "Author 1", "Description 1");
        library.addBook(1, book1);
        BookForBorrow book2 = new BookForBorrow (2, "Book 2", "Author 2", "Description 2");
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

            switch (choice) {
                case 1:
                    System.out.print("Enter your library card id: ");
                    memberId = SCANNER.nextLine();
                    if (!library.isLibraryMember(memberId)) {
                        System.out.println("Invalid library card id.");
                        continue;
                    }

                    System.out.print("Enter the book id to check out: ");
                    bookId = SCANNER.nextInt();
                    SCANNER.nextLine();
                    if (!library.isLibraryBook(bookId)) {
                        System.out.println("Invalid book id.");
                        continue;
                    }

                    if (library.checkOutBook(memberId, bookId)) {
                        System.out.println("Book successfully checked out.");
                    } else {
                        System.out.println("The book is already checked out.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your library card id: ");
                    memberId = SCANNER.nextLine();
                    if (!library.isLibraryMember(memberId)) {
                        System.out.println("Invalid library card id.");
                        continue;
                    }

                    System.out.print("Enter the book id to return: ");
                    bookId = SCANNER.nextInt();
                    SCANNER.nextLine();
                    if (!library.isLibraryBook(bookId)) {
                        System.out.println("Invalid book id.");
                        continue;
                    }

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
                    System.out.print("Enter your library card id: ");
                    memberId = SCANNER.nextLine();
                    if (library.isLibraryMember(memberId)) {
                        System.out.println("Invalid library card id.");
                        continue;
                    }

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
                    System.out.print("Enter your library card id: ");
                    memberId = SCANNER.nextLine();

                    if (library.isLibraryMember(memberId)) {
                        System.out.println("Invalid library card id.");
                        continue;
                    }

                    System.out.println(library.getMembersOverDueBooks(memberId));
                    break;

                case 5:
                    System.out.print("Enter your library card id: ");
                    memberId = SCANNER.nextLine();

                    if (library.isLibraryMember(memberId)) {
                        System.out.println("Invalid library card id.");
                        continue;
                    }

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
        }
    }
}
