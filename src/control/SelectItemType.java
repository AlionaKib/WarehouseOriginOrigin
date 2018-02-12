package control;

import model.ItemType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Panes.BOX_ITEM;

/**
 * Created by Алена on 28.11.2017.
 */
public class SelectItemType implements ActionListener {
    public static ItemType typeSelectedItem;

    @Override
    public void actionPerformed(ActionEvent e) {
        typeSelectedItem = (ItemType) BOX_ITEM.getSelectedItem();
    }
}
