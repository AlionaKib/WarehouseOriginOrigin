package control;

import model.Event;
import model.Item;
import model.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.TreeSet;


/**
 * Created by Алена on 28.11.2017.
 */
@XmlRootElement(name = "Warehouse")
@XmlType(propOrder = {"eventList"})
@Deprecated
public class DataList {
    public static Event defaultEvent = new Event();
    public static Person defaultPersone = new Person();

    @XmlElementWrapper(name = "eventList")
    @XmlElement(name = "event")
    public static TreeSet<Event> eventList = new TreeSet<>();

    public static TreeSet<Person> personeList = new TreeSet<>();
    public static TreeSet<Item> itemsList = new TreeSet<>();
    public static TreeSet<Item> itemsWithoutPersone = new TreeSet<>();
    public static TreeSet<Person> personeWithoutEvent = new TreeSet<>();

    public static Person selectedPersone = null;
    public static Item selectedItem = null;
    public static Event selectedEvent = null;
}
