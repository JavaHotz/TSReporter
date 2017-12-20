package addressBook;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @author ashraf_sarhan
 * 
 */
public class ChangeRowColorRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		// Apply zebra style on table rows
		if (row % 2 == 0) {
			c.setBackground(Color.WHITE);
			c.setForeground(Color.BLACK);
		} else {
			c.setBackground(Color.decode("#686868"));
			c.setForeground(Color.WHITE);
		}
		//條件改變字體顏色方法
//		if (column == Constants.CHANGE_IDX
//				|| column == Constants.PERCENTAGE_CHANGE_IDX) {
//			Object priceChangeObj = table.getModel().getValueAt(row,
//					Constants.CHANGE_IDX);
//			double priceChange = Double.parseDouble(priceChangeObj.toString());
//			Color color;
//			if (priceChange > 0) {
//				color = Constants.PRICE_UP_COLOR;
//			} else {
//				color = Constants.PRICE_DOWN_COLOR;
//			}
//			c.setForeground(color);
//		} else {
//			c.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
//		}

		return c;
	}

}
