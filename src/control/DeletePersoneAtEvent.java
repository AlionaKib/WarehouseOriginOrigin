package control;

import data.DaoException;
import data.EventDao;
import data.PersonDao;
import model.Person;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeletePersoneAtEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PersonDao personDao = MainApp.getDao(PersonDao.class);
            EventDao eventDao = MainApp.getDao(EventDao.class);

            Person updatePerson = Panes.getSelectedPersonNN(true);
            updatePerson.setEventId(null);
            personDao.updateItem(updatePerson);

            Panes.EVENT_PERSONELIST.setListData(eventDao.getAllDependentPersons(Panes.getSelectedEventNN(false)).toArray());
            JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
