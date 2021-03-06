package com.gumeniuk;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gumeniuk.Resources.Resources;

public class FirstFrame extends JFrame {

    private final JButton button;
    private final JTextField textField;
    private final JLabel label;

    private final ActionListener actionListener = e -> {
        switch (e.getActionCommand()) {
            case "push":
                changeLabelText();
                break;
        }
    };


    private void changeLabelText() {
        label.setText(textField.getText());
        textField.setText(null);
    }

    public FirstFrame() throws HeadlessException {
        super(Resources.string("application.title"));

        label = new JLabel(Resources.string("label.message"), JLabel.CENTER);
        button = new JButton(Resources.string("button.push.me.text"));
        textField = new JTextField(16);

        initComponents();
    }

    private final CaretListener caretListener = new CaretListener() {
        @Override
        public void caretUpdate(CaretEvent e) {
            button.setEnabled(!textField.getText().isEmpty());
        }
    };

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        setContentPane(createContentPane());

        button.addActionListener(actionListener);
        button.setActionCommand(Resources.string("button.push.me.action"));
        button.setEnabled(false);

        textField.addCaretListener(caretListener);

        add(textField, BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);

        add(label, BorderLayout.CENTER);

        pack();
    }

    private int getGap(JComponent component) {
        LayoutStyle style = LayoutStyle.getInstance();
        return style.getContainerGap(component, SwingConstants.NORTH, null);
    }

    private Container createContentPane() {
        Dimension dimension = new Dimension(350, 200);

        JPanel panel = new JPanel();

        int vgap = getGap(panel);

        Border border = BorderFactory.createEmptyBorder(vgap, vgap, vgap, vgap);
        panel.setPreferredSize(dimension);

        panel.setBorder(border);

        return panel;
    }
}
