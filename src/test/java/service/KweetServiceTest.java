package service;

import com.gregory.kwetter.dao.KweetDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.KweetService;
import com.gregory.kwetter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KweetServiceTest {

    @InjectMocks
    private KweetService kweetService;

    @Mock
    KweetDAO kweetDAO;

    @Test
    public void testAddKweet() {
        User user = new User("gregory_lammers@hotmail.com", "test");
        Kweet kweet = new Kweet("dit is test kweet", user);
        kweetService.addKweet(kweet);

        Mockito.verify(kweetDAO, Mockito.times(1)).addKweet(kweet.getMessage(), kweet.getUser());
    }

    @Test
    public void testFindAll() {
        kweetService.findAllKweets();

        Mockito.verify(kweetDAO, Mockito.times(1)).findAllKweets();
    }
}
