package org.example;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel extends JPanel {

    public ConnectionPanel(String clusterId) {
        super(new BorderLayout(3,3));


        var name = new JLabel("Connection ID: " + clusterId);
        var connectTo = new JButton("Connect");
        var configure = new JButton("Configure");
        configure.setActionCommand(clusterId);
        configure.addActionListener((l) -> {
            System.out.println("!!!");
            var ccp = new ConfigureClusterPanel(l.getActionCommand());
            ccp.setVisible(true);


            ConnectionPanel.this.setVisible(false);
            //ConnectionPanel.this.setContentPane(newContents());
            //frame.revalidate(); // frame.pack(

        });

        this.add(name, BorderLayout.WEST);
        this.add(connectTo, BorderLayout.EAST);
        this.add(configure, BorderLayout.NORTH);
    }
}
