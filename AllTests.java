import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestBook.class, TestBookForBorrow.class, TestLibrarySystem.class, 
    TestLibraryMember.class, TestRating.class})

public class AllTests {}
