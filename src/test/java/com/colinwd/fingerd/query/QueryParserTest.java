package com.colinwd.fingerd.query;

import org.junit.Assert;
import org.junit.Test;

public class QueryParserTest {

    private static QueryParser parser = new QueryParser();

    @Test(expected = MalformedQueryException.class)
    public void inputMustBeTerminated() {
        parser.parse("");
    }

    @Test
    public void terminatedInputReturnsListQuery() {
        Query query = parser.parse("\r\n");
        Assert.assertTrue(query.isListQuery());
    }

    @Test
    public void inputWithUsernameReturnsUserQuery() {
        Query query = parser.parse("colin\r\n");
        Assert.assertTrue(query.getUsername().isPresent());
        Assert.assertEquals("colin", query.getUsername().get());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void inputWithHostnameIsUnsupported() {
        parser.parse("colin@hostname\r\n");
    }
}
