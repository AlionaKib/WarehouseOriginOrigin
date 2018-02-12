package control;

import data.DaoException;
import data.EventDao;
import logging.LogLevel;
import model.Event;
import view.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Panes.EVENTS_LIST;
import static view.Panes.NAME_EVENT_TEXTFIELD;

/**
 * Created by Алена on 29.11.2017.
 */
public class AddEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            EventDao eventDao = MainApp.getDao(EventDao.class);

            Event event = new Event(NAME_EVENT_TEXTFIELD.getText());
            eventDao.addItem(event);

            MainApp.getLogging().log(LogLevel.DEBUG, () -> eventDao.getAllItems().toString());

            EVENTS_LIST.setListData(eventDao.getAllItems().toArray()); //ЛистСтафф - Список всех работников строками для ЛистВью
            NAME_EVENT_TEXTFIELD.setText("");
            JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
