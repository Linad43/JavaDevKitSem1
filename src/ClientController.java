import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ClientController{

    private ClientView clientView;
    private ServerController server;
    // Флаг полключенния
    private boolean connected = false;

    /**
     * Сеттер
     * @param server
     */
    public void setServer(ServerController server) {
        this.server = server;
    }
    /**
     * Действия на кнопку логин
     */
    public void login(String client){
        try {
            connectToServer(client);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Действия на кнопку отправить
     */
    public void send(String client, String text){
        if (connected){
            server.inMessage(client + ": " +  text);
        }else {
            clientView.printMessage("Вы не подключены\n");
        }
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Подключение к серверу
     * @throws IOException - от ServerWindow.printLog
     */
    private void connectToServer(String client) throws IOException {
        if (server.connectUser(this) && !connected){
            server.inMessage("Клиент " + client + " подключен к серверу");
            connected = true;
        } else if (!server.connectUser(this)) {
            clientView.printMessage("Подключение не удалось\n");
        } else {
            clientView.printMessage("Вы уже подключены\n");
        }
    }
    public void printMessage(String text) {
        clientView.printMessage(text);
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }
}
