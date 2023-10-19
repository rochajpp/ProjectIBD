package src.screens;

import src.entities.*;
import src.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CarsScreen {
    public CarsScreen(User user) {
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

        JButton addCarButton = new JButton("Adicionar Carro");
        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterCarScreen(user.getId());
            }
        });
        frame.add(addCarButton, BorderLayout.SOUTH);

        // Adiciona um ListSelectionListener para detectar a seleção de carros
        carList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = carList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        // Aqui você pode executar a função desejada com base no carro selecionado
                        Car selectedCar = carListModel.get(selectedIndex);
                        executeFunction(selectedCar);
                    }
                }
            }
        });

        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = frame.getWidth();
        int height = frame.getHeight();
        frame.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
    }

    // Função para executar com base no carro selecionado
    private void executeFunction(Car selectedCar) {
        // Substitua pelo código que você deseja executar com base no carro selecionado
        JOptionPane.showMessageDialog(null, "Carro selecionado: " + selectedCar.getId());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User user = new User(); // Substitua por seu próprio objeto de usuário
            new CarsScreen(user);
        });
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



