public interface ServerView {
    void sendingMessage(String text);

    void setLog(String logText);
    void printLog(String text);
}
