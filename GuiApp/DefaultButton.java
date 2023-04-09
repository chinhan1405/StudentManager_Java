package GuiApp;

import java.awt.Font;

import javax.swing.JButton;

public class DefaultButton extends JButton{
    public DefaultButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 16));
    }
}
