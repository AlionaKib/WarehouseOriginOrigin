package control;

import data.DaoException;
import data.EventDao;
import data.PersonDao;
import logging.LogLevel;
import model.Event;
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
 * Created by Алена on 11.12.2017.
 */
public class AddPersoneToEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame addPersone = new JFrame();
        addPersone.setTitle("Select Persone");

        addPersone.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                addPersone.dispose();
            }
        });

        addPersone.setPreferredSize(new Dimension(300, 100));
        addPersone.setSize(300, 100);
        addPersone.pack();
        addPersone.setLocationRelativeTo(null);
        //addItem.setContentPane(addItem.getContentPane()); //панель - статическое поле в классе Panes

        MainApp.getLogging().log(LogLevel.DEBUG, () -> {
            PersonDao personDao = MainApp.getDao(PersonDao.class);
            return personDao.getAllItems() + "\n" + personDao.getAllPersonsWithoutEvent();
        });

        //LIST_ITEMS_WITHOUT_PERSONE.setListData(itemsWithoutPersone.toArray()); //связываем список доступного снаряжения с его отображением
        addActions(addPersone.getContentPane());

        addPersone.setVisible(true);
    }

    private void addActions(Container contentPane) {
        try {
            PersonDao personDao = MainApp.getDao(PersonDao.class);

            JList<Object> listPersoneWithoutEvent = new JList<>(personDao.getAllPersonsWithoutEvent().toArray());
            contentPane.setLayout(new GridLayout());
            contentPane.add(listPersoneWithoutEvent);
            listPersoneWithoutEvent.setBackground(Color.white);

            listPersoneWithoutEvent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listPersoneWithoutEvent.addListSelectionListener(e -> selectItem(listPersoneWithoutEvent, e));  //ЛЯМБДА
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }

    private void selectItem(JList<Object> listPersoneWithoutEvent, ListSelectionEvent e) {
        try {

            MainApp.getLogging().log(LogLevel.INFO, "Item selected");
            //LIST_ITEMS_WITHOUT_PERSONE.clearSelection();
            if (!e.getValueIsAdjusting()) {  //что-то произойдет только если есть выбранное значение в событии
                MainApp.getLogging().log(LogLevel.INFO, "Adding persone to event");
                Person selectedPersone = (Person) listPersoneWithoutEvent.getSelectedValue();
                MainApp.getLogging().log(LogLevel.INFO, () -> "Selected persone " + selectedPersone);

                if (selectedPersone != null) {
                    PersonDao personDao = MainApp.getDao(PersonDao.class);
                    Event selectedEvent = Panes.getSelectedEventNN(true);
                    selectedPersone.setEventId(selectedEvent.getId());
                    personDao.updateItem(selectedPersone);

                    EventDao eventDao = MainApp.getDao(EventDao.class);
                    MainApp.getLogging().log(LogLevel.DEBUG, () ->
                            "Event personeList " + eventDao.getAllDependentPersons(selectedEvent) +
                                    "\nPersone added to event " + selectedPersone
                    );

                    Panes.EVENT_PERSONELIST.setListData(eventDao.getAllDependentPersons(selectedEvent).toArray());

                    listPersoneWithoutEvent.setListData(personDao.getAllPersonsWithoutEvent().toArray());
                    JOptionPane.showMessageDialog(null, "Success", "Persone add", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
