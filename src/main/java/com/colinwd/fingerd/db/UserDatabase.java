package com.colinwd.fingerd.db;

import com.colinwd.fingerd.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    Map<String, User> userDb = new HashMap<>();

    public UserDatabase() {
        userDb = new DatabaseGenerator().generateDatabase();
    }

    public Collection<User> query(Query query) {
        if (query.isListQuery()) {
            return userDb.values();
        } else {
            Collection<User> results = new ArrayList<>();

            query.getUsername().ifPresent(u -> {
                if (userDb.containsKey(u)) {
                    results.add(userDb.get(u));
                }
            });

            return results;
        }
    }
}
