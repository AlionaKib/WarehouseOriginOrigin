package model;

import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Created by Алена on 28.11.2017.
 */
@XmlType(propOrder = {"name"})
public class Event extends DataItem implements Comparable<Event> {
    private String name;

    public Event() {
        name = "";
    }

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Event event) {
        if (getName().compareTo(event.getName()) > 0)
            return 1;
        else if (getName().compareTo(event.getName()) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        return Objects.equals(getId(), event.getId()) &&
                Objects.equals(getName(), event.getName());
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(name);
        return s.toString();
    }

    public String info() {
        return name;
    }
}
