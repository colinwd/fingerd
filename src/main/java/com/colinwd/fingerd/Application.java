package com.colinwd.fingerd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    //Modified port number from RFC to avoid root permissions requirement on ports < 1024
    private static final int PORT = 8079;

    public static void main(String[] args) {
        configureLogging();

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                new Listener(serverSocket).listen();

            } catch (IOException e) {
                log.error("Error establishing service on port " + PORT + ". Exiting.", e);
                System.exit(1);
            } catch (IllegalArgumentException e) {
                log.error("Port " + PORT + " is invalid. Port must be between 0-65535.", e);
                System.exit(1);
            }
        }
    }

    private static void configureLogging() {
        System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
    }
}
