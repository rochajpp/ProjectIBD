package src.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Database;

public class RegisterScreen extends JFrame {

    private JLabel title, name, cpf, username, password;
    private JTextField nameInput, cpfInput, usernameInput;
    private JPasswordField passwordInput;
    private JButton send, back;

    public RegisterScreen() {
        initComponents();
        setSize(400, 420);
        Dimension screenSize = getToolkit().getScreenSize();

        int width = getWidth();
        int height = getHeight();

        setLocation((screenSize.width- width) / 2, (screenSize.height - height) / 2);
    
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        title = new JLabel("REGISTRAR");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        name = new JLabel("Nome:");
        nameInput = new JTextField(20);

        cpf = new JLabel("CPF:");
        cpfInput = new JTextField(20);

        username = new JLabel("Usuário:");
        usernameInput = new JTextField(20);

        password = new JLabel("Senha:");
        passwordInput = new JPasswordField(20);
        
        send = new JButton("Registrar");
        back = new JButton("Voltar");

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(name, gbc);

        gbc.gridy++;
        panel.add(nameInput, gbc);

        gbc.gridy++;
        panel.add(cpf, gbc);

        gbc.gridy++;
        panel.add(cpfInput, gbc);

        gbc.gridy++;
        panel.add(username, gbc);

        gbc.gridy++;
        panel.add(usernameInput, gbc);

        gbc.gridy++;
        panel.add(password, gbc);

        gbc.gridy++;
        panel.add(passwordInput, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);

        Dimension buttonSize = new Dimension(150, 30);
        send.setPreferredSize(buttonSize);
        back.setPreferredSize(buttonSize);

        gbc.gridy++;
        panel.add(send, gbc);

        gbc.gridy++;
        panel.add(back, gbc);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        String name = nameInput.getText();
        String cpf = cpfInput.getText();
        String user = usernameInput.getText();
        char[] pass = passwordInput.getPassword();
        String password = new String(pass);

        if(cpf.length() != 11){
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return;
        }

        if(!cpfScanner(cpf)){
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return;
        }

        if (name.isEmpty() || user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }


        Database database = new Database();

        int register = database.registerUser(name, cpf, user, password);
        if(register == 0){
            JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
        }else if (register == 1) {
            JOptionPane.showMessageDialog(null, "Usuário já existente!");
        } else if(register == 2){
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
            new LoginScreen();
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, contate o administrado do sistema!");
        }      
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        new LoginScreen();
        this.dispose();
    }
    

    private boolean cpfScanner(String cpf) {
       
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);


        if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
            return false;
        }


        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);


        if (Character.getNumericValue(cpf.charAt(10)) != digito2) {
            return false;
        }

        return true;
    }
}
