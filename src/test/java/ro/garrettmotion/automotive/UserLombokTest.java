package ro.garrettmotion.automotive;


import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserLombokTest {

    @Test
    public void givenAnnotatedUser_thenHasGettersAndSetters() {
        User user = new User();
        user.setFirstName("Test");
        assertEquals(user.gerFirstName(), "Test");
    }

    @Getter @Setter
    public class User {
        private String firstName;
    }
}
