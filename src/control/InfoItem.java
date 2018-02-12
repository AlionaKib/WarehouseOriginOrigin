package control;

import data.DaoException;
import model.Item;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Created by Алена on 04.12.2017.
 */
public class InfoItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Optional<Item> selectedItem = Panes.getSelectedItem(true);
            selectedItem.ifPresent(item -> JOptionPane.showMessageDialog(null, item.info(), "Информация о снаряжении", JOptionPane.PLAIN_MESSAGE));
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
