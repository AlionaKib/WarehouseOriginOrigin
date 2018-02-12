package data;

import com.sun.istack.internal.NotNull;
import exceptions.DevelopmentException;
import model.DataItem;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public abstract class AbstractWarehouseDao<T extends DataItem> implements WarehouseDao<T> {

    protected abstract Class<T> getItemClass();

    private boolean initialized = false;
    protected WarehouseStorage storage;

    @Override
    public void init(WarehouseStorage storage) {
        this.storage = storage;
        initialized = true;
    }

    @Override
    public void addItem(T dataItem) throws DaoException {
        checkInitialized();

        storage.addItem(dataItem);
    }

    @Override
    public void updateItem(@NotNull T dataItem) throws DaoException {
        checkInitialized();

        storage.updateItem(dataItem);
    }

    @Override
    public Optional<T> getItem(UUID id) throws DaoException {
        checkInitialized();

        return storage.getItem(id, getItemClass());
    }

    @Override
    public Collection<T> getAllItems() throws DaoException {
        return storage.getAllItems(getItemClass());
    }

    @Override
    public boolean isItemExists(UUID id) throws DaoException {
        return storage.isItemExists(id, getItemClass());
    }

    @Override
    public void deleteItem(T dataItem) throws DaoException {
        checkInitialized();

        storage.deleteItem(dataItem);
    }

    @Override
    public void deleteItemById(UUID id) throws DaoException {
        checkInitialized();

        storage.deleteItemById(id, getItemClass());
    }

    protected void checkInitialized() {
        if (!initialized)
            throw new DevelopmentException(this.getClass().getSimpleName() + " is not initialized");
    }
}
