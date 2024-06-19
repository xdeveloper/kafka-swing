package ua.com.abakumov.kafkaswing;

import javax.swing.*;
import java.time.Instant;
import java.util.Set;
import java.util.function.Consumer;

public class UIBuilder {

    public static void buildDynamicFields(JPanel panel, ConnectionSettings connectionSettings) {
        Set<ConnectionSettings.UIModel> uiModel = connectionSettings.getUIModel();
        for (ConnectionSettings.UIModel next : uiModel) {
            ConnectionSettings.Type type = next.getType();
            JLabel label = new JLabel(next.getLabel());
            panel.add(label);

            // JText
            if (type == ConnectionSettings.Type.String) {
                String value = (String) next.getAccessor().get();
                JTextField text = new JTextField(value);
                text.setEditable(false);
                if (next.getUpdater().isPresent()) {
                    text.setEditable(true);
                    text.getDocument().addDocumentListener(new DocListener(next));
                }
                panel.add(text, "wrap");
            } else if (type == ConnectionSettings.Type.Boolean) { // JCheckBox
                boolean selected = (Boolean) next.getAccessor().get();
                var checkBox = new JCheckBox("", selected);
                checkBox.setEnabled(false);
                if (next.getUpdater().isPresent()) {
                    checkBox.setEnabled(true);
                    checkBox.addChangeListener((x) -> {
                        Consumer consumer = next.getUpdater().get();
                        consumer.accept(checkBox.isSelected());
                    });
                }
                panel.add(checkBox, "wrap");
            } else if (type == ConnectionSettings.Type.Instant) { // JLabel for Instant
                var instant = (Instant) next.getAccessor().get();
                if (next.getUpdater().isEmpty()) {
                    var instantLabel = new JLabel(instant.toString());
                    panel.add(instantLabel, "wrap");
                } else {
                    var text = new JTextField(instant.toString());
                    text.setEditable(false);
                    panel.add(text, "wrap");
                }
            } else if (type == ConnectionSettings.Type.Label) {
                var labelValue = (String) next.getAccessor().get();
                var justLabel = new JLabel(labelValue);
                panel.add(justLabel, "wrap");
            }
        }
    }
}