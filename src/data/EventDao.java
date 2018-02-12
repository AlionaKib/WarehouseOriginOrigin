package data;

import model.Event;
import model.Person;
import view.MainApp;

import java.util.Collection;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public class EventDao extends AbstractWarehouseDao<Event> {
    @Override
    protected Class<Event> getItemClass() {
        return Event.class;
    }

    @Override
    public void deleteItem(Event dataItem) throws DaoException {
        checkInitialized();

        PersonDao personDao = MainApp.getDao(PersonDao.class);
        for (Person person : getAllDependentPersons(dataItem)) {
            person.setEventId(null);
            personDao.updateItem(person);
        }

        super.deleteItem(dataItem);
    }

    public Collection<Person> getAllDependentPersons(Event event) throws DaoException {
        return storage.getDependentItems(event.getId(), "eventId", Person.class);
    }
}
