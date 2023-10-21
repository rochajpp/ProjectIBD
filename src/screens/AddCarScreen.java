package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.Database;
import src.entities.*;
import src.screens.*;

public class AddCarScreen extends JFrame{

    private JLabel title, brandLabel, modelLabel, manufactureYearLabel, valueLabel;
    private JTextField brandInput, modelInput, manufactureYearInput, valueInput;
    private JToggleButton add, back;

    private User user;

    public AddCarScreen(User user){
        this.user = user;

        initComponents();

        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setSize(450, 380);
        setLocationRelativeTo(null); // Centralize a janela na tela
    }


    private void initComponents(){
        title = new JLabel("ADICIONAR CARRO");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        brandLabel = new JLabel("Marca:");
        brandInput = new JTextField(20);
        
        modelLabel = new JLabel("Modelo:");
        modelInput = new JTextField(20);

        manufactureYearLabel = new JLabel("Ano de fabricação");
        manufactureYearInput = new JTextField(20);

        valueLabel = new JLabel("Valor:");
        valueInput = new JTextField(20);

        add = new JToggleButton("Adicionar");
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        back = new JToggleButton("Voltar");
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add.addActionListener(evt -> addCar(evt));
        back.addActionListener(evt -> backButton(evt));

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);
        gbc.gridy++;

        gbc.gridy++;
        panel.add(brandLabel, gbc);
        gbc.gridy++;
        panel.add(brandInput, gbc);

        gbc.gridy++;
        panel.add(modelLabel, gbc);
        gbc.gridy++;
        panel.add(modelInput, gbc);

        gbc.gridy++;
        panel.add(manufactureYearLabel, gbc);
        gbc.gridy++;
        panel.add(manufactureYearInput, gbc);
        
        gbc.gridy++;
        panel.add(valueLabel, gbc);
        gbc.gridy++;
        panel.add(valueInput, gbc);
        gbc.gridy++;
        
        gbc.gridy++;
        panel.add(add, gbc);
        gbc.gridy++;
        panel.add(back, gbc);

        add(panel);
        setVisible(true);

    }

    private void addCar(ActionEvent evt){
        Database d = new Database();

        int idUser = this.user.getId();
        String brand = brandInput.getText();
        String model = modelInput.getText();
        int manufactureYear = Integer.parseInt(manufactureYearInput.getText());
        float value = Float.parseFloat(valueInput.getText());

        
        boolean result = d.addCar(idUser, brand, model, manufactureYear, value);

        if(result){
            JOptionPane.showMessageDialog(null, "Carro adicionado com sucesso!");
            new CarsScreen(this.user);
            this.dispose();
        } else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, contate o administrador do sistema");
        }
    }

    private void backButton(ActionEvent evt){
        new CarsScreen(this.user);
        this.dispose();
    }
    
}

