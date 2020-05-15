package cft.focus.client.view;

import javax.swing.*;

public class NotificationMessagePane {
    public void sayNameIsBusy() {
        JOptionPane.showMessageDialog(null, "К сожалению, это имя занято", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }
}