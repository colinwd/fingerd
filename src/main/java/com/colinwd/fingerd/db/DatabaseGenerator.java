package com.colinwd.fingerd.db;

import java.util.HashMap;
import java.util.Map;

public class DatabaseGenerator {

    public Map<String, User> generateDatabase() {
        Map<String, User> userDb = new HashMap<>();

        userDb.put("dcooper", new User("Dale Cooper", "dcooper", "dcooper@fbi.gov"));
        userDb.put("ahorne", new User("Audrey Horne", "ahorne", "ahorne@greatnorthern.com"));
        userDb.put("bhorne", new User("Benjamin Horne", "bhorne", "bhorne@greatnorthern.com"));
        userDb.put("htruman", new User("Sheriff Harry S. Truman", "htruman", "htruman@twinpeaks.wa.us"));

        return userDb;
    }
}
