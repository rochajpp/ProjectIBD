package src.screens;

import src.entities.*;
import src.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CarsScreen extends JFrame{
    private User user;
    public CarsScreen(User user) {
        this.user = user;
        Database database = new Database();
        List<Car> carsUser = database.getCarsByIdUser(user.getId());

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        DefaultListModel<Car> carListModel = new DefaultListModel<>();
        carListModel.addAll(carsUser);

        JList<Car> carList = new JList<>(carListModel);
        carList.setCellRenderer(new CarListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(carList);
        add(scrollPane, BorderLayout.CENTER);

        JButton addCarButton = new JButton("Adicionar Carro");
        addCarButton.addActionListener(e -> addCarButtonActionPerformed(user));
        add(addCarButton, BorderLayout.SOUTH);

        carList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = carList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Car selectedCar = carListModel.getElementAt(selectedIndex);
                    executeFunction(selectedCar, user.getId());
                }
            }
        });

        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setSize(420, 350);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void executeFunction(Car selectedCar, int idUser) {
        new OptionsScreen(this.user, selectedCar);
        this.dispose();
    }

    private void addCarButtonActionPerformed(User user) {
        new AddCarScreen(this.user);
        this.dispose();
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
