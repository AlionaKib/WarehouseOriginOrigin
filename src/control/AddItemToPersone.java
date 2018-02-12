package control;

import data.DaoException;
import data.ItemDao;
import data.PersonDao;
import logging.LogLevel;
import model.Item;
import model.Person;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Алена on 29.11.2017.
 */
public class AddItemToPersone implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame addItem = new JFrame();
        addItem.setTitle("Выберете снаряжение");

        addItem.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                addItem.dispose();
            }
        });

        addItem.setPreferredSize(new Dimension(300, 100));
        addItem.setSize(300, 100);
        addItem.pack();
        addItem.setLocationRelativeTo(null);
        //addItem.setContentPane(addItem.getContentPane()); //панель - статическое поле в классе Panes

        MainApp.getLogging().log(LogLevel.DEBUG, () -> {
            ItemDao itemDao = MainApp.getDao(ItemDao.class);
            return itemDao.getAllItems().toString() + "\n" + itemDao.getAllItemsWithoutPersone().toString();
        });

        //LIST_ITEMS_WITHOUT_PERSONE.setListData(itemsWithoutPersone.toArray()); //связываем список доступного снаряжения с его отображением
        addActions(addItem.getContentPane());

        addItem.setVisible(true);
    }

    private void addActions(Container contentPane) {
        try {
            ItemDao itemDao = MainApp.getDao(ItemDao.class);
            JList<Object> listItemsWithoutPersone = new JList<>(itemDao.getAllItemsWithoutPersone().toArray());
            contentPane.setLayout(new GridLayout());
            contentPane.add(listItemsWithoutPersone);
            listItemsWithoutPersone.setBackground(Color.white);

            listItemsWithoutPersone.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listItemsWithoutPersone.addListSelectionListener(e -> selectItem(listItemsWithoutPersone, e));  //ЛЯМБДА
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }

    private void selectItem(JList<Object> listItemsWithoutPersone, ListSelectionEvent e) {
        try {
            MainApp.getLogging().log(LogLevel.INFO, "Item selected");
            //LIST_ITEMS_WITHOUT_PERSONE.clearSelection();
            if (!e.getValueIsAdjusting()) {  //что-то произойдет только если есть выбранное значение в событии
                ItemDao itemDao = MainApp.getDao(ItemDao.class);

                MainApp.getLogging().log(LogLevel.INFO, "Adding item to persone");
                Item selectedItem = (Item) listItemsWithoutPersone.getSelectedValue();
                MainApp.getLogging().log(LogLevel.INFO, () -> "Selected item " + selectedItem);

                if (selectedItem != null) {
                    Person selectedPerson = Panes.getSelectedPersonNN(false);
                    selectedItem.setPersonId(selectedPerson.getId());
                    itemDao.updateItem(selectedItem);

                    PersonDao personDao = MainApp.getDao(PersonDao.class);
                    MainApp.getLogging().log(LogLevel.DEBUG, () ->
                            "Person itemsList " + personDao.getAllDependentItems(selectedPerson) +
                                    "\nItem added to persone" + personDao.getItem(selectedItem.getPersonId())
                    );

                    Panes.ITEMS_AT_PERSONE.setListData(personDao.getAllDependentItems(selectedPerson).toArray());

                    listItemsWithoutPersone.setListData(itemDao.getAllItemsWithoutPersone().toArray());
                    JOptionPane.showMessageDialog(null, "Готово", "Снаряжение выдано", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
