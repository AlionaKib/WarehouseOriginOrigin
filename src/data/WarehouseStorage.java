package data;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import model.DataItem;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gray-Wanderer on 05.01.2018.
 */
public interface WarehouseStorage {

    void init(@Nullable Map<String, Object> params);

    void addItem(@NotNull DataItem dataItem) throws DaoException;

    void updateItem(@NotNull DataItem dataItem) throws DaoException;

    @NotNull
    <T extends DataItem> Optional<T> getItem(@NotNull Object id, @NotNull Class<T> objClass) throws DaoException;

    <T extends DataItem> Collection<T> getAllItems(@NotNull Class<T> objClass) throws DaoException;

    boolean isItemExists(@NotNull Object id, @NotNull Class<? extends DataItem> objClass) throws DaoException;

    void deleteItem(@NotNull DataItem dataItem) throws DaoException;

    void deleteItemById(@NotNull Object id, @NotNull Class<? extends DataItem> objClass) throws DaoException;

    @NotNull
    <T extends DataItem> List<T> getDependentItems(@NotNull Object id, @NotNull String relativePropertyName, @NotNull Class<T> objClass) throws DaoException;

    void clearAllData();

    void end();

}
