package org.example;

import javax.swing.*;
import java.awt.*;

public class ConnectionsPanel extends JPanel {

    public ConnectionsPanel() {

        super(new BorderLayout(10,10));

        var cp1 = new ConnectionPanel("cluster-1");
        var cp2 = new ConnectionPanel("cluster-2");

        this.add(cp1, BorderLayout.WEST);
        this.add(cp2, BorderLayout.EAST);
    }





}
