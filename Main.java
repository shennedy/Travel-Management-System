import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Start with the initial login
                TMSMenu.TMS_Login_XO();
            }
        });
    }
}