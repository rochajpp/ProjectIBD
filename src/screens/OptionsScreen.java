package src.screens;

import javax.swing.*;
import java.awt.*;
import src.entities.*;

public class OptionsScreen extends JFrame {

    private JLabel carInfo;
    private JToggleButton removeButton;
    private JToggleButton alterButton;
    private int idUser;
    private Car car;

    public OptionsScreen(int idUser, Car car) {
        this.idUser = idUser;
        this.car = car;
        initComponents();
        setSize(420, 380);
    }

    private void initComponents() {
        removeButton = new JToggleButton("Remover");
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(evt -> removeCarButton(evt));

        alterButton = new JToggleButton("Alterar");
        alterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        alterButton.addActionListener(evt -> alterCarButton(evt));

        carInfo = new JLabel(car.getId() + " - " + car.getBrand() + " " + car.getModel() + " - " + car.getManufactureYear() + " - " + car.getValue());
        carInfo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento interno

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span 2 colunas
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(carInfo, gbc);
  

        gbc.gridy = 2;
        contentPanel.add(alterButton, gbc);

        gbc.gridy = 3;
        contentPanel.add(removeButton, gbc);

        setContentPane(contentPanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void alterCarButton(java.awt.event.ActionEvent evt) {
        new AlterScreen(car);
    }

    private void removeCarButton(java.awt.event.ActionEvent evt){
        
    }

}
