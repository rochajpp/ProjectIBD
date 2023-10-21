package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Database;

public class RegisterScreen extends JFrame {

    private JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private JTextField jTextField1, jTextField2;
    private JPasswordField jPasswordField1;
    private JButton jToggleButton1, jToggleButton2;

    public RegisterScreen() {
        initComponents();
        setSize(420, 350);
        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width- width) / 2, (screenSize.height - height) / 2);
        setSize(420, 350);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jLabel1 = new JLabel("REGISTRAR");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));

        jLabel2 = new JLabel("Nome:");
        jTextField1 = new JTextField(20);

        jLabel3 = new JLabel("Usuário:");
        jTextField2 = new JTextField(20);

        jLabel4 = new JLabel("Senha:");
        jPasswordField1 = new JPasswordField(20);
        
        jToggleButton1 = new JButton("Registrar");
        jToggleButton2 = new JButton("Voltar");

        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        jToggleButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(jLabel1, gbc);

        gbc.gridy++;
        panel.add(jLabel2, gbc);

        gbc.gridy++;
        panel.add(jTextField1, gbc);

        gbc.gridy++;
        panel.add(jLabel3, gbc);

        gbc.gridy++;
        panel.add(jTextField2, gbc);

        gbc.gridy++;
        panel.add(jLabel4, gbc);

        gbc.gridy++;
        panel.add(jPasswordField1, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);

        Dimension buttonSize = new Dimension(150, 30);
        jToggleButton1.setPreferredSize(buttonSize);
        jToggleButton2.setPreferredSize(buttonSize);

        gbc.gridy++;
        panel.add(jToggleButton1, gbc);

        gbc.gridy++;
        panel.add(jToggleButton2, gbc);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        String name = jTextField1.getText();
        String user = jTextField2.getText();
        char[] pass = jPasswordField1.getPassword();
        String password = new String(pass);

        if (name.isEmpty() || user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            Database database = new Database();

            try {
                boolean register = database.registerUser(name, user, password);
                if (!register) {
                    JOptionPane.showMessageDialog(null, "Usuário já existente!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
                    new LoginScreen();
                    this.dispose();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        new LoginScreen();
        this.dispose();
    }
}
