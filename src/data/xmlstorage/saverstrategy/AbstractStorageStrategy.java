package data.xmlstorage.saverstrategy;

import com.sun.istack.internal.Nullable;
import data.xmlstorage.XmlWarehouseDaoException;
import exceptions.DevelopmentException;
import model.DataItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public abstract class AbstractStorageStrategy implements StorageStrategy {

    private static final String DEFAULT_DATA_DIRECTORY = "data";
    protected static final String TMP_FILE_PREFIX = "_";

    private boolean initialized = false;
    protected String dataDirectory = null;

    @Override
    public void init(@Nullable Map<String, Object> params) {
        dataDirectory = getDataDirectory(params);

        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    private String getDataDirectory(@Nullable Map<String, Object> params) {
        Object directoryParam = params != null ? params.get(DATA_DIRECTORY_PARAM) : null;
        if (directoryParam != null) {
            if (directoryParam instanceof String) {
                return (String) directoryParam;
            } else {
                throw new DevelopmentException("DATA_DIRECTORY_PARAM should be a String");
            }
        }
        return DEFAULT_DATA_DIRECTORY;
    }

    protected void saveDataClass(Wrapper wrapper, String tmpFileName, Class... classesForJAXB) {
        createDataDirectoryIfNotExists();

        File tmpFile = new File(tmpFileName);

        if (tmpFile.exists() && !tmpFile.delete()) {
            throw new XmlWarehouseDaoException("Can't delete dile '" + tmpFileName + "'");
        }

        if (wrapper.getItems().isEmpty())
            return;

        boolean fileCreated;
        try {
            fileCreated = tmpFile.createNewFile();
        } catch (IOException e) {
            fileCreated = false;
        }

        if (!fileCreated)
            throw new XmlWarehouseDaoException("Can't create file '" + tmpFileName + "'");

        try {
            JAXBContext c = JAXBContext.newInstance(classesForJAXB);
            Marshaller m = c.createMarshaller();

            m.marshal(wrapper, tmpFile);
        } catch (JAXBException e) {
            throw new XmlWarehouseDaoException(tmpFileName + " parsing error", e);
        }
    }

    protected void removeTmpData(String tmpFileName, String fileName) {
        clearDataFile(fileName);
        renameFile(tmpFileName, fileName);
    }

    protected void clearDataFile(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            throw new XmlWarehouseDaoException("Can't delete file '" + fileName + "'", e);
        }
    }

    private void createDataDirectoryIfNotExists() {
        if (!this.dataDirectory.isEmpty()) {
            File dataDirectory = new File(this.dataDirectory);
            if (!dataDirectory.exists()) {
                if (!dataDirectory.mkdir())
                    throw new XmlWarehouseDaoException("Can't create directory '" + this.dataDirectory + "'");
            }
        }
    }

    private void renameFile(String oldFileName, String newFileName) {
        File oldFile = new File(oldFileName);
        if (!oldFile.exists())
            return;

        File newFile = new File(newFileName);

        if (newFile.exists()) {
            throw new XmlWarehouseDaoException("Can't save new database file '" + oldFileName + "'");
        }

        boolean renamed = oldFile.renameTo(newFile);

        if (!renamed) {
            throw new XmlWarehouseDaoException("Can't save new database file '" + oldFileName + "'");
        }
    }

    @XmlRootElement(name = "items")
    protected static class Wrapper {

        private Collection<DataItem> items;

        public Wrapper() {
            items = new ArrayList<>();
        }

        @SuppressWarnings("unused")
        public Wrapper(Collection<DataItem> items) {
            this.items = items;
        }

        @XmlElement(name = "item")
        public Collection<DataItem> getItems() {
            return items;
        }
    }

}
