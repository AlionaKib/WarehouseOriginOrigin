package control;

import data.DaoException;
import data.ItemDao;
import model.Item;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


/**
 * Created by Алена on 04.12.2017.
 */
public class DeleteItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ItemDao itemDao = MainApp.getDao(ItemDao.class);

            Optional<Item> selectedItem = Panes.getSelectedItem(false);
            selectedItem.ifPresent(itemDao::deleteItem);

            Panes.ITEMS_LIST.setListData(itemDao.getAllItems().toArray());
            JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
