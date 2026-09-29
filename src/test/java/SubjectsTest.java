import org.example.Subjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubjectsTest {
    Subjects s = new Subjects("math", "Ilon_Mask");
    @Test
    void getSubjectReturnsConstructorValue() {
        Assertions.assertEquals("math", s.getSubject());
    }
    @Test
    void getAuthorReturnsConstructorValue() {
        Assertions.assertEquals("Ilon_Mask", s.getAuthor());
    }
}
