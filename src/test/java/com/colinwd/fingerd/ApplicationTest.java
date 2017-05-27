package com.colinwd.fingerd;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationTest {

    @BeforeClass
    public static void beforeClass() {
        startServerOnNewThread();
    }

    @Test
    public void testConnection() {
        try (Socket socket = new Socket("localhost", Application.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("Boop!");
            Optional<String> inputLine;
            while ((inputLine = Optional.ofNullable(in.readLine())).isPresent()) {
                Assert.assertEquals("Howdy", inputLine.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    private static void startServerOnNewThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> Application.main(new String[]{}));
    }
}
