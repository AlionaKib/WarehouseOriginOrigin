package data;

import model.Item;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Gray-Wanderer on 07.01.2018.
 */
public class ItemDao extends AbstractWarehouseDao<Item> {
    @Override
    protected Class<Item> getItemClass() {
        return Item.class;
    }

    public Collection<Item> getAllItemsWithoutPersone() throws DaoException {
        checkInitialized();

        return super.getAllItems().stream()
                .filter(item -> item.getPersonId() == null)
                .collect(Collectors.toList());
    }
}
