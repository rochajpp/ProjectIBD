package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Database;
import src.entities.User;

public class LoginScreen extends JFrame {

    private JLabel jLabel1, jLabel2, jLabel3;
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jToggleButton1, jToggleButton2;

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
        jLabel1 = new JLabel("LOGIN");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));

        jLabel2 = new JLabel("Usuário:");
        jLabel3 = new JLabel("Senha:");

        jTextField1 = new JTextField(18);
        jPasswordField1 = new JPasswordField(18);

        jToggleButton1 = new JButton("Entrar");
        jToggleButton2 = new JButton("Registrar");

        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jToggleButton2.addActionListener(new ActionListener() {
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
        panel.add(jLabel1, gbc);

        gbc.gridy = 1;
        panel.add(jLabel2, gbc);

        gbc.gridy = 2;
        panel.add(jTextField1, gbc);

        gbc.gridy = 3;
        panel.add(jLabel3, gbc);

        gbc.gridy = 4;
        panel.add(jPasswordField1, gbc);


        Dimension buttonSize = new Dimension(100, 28); // Largura x Altura
        jToggleButton1.setPreferredSize(buttonSize);
        jToggleButton2.setPreferredSize(buttonSize);

        gbc.gridy = 5;
        panel.add(jToggleButton1, gbc);

        gbc.gridy = 6;
        panel.add(jToggleButton2, gbc);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
    }

    private void loginButtonActionPerformed(ActionEvent evt) {
        String user = jTextField1.getText();
        char[] pass = jPasswordField1.getPassword();
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
