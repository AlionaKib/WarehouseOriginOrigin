package model;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Created by Алена on 20.12.2017.
 */
public class GroupItems implements Comparable<GroupItems> {
    private String name;
    private TreeSet<Item> itemsList;

    public GroupItems() {
        name = "";
        itemsList = new TreeSet<>();
    }

    public GroupItems(String name, TreeSet<Item> itemsList) {
        this.name = name;
        this.itemsList = itemsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeSet<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(TreeSet<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void removeItem(Item item) {
        itemsList.remove(item);
    }


    @Override
    public int compareTo(GroupItems o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupItems)) return false;

        GroupItems that = (GroupItems) o;

        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getItemsList(), that.getItemsList());
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getItemsList() != null ? getItemsList().hashCode() : 0);
        return result;
    }
}
