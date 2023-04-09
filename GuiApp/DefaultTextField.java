package GuiApp;

import java.awt.Font;

import javax.swing.JTextField;

public class DefaultTextField extends JTextField{
    public DefaultTextField(String text, int columns) {
        super(text, columns);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
