import com.example.WebAppPayments.utility.validator.DateValidator;
import com.example.WebAppPayments.utility.validator.UserValidator;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ValidatorTest {

    @Test
    public void testDateValidatorT1() {
        String yesterday;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        yesterday = sdf.format(cal.getTime());
        Assert.assertTrue(DateValidator.isAfterToday(yesterday));
    }

    @Test
    public void testDateValidatorT2() {
        String yesterday;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        yesterday = sdf.format(cal.getTime());
        Assert.assertFalse(DateValidator.isAfterToday(yesterday));
    }

    @Test
    public void testUserValidatorT1() {
        String login = "goodLogin";
        Assert.assertTrue(UserValidator.isLoginValid(login));
    }

    @Test
    public void testUserValidatorT2() {
        String login = "$*@#BDS@1";
        Assert.assertFalse(UserValidator.isLoginValid(login));
    }

    @Test
    public void testUserValidatorT3() {
        String password = "qwerty123";
        Assert.assertTrue(UserValidator.isPasswordValid(password));
    }

    @Test
    public void testUserValidatorT4() {
        String password = "123";
        Assert.assertFalse(UserValidator.isPasswordValid(password));
    }

    @Test
    public void testUserValidatorT5() {
        String mail = "qwerty@gmail.com";
        Assert.assertTrue(UserValidator.isEmailValid(mail));
    }

    @Test
    public void testUserValidatorT6() {
        String mail = "qwertygmailcom";
        Assert.assertFalse(UserValidator.isEmailValid(mail));
    }

    @Test
    public void testUserValidatorT7() {
        String goodDate;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        goodDate = sdf.format(cal.getTime());
        Assert.assertTrue(UserValidator.isBirthDateValid(goodDate));
    }

    @Test
    public void testUserValidatorT8() {
        String badDate;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        badDate = sdf.format(cal.getTime());
        Assert.assertFalse(UserValidator.isBirthDateValid(badDate));
    }
}
