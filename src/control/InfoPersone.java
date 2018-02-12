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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static view.Panes.*;

/**
 * Created by Алена on 03.12.2017.
 */
public class InfoPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MainApp.getLogging().log(LogLevel.INFO, "Selected Persone");

            JFrame selectPersone = new JFrame();
            Person selectedPersone = Panes.getSelectedPersonNN(false);
            selectPersone.setTitle(selectedPersone.toString());
            selectPersone.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    selectPersone.dispose();
                }
            });

            selectPersone.setPreferredSize(new Dimension(300, 200));
            selectPersone.setSize(300, 200);
            selectPersone.pack();
            selectPersone.setLocationRelativeTo(null);
            selectPersone.setContentPane(selectPersonePane); //панель - статическое поле класса Panes

            addActions();

            selectPersone.setVisible(true);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }

    private void addActions() throws DaoException {
        EventDao eventDao = MainApp.getDao(EventDao.class);
        Person selectedPersone = Panes.getSelectedPersonNN(true);
        if (selectedPersone.getEventId() != null) {
            Event event = eventDao.getItem(selectedPersone.getEventId()).orElseThrow(() -> new DaoException("Event is not found"));
            PERSONE_EVENT_LABEL.setText("Event: " + event.toString());
        } else {
            PERSONE_EVENT_LABEL.setText("Event: null");
        }
        PersonDao personDao = MainApp.getDao(PersonDao.class);
        ITEMS_AT_PERSONE.setListData(personDao.getAllDependentItems(selectedPersone).toArray());
    }
}
