package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static view.Panes.CREATE_EVENT;
import static view.Panes.createEventPane;

/**
 * Created by Алена on 28.11.2017.
 */
public class CreateEvent implements ActionListener {
    private AddEvent addEvent;

    public CreateEvent() {
        addEvent = new AddEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createEvent = new JFrame();
        createEvent.setTitle("Event parameters");

        createEvent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                createEvent.dispose();
                addEvent = null;
            }
        });

        createEvent.setPreferredSize(new Dimension(300, 120));
        createEvent.setSize(300, 120);
        createEvent.pack();
        createEvent.setLocationRelativeTo(null);
        createEvent.setContentPane(createEventPane);
        addActions();

        createEvent.setVisible(true);
    }

    private void addActions() {
        CREATE_EVENT.addActionListener(addEvent);
    }
}
