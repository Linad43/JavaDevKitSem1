import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView{

    private static final int WIDTH = 400; //ширина
    private static final int HEIGHT = 500; //высота

    //кнопки
    private final JButton btnStart = new JButton("Старт");
    private final JButton btnStop = new JButton("Стоп");

    //окно логирования
    private final JTextArea log = new JTextArea();
    private ServerController serverController = new ServerController();
    public ServerGUI()
    {
        serverController.isSerwerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stopServer();
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.startServer();
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

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    @Override
    public void setLog(String text){
        log.append(text);
    }

    @Override
    public void printLog(String text) {
        new PrintLog(text,this);
    }

    @Override
    public void sendingMessage(String text) {
        serverController.sendingMessage(text);
    }
}
