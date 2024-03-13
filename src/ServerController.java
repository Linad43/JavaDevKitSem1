
import java.util.ArrayList;

public class ServerController {

    private ServerView serverView;
    //Флаг работы сервера
    protected boolean isSerwerWorking;


    //Список подключенных клиентов
    public ArrayList<ClientController> clients = new ArrayList<>();

    /**
     * Действия для запуска сервера
     */
    protected void startServer()
    {
        if (isSerwerWorking)
        {
            serverView.printLog("Ошибка, сервер работает (" + isSerwerWorking + ")\n");
        }
        else
        {
            isSerwerWorking = true;
            serverView.printLog("Сервер запущен (" + isSerwerWorking + ")\n");
        }
    }
    /**
     * Действия для остановки сервера
     */
    protected void stopServer()
    {
        if (isSerwerWorking)
        {
            isSerwerWorking = false;
            for (ClientController client:clients) {
                client.setConnected(false);
            }
            clients.clear();
            serverView.printLog("Сервер остановлен (" + isSerwerWorking + ")\n");
        }
        else
        {
            serverView.printLog("Ошибка, сервер не запущен (" + isSerwerWorking + ")\n");
        }
    }

    /**
     * Флаг добавления клиента
     * @param client - класс окна клиента
     * @return - true/false добавлен не добавлен клиент
     */
    public boolean connectUser(ClientController client){
        if (!isSerwerWorking){
            return false;
        }
        clients.add(client);
        return true;
    }

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }
    public void inMessage(String text){
        serverView.printLog(text);
    }
    public void sendingMessage(String text) {
        for (ClientController client:clients) {
            client.printMessage(text);
        }
    }
}
