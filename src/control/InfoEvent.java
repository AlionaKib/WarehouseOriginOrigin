package control;

import data.DaoException;
import data.EventDao;
import logging.LogLevel;
import model.Event;
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
 * Created by Алена on 04.12.2017.
 */
public class InfoEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MainApp.getLogging().log(LogLevel.INFO, "Selected Event");

            JFrame selectEvent = new JFrame();
            selectEvent.setTitle("Event");
            selectEvent.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    selectEvent.dispose();
                }
            });

            selectEvent.setPreferredSize(new Dimension(300, 250));
            selectEvent.setSize(300, 250);
            selectEvent.pack();
            selectEvent.setLocationRelativeTo(null);
            selectEvent.setContentPane(addPersoneToEvent); //панель - статическое поле класса Panes

            addActions();

            selectEvent.setVisible(true);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }

    private void addActions() throws DaoException {
        Event selectedEvent = Panes.getSelectedEventNN(false);
        EVENT_NAME_TEXTFIELD.setText(selectedEvent.getName());
        EVENT_PERSONELIST.setListData(MainApp.getDao(EventDao.class).getAllDependentPersons(selectedEvent).toArray());
    }
}
