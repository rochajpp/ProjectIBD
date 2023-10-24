package src.screens;

import src.entities.*;
import src.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CarsScreen extends JFrame{
    private User user;

    public CarsScreen(User user, List<Car> cars) {
        this.user = user;
        Database database = new Database();
        List<Car> carsUser;
        if(cars == null){
            carsUser = database.getCarsByIdUser(user.getId());
        }else{
            carsUser = cars;
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        DefaultListModel<Car> carListModel = new DefaultListModel<>();
        carListModel.addAll(carsUser);

        JList<Car> carList = new JList<>(carListModel);
        carList.setCellRenderer(new CarListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(carList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // Painel para os botões
        add(buttonPanel, BorderLayout.SOUTH);

        JButton addCarButton = new JButton("Adicionar Carro");
        addCarButton.addActionListener(e -> addCarButtonActionPerformed(user));
        buttonPanel.add(addCarButton);

        JButton searchCarButton = new JButton("Procurar");
        searchCarButton.addActionListener(e -> searchCarButtonActionPerformed(user));
        buttonPanel.add(searchCarButton);

        JButton updateCarButton = new JButton("Atualizar");
        updateCarButton.addActionListener(e -> updateCarButtonActionPerformed(user));
        buttonPanel.add(updateCarButton);

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
        setTitle("Abner");
    }

    private void executeFunction(Car selectedCar, int idUser) {
        new OptionsScreen(this.user, selectedCar);
        this.dispose();
    }

    private void addCarButtonActionPerformed(User user) {
        new AddCarScreen(this.user);
        this.dispose();
    }

     private void searchCarButtonActionPerformed(User user) {
        new SearchScreen(this.user);
        this.dispose();
    }

     private void updateCarButtonActionPerformed(User user) {
        new CarsScreen(this.user, null);
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
        label.setText((index + 1) + " - " + car.getBrand() + " " + car.getModel() + " " + car.getManufactureYear() + " - " + car.getValue());

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
