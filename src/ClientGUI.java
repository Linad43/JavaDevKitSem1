import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400; //ширина
    private static final int HEIGHT = 300; //высота

    private final JTextArea log = new JTextArea();//лог

    //Верхняя панель с полями ввода и кнопкой подключения
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    public final JTextField tfLogin = new JTextField("Linad");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    //Нижняя панель с набором текста и кнопкой отправки
    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    ClientController clientController = new ClientController();

    public ClientGUI() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.login(tfLogin.getText());
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.send(tfLogin.getText(),tfMessage.getText());
                tfMessage.setText("");
            }
        });
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    clientController.send(tfLogin.getText(),tfMessage.getText());
                    tfMessage.setText("");
                }
            }
        });
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    public void setClientController(ClientController clientController){
        this.clientController = clientController;
    }
    @Override
    public void printMessage(String text) {
        log.append(text);
    }
}
