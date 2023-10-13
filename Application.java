import javax.swing.SwingUtilities;

import screens.LoginScreen;

public class Application {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen();
            }
        });
    }
}
