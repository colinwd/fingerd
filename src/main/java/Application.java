import java.io.IOException;
import java.net.ServerSocket;

public class Application {

    private static final int PORT = 79;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
