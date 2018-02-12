package control;

import data.DaoException;
import data.ItemDao;
import data.PersonDao;
import model.Item;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Panes.ITEMS_AT_PERSONE;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeleteItemAtPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ItemDao itemDao = MainApp.getDao(ItemDao.class);
            PersonDao personDao = MainApp.getDao(PersonDao.class);

            Item updateItem = Panes.getSelectedItemNN(true);
            updateItem.setPersonId(null);
            itemDao.updateItem(updateItem);
            ITEMS_AT_PERSONE.setListData(personDao.getAllDependentItems(Panes.getSelectedPersonNN(false)).toArray());
            JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
