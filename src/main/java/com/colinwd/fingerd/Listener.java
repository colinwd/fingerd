package com.colinwd.fingerd;

import com.colinwd.fingerd.db.User;
import com.colinwd.fingerd.db.UserDatabase;
import com.colinwd.fingerd.query.MalformedQueryException;
import com.colinwd.fingerd.query.Query;
import com.colinwd.fingerd.query.QueryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

class Listener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private UserDatabase userDb = new UserDatabase();
    private QueryParser queryParser = new QueryParser();
    private ResponseFormatter responseFormatter = new ResponseFormatter();

    private ServerSocket serverSocket;

    Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    void listen() {
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII))) {

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                try {
                    Query query = queryParser.parse(inputLine);
                    Collection<User> users = userDb.query(query);
                    List<String> response = responseFormatter.format(users);
                    response.stream().forEach(out::println);
                } catch (MalformedQueryException e) {
                    out.println("Invalid input: " + inputLine);
                    break;
                } catch (UnsupportedOperationException e) {
                    out.println("Hostname queries are not supported by this finger server.");
                    break;
                }
            }
        } catch (IOException e) {
            log.warn("Error establishing communication with client.", e);

        }
    }
}
