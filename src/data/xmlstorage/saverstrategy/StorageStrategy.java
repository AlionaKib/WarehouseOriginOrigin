package data.xmlstorage.saverstrategy;

import com.sun.istack.internal.Nullable;
import model.DataItem;
import model.Event;
import model.Item;
import model.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public interface StorageStrategy {

    String DATA_DIRECTORY_PARAM = "DATA_DIRECTORY_PARAM";

    List<Class<? extends DataItem>> DATA_CLASSES = Collections.unmodifiableList(Arrays.asList(Event.class, Item.class, Person.class));

    void save(Map<Class<? extends DataItem>, Map<Object, DataItem>> data);

    void init(@Nullable Map<String, Object> params);

    Map<Class<? extends DataItem>, Map<Object, DataItem>> load();

    void clearAllData();

    boolean isInitialized();
}
