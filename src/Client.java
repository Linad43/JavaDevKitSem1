import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Client extends JFrame {
    private static final int WIDTH = 400; //ширина
    private static final int HEIGHT = 300; //высота
    protected final JTextArea log = new JTextArea();//лог

    //Верхняя панель с полями ввода и кнопкой подключения
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Linad");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    //Нижняя панель с набором текста и кнопкой отправки
    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    //Для работы с основным окном
    // private ServerWindow server;
    //Флаг полключенния
    private boolean connected = false;
    //private boolean isLogin = false;

    /**
     * Подключение к серверу
     * @throws IOException - от ServerWindow.PrintLog
     */
    private void connectToServer(ServerWindow server) throws IOException {
        if (server.connectUser(this) && !connected){
            ServerWindow.PrintLog("Клиент " + this.tfLogin.getText() + " успешно подключился!\n");
            connected = true;
        } else if (!server.connectUser(this)){
            log.append("Подключение не удалось\n");
        }else {
            log.append("Вы уже подключены\n");
        }
    }
    Client(ServerWindow server) throws IOException {
        /**
         * Нажатие на кнопку Login
         */
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectToServer(server);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * Нажатие на кнопку отправки
         */
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connected){
                    try {
                        ServerWindow.PrintLog(tfLogin.getText() + ": " +  tfMessage.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    log.append("Вы не подключены\n");
                }

            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH,HEIGHT);
        setTitle("ChatClient");

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBotton.add(tfMessage, BorderLayout.CENTER);
        panelBotton.add(btnSend, BorderLayout.EAST);
        add(panelBotton, BorderLayout.SOUTH);
        log.setEditable(false);
        JScrollPane scrol = new JScrollPane(log);
        add(scrol);
        setVisible(true);
    }


}
