import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PrintLog {
    private Date date = new Date();
    private String logText;
    //ServerController server;

    /*public PrintLog(String text, ServerController server){
        logText = date + ":\n" + text + "\n";
        try (FileWriter fileLog = new FileWriter("log.txt", true)) {
            fileLog.write(logText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.setLog(logText);
        server.sendingMessage(logText);
    }*/
    public PrintLog(String text, ServerView server){
        logText = date + ":\n" + text + "\n";
        try (FileWriter fileLog = new FileWriter("log.txt", true)) {
            fileLog.write(logText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.setLog(logText);
        server.sendingMessage(logText);
    }
/*
    public void setServer(ServerView server) {
        this.server = server;
    }*/
}
