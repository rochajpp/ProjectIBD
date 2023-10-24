
import javax.swing.SwingUtilities;

import src.screens.*;

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
