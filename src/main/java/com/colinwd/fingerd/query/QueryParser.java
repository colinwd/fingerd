package com.colinwd.fingerd.query;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*
The Finger query specification is defined:
    {Q1}    ::= [{W}|{W}{S}{U}]{C}
    {Q2}    ::= [{W}{S}][{U}]{H}{C}
    {U}     ::= username
    {H}     ::= @hostname | @hostname{H}
    {W}     ::= /W
    {S}     ::= <SP> | <SP>{S}
    {C}     ::= <CRLF>
 */
public class QueryParser {
    public Query parse(String input) {
        if (StringUtils.isEmpty(input) || !StringUtils.endsWith(input, "\r\n")) {
            throw new MalformedQueryException("Empty query is not accepted.");
        }

        if (StringUtils.equals(input, "\r\n")) {
            return Query.list();
        }

        List<String> tokens = tokenize(input);
        if (tokens.isEmpty()) {
            throw new MalformedQueryException(("Unable to parse input: " + input));
        } else {
            return parse(tokens);
        }
    }

    private Query parse(List<String> tokens) {
        for (String token : tokens) {
            if (Objects.equals(token, "/W")) {
                //Not implementing verbose mode for now
                continue;
            } else if (StringUtils.contains(token, "@")) {
                throw new UnsupportedOperationException("Finger forwarding service denied.");
            } else {
                return Query.user(token);
            }
        }

        //If we fall through here, something's gone wrong
        throw new MalformedQueryException("Unable to parse input.");
    }

    private List<String> tokenize(String input) {
        return Arrays.asList(input.split("\\s"));
    }
}
