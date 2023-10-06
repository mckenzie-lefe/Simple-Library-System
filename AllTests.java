import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestBook.class, TestLibrarySystem.class, TestLibraryMember.class, 
    TestStudentMember.class, TestTeacherMember.class})

public class AllTests {}
