import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerWindow extends JFrame {
    /*private static final int POS_X = 500;
    private static final int POS_Y = 200;*/
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;
    private final JButton btnStart = new JButton("Старт");
    private final JButton btnStop = new JButton("Стоп");
    private final JButton btnClear = new JButton("Очистить лог");
    protected final JTextArea log = new JTextArea();
    private boolean isSerwerWorking;
    public void PrintLog(String text) throws IOException {
        byte[] textInByte = text.getBytes();
        try(FileOutputStream fileLog = new FileOutputStream("log.txt")){
            fileLog.write(textInByte);
        }
        log.append(text);
    }
    public ServerWindow()
    {
        isSerwerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSerwerWorking)
                {
                    isSerwerWorking = false;
                    //System.out.println("Сервер остановлен (" + isSerwerWorking + ")");
                    try {
                        PrintLog("Сервер остановлен (" + isSerwerWorking + ")\n");
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
