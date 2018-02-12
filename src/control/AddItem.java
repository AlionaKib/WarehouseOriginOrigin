package control;

import data.DaoException;
import data.ItemDao;
import logging.LogLevel;
import model.Item;
import view.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static control.SelectItemType.typeSelectedItem;
import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class AddItem implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ItemDao itemDao = MainApp.getDao(ItemDao.class);

            Item item = new Item(typeSelectedItem, ITEM_MAKER_TEXTFIELD.getText(), ITEM_NUMBER_TEXTFIELD.getText());
            itemDao.addItem(item);

            MainApp.getLogging().log(LogLevel.DEBUG, itemDao.getAllItems().toString());

            ITEMS_LIST.setListData(itemDao.getAllItems().toArray()); //ЛистСтафф - Список всех работников строками для ЛистВью
            JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
