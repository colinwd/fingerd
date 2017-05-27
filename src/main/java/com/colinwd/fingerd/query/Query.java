package com.colinwd.fingerd.query;

import java.util.Optional;

public class Query {
    private String username;
    private boolean isListQuery;

    private Query(String username) {
        this.username = username;
        this.isListQuery = false;
    }

    private Query() {
        this.isListQuery = true;
    }

    public static Query user(String username) {
        return new Query(username);
    }

    public static Query list() {
        return new Query();
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(this.username);
    }

    public boolean isListQuery() {
        return this.isListQuery;
    }
}
