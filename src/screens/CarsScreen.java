package src.screens;

import java.util.List;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import src.Database;


import java.awt.event.ActionEvent;


import src.entities.*;

public class CarsScreen{
    public CarsScreen(User user){
        Database d = new Database();
        List<Car> carsUser = d.getCarsByIdUser(user.getId());

        JFrame frame = new JFrame("Lista de Carros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        DefaultListModel<Car> carListModel = new DefaultListModel<>();
        for (Car car : carsUser) {
            carListModel.addElement(car);
        }

        JList<Car> carList = new JList<>(carListModel);
        carList.setCellRenderer(new CarListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(carList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton callFunctionButton = new JButton("Adicionar carro");
        callFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterCarScreen(user.getId());
            }
        });

        frame.add(callFunctionButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

class CarListCellRenderer extends JPanel implements ListCellRenderer<Car> {
    private JLabel label;

    public CarListCellRenderer() {
        setLayout(new BorderLayout());
        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Car> list, Car car, int index, boolean isSelected, boolean cellHasFocus) {
        label.setText(car.getId() + " - " + car.getBrand() + " " + car.getModel() + " " + car.getManufactureYear() + " - " + car.getValue());

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a lógica para atualizar o carro aqui
                JOptionPane.showMessageDialog(label, "Carro Atualizado: " + car.getId());
            }
        });

        JButton deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a lógica para deletar o carro aqui
                JOptionPane.showMessageDialog(label, "Carro Deletado: " + car.getId());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        removeAll();
        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;

    }
}
