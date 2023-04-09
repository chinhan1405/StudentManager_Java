package GuiApp;

import java.awt.Font;

import javax.swing.JTable;

public class DefaultTable extends JTable{
    public DefaultTable(Object[][] data, String[] cols) {
        super(data, cols);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
