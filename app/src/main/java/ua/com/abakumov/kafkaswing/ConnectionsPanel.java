package ua.com.abakumov.kafkaswing;

import javax.swing.*;
import java.awt.*;

public class ConnectionsPanel extends JPanel implements SelfClosable, SelfDrawable {

    private final JFrame parentFrame;

    public ConnectionsPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setLayout(new BorderLayout());
        JPanel toolsPanel = buildToolsPanel();
        JPanel connectionsListPanel = buildConnectionsListPanel(parentFrame);
        this.add(toolsPanel, BorderLayout.WEST);
        this.add(connectionsListPanel, BorderLayout.EAST);
    }

    private JPanel buildToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());
        JButton addConnectionButton = new JButton("+");
        toolsPanel.add(addConnectionButton);
        return toolsPanel;
    }

    private JPanel buildConnectionsListPanel(JFrame parentFrame) {
        JPanel connectionsListPanel = new JPanel();
        var cp1 = new ConnectionPanel(parentFrame, this, "conn-1");
        var cp2 = new ConnectionPanel(parentFrame, this, "conn-2");
        connectionsListPanel.add(cp1);
        connectionsListPanel.add(cp2);
        return connectionsListPanel;
    }

    @Override
    public void closeYouself() {
        parentFrame.remove(this);
    }

    @Override
    public void drawYourself() {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(this);
        parentFrame.invalidate();
        parentFrame.repaint();
    }
}
