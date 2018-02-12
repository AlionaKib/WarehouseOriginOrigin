package data;

import com.sun.istack.internal.NotNull;
import model.DataItem;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public interface WarehouseDao<T extends DataItem> {

    void init(WarehouseStorage storage);

    void addItem(@NotNull T dataItem) throws DaoException;

    void updateItem(@NotNull T dataItem) throws DaoException;

    @NotNull
    Optional<T> getItem(@NotNull UUID id) throws DaoException;

    Collection<T> getAllItems() throws DaoException;

    boolean isItemExists(@NotNull UUID id) throws DaoException;

    void deleteItem(@NotNull T dataItem) throws DaoException;

    void deleteItemById(@NotNull UUID id) throws DaoException;

}
