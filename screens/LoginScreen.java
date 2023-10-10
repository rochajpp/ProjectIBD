package screens;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JFrame;

public class LoginScreen extends JFrame{
    public LoginScreen(){
        // Configure a janela
        setTitle("Minha Janela");
        setSize(800, 600); // Defina o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação padrão ao fechar a janela


        //==== Configurando Painel ======================
        JPanel panel = new JPanel();
        panel.add(Box.createVerticalStrut(100));
        //===============================================

        
        //====Fontes ================================================
        Font fontTitle = new Font("Arial", Font.BOLD, 28);
        //============================================================


        //==== Configurando o título LOGIN ====================
        JLabel title = new JLabel("LOGIN");
        title.setFont(fontTitle);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        //=====================================================

        panel.add(title);
        
        add(panel); //Adicional panel

        setVisible(true);
    }
}
