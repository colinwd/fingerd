package com.colinwd.fingerd.query;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

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
        return parse(tokenize(input));
    }

    private Query parse(Stack<String> tokens) {
        while (tokens.iterator().hasNext()) {
            String token = tokens.pop();

            if (Objects.equals(token, "/W")) {
                //Not implementing verbose mode for now
                continue;
            } else if (StringUtils.contains(token, "@")) {
                throw new UnsupportedOperationException("Finger forwarding service denied.");
            } else {
                return Query.user(token);
            }
        }

        return Query.list();
    }

    private Stack<String> tokenize(String input) {
        Stack<String> tokens = new Stack<>();
        List<String> split = Arrays.asList(input.split("\\s"));
        split.stream().forEach(s -> {
            if (StringUtils.isNotBlank(s)) {
                tokens.add(s);
            }
        });
        return tokens;
    }
}
