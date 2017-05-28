package com.colinwd.fingerd.db;

import com.colinwd.fingerd.query.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class UserDatabaseTest {

    private static UserDatabase userDb = new UserDatabase();

    @Test
    public void testLookupByUsername() {
        Collection<User> results = userDb.query(Query.user("dcooper"));

        Assert.assertTrue(results.size() == 1);

        User dcooper = results.iterator().next();
        Assert.assertEquals("Dale Cooper", dcooper.fullName);
        Assert.assertEquals("dcooper", dcooper.username);
        Assert.assertEquals("dcooper@fbi.gov", dcooper.email);
    }

    @Test
    public void testListQuery() {
        Collection<User> results = userDb.query(Query.list());
        Assert.assertTrue(results.size() > 1);
    }
}
