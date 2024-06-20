package ua.com.abakumov.kafkaswing;

import ua.com.abakumov.kafkaswing.connections.ConnectionSettingsManager;
import ua.com.abakumov.kafkaswing.interfaces.SelfClosable;
import ua.com.abakumov.kafkaswing.interfaces.SelfDrawable;

import javax.swing.*;
import java.awt.*;

public class UseConnectionPanel  extends JPanel implements SelfDrawable, SelfClosable {
    private final JFrame parentFrame;
    private final ConnectionsPanel connectionsPanel;

    public UseConnectionPanel(JFrame parentFrame, ConnectionsPanel connectionsPanel, String connectionId) {
        this.parentFrame = parentFrame;
        this.connectionsPanel = connectionsPanel;

        ConnectionSettingsManager connectionSettingsManager = new ConnectionSettingsManager();
        ConnectionSettings connectionSettings = connectionSettingsManager.getConnectionSettings(connectionId);

        this.setLayout(new BorderLayout());
        JPanel toolsPanel = buildToolsPanel();
        //JPanel editingPanel = buildEditingPanel();
        this.add(toolsPanel, BorderLayout.WEST);
        //this.add(editingPanel, BorderLayout.EAST);
    }

    private JPanel buildToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        // Back
        ImageIcon imageIcon = Utils.getImageIcon("/back.png");
        var backButton = new JButton(imageIcon);
        //backButton.setPreferredSize(buttonSize);
        backButton.addActionListener((actionEvent) -> {
            this.closeYouself();
            connectionsPanel.drawYourself();
        });
        JButton topicsButton = new JButton("Topics");
        JButton consumersButton = new JButton("Consumers");

        toolBar.add(backButton);
        toolBar.add(topicsButton);
        toolBar.add(consumersButton);

        toolsPanel.add(toolBar);

        return toolsPanel;
    }

    @Override
    public void drawYourself() {
        parentFrame.getContentPane().add(this);
        parentFrame.setVisible(true);
    }

    @Override
    public void closeYouself() {
        parentFrame.remove(this);
        parentFrame.invalidate();
    }
}
