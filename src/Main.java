import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerWindow server = new ServerWindow();
        new Client(server);
        new Client(server);
    }
}