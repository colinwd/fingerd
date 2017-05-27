import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Listener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private ServerSocket serverSocket;

    Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    void listen() {
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String response = "Howdy";
                out.println(response);
                break;
            }
        } catch (IOException e) {
            log.warn("Error establishing communication with client.", e);

        }
    }
}
