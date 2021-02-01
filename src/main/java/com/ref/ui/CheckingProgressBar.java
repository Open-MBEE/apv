package com.ref.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class CheckingProgressBar extends JFrame implements FocusListener {

    private boolean step1;
    private boolean step2;
    private boolean step3;
    private boolean step4;
    private boolean step5;

    private int typeAssertion;
    private JProgressBar progressBar;
    private JButton closeButton;
    private JLabel textLabel;
    private StringBuilder text = new StringBuilder();
    private ImageIcon loadGif = new ImageIcon(getPath(new String[]{"src", "main", "java", "com", "ref", "ui", "icons", "loading.gif"}));
    private ImageIcon correctIco = new ImageIcon(getPath(new String[]{"src", "main", "java", "com", "ref", "ui", "icons", "ico_correct.png"}));
    private ImageIcon errorIco = new ImageIcon(getPath(new String[]{"src", "main", "java", "com", "ref", "ui", "icons", "ico_error.png"}));
    public CheckingProgressBar() {
        startElements();
    }

    private void startElements() {
        setSize(380, 200);
        Container contentPane = getContentPane();
        textLabel = new JLabel();
        System.out.println(getClass().getResource(""));
        textLabel.setIcon(loadGif);
        textLabel.setHorizontalAlignment(JLabel.LEFT);
        textLabel.setVerticalAlignment(JLabel.TOP);
        // set up panel with button and progress bar
        JPanel panel = new JPanel();
        closeButton = new JButton("Close");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMaximum(5);
        reset();
        panel.add(progressBar);
        panel.add(closeButton);
        contentPane.add(new JScrollPane(textLabel), "Center");
        contentPane.add(panel, "North");
        contentPane.setVisible(true);

        closeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public void setNewTitle(String title) {
        this.setTitle(title);
    }

    public void setAssertion(int assertion) {
        this.typeAssertion = assertion;
    }

    private void reset() {
        step1 = false;
        step1 = false;
        step3 = false;
        step4 = false;
        step5 = false;
        progressBar.setValue(0);
        closeButton.setEnabled(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }

    private void handleValue(String log, boolean correct){
        int value = progressBar.getValue();

        if (!step1 && value == 1) {
            text.append("Translating diagram to CSP...<br>");
            step1 = true;
        } else if (!step2 && value == 2) {
            if (typeAssertion == 0) {
                text.append("Checking for deadlock...<br>");
            } else {
                text.append("Checking for non-determinism...<br>");
            }
            step2 = true;
        } else if (!step3 && value == 3) {
            text.append("Creating counterexamples...<br>");
            step3 = true;
        } else if (!step4 && value == 4) {
            text.append("Finished!<br>");
            step4 = true;
        } else if (!step5 && value == 5) {
            text.append(log);

            if (correct) {
                textLabel.setIcon(correctIco);
            } else {
                textLabel.setIcon(errorIco);
            }

            step5 = true;
        }

        textLabel.setText("<html>" + text.toString() + "</html>");


        if (value == 5) {
            closeButton.setEnabled(true);
        }
    }

    public void setProgress(int value, String log, boolean correct) {
        progressBar.setValue(value);
        handleValue(log, correct);
    }

    private String getPath(String[] path) {
        String fs = System.getProperty("file.separator");
        String pathText = System.getProperty("user.dir");

        for (int i = 0; i < path.length; i++) {
            pathText += fs + path[i];
        }

        return pathText;
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        setAlwaysOnTop(false);
        setAlwaysOnTop(true);
    }
}
