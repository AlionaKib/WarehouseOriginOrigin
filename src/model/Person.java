package model;

import data.DaoException;
import data.EventDao;
import view.MainApp;

import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Алена on 28.11.2017.
 */
@XmlType(propOrder = {"name", "surname", "eventId"})
public class Person extends DataItem implements Comparable<Person> {
    private String name;
    private String surname;


    private UUID eventId;

    public Person() {
        name = "";
        surname = "";
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    @Override
    public int compareTo(Person person) {
        if (getSurname().compareTo(person.getSurname()) > 0)
            return 1;
        else if (getSurname().compareTo(person.getSurname()) < 0)
            return -1;
        else
            return getName().compareTo(person.getName());
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(surname).append(" ").append(name);
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(eventId, person.eventId);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getEventId() != null ? getEventId().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    public String info() throws DaoException {
        StringBuffer s = new StringBuffer();
        s.append(name).append(" ").append(surname)
                .append("\nAt event: ");
        if (eventId != null) {
            EventDao eventDao = MainApp.getDao(EventDao.class);
            s.append(eventDao.getItem(eventId).map(Event::toString).orElseThrow(() -> new DaoException("Event is not found")));
        } else {
            s.append("none");
        }
        return s.toString();
    }
}
