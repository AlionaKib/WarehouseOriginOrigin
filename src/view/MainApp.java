package view;

import data.DaoFactory;
import data.WarehouseDao;
import data.WarehouseStorage;
import data.xmlstorage.XmlWarehouseStorage;
import logging.ConsoleLogging;
import logging.Logging;
import logging.LoggingFactory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

public class MainApp {

    private static LoggingFactory loggingFactory;
    private static Logging logging;
    private static WarehouseStorage storage;
    private static DaoFactory daoFactory;

    private static OpenFrame mainFrame;

    public static void main(String[] args) {
        loggingFactory = new LoggingFactory();
        logging = createLogging();

        storage = new XmlWarehouseStorage();
        storage.init(Collections.singletonMap(XmlWarehouseStorage.MULTI_FILE_STORAGE_STRATEGY, Boolean.TRUE));

        daoFactory = new DaoFactory(storage);

        mainFrame = new OpenFrame();
        mainFrame.init();

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                storage.end();
            }
        });
    }

    private static Logging createLogging() {
        return loggingFactory.getLogging(ConsoleLogging.class);
    }

    public static <T extends WarehouseDao> T getDao(Class<T> daoClass) {
        return daoFactory.getWarehouseDao(daoClass);
    }

    public static Logging getLogging() {
        return logging;
    }

}
