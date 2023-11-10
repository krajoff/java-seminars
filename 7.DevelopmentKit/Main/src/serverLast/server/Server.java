package serverLast.server;
import serverLast.client.Client;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Server {
    boolean work;
    private ServerView serverView;
    public static final String LOG_PATH = "src/serverLast/server/log.txt";
    List<Client> clientList;

    public Server(ServerView serverView, List<Client> clientList) {
        this.serverView = serverView;
        this.clientList = clientList;
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    public String getHistory() {
        return readLog();
    }

    public void sendMessage(String text) {
        if (!work) {
            return;
        }
        answerAll(text);
        saveInLog(text);
        serverView.sendMessage(text);
    }

    private void answerAll(String text) {
        for (Client client : clientList) {
            client.serverAnswer(text);
        }
    }

    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
