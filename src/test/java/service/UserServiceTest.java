package service;

import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserDAO userDAO;

    @Test
    public void testAddUser() {
        User user = new User("gregory_lammers@hotmail.com", "test");

        userService.createUser(user);

        Mockito.verify(userDAO, Mockito.times(1)).createUser(user);
    }

    @Test
    public void testFindAll() {
        userService.findAllUsers();

        Mockito.verify(userDAO, Mockito.times(1)).findAllUsers();
    }

    @Test
    public void testFindUser() {
        User user = new User("", "Gregga", "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", "test");
        userService.createUser(user);
        userService.findByName("Gregory");

        Mockito.verify(userDAO, Mockito.times(1)).findByName("Gregory");
    }

    @Test
    public void testAddFollow() {
        User user1 = new User("gregory_lammers@hotmail.com", "test");
        User user2 = new User("pietje@hotmail.com", "piet");

        userService.addFollow(user1, user2);

        Mockito.verify(userDAO, Mockito.times(1)).addFollow(user1, user2);
    }
}