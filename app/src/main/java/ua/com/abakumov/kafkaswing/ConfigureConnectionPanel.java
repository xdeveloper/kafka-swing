package ua.com.abakumov.kafkaswing;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ConfigureConnectionPanel extends JPanel implements SelfDrawable, SelfClosable {

    private final JFrame parentFrame;
    private final ConnectionsPanel connectionsPanel;
    private final String connectionId;
    private final ConnectionSettings connectionSettings;

    public ConfigureConnectionPanel(JFrame parentFrame, ConnectionsPanel connectionsPanel, String connectionId) {
        this.parentFrame = parentFrame;
        this.connectionsPanel = connectionsPanel;
        this.connectionId = connectionId;

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
        var nameLabel = new JLabel("Configure connection");
        var nameField = new JTextField(connectionSettings.getId());

        var descriptionLabel = new JLabel("Description");
        var descriptionField = new JTextField(connectionSettings.getDescription());

        var noteLabel = new JLabel("Note");
        var noteField = new JTextField(connectionSettings.getNote());

        var datesLabel = new JLabel("Created / updated");
        var datesField = new JTextField(connectionSettings.getCreated().toString() + connectionSettings.getLastUpdated().toString());
        datesField.setEditable(false);

        connectionEditingPanel.add(nameLabel);
        connectionEditingPanel.add(nameField, "wrap");
        connectionEditingPanel.add(descriptionLabel);
        connectionEditingPanel.add(descriptionField, "wrap");
        connectionEditingPanel.add(noteLabel);
        connectionEditingPanel.add(noteField, "wrap");
        connectionEditingPanel.add(datesLabel);
        connectionEditingPanel.add(datesField, "wrap");


        wrapperPanel.add(new JLabel("Editing"), BorderLayout.NORTH);
        wrapperPanel.add(connectionEditingPanel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel buildToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());
        JButton addConnectionButton = new JButton("<");
        addConnectionButton.addActionListener((actionEvent) -> {
            this.closeYouself();
            connectionsPanel.drawYourself();
        });
        toolsPanel.add(addConnectionButton);
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
