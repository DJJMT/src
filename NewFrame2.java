//import statements here
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.BorderLayout;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.GregorianCalendar;

public class NewFrame2 extends JFrame implements ActionListener {
	static JLabel Month_Label, Year_Label;
	static JButton Last_Button, Next_Button, New_Appt_Button,
			Delete_Appt_Button;
	static JTable Calendar_Table;
	static JComboBox Year_ComboBox;
	static JFrame Main_JFrame;
	static Container pane;
	static DefaultTableModel Calendar_TableModel; // Table model
	static JScrollPane Calendar_ScrollPane; // The scrollpane
	static JPanel Calendar_Panel;
	static int Year_Actual, Month_Actual, Day_Actual, Year_Current,
			Month_Current;

	// initialises the frame and opens it
	public NewFrame2() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		// Get Screen Size
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = (int) screen.getWidth();
		int height = (int) screen.getHeight();

		// Prepare frame
		Main_JFrame = new JFrame("Team J Calendar App"); // Create frame
		Main_JFrame.setSize(width - 50, height - 50); // Set size
		pane = Main_JFrame.getContentPane(); // Get content pane
		pane.setLayout(null); // Apply null layout
		Main_JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close
																	// when X is
																	// clicked

		// Create controls
		Month_Label = new JLabel("January");
		Year_Label = new JLabel("Change year:");
		Year_ComboBox = new JComboBox();
		Last_Button = new JButton("LAST");
		Next_Button = new JButton("NEXT");
		New_Appt_Button = new JButton("New Appointment");
		Delete_Appt_Button = new JButton("Delete Appointment");
		Calendar_TableModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		Calendar_Table = new JTable(Calendar_TableModel);
		Calendar_ScrollPane = new JScrollPane(Calendar_Table);
		Calendar_Panel = new JPanel(null);

		// Set border
		Calendar_Panel.setBorder(BorderFactory.createTitledBorder("Calendar"));

		// Register action listeners
		Last_Button.addActionListener(new Last_Button_Action());
		Next_Button.addActionListener(new Next_Button_Action());
		New_Appt_Button.addActionListener(new New_Appt_Button_Action());
		Delete_Appt_Button.addActionListener(new Delete_Appt_Button_Action());
		Year_ComboBox.addActionListener(new Year_ComboBox_Action());

		// Add controls to pane
		pane.add(Calendar_Panel);
		Calendar_Panel.add(Month_Label);
		Calendar_Panel.add(Year_Label);
		Calendar_Panel.add(Year_ComboBox);
		Calendar_Panel.add(Last_Button);
		Calendar_Panel.add(Next_Button);
		Calendar_Panel.add(New_Appt_Button);
		Calendar_Panel.add(Delete_Appt_Button);
		Calendar_Panel.add(Calendar_ScrollPane);

		// Set bounds (x,y,width,height)
		Calendar_Panel.setBounds(0, 0, width - 50, height - 50);
		Month_Label.setBounds((width - 50) / 2 - 50, 25, 100, 25);
		Year_Label.setBounds(width - 270, height - 120, 80, 25);
		Year_ComboBox.setBounds(width - 180, height - 120, 80, 25);
		Last_Button.setBounds(((width - 50) / 2) - 218, 25, 75, 25);
		Next_Button.setBounds(((width - 50) / 2) + 75, 25, 75, 25);
		New_Appt_Button.setBounds(0, 25, 150, 25);
		Delete_Appt_Button.setBounds(155, 25, 150, 25);
		Calendar_ScrollPane.setBounds(10, 55, width - 65, ((height - 200) - 5));

		// Make frame visible
		Main_JFrame.setResizable(false);
		Main_JFrame.setVisible(true);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		Day_Actual = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		Month_Actual = cal.get(GregorianCalendar.MONTH); // Get month
		Year_Actual = cal.get(GregorianCalendar.YEAR); // Get year
		Month_Current = Month_Actual; // Match month and year
		Year_Current = Year_Actual;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat" }; // All
																					// headers
		for (int i = 0; i < 7; i++) {
			Calendar_TableModel.addColumn(headers[i]);
		}

		Calendar_Table.getParent()
				.setBackground(Calendar_Table.getBackground()); // Set
																// background

		// No resize/reorder
		Calendar_Table.getTableHeader().setResizingAllowed(false);
		Calendar_Table.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		Calendar_Table.setColumnSelectionAllowed(true);
		Calendar_Table.setRowSelectionAllowed(true);
		Calendar_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		Calendar_Table.setRowHeight(((height - 200) - 5) / 6);
		Calendar_TableModel.setColumnCount(7);
		Calendar_TableModel.setRowCount(6);

		// Populate table
		for (int i = Year_Actual - 100; i <= Year_Actual + 100; i++) {
			Year_ComboBox.addItem(String.valueOf(i));
		}

		// Refresh calendar
		refreshCalendar(Month_Actual, Year_Actual); // Refresh calendar
	}

	public static void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int Num_Days, Start_Of_Month; // Number Of Days, Start Of Month
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screen.getWidth();

		// Allow/disallow buttons
		Last_Button.setEnabled(true);
		Next_Button.setEnabled(true);
		New_Appt_Button.setEnabled(true);
		Delete_Appt_Button.setEnabled(true);
		if (month == 0 && year <= Year_Actual - 10) {
			Last_Button.setEnabled(false);
		} // Too early
		if (month == 11 && year >= Year_Actual + 100) {
			Next_Button.setEnabled(false);
		} // Too late
		Month_Label.setText(months[month]); // Refresh the month label (at the
											// top)
		Month_Label.setBounds((width - 50) / 2 - 50, 25, 100, 25); // Re-align
																	// label
																	// with
																	// calendar
		Year_ComboBox.setSelectedItem(String.valueOf(year)); // Select the
																// correct year
																// in the combo
																// box

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				Calendar_TableModel.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		Num_Days = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		Start_Of_Month = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= Num_Days; i++) {
			int row = new Integer((i + Start_Of_Month - 2) / 7);
			int column = (i + Start_Of_Month - 2) % 7;
			Calendar_TableModel.setValueAt(i, row, column);
		}

		// Apply renderers
		Calendar_Table.setDefaultRenderer(Calendar_Table.getColumnClass(0),
				new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean selected, boolean focused, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, selected,
					focused, row, column);
			if (column == 0 || column == 6) { // Week-end
				setBackground(new Color(159, 219, 245)); // blue
			} else { // Week
				setBackground(new Color(255, 255, 255)); // white
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == Day_Actual
						&& Month_Current == Month_Actual
						&& Year_Current == Year_Actual) { // Today
					setBackground(new Color(220, 220, 255)); // white
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	static class Last_Button_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Month_Current == 0) { // Back one year
				Month_Current = 11;
				Year_Current -= 1;
			} else { // Back one month
				Month_Current -= 1;
			}
			refreshCalendar(Month_Current, Year_Current);
		}
	}

	static class Next_Button_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Month_Current == 11) { // Foward one year
				Month_Current = 0;
				Year_Current += 1;
			} else { // Foward one month
				Month_Current += 1;
			}
			refreshCalendar(Month_Current, Year_Current);
		}
	}

	static class New_Appt_Button_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Appt_Frame();

		}
	}

	static class Delete_Appt_Button_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// new Delete_Appt_Frame();

		}
	}

	static class Year_ComboBox_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Year_ComboBox.getSelectedItem() != null) {
				String b = Year_ComboBox.getSelectedItem().toString();
				Year_Current = Integer.parseInt(b);
				refreshCalendar(Month_Current, Year_Current);
			}
		}
	}

	public void actionPerformed(ActionEvent event) {
		// code for the new frame
	}

}