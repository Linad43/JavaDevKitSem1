import java.io.FileOutputStream;
import java.io.IOException;


public class PrintLog extends ServerWindow{
    public PrintLog(String text) throws IOException {
        byte[] textInByte = text.getBytes();
        try(FileOutputStream fileLog = new FileOutputStream("log.txt")){
            fileLog.write(textInByte);
        }
        log.append(text);
    }
}
