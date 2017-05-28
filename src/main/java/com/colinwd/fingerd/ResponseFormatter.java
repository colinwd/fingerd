package com.colinwd.fingerd;

import com.colinwd.fingerd.db.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseFormatter {

    public List<String> format(Collection<User> users) {
        List<String> outputLines = new ArrayList<>();
        outputLines.add(header());
        outputLines.addAll(users.stream().map(this::format).collect(Collectors.toList()));
        return outputLines;
    }

    private String header() {
        return "Full Name\tUsername\tE-mail";
    }

    private String format(User user) {
        return user.fullName + "\t" + user.username + "\t" + user.email;
    }
}
