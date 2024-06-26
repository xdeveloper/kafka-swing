package ua.com.abakumov.kafkaswing;


import net.miginfocom.swing.MigLayout;
import ua.com.abakumov.kafkaswing.connections.ConnectionSettingsManager;
import ua.com.abakumov.kafkaswing.interfaces.SelfClosable;
import ua.com.abakumov.kafkaswing.interfaces.SelfDrawable;
import ua.com.abakumov.kafkaswing.ui.UIBuilder;

import javax.swing.*;
import java.awt.*;

public class ConfigureConnectionPanel extends JPanel implements SelfDrawable, SelfClosable {

    private final JFrame parentFrame;
    private final ConnectionsPanel connectionsPanel;
    private final ConnectionSettings connectionSettings;

    public ConfigureConnectionPanel(JFrame parentFrame, ConnectionsPanel connectionsPanel, String connectionId) {
        this.parentFrame = parentFrame;
        this.connectionsPanel = connectionsPanel;

        ConnectionSettingsManager connectionSettingsManager = new ConnectionSettingsManager();
        connectionSettings = connectionSettingsManager.getConnectionSettings(connectionId);

        this.setLayout(new BorderLayout());
        JPanel toolsPanel = buildToolsPanel();
        JPanel editingPanel = buildEditingPanel();
        this.add(toolsPanel, BorderLayout.WEST);
        this.add(editingPanel, BorderLayout.EAST);
    }

    private JPanel buildEditingPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        JPanel connectionEditingPanel = new JPanel();
        var layout = new MigLayout();
        connectionEditingPanel.setLayout(layout);
        UIBuilder.buildDynamicFields(connectionEditingPanel, connectionSettings);
        wrapperPanel.add(new JLabel("Editing"), BorderLayout.NORTH);
        wrapperPanel.add(connectionEditingPanel, BorderLayout.CENTER);

        var saveButton = new JButton("Save");
        saveButton.addActionListener((x) -> {
            System.out.println(connectionSettings);
        });
        connectionEditingPanel.add(saveButton, "wrap");
        return wrapperPanel;
    }

    private JPanel buildToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);
        // Back
        ImageIcon imageIcon = Utils.getImageIcon("/back.png");
        var backButton = new JButton(imageIcon);
        backButton.addActionListener((actionEvent) -> {
            this.closeYouself();
            connectionsPanel.drawYourself();
        });
        toolBar.add(backButton);
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
