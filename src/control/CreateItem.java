package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class CreateItem implements ActionListener {
    AddItem addItem = new AddItem();
    SelectItemType selectItemType = new SelectItemType();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createItem = new JFrame();
        createItem.setTitle("Item parameters");

        createItem.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                createItem.dispose();
                addItem = null;
            }
        });

        createItem.setPreferredSize(new Dimension(300, 200));
        createItem.setSize(300, 200);
        createItem.pack();
        createItem.setLocationRelativeTo(null);
        createItem.setContentPane(createItemPane);
        addActions();

        createItem.setVisible(true);
    }

    private void addActions() {
        BOX_ITEM.addActionListener(selectItemType);
        CREATE_ITEM.addActionListener(addItem);
    }
}
