package ua.com.abakumov.kafkaswing;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.miginfocom.swing.MigLayout;
import ua.com.abakumov.kafkaswing.connections.ConnectionSettingsManager;
import ua.com.abakumov.kafkaswing.interfaces.SelfClosable;
import ua.com.abakumov.kafkaswing.interfaces.SelfDrawable;
import ua.com.abakumov.kafkaswing.ui.UIBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UseConnectionPanel  extends JPanel implements SelfDrawable, SelfClosable {
    private final JFrame parentFrame;
    private final ConnectionsPanel connectionsPanel;
    private final EventBus eventBus;

    private JButton enterTopicButton;
    private TopicSelectedEvent selectedTopic;

    public UseConnectionPanel(JFrame parentFrame, ConnectionsPanel connectionsPanel, String connectionId) {
        eventBus = EventBusSingleton.getEventBus();
        eventBus.register(this);

        this.parentFrame = parentFrame;
        this.connectionsPanel = connectionsPanel;

        ConnectionSettingsManager connectionSettingsManager = new ConnectionSettingsManager();
        ConnectionSettings connectionSettings = connectionSettingsManager.getConnectionSettings(connectionId);

        this.setLayout(new BorderLayout());
        JPanel toolsPanel = buildToolsPanel();
        JPanel editingPanel = buildEditingPanel();
        this.add(toolsPanel, BorderLayout.WEST);
        this.add(editingPanel, BorderLayout.EAST);
    }

    private JPanel buildEditingPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        JPanel topicsPanel = new JPanel();
        var layout = new MigLayout();
        topicsPanel.setLayout(layout);

        /*// Topic tabbed pane
        JTabbedPane topicsTabbedPane = new JTabbedPane();

        // List topics
        JPanel listTopics = new JPanel();
        listTopics.add(new JLabel("This is Tab 1"));
        listTopics.add(new JButton("Button 1"));
        topicsTabbedPane.addTab("Topics", listTopics);

        topicsPanel.add(topicsTabbedPane, "wrap");*/

        JPanel topicToolsPanel = buildTopicToolsPanel();

        topicsPanel.add(topicToolsPanel, "wrap");

        String[] columnNames = {"ID", "Name", "Created"};
        Object[][] data = {
                {1, "Topic 1", "12 June 2024, 2:11PM EET"},
                {2, "Topic 2", "4 June 2024, 8:38AM EET"},
                {3, "Topic 3", "3 May 2022, 5:32PM EET"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                eventBus.post(new TopicSelectedEvent((int)data[row][0], (String)data[row][1]));
            }
        });
        JScrollPane tableScrollPane = new JScrollPane(table);
        topicsPanel.add(tableScrollPane, "wrap");

        wrapperPanel.add(topicsPanel);
        return wrapperPanel;
    }

    private JPanel buildTopicToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);

        // Enter
        enterTopicButton = new JButton(Utils.getImageIcon("/enter.png"));
        enterTopicButton.setEnabled(false);
        enterTopicButton.addActionListener((actionEvent) -> {
            //this.closeYouself();
            //connectionsPanel.drawYourself();
            eventBus.post(new TopicEnteredEvent(selectedTopic.getName()));
        });
        toolBar.add(enterTopicButton);
        toolsPanel.add(toolBar);
        return toolsPanel;
    }

    private JPanel buildToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        // Back
        var backButton = new JButton(Utils.getImageIcon("/back.png"));
        backButton.addActionListener((actionEvent) -> {
            this.closeYouself();
            connectionsPanel.drawYourself();
        });

        // Topics
        JButton topicsButton = new JButton(Utils.getImageIcon("/topic.png"));
        topicsButton.addActionListener((actionEvent) -> {
        });

        // Consumers
        JButton consumersButton = new JButton(Utils.getImageIcon("/consumer.png"));
        consumersButton.addActionListener((actionEvent) -> {
        });

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

    @Subscribe
    public void remember(TopicSelectedEvent e) {
        this.selectedTopic = e;
    }

    @Subscribe
    public void topicSelected(TopicSelectedEvent e) {
        enterTopicButton.setEnabled(true);
    }
}
