import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ServerWindow extends JFrame {
    /*private static final int POS_X = 500;
    private static final int POS_Y = 200;*/
    private static final int WIDTH = 400; //ширина
    private static final int HEIGHT = 500; //высота
    //кнопки
    private final JButton btnStart = new JButton("Старт");
    private final JButton btnStop = new JButton("Стоп");
    private final JButton btnClear = new JButton("Очистить лог");
    //окно логирования
    protected static final JTextArea log = new JTextArea();

    /**
     * Флаг добавления клиента
     * @param client - класс окна клиента
     * @return - true/false добавлен не добавлен клиент
     */
    public boolean connectUser(Client client){
        if (!isSerwerWorking){
            return false;
        }
        clients.add(client);
        return true;
    }
    //Флаг работы сервера
    public static boolean isSerwerWorking;
    //Список подключенных клиентов
    public static ArrayList<Client> clients = new ArrayList<>();

    /**
     * Печать логирования, вывод на лог каждого клиента,
     * печать в файл
     * @param text - текст логирования
     * @throws IOException - ошибка при печати в файл
     */
    public static void PrintLog(String text) throws IOException {
        Date date = new Date();
        String logText = date + ":\n" + text + "\n";
        try (FileWriter fileLog = new FileWriter("log.txt", true)) {
            fileLog.write(logText);
        }
        log.append(logText);
        for (Client client:clients) {
            client.log.append(logText);
        }
    }

    public ServerWindow()
    {
        clients.clear();
        isSerwerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSerwerWorking)
                {
                    isSerwerWorking = false;
                    for (Client client:clients) {
                        client.setConnected(false);
                    }
                    //System.out.println("Сервер остановлен (" + isSerwerWorking + ")");
                    try {
                        PrintLog("Сервер остановлен (" + isSerwerWorking + ")\n");
                        clients.clear();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    //System.out.println("Ошибка, сервер не запущен (" + isSerwerWorking + ")");
                    try {
                        PrintLog("Ошибка, сервер не запущен (" + isSerwerWorking + ")\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSerwerWorking)
                {
                    //System.out.println("Ошибка, сервер работает (" + isSerwerWorking + ")");
                    try {
                        PrintLog("Ошибка, сервер работает (" + isSerwerWorking + ")\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    isSerwerWorking = true;
                    //System.out.println("Сервер запущен (" + isSerwerWorking + ")");
                    try {
                        PrintLog("Сервер запущен (" + isSerwerWorking + ")\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                log.removeAll();
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setTitle("Чат сервер");
        setAlwaysOnTop(true);
        //setLayout(new GridBagLayout());
        JPanel buttons = new JPanel();
        buttons.add(btnStart);
        buttons.add(btnStop);
        //buttons.add(btnClear);
        add(BorderLayout.CENTER, log);
        add(BorderLayout.SOUTH, buttons);
        //add(BorderLayout.SOUTH, btnStop);

        setVisible(true);
    }

}
