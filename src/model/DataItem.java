package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

/**
 * @author Gray-Wanderer on 05.01.2018.
 */
@XmlType
public abstract class DataItem implements Cloneable {

    private UUID id;

    @XmlElement
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public DataItem clone() throws CloneNotSupportedException {
        return (DataItem) super.clone();
    }
}
