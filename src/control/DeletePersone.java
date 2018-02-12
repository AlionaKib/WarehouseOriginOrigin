package control;

import data.DaoException;
import data.PersonDao;
import model.Person;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Created by Алена on 04.12.2017.
 */
public class DeletePersone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PersonDao personDao = MainApp.getDao(PersonDao.class);

            Optional<Person> selectedPerson = Panes.getSelectedPerson(false);
            selectedPerson.ifPresent(personDao::deleteItem);

            Panes.PERSONE_LIST.setListData(personDao.getAllItems().toArray());
            JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
