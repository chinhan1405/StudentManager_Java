package GuiApp;

import java.awt.Font;

import javax.swing.JLabel;

public class DefaultLabel extends JLabel{
    public DefaultLabel(String text) {
        super(text, JLabel.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
