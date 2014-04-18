//Appointment Scheduler Frame
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appt_Frame extends JFrame // implements ActionListener
{
	JPanel pane = new JPanel(); // create pane object

	// Text field and Combo Box Labels
	JLabel Title_Label = new JLabel("Event Title: ");
	JLabel Description_Label = new JLabel("Description: ");
	JLabel Location_Label = new JLabel("Location: ");
	JLabel Start_Time_Label = new JLabel("Start Time: ");
	JLabel End_Time_Label = new JLabel("End Time: ");
	JLabel Month_Label = new JLabel("Month: ");
	JLabel Date_Label = new JLabel("Date: ");
	JLabel Year_Label = new JLabel("Year: ");

	// Text Fields
	JTextField Title_Box = new JTextField();
	JTextField Description_Box = new JTextField();
	JTextField Location_Box = new JTextField();
	JTextField Date = new JTextField();
	JTextField Year = new JTextField();

	// Combo Boxes
	JComboBox Start_Hr, Start_Min, End_Hr, End_Min, Month, startAM_PM,
			endAM_PM;

	// Buttons
	JButton Create_Button = new JButton("Create"); // create appointment
	JButton Cancel_Button = new JButton("Cancel"); // cancel create appointment
	JPanel buttonPanel = new JPanel();

	public Container con = this.getContentPane();

	// initialises the frame and opens it
	public Appt_Frame() {
		super("New Appointment");
		setBounds(550, 100, 500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		con.add(pane);
		pane.setLayout(null);

		// LABEL -- TITLE_LABEL
		Title_Label.setLayout(null);
		Title_Label.setLocation(10, 10); // (x,y)
		Title_Label.setSize(80, 25); // (width,height)
		pane.add(Title_Label);

		// LABEL -- DESCRIPTION_LABEL
		Description_Label.setLayout(null);
		Description_Label.setLocation(10, 40); // (x,y)
		Description_Label.setSize(80, 25); // (width,height)
		pane.add(Description_Label);

		// LABEL -- LOCATION_LABEL
		Location_Label.setLayout(null);
		Location_Label.setLocation(10, 70); // (x,y)
		Location_Label.setSize(80, 25); // (width,height)
		pane.add(Location_Label);

		// LABEL -- START_TIME_LABEL
		Start_Time_Label.setLayout(null);
		Start_Time_Label.setLocation(10, 100); // (x,y)
		Start_Time_Label.setSize(80, 25); // (width,height)
		pane.add(Start_Time_Label);

		// LABEL -- END_TIME_LABEL
		End_Time_Label.setLayout(null);
		End_Time_Label.setLocation(10, 130); // (x,y)
		End_Time_Label.setSize(80, 25); // (width,height)
		pane.add(End_Time_Label);

		// LABEL -- MONTH_LABEL
		Month_Label.setLayout(null);
		Month_Label.setLocation(10, 160); // (x,y)
		Month_Label.setSize(80, 25); // (width,height)
		pane.add(Month_Label);

		// LABEL -- DATE_LABEL
		Date_Label.setLayout(null);
		Date_Label.setLocation(10, 190); // (x,y)
		Date_Label.setSize(80, 25); // (width,height)
		pane.add(Date_Label);

		// LABEL -- YEAR_LABEL
		Year_Label.setLayout(null);
		Year_Label.setLocation(10, 220); // (x,y)
		Year_Label.setSize(80, 25); // (width,height)
		pane.add(Year_Label);

		// TEXT FIELD -- TITLE_BOX
		Title_Box.setLayout(null);
		Title_Box.setLocation(82, 10); // (x,y)
		Title_Box.setSize(210, 25); // (width,height)
		pane.add(Title_Box);

		// TEXT FIELD -- DESCRIPTION_BOX
		Description_Box.setLayout(null);
		Description_Box.setLocation(82, 40); // (x,y)
		Description_Box.setSize(210, 25); // (width,height)
		pane.add(Description_Box);

		// TEXT FIELD -- LOCATION_BOX
		Location_Box.setLayout(null);
		Location_Box.setLocation(82, 70); // (x,y)
		Location_Box.setSize(210, 25); // (width,height)
		pane.add(Location_Box);

		// COMBO BOX -- START_HR
		String[] s_hr = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12" };
		Start_Hr = new JComboBox(s_hr);
		// Start_Hr.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		Start_Hr.setLocation(82, 100);
		Start_Hr.setSize(50, 25);
		pane.add(Start_Hr);

		// COMBO BOX -- START_MIN
		String[] s_min = { " ", "00", "05", "10", "15", "20", "25", "30", "35",
				"40", "45", "50", "55" };
		Start_Min = new JComboBox(s_min);
		// Start_Min.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		Start_Min.setLocation(135, 100);
		Start_Min.setSize(50, 25);
		pane.add(Start_Min);

		// COMBO BOX -- startAM_PM
		String[] s_am_pm = { " ", "AM", "PM" };
		startAM_PM = new JComboBox(s_am_pm);
		startAM_PM.setLocation(190, 100);
		startAM_PM.setSize(50, 25);
		pane.add(startAM_PM);

		// COMBO BOX -- END_HR
		String[] e_hr = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12" };
		End_Hr = new JComboBox(e_hr);
		// End_Hr.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		End_Hr.setLocation(82, 130);
		End_Hr.setSize(50, 25);
		pane.add(End_Hr);

		// COMBO BOX -- END_MIN
		String[] e_min = { " ", "00", "05", "10", "15", "20", "25", "30", "35",
				"40", "45", "50", "55" };
		End_Min = new JComboBox(e_min);
		// End_Min.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		End_Min.setLocation(135, 130);
		End_Min.setSize(50, 25);
		pane.add(End_Min);

		// COMBO BOX -- endAM_PM
		String[] e_am_pm = { " ", "AM", "PM" };
		endAM_PM = new JComboBox(e_am_pm);
		endAM_PM.setLocation(190, 130);
		endAM_PM.setSize(50, 25);
		pane.add(endAM_PM);

		// COMBO BOX -- MONTH
		String[] m = { " ", "Jan", "Feb", "Mar", "Apr", "May", "June", "July",
				"Aug", "Sept", "Oct", "Nov", "Dec" };
		Month = new JComboBox(m);
		// Month.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		Month.setLocation(82, 160);
		Month.setSize(100, 25);
		pane.add(Month);

		// TEXT FIELD -- DATE
		Date.setLayout(null);
		Date.setLocation(82, 190); // (x,y)
		Date.setSize(100, 25); // (width,height)
		pane.add(Date);

		// TEXT FIELD -- YEAR
		Year.setLayout(null);
		Year.setLocation(82, 220); // (x,y)
		Year.setSize(100, 25); // (width,height)
		pane.add(Year);

		ClickListener cl = new ClickListener();

		// BUTTON -- CREATE_BUTTON
		Create_Button.setLayout(null);
		Create_Button.setLocation(95, 425);
		Create_Button.setSize(150, 25);
		Create_Button.addActionListener(cl);
		pane.add(Create_Button);

		// BUTTON -- CANCEL_BUTTON
		Cancel_Button.setLayout(null);
		Cancel_Button.setLocation(255, 425);
		Cancel_Button.setSize(150, 25);
		Cancel_Button.addActionListener(cl);
		pane.add(Cancel_Button);

		// JButton open = new JButton("New Appointment");
		// open.addActionListener(this);
		// add(open);
		setVisible(true);

		ButtonHandler handler = new ButtonHandler(); // Creation of a new Object
		Create_Button.addActionListener(handler); // Attach & register handler
													// to Create_Button
		Cancel_Button.addActionListener(handler); // Attach & register handler
													// to Cancel_Button
		setVisible(true);
	}

	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Create_Button) {
				setVisible(false);

				JComboBox comboBox = (JComboBox) e.getSource();

				String title = Title_Box.getText();
				String desc = Description_Box.getText();
				String loc = Location_Box.getText();
				int sh = Integer.parseInt((String) Start_Hr.getSelectedItem());
				int sm = Integer.parseInt((String) Start_Min.getSelectedItem());
				int s_AMPM = Integer.parseInt((String) startAM_PM
						.getSelectedItem());
				int e_AMPM = Integer.parseInt((String) endAM_PM
						.getSelectedItem());
				int eh = Integer.parseInt((String) End_Hr.getSelectedItem());
				int em = Integer.parseInt((String) End_Min.getSelectedItem());
				// String month = Month.getText();
				String date = Date.getText();
				String yr = Year.getText();

				// ERROR IF FIELDS ARE BLANK
				/*
				 * if(title == "" || desc == "" || log == "" || date == "" || yr
				 * == "" || sh == 0 || sm == 0 || s_AMPM == 0 || e_AMPM == 0 ||
				 * eh == 0 || em == 0) { ; }
				 */

			}
			if (e.getSource() == Cancel_Button) {
				setVisible(false);
				dispose();
			}
		}
	}

	/*
	 * public void actionPerformed(ActionEvent event) { String create_event =
	 * event.getActionCommand(); JComboBox comboBox =
	 * (JComboBox)event.getSource();
	 * 
	 * if(create_event.equals("Create")) { String title = Title_Box.getText();
	 * String desc = Description_Box.getText(); String loc =
	 * Location_Box.getText(); int sh =
	 * Integer.parseInt((String)Start_Hr.getSelectedItem()); int sm =
	 * Integer.parseInt((String)Start_Min.getSelectedItem()); int s_AMPM =
	 * Integer.parseInt((String)startAM_PM.getSelectedItem()); int e_AMPM =
	 * Integer.parseInt((String)endAM_PM.getSelectedItem()); int eh =
	 * Integer.parseInt((String)End_Hr.getSelectedItem()); int em =
	 * Integer.parseInt((String)End_Min.getSelectedItem()); String month =
	 * Month.getText(); String date = Date.getText(); String yr =
	 * Year.getText(); } }
	 */
}
