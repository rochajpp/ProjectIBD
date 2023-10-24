package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.entities.*;
import src.Database;

public class OptionsScreen extends JFrame {

    private JLabel carInfo;
    private JToggleButton removeButton;
    private JToggleButton alterButton; 
    private JToggleButton backButton;
    private Car car;
    private User user;

    public OptionsScreen(User user, Car car) {
        this.user = user;
        this.car = car;
        initComponents();
        setSize(420, 380);
        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setLocationRelativeTo(null); // Centralize a janela na tela
    }

    private void initComponents() {
        removeButton = new JToggleButton("Remover");
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(evt -> removeCarButton(evt));

        alterButton = new JToggleButton("Alterar");
        alterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        alterButton.addActionListener(evt -> alterCarButton(evt));

        backButton = new JToggleButton("Voltar");
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(evt -> backPageButton(evt));

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

        gbc.gridy++;
        contentPanel.add(backButton, gbc);

        setContentPane(contentPanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void alterCarButton(java.awt.event.ActionEvent evt) {
        new AlterScreen(this.user, car);
        this.dispose();
    }

    private void removeCarButton(java.awt.event.ActionEvent evt){
        Database d = new Database();
        
        boolean result = d.removeCar(this.car.getId());

        if(result){
            JOptionPane.showMessageDialog(null, "Carro removido com sucesso!");
            new CarsScreen(this.user, null);
            this.dispose();
        }
    }

    private void backPageButton(ActionEvent evt){
        new CarsScreen(this.user, null);
        this.dispose();
    }

}
