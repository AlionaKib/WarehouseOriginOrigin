package model;

import data.DaoException;
import data.PersonDao;
import view.MainApp;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Алена on 28.11.2017.
 */
//@XmlRootElement(name = "Item")
@XmlType(propOrder = {"type", "maker", "name", "personId"})
public class Item extends DataItem implements Comparable<Item> {
    //@XmlElement(name = "type")
    private ItemType type;
    //@XmlElement(name = "maker")
    private String maker;
    //@XmlElement(name = "nameItem")
    private String name;
    //@XmlElement(name = "event")
    private GroupItems group;
    private UUID personId;
    //private Calendar dateRemove;

    public Item() {
        type = ItemType.None;
        maker = "";
        name = "";
        group = null;
        //dateRemove = null;
    }

    public Item(ItemType type, String maker, String name) {
        this.type = type;
        this.maker = maker;
        this.name = name;
        group = null;
        //dateRemove = null;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public GroupItems getGroup() {
        return group;
    }

    public void setGroup(GroupItems group) {
        this.group = group;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    @Override
    public int compareTo(Item item) {
        if (getType().compareTo(item.getType()) > 0)
            return 1;
        else if (getType().compareTo(item.getType()) < 0)
            return -1;
        else
            return getName().compareTo(item.getName());
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(type).append(" ").append(maker).append(" ").append(name);
        return s.toString();
    }

    public String info() throws DaoException {
        StringBuffer s = new StringBuffer();
        s.append("Equip info:\n").append(type).append(" ").append(maker).append(" ").append(name).append("\n");
        s.append("At persone: ");
        if (personId != null) {
            PersonDao personDao = MainApp.getDao(PersonDao.class);
            s.append(personDao.getItem(personId).map(Person::toString).orElseThrow(() -> new DaoException("Person is not found"))).append("\n");
        } else {
            s.append("none");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(name, item.name) &&
                Objects.equals(getType(), item.getType()) &&
                Objects.equals(getMaker(), item.getMaker()) &&
                Objects.equals(getPersonId(), item.getPersonId());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getMaker() != null ? getMaker().hashCode() : 0);
        result = 31 * result + (getPersonId() != null ? getPersonId().hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}
