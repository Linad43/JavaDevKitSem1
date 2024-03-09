import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class PrintLog extends ServerWindow{
    public PrintLog(String text) throws IOException {
        try(FileWriter fileLog = new FileWriter("log.txt")){
            fileLog.write(text);
        }
        log.append(text);
    }
}
