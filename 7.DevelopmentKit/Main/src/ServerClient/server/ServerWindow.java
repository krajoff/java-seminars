package ServerClient.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POS_X = 300;
    private static final int POS_Y = 400;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnStart = new JButton("Start server");
    private final JButton btnStop = new JButton("Stop server");

    private JTextArea log = new JTextArea();
    private boolean isServerWorking;

    public ServerWindow() {
        isServerWorking = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(false);

        panelBottom.add(btnStart, BorderLayout.CENTER);
        panelBottom.add(btnStop, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);
        setVisible(true);

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    log.append("Server is not started\n");
                } else {
                    isServerWorking = false;
                    log.append("Server is stopped\n");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.append("Server is already running\n");
                } else {
                    isServerWorking = true;
                    log.append("Server is started\n");
                }
            }
        });
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void setLog(String log) {
        this.log.append(log);
    }
}
