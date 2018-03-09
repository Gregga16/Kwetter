package model;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testGetters() {
        String picture = "none";
        String userName = "Gregga";
        String firstName = "Gregory";
        String lastName = "Lammers";
        String location = "Helmond";
        String web = "www.test.com";
        String bio = "24 jaar oud";
        String email = "gregory_lammers@hotmail.com";
        String password = "test";

        User user = new User(picture, userName, firstName, lastName, location, web, bio, email, password);

        assertEquals(user.getPicture(), picture);
        assertEquals(user.getUserName(), userName);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getLastName(), lastName);
        assertEquals(user.getLocation(), location);
        assertEquals(user.getWeb(), web);
        assertEquals(user.getBio(), bio);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void testGetKweets() {
        User user = new User("", "Gregga", "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", "test");

        Set<Kweet> expResult = new HashSet<>();
        Set<Kweet> result = user.getKweets();

        assertEquals(expResult, result);

        user.addKweet("test");
        expResult.add(new Kweet("test", user));

        assertEquals(user.getKweets().size(), expResult.size());

    }

    @Test
    public void testFollow() {
        User user1 = new User("greg", "test");
        User user2 = new User("piet", "piet");

        boolean result = user1.follow(user2);
        assertTrue(result);

        User followingUser = user1.getFollowing().iterator().next();
        assertEquals(user2, followingUser);

        User followedUser = user2.getFollowers().iterator().next();
        assertEquals(user1, followedUser);

        boolean result1 = user1.follow(user1);
        assertFalse(result1);
    }

    @Test
    public void testUnfollow() {
        User user1 = new User("greg", "test");
        User user2 = new User("piet", "piet");

        user1.follow(user2);
        boolean unfollowresult = user1.unfollow(user2);
        assertTrue(unfollowresult);

        int folllwing = user1.getFollowing().size();
        assertEquals(0, folllwing);
    }
}
