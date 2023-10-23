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
    private Car car;
    private User user;

    public AlterScreen(User user, Car car){
        this.user = user;
        this.car = car;
        initComponents();
        setSize(420, 380);
        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width- width) / 2, (screenSize.height - height) / 2);

        setLocationRelativeTo(null);
    }

    private void initComponents(){
        title = new JLabel("ALTERAR");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        brandLabel = new JLabel("Marca:");
        brandInput = new JTextField(20);
        brandInput.setText(this.car.getBrand());
        
        modelLabel = new JLabel("Modelo:");
        modelInput = new JTextField(20);
        modelInput.setText(this.car.getModel());

        manufactureYearLabel = new JLabel("Ano de fabricação:");
        manufactureYearInput = new JTextField(20);
        manufactureYearInput.setText(Integer.toString(this.car.getManufactureYear()));
        
        valueLabel = new JLabel("Valor:");
        valueInput = new JTextField(20);
        valueInput.setText(Double.toString(this.car.getValue()));

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
        int id = this.car.getId();
        String brandUpdate = brandInput.getText();
        String modelUpdate = modelInput.getText();
        int manufactureYearUpdate = Integer.parseInt(manufactureYearInput.getText());
        double valueUpdate = Double.parseDouble(valueInput.getText());
        
        Database d = new Database();
        boolean result = d.updateCar(id, brandUpdate, modelUpdate, manufactureYearUpdate, valueUpdate);

        if(result){
            JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso!");
            new CarsScreen(this.user);
            this.dispose();
            
        } else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, contate com o administrador do sistema");
            this.dispose();
        }
    }

    private void backButton(ActionEvent evt){
        new OptionsScreen(this.user, this.car);
        this.dispose();
    }
}
