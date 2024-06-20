package ua.com.abakumov.kafkaswing.ui;

import ua.com.abakumov.kafkaswing.ConnectionSettings;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.function.Consumer;

public class DocListener implements DocumentListener {
    private final ConnectionSettings.UIModel uiModel;

    public DocListener(ConnectionSettings.UIModel uiModel) {
        this.uiModel = uiModel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        goUpdate(getAllText(e));
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        goUpdate(getAllText(e));
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        goUpdate(getAllText(e));
    }

    private void goUpdate(String newValue) {
        Consumer consumer = uiModel.getUpdater().get();
        consumer.accept(newValue);
    }

    private String getAllText(DocumentEvent e) {
        Document document = e.getDocument();
        try {
            return document.getText(0, document.getLength());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
