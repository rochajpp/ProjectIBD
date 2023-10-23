package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Database;
import src.entities.User;

public class LoginScreen extends JFrame {

    private JLabel title, usernameLabel, passwordLabel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton send, register;

    public LoginScreen() {
        initComponents();
        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width- width) / 2, (screenSize.height - height) / 2);
        setSize(420, 350);
        setLocationRelativeTo(null); // Centralize a janela na tela
    }

    private void initComponents() {
        title = new JLabel("LOGIN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        usernameLabel = new JLabel("Usuário:");
        passwordLabel = new JLabel("Senha:");

        usernameInput = new JTextField(18);
        passwordInput = new JPasswordField(18);

        send = new JButton("Entrar");
        register = new JButton("Registrar");

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridy = 2;
        panel.add(usernameInput, gbc);

        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        gbc.gridy = 4;
        panel.add(passwordInput, gbc);


        Dimension buttonSize = new Dimension(100, 28); // Largura x Altura
        send.setPreferredSize(buttonSize);
        register.setPreferredSize(buttonSize);

        gbc.gridy = 5;
        panel.add(send, gbc);

        gbc.gridy = 6;
        panel.add(register, gbc);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
    }

    private void loginButtonActionPerformed(ActionEvent evt) {
        String user = usernameInput.getText();
        char[] pass = passwordInput.getPassword();
        String password = new String(pass);

        Database database = new Database();
        User userLogin = database.checkLogin(user, password);

        if (userLogin.getId() == -1) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, contate o administrador!");
        } else if (userLogin.getUser() == null) {
            JOptionPane.showMessageDialog(null, "Credenciais incorretas!");
        } else {
            JOptionPane.showMessageDialog(null, "Logado com sucesso!");
            new CarsScreen(userLogin);
            dispose();
        }
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        new RegisterScreen();
        dispose();
    }
}
