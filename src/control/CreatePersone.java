package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static view.Panes.CREATE_PERSONE;
import static view.Panes.createPersonePane;

/**
 * Created by Алена on 28.11.2017.
 */
public class CreatePersone implements ActionListener {
    AddPersone addPersone = new AddPersone();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame createPersone = new JFrame();
        createPersone.setTitle("Persone parameters");

        createPersone.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                createPersone.dispose();
                addPersone = null;
            }
        });

        createPersone.setPreferredSize(new Dimension(300, 150));
        createPersone.setSize(300, 150);
        createPersone.pack();
        createPersone.setLocationRelativeTo(null);
        createPersone.setContentPane(createPersonePane);
        addActions();

        createPersone.setVisible(true);
    }

    private void addActions() {
        CREATE_PERSONE.addActionListener(addPersone);
    }
}
