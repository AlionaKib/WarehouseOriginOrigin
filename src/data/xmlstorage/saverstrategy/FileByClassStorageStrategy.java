package data.xmlstorage.saverstrategy;

import com.sun.istack.internal.NotNull;
import data.xmlstorage.XmlWarehouseDaoException;
import exceptions.DevelopmentException;
import model.DataItem;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class FileByClassStorageStrategy extends AbstractStorageStrategy {

    private static final String FILE_EXT = ".xml";

    @Override
    public void save(Map<Class<? extends DataItem>, Map<Object, DataItem>> data) {
        XmlHelper.checkDataBeforeSaving(DATA_CLASSES, data);

        for (Class<? extends DataItem> dataClass : DATA_CLASSES) {
            saveDataClass(new Wrapper(data.get(dataClass).values()),
                    getTmpFilename(dataClass),
                    Wrapper.class, dataClass);
        }

        for (Class<? extends DataItem> dataClass : DATA_CLASSES) {
            removeTmpData(getTmpFilename(dataClass), getFilename(dataClass));
        }
    }

    @Override
    public Map<Class<? extends DataItem>, Map<Object, DataItem>> load() {
        if (isInitialized()) {
            return DATA_CLASSES.stream()
                    .collect(Collectors.toMap(clazz -> clazz, this::loadDataFromClass));
        } else {
            throw new DevelopmentException("Storage isn't initialized");
        }
    }

    private Map<Object, DataItem> loadDataFromClass(Class<? extends DataItem> itemClass) {
        return loadData(getFilename(itemClass), itemClass);
    }

    @NotNull
    private Map<Object, DataItem> loadData(String fileName, Class<? extends DataItem> itemClass) {
        File file = XmlHelper.getFileFromName(fileName, true);
        if (file == null)
            return new HashMap<>();

        try {
            return XmlHelper.readData(file, Wrapper.class, Wrapper.class, itemClass)
                    .getItems().stream()
                    .collect(Collectors.toMap(DataItem::getId, item -> item));
        } catch (JAXBException e) {
            throw new XmlWarehouseDaoException(fileName + " parsing error", e);
        }
    }

    private String getFilename(Class clazz) {
        return dataDirectory + File.separator + clazz.getSimpleName() + FILE_EXT;
    }

    private String getTmpFilename(Class clazz) {
        return dataDirectory + File.separator + TMP_FILE_PREFIX + clazz.getSimpleName() + FILE_EXT;
    }

    @Override
    public void clearAllData() {
        DATA_CLASSES.stream()
                .map(this::getFilename)
                .forEach(this::clearDataFile);
    }
}
