package com.colinwd.fingerd;

import com.colinwd.fingerd.db.User;
import com.colinwd.fingerd.db.UserDatabase;
import com.colinwd.fingerd.query.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResponseFormatterTest {

    private static ResponseFormatter responseFormatter = new ResponseFormatter();

    @Test
    public void testFormatSingleUserResponse() {
        Collection<User> user = new ArrayList<>();
        user.add(new User("Colin Davis", "cdavis", "me@colinwd.com"));
        List<String> formatted = responseFormatter.format(user);
        assertFirstLineIsHeader(formatted);
        assertSecondLineIsUser(formatted);
    }

    @Test
    public void testFormatMultiUserResponse() {
        Collection<User> users = new UserDatabase().query(Query.list());
        List<String> formatted = responseFormatter.format(users);
        Assert.assertEquals(users.size() + 1, formatted.size());
        assertFirstLineIsHeader(formatted);
    }

    private void assertFirstLineIsHeader(List<String> format) {
        String line = format.get(0);
        Assert.assertEquals("Full Name\tUsername\tE-mail", line);
    }

    private void assertSecondLineIsUser(List<String> format) {
        String line = format.get(1);
        Assert.assertEquals("Colin Davis\tcdavis\tme@colinwd.com", line);
    }
}
