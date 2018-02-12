package data.xmlstorage.saverstrategy;

import com.sun.istack.internal.NotNull;
import model.DataItem;
import data.xmlstorage.XmlWarehouseDaoException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class XmlHelper {

    public static File getFileFromName(String fileName, boolean onlyExists) {
        File file = new File(fileName);

        if (file.exists() && !file.isFile()) {
            throw new XmlWarehouseDaoException(fileName + " is not a file!");
        }

        if (onlyExists && !file.exists())
            return null;

        return file;
    }

    @NotNull
    public static <T> T readData(File file, Class<T> outClass, Class... fileClasses) throws JAXBException {
        JAXBContext c = JAXBContext.newInstance(fileClasses);
        Unmarshaller um = c.createUnmarshaller();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return um.unmarshal(new StreamSource(fileInputStream), outClass).getValue();
        } catch (IOException e) {
            throw new XmlWarehouseDaoException("Can't read data from file '" + file.getName() + "'", e);
        }
    }

    public static void checkDataBeforeSaving(List<Class<? extends DataItem>> dataClasses, Map<Class<? extends DataItem>, Map<Object, DataItem>> data) {
        for (Class<? extends DataItem> dataClass : dataClasses) {
            if (data.get(dataClass) == null) {
                throw new XmlWarehouseDaoException("Data for class '" + dataClass.getSimpleName() + "' is not found");
            }
        }
    }
}
