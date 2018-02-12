package control;

import data.DaoException;
import data.EventDao;
import model.Event;
import view.MainApp;
import view.Panes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Created by Алена on 11.12.2017.
 */
public class DeleteEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            EventDao eventDao = MainApp.getDao(EventDao.class);

            Optional<Event> selectedEvent = Panes.getSelectedEvent(false);
            selectedEvent.ifPresent(eventDao::deleteItem);

            Panes.EVENTS_LIST.setListData(eventDao.getAllItems().toArray());
            JOptionPane.showMessageDialog(null, "Success", "Deleting done", JOptionPane.PLAIN_MESSAGE);
        } catch (DaoException e1) {
            throw new RuntimeException(e1);  //TODO:Gray-Wanderer show error message
        }
    }
}
