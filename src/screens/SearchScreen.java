package src.screens;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.*;

import java.util.List;
import java.util.ArrayList;

import src.entities.*;
import src.screens.*;
import src.Database;

public class SearchScreen extends JFrame{

    //Declarando variáveis
    private JLabel title, searchLabel;
    private JTextField searchInput;
    private JToggleButton searchButton, backButton;

    private User user;

    public SearchScreen(User user){
        this.user = user;

        initComponents();

        //Configurações do JFrame
        setSize(400, 400);

        // int width = getWidth();
        // int height = getHeight();

        // setLocation((screenSize.width- width) / 2, (screenSize.height - height) / 2);

        //Configurando para centralizar a tela no monitor

        Dimension dimension = new Dimension();

        int width = getWidth();
        int height = getHeight();

        setLocation((dimension.width - width) / 2, (dimension.height - height) / 2);
        setLocationRelativeTo(null);
    }


    private void initComponents(){
        //Iniciando os componentes
        title = new JLabel("Procurar");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        searchLabel = new JLabel("Carro:");

        searchInput = new JTextField(20);

        searchButton = new JToggleButton("Procurar");
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(evt -> searchCar(evt));

        backButton = new JToggleButton("Voltar");
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(evt -> backButton(evt));



        //Criando o Painel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); //Definindo espçamento de 5 no top right left e bottom

        //Adicionando componentes ao painel
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(searchLabel, gbc);

        gbc.gridy++;
        panel.add(searchInput, gbc);

        gbc.gridy++;
        panel.add(searchButton, gbc);


        //Adicionando o apinel ao Frame
        add(panel);
        //Dexando o Frame visível
        setVisible(true);
    }

    private void searchCar(ActionEvent evt){

        List<Car> cars = new ArrayList<Car>();
        String search = searchInput.getText();

        Database d = new Database();

        cars = d.searchCars(this.user.getId(), search);
        new CarsScreen(this.user, cars);
        this.dispose();
    }

    private void backButton(ActionEvent evt){
        new CarsScreen(this.user, null);
        this.dispose();
    }
}
