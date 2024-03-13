import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //создание объектов сервера и создание связи между ними
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController();
        serverGUI.setServerController(serverController);
        serverController.setServerView(serverGUI);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClientController(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClientController(clientController2);
        clientController2.setServer(serverController);


    }
}