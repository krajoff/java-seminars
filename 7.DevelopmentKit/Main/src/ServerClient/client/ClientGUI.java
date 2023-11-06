package ServerClient.client;

import ServerClient.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("ivan_igorevich");
    private final JPasswordField tfPassword = new JPasswordField("1234");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private ServerWindow serverWindow;
    private boolean isAuthorized;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        this.isAuthorized = false;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverWindow.isServerWorking()) {
                    log.append("Failed to connect to server\n");
                } else {
                    setAuthorized(true);
                    log.append("Succession connection\n");
                    serverWindow.setLog(tfLogin.getText() + " is connected\n");
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverWindow.isServerWorking()) {
                    log.append("Failed to connect to server\n");
                } else if (serverWindow.isServerWorking() && isAuthorized) {
                    serverWindow.setLog(tfLogin.getText() + ": " + tfMessage.getText() + "\n");
                    log.append("You: " + tfMessage.getText() + "\n");
                    tfMessage.setText("");
                } else {
                    log.append("It is necessary to authorize\n");
                }
            }
        });
    }

    public void setAuthorized(boolean authorized) {
        this.isAuthorized = authorized;
    }

}
