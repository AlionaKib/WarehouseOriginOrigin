package view;

import data.DaoException;
import data.EventDao;
import data.ItemDao;
import data.PersonDao;
import exceptions.DevelopmentException;
import model.Event;
import model.Item;
import model.ItemType;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * Created by Алена on 28.11.2017.
 */
public class Panes {
    public static final JList<Object> ITEMS_LIST = new JList<>();
    public static final JScrollPane ITEMS_LIST_SCROLL = new JScrollPane(ITEMS_LIST);
    public static final JList<Object> PERSONE_LIST = new JList<>();
    public static final JScrollPane PERSONE_LIST_SCROLL = new JScrollPane(PERSONE_LIST);
    public static final JList<Object> EVENTS_LIST = new JList<>();
    public static final JScrollPane EVENTS_LIST_SCROLL = new JScrollPane(EVENTS_LIST);
    public static final JButton ADD_ITEM = new JButton("Add");
    public static final JButton ADD_PERSONE = new JButton("Add");
    public static final JButton ADD_EVENT = new JButton("Add");
    public static final JButton DELETE_ITEM = new JButton("Delete");
    public static final JButton DELETE_PERSONE = new JButton("Delete");
    public static final JButton DELETE_EVENT = new JButton("Delete");
    public static final JButton INFO_ITEM = new JButton("Info");
    public static final JButton INFO_PERSONE = new JButton("Actions");
    public static final JButton INFO_EVENT = new JButton("Actions");
    public static final JLabel ITEM_LABEL = new JLabel("Equipment");
    public static final JLabel PERSONE_LABEL = new JLabel("People");
    public static final JLabel EVENTS_LABEL = new JLabel("Events");
    public static final JComboBox<ItemType> BOX_ITEM = new JComboBox<>(ItemType.values());
    public static final JScrollPane BOX_ITEM_SCROLL = new JScrollPane(BOX_ITEM);
    public static final JTextField ITEM_MAKER_TEXTFIELD = new JTextField();
    public static final JTextField ITEM_NUMBER_TEXTFIELD = new JTextField();
    public static final JLabel ITEM_TYPE_LABEL = new JLabel("Type");
    public static final JLabel ITEM_MAKER_LABEL = new JLabel("Maker");
    public static final JLabel ITEM_NUMBER_LABEL = new JLabel("ID");
    public static final JButton CREATE_ITEM = new JButton("Add");
    public static final JLabel PERSONE_NAME_LABEL = new JLabel("Name");
    public static final JLabel PERSONE_SURNAME_LABEL = new JLabel("Surname");
    public static final JTextField PERSONE_NAME_TEXTFIELD = new JTextField();
    public static final JTextField PERSONE_SURNAME_TEXTFIELD = new JTextField();
    public static final JButton CREATE_PERSONE = new JButton("Add");
    public static final JLabel NAME_EVENT_LABEL = new JLabel("Name");
    public static final JTextField NAME_EVENT_TEXTFIELD = new JTextField();
    public static final JButton CREATE_EVENT = new JButton("Create");
    public static final JButton ADD_ITEM_TO_PERSONE = new JButton("Add");
    public static final JButton DELETE_ITEM_AT_PERSONE = new JButton("Remove");
    public static final JLabel PERSONE_EVENT_LABEL = new JLabel("Event: none");
    public static final JLabel PERSONE_ITEMS_LIST = new JLabel("Equipment list");
    public static final JList<Object> ITEMS_AT_PERSONE = new JList<>();
    public static final JScrollPane ITEMS_AT_PERSONE_SCROLL = new JScrollPane(ITEMS_AT_PERSONE);
    public static final JLabel EVENT_NAME_LABEL = new JLabel("Name");
    public static final JLabel EVENT_NAME_TEXTFIELD = new JLabel("<event name>");
    public static final JLabel EVENT_PERSONELIST_LABEL = new JLabel("Persone list");
    public static final JList<Object> EVENT_PERSONELIST = new JList<>();
    public static final JScrollPane EVENT_PERSONELIST_SCROLLPANE = new JScrollPane(EVENT_PERSONELIST);
    public static final JButton ADD_PERSONE_TO_EVENT = new JButton("Add");
    public static final JButton DELETE_PERSONE_AT_EVENT = new JButton("Delete");
    public static final JButton ACTIONS_WITH_PERSONE_AT_EVENT = new JButton("Actions");
    public static JPanel openPane;
    public static JPanel createItemPane;
    public static JPanel createPersonePane;
    public static JPanel createEventPane;
    public static JPanel selectItemPane;
    public static JPanel selectPersonePane;
    public static JPanel addItemToPersonePane;
    public static JPanel addPersoneToEvent;

    //public static final JList<Object> LIST_ITEMS_WITHOUT_PERSONE = new JList<>();

    public Panes() {
        try {
            ITEMS_LIST.setListData(MainApp.getDao(ItemDao.class).getAllItems().toArray());
            PERSONE_LIST.setListData(MainApp.getDao(PersonDao.class).getAllItems().toArray());
            EVENTS_LIST.setListData(MainApp.getDao(EventDao.class).getAllItems().toArray());
        } catch (DaoException e) {
            throw new DevelopmentException("Can't initialize data");
        }

        openPane = new JPanel();
        createEventPane = new JPanel();
        createItemPane = new JPanel();
        createPersonePane = new JPanel();
        selectItemPane = new JPanel();
        selectPersonePane = new JPanel();
        addPersoneToEvent = new JPanel();
        //addItemToPersonePane = new JPanel();

        setOpenPane();
        setCreateItem();
        setCreatePersonePane();
        setCreateEventPane();
        setSelectPersonePane();
        setAddPersoneToEventPane();
        //setItemToPersonePane();
    }

    /*private void setItemToPersonePane(){
        addItemToPersonePane.setLayout(new BoxLayout(addItemToPersonePane, BoxLayout.Y_AXIS));

        addItemToPersonePane.add(LIST_ITEMS_WITHOUT_PERSONE);
    }*/

    public static Person getSelectedPersonNN(boolean reload) throws DaoException {
        return getSelectedPerson(reload).orElseThrow(() -> new DaoException("Event is not selected"));
    }

    public static Item getSelectedItemNN(boolean reload) throws DaoException {
        return getSelectedItem(reload).orElseThrow(() -> new DaoException("Event is not selected"));
    }

    public static model.Event getSelectedEventNN(boolean reload) throws DaoException {
        return getSelectedEvent(reload).orElseThrow(() -> new DaoException("Event is not selected"));
    }

    public static Optional<Person> getSelectedPerson(boolean reload) throws DaoException {
        Person person = (Person) PERSONE_LIST.getSelectedValue();
        if (person == null || !reload)
            return Optional.ofNullable(person);

        PersonDao personDao = MainApp.getDao(PersonDao.class);
        return personDao.getItem(person.getId());
    }

    public static Optional<Item> getSelectedItem(boolean reload) throws DaoException {
        Item item = (Item) ITEMS_LIST.getSelectedValue();
        if (item == null || !reload)
            return Optional.ofNullable(item);

        ItemDao itemDao = MainApp.getDao(ItemDao.class);
        return itemDao.getItem(item.getId());
    }

    public static Optional<model.Event> getSelectedEvent(boolean reload) throws DaoException {
        model.Event event = (Event) EVENTS_LIST.getSelectedValue();
        if (event == null || !reload)
            return Optional.ofNullable(event);

        EventDao eventDao = MainApp.getDao(EventDao.class);
        return eventDao.getItem(event.getId());
    }

    private void setAddPersoneToEventPane() {
        GridBagLayout gbl = new GridBagLayout();

        addPersoneToEvent.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;
        gbl.setConstraints(EVENT_NAME_LABEL, c);
        addPersoneToEvent.add(EVENT_NAME_LABEL);

        gbl.setConstraints(EVENT_NAME_TEXTFIELD, c);
        addPersoneToEvent.add(EVENT_NAME_TEXTFIELD);

        c.gridy = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridheight = 3;
        gbl.setConstraints(EVENT_PERSONELIST_LABEL, c);
        addPersoneToEvent.add(EVENT_PERSONELIST_LABEL);

        c.gridwidth = 3;
        gbl.setConstraints(EVENT_PERSONELIST_SCROLLPANE, c);
        addPersoneToEvent.add(EVENT_PERSONELIST_SCROLLPANE);

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridheight = 1;
        c.gridwidth = 1;
        gbl.setConstraints(ADD_PERSONE_TO_EVENT, c);
        addPersoneToEvent.add(ADD_PERSONE_TO_EVENT);

        c.gridx = 1;
        gbl.setConstraints(DELETE_PERSONE_AT_EVENT, c);
        addPersoneToEvent.add(DELETE_PERSONE_AT_EVENT);

        c.gridx = 2;
        gbl.setConstraints(ACTIONS_WITH_PERSONE_AT_EVENT, c);
        addPersoneToEvent.add(ACTIONS_WITH_PERSONE_AT_EVENT);
    }

    private void setSelectPersonePane() {
        GridBagLayout gbl = new GridBagLayout();

        selectPersonePane.setLayout(gbl);
        //openPane.setSize(500,500);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;
        gbl.setConstraints(PERSONE_EVENT_LABEL, c);
        selectPersonePane.add(PERSONE_EVENT_LABEL);

        gbl.setConstraints(PERSONE_ITEMS_LIST, c);
        selectPersonePane.add(PERSONE_ITEMS_LIST);

        c.weighty = 1.0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(ITEMS_AT_PERSONE_SCROLL, c);
        selectPersonePane.add(ITEMS_AT_PERSONE_SCROLL);

        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 4;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(ADD_ITEM_TO_PERSONE, c);
        selectPersonePane.add(ADD_ITEM_TO_PERSONE);

        gbl.setConstraints(DELETE_ITEM_AT_PERSONE, c);
        selectPersonePane.add(DELETE_ITEM_AT_PERSONE);
    }

    private void setCreateEventPane() {
        createEventPane.setLayout(new BoxLayout(createEventPane, BoxLayout.Y_AXIS));

        createEventPane.add(NAME_EVENT_LABEL);
        createEventPane.add(NAME_EVENT_TEXTFIELD);
        createEventPane.add(CREATE_EVENT);
    }

    private void setCreatePersonePane() {
        createPersonePane.setLayout(new BoxLayout(createPersonePane, BoxLayout.Y_AXIS));

        createPersonePane.add(PERSONE_NAME_LABEL);
        createPersonePane.add(PERSONE_NAME_TEXTFIELD);
        createPersonePane.add(PERSONE_SURNAME_LABEL);
        createPersonePane.add(PERSONE_SURNAME_TEXTFIELD);
        createPersonePane.add(CREATE_PERSONE);
    }

    private void setCreateItem() {
        //createItem.setBackground(colorListStuff);
        createItemPane.setLayout(new BoxLayout(createItemPane, BoxLayout.Y_AXIS));

        createItemPane.add(ITEM_TYPE_LABEL);
        createItemPane.add(BOX_ITEM_SCROLL);
        createItemPane.add(ITEM_MAKER_LABEL);
        createItemPane.add(ITEM_MAKER_TEXTFIELD);
        createItemPane.add(ITEM_NUMBER_LABEL);
        createItemPane.add(ITEM_NUMBER_TEXTFIELD);
        createItemPane.add(CREATE_ITEM);
    }

    private void setOpenPane() {
        openPane.setBackground(Color.white);

        /*listStuff.setBackground(colorListStuff);
        listStuff.setFont(fontList);
        listStuff.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        labelStaff.setFont(fontLabel);
        labelStaff.setHorizontalAlignment(JLabel.CENTER);
        labelStaff.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        labelStaff.setBackground(colorButtonStuff);
        addStuff.setBackground(colorButtonStuff);
        deleteStuff.setBackground(colorButtonStuff);
        infoStuff.setBackground(colorButtonStuff);
        searchStuff.setBackground(colorButtonStuff);

        listDepart.setBackground(colorListDepart);
        listDepart.setFont(fontList);
        listDepart.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        labelDepart.setFont(fontLabel);
        labelDepart.setHorizontalAlignment(JLabel.CENTER);
        labelDepart.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        labelDepart.setBackground(colorLabelDepart);
        addDepart.setBackground(colorButtonDepart);
        deleteDepart.setBackground(colorButtonDepart);
        infoDepart.setBackground(colorButtonDepart);
        searchDepart.setBackground(colorButtonDepart);*/

        GridBagLayout gbl = new GridBagLayout();

        openPane.setLayout(gbl);
        //openPane.setSize(500,500);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;
        gbl.setConstraints(EVENTS_LABEL, c);
        openPane.add(EVENTS_LABEL);


        c.gridx = 3;
        gbl.setConstraints(PERSONE_LABEL, c);
        openPane.add(PERSONE_LABEL);

        c.gridx = 6;
        gbl.setConstraints(ITEM_LABEL, c);
        openPane.add(ITEM_LABEL);

        c.gridy = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        gbl.setConstraints(ADD_EVENT, c);
        openPane.add(ADD_EVENT);


        c.gridx = GridBagConstraints.RELATIVE;
        gbl.setConstraints(DELETE_EVENT, c);
        openPane.add(DELETE_EVENT);

        gbl.setConstraints(INFO_EVENT, c);
        openPane.add(INFO_EVENT);

        gbl.setConstraints(ADD_PERSONE, c);
        openPane.add(ADD_PERSONE);

        gbl.setConstraints(DELETE_PERSONE, c);
        openPane.add(DELETE_PERSONE);

        gbl.setConstraints(INFO_PERSONE, c);
        openPane.add(INFO_PERSONE);

        gbl.setConstraints(ADD_ITEM, c);
        openPane.add(ADD_ITEM);

        gbl.setConstraints(DELETE_ITEM, c);
        openPane.add(DELETE_ITEM);

        gbl.setConstraints(INFO_ITEM, c);
        openPane.add(INFO_ITEM);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(EVENTS_LIST_SCROLL, c);
        openPane.add(EVENTS_LIST_SCROLL);

        c.gridx = 3;
        gbl.setConstraints(PERSONE_LIST_SCROLL, c);
        openPane.add(PERSONE_LIST_SCROLL);

        c.gridx = 6;
        gbl.setConstraints(ITEMS_LIST_SCROLL, c);
        openPane.add(ITEMS_LIST_SCROLL);
    }
}
