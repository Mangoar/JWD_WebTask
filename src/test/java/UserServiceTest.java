import com.example.WebAppPayments.exception.ServiceException;
import com.example.WebAppPayments.model.service.impl.UserServiceImpl;
import com.example.WebAppPayments.utility.validator.DateValidator;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Test
    public void testUniqueLogin() throws ServiceException {
        String login = "admin";
        Assert.assertFalse(userService.isLoginUnique(login));
    }

}
