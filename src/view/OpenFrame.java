package view;

import control.*;

import javax.swing.*;
import java.awt.*;

import static view.Panes.*;

/**
 * Created by Алена on 28.11.2017.
 */
public class OpenFrame extends JFrame {
    private CreateItem createItem;  //объекты слушателей событий действия
    private CreatePersone createPersone;
    private CreateEvent createEvent;
    private InfoPersone infoPersone;
    private AddItemToPersone addItemToPersone; //слушатель события нажатия на кнопку "Выдать"
    private InfoItem infoItem;
    private DeleteItem deleteItem;
    private DeletePersone deletePersone;
    private InfoEvent infoEvent;
    private AddPersoneToEvent addPersoneToEvent;
    private DeleteItemAtPersone deleteItemAtPersone;
    private DeletePersoneAtEvent deletePersoneAtEvent;
    private DeleteEvent deleteEvent;

    private Panes panes;

    public OpenFrame() {
        super();

        createItem = new CreateItem();
        createPersone = new CreatePersone();
        createEvent = new CreateEvent();
        addItemToPersone = new AddItemToPersone();
        infoItem = new InfoItem();
        deleteItem = new DeleteItem();
        deletePersone = new DeletePersone();
        infoEvent = new InfoEvent();
        addPersoneToEvent = new AddPersoneToEvent();
        deleteItemAtPersone = new DeleteItemAtPersone();
        deletePersoneAtEvent = new DeletePersoneAtEvent();
        deleteEvent = new DeleteEvent();
    }

    public void init() {

        setTitle("Warehouse");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(900, 400));
        setSize(900, 400);

        pack();

        setLocationRelativeTo(null);

        panes = new Panes();

        setContentPane(openPane); //openPane  - статическое поле класса Panes

        infoPersone = new InfoPersone();

        addActions();

        setVisible(true);
    }

    private void addActions() {

        ADD_EVENT.addActionListener(createEvent);  //при нажатии на кнопку "добавить" оповещаются слушатели события действия
        ADD_PERSONE.addActionListener(createPersone);
        ADD_ITEM.addActionListener(createItem);

        INFO_PERSONE.addActionListener(infoPersone);
        INFO_ITEM.addActionListener(infoItem);
        INFO_EVENT.addActionListener(infoEvent);

        DELETE_ITEM.addActionListener(deleteItem);
        DELETE_PERSONE.addActionListener(deletePersone);
        DELETE_EVENT.addActionListener(deleteEvent);

        ADD_ITEM_TO_PERSONE.addActionListener(addItemToPersone);  //оповещается слушатель addItemToPersone
        DELETE_ITEM_AT_PERSONE.addActionListener(deleteItemAtPersone);

        ACTIONS_WITH_PERSONE_AT_EVENT.addActionListener(infoPersone);
        ADD_PERSONE_TO_EVENT.addActionListener(addPersoneToEvent);
        DELETE_PERSONE_AT_EVENT.addActionListener(deletePersoneAtEvent);

        PERSONE_LIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //модель выбора - единственный элемент

        ITEMS_AT_PERSONE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        EVENT_PERSONELIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
