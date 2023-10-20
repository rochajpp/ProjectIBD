package src.screens;

import src.entities.*;
import src.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CarsScreen {
    public CarsScreen(User user) {
        Database database = new Database();
        List<Car> carsUser = database.getCarsByIdUser(user.getId());

        JFrame frame = new JFrame("Lista de Carros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        DefaultListModel<Car> carListModel = new DefaultListModel<>();
        carListModel.addAll(carsUser);

        JList<Car> carList = new JList<>(carListModel);
        carList.setCellRenderer(new CarListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(carList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton addCarButton = new JButton("Adicionar Carro");
        addCarButton.addActionListener(e -> addCarButtonActionPerformed(user));
        frame.add(addCarButton, BorderLayout.SOUTH);

        carList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = carList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Car selectedCar = carListModel.getElementAt(selectedIndex);
                    executeFunction(selectedCar, user.getId());
                }
            }
        });

        frame.setVisible(true);
    }

    private void executeFunction(Car selectedCar, int idUser) {
        new OptionsScreen(idUser, selectedCar);
    }

    private void addCarButtonActionPerformed(User user) {
        // Implementar a ação para adicionar um novo carro aqui
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
