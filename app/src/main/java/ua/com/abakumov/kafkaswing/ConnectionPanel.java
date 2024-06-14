package ua.com.abakumov.kafkaswing;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel extends JPanel {

    private final JFrame parentFrame;

    public ConnectionPanel(JFrame parentFrame, ConnectionsPanel connectionsPanel, String connectionId) {
        super(new BorderLayout(3,3));
        this.parentFrame = parentFrame;

        var name = new JLabel("Connection ID: " + connectionId);
        var connectTo = new JButton("Connect");

        // Configure
        var configure = new JButton(Utils.getImageIcon("/edit.png"));
        configure.setSize(40, 40);

        configure.setActionCommand(connectionId);
        configure.addActionListener((actionEvent) -> {
            var configureClusterPanel = new ConfigureConnectionPanel(parentFrame, connectionsPanel, actionEvent.getActionCommand());
            connectionsPanel.closeYouself();
            configureClusterPanel.drawYourself();
        });

        this.add(name, BorderLayout.WEST);
        this.add(connectTo, BorderLayout.EAST);
        this.add(configure, BorderLayout.NORTH);
    }

}
