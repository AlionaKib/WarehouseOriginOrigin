package data.xmlstorage.saverstrategy;

import data.xmlstorage.XmlWarehouseDaoException;
import exceptions.DevelopmentException;
import model.DataItem;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class SingleFileStorageStrategy extends AbstractStorageStrategy {

    private static final String FILE_NAME = "Warehouse.xml";

    @Override
    public void save(Map<Class<? extends DataItem>, Map<Object, DataItem>> data) {
        XmlHelper.checkDataBeforeSaving(DATA_CLASSES, data);

        SingleFileWrapper wrapper = new SingleFileWrapper();

        for (Class<? extends DataItem> dataClass : DATA_CLASSES) {
            wrapper.getItems().addAll(data.get(dataClass).values());
        }

        saveDataClass(wrapper, getTmpFileName(), getClassesForJAXB());
        removeTmpData(getTmpFileName(), getFileName());
    }

    private String getFileName() {
        return dataDirectory + File.separator + FILE_NAME;
    }

    private String getTmpFileName() {
        return dataDirectory + File.separator + TMP_FILE_PREFIX + FILE_NAME;
    }

    private Class[] getClassesForJAXB() {
        List<Class> classes = new ArrayList<>();
        classes.add(SingleFileWrapper.class);
        classes.addAll(DATA_CLASSES);
        return classes.toArray(new Class[]{});
    }

    @Override
    public Map<Class<? extends DataItem>, Map<Object, DataItem>> load() {
        if (isInitialized()) {
            File file = XmlHelper.getFileFromName(getFileName(), true);
            if (file == null) {
                Map<Class<? extends DataItem>, Map<Object, DataItem>> result = new HashMap<>();
                DATA_CLASSES.forEach(clazz -> result.put(clazz, new HashMap<>()));
                return result;
            }

            try {
                return XmlHelper.readData(file, SingleFileWrapper.class, getClassesForJAXB()).getDataItems();
            } catch (JAXBException e) {
                throw new XmlWarehouseDaoException(getFileName() + " parsing error", e);
            }
        } else {
            throw new DevelopmentException("Storage isn't initialized");
        }
    }

    @Override
    public void clearAllData() {
        clearDataFile(getFileName());
    }

    @XmlRootElement(name = "items")
    private static class SingleFileWrapper extends Wrapper {
        @XmlTransient
        public Map<Class<? extends DataItem>, Map<Object, DataItem>> getDataItems() {
            Map<Class<? extends DataItem>, Map<Object, DataItem>> result = new HashMap<>();

            DATA_CLASSES.forEach(dataClass -> result.put(dataClass, new HashMap<>()));

            getItems().forEach(dataItem -> result.get(dataItem.getClass()).put(dataItem.getId(), dataItem));

            return result;
        }
    }

}
