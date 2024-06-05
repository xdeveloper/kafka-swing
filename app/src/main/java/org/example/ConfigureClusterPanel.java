package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigureClusterPanel extends JPanel {

    public ConfigureClusterPanel(String clusterId) {
        super(new BorderLayout(3,3));

        var clusterName = new JLabel("Configure cluster: "+ clusterId);

        this.add(clusterName, BorderLayout.WEST);
    }
}
