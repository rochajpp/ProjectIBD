package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.Database;
import src.entities.*;
import src.screens.*;

public class AlterScreen extends JFrame{

    private JLabel title, brandLabel, modelLabel, manufactureYearLabel, valueLabel;
    private JTextField brandInput ,modelInput, manufactureYearInput, valueInput;
    private JToggleButton send, back;


    public AlterScreen(Car car){
        initComponents(car);
        setSize(420, 380);
        setLocationRelativeTo(null);
    }

    private void initComponents(Car car){
        title = new JLabel("ALTERA");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        brandLabel = new JLabel("Marca:");
        brandInput = new JTextField(20);
        brandInput.setText(car.getBrand());
        
        modelLabel = new JLabel("Modelo:");
        modelInput = new JTextField(20);
        modelInput.setText(car.getModel());

        manufactureYearLabel = new JLabel("Ano de fabricação:");
        manufactureYearInput = new JTextField(20);
        manufactureYearInput.setText(Integer.toString(car.getManufactureYear()));
        
        valueLabel = new JLabel("Valor:");
        valueInput = new JTextField(20);
        valueInput.setText(Float.toString(car.getValue()));

        send = new JToggleButton("Alterar");
        send.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back = new JToggleButton("Voltar");
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        send.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                sendButton(evt);
            }
        });

        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                backButton(evt);
            }
        });



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
        panel.add(send, gbc);
        gbc.gridy++;
        panel.add(back, gbc);

        add(panel);
        setVisible(true);

    }

    private void sendButton(ActionEvent evt){

    }

    private void backButton(ActionEvent evt){
        this.dispose();
    }
}
