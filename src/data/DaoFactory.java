package data;

import exceptions.DevelopmentException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public class DaoFactory {

    private WarehouseStorage warehouseStorage;
    private Map<Class<? extends WarehouseDao>, WarehouseDao> warehouseDaoMap = new HashMap<>();

    public DaoFactory(WarehouseStorage warehouseStorage) {
        this.warehouseStorage = warehouseStorage;
    }

    @SuppressWarnings("unchecked")
    public <T extends WarehouseDao> T getWarehouseDao(Class<T> daoClass) {
        return (T) warehouseDaoMap.computeIfAbsent(daoClass, this::createWarehouseDao);
    }

    private <T extends WarehouseDao> T createWarehouseDao(Class<T> daoClass) {
        try {
            T warehouseDao = daoClass.getConstructor().newInstance();
            warehouseDao.init(warehouseStorage);
            return warehouseDao;
        } catch (NoSuchMethodException e) {
            throw new DevelopmentException(daoClass.getSimpleName() + " doesn't have a default constructor");
        } catch (IllegalAccessException e) {
            throw new DevelopmentException("The default constructor in the " + daoClass.getSimpleName() + " is unavailable");
        } catch (InstantiationException | InvocationTargetException e) {
            throw new DevelopmentException("Can't create " + daoClass.getSimpleName());
        }
    }
}
