package control;

import data.DaoException;
import data.PersonDao;
import logging.LogLevel;
import model.Person;
import view.MainApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class AddPersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PersonDao personDao = MainApp.getDao(PersonDao.class);

            Person person = new Person(PERSONE_NAME_TEXTFIELD.getText(), PERSONE_SURNAME_TEXTFIELD.getText());
            personDao.addItem(person);

            MainApp.getLogging().log(LogLevel.DEBUG, () -> personDao.getAllItems().toString());

            PERSONE_LIST.setListData(personDao.getAllItems().toArray());
            PERSONE_NAME_TEXTFIELD.setText("");
            PERSONE_SURNAME_TEXTFIELD.setText("");
            JOptionPane.showMessageDialog(null, "Success", "Creating done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
