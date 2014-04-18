//The applications first or the main frame
import java.awt.Color;
import java.io.*;
import java.sql.*;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	JPanel pane = new JPanel();
	private JLabel Title;
	private JButton Create_Button;
	private JButton Login_Button;
	private JLabel Username_Label;
	private JLabel Password_Label;
	private JTextField Username_Box;
	private JPasswordField Password_Box;
	public Container cont = this.getContentPane();

	// Constructor for a new frame

	public MainFrame() throws SQLException {

		super("Team J's Scheduler");
		setBounds(550, 350, 350, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// DATABASE_VARIABLES
		BufferedReader console = new BufferedReader(new InputStreamReader(
				System.in));
		String input = "", f_name = "", l_name = "", a_code = "", u_name = "", p_word = "", ID = "", g_name = "";
		java.sql.Connection con = DriverManager.getConnection(
				"jdbc:mysql://99.3.32.166:3306/scheduler", "root", "mysql");

		cont.add(pane);
		pane.setLayout(null);

		Title = new JLabel("Team J 2014 Calendar!");
		Title.setLayout(null);
		Title.setLocation(0, 0);
		Title.setSize(450, 50);
		Title.setFont(new Font("Serif", Font.BOLD, 34));
		Title.setForeground(Color.BLACK);
		pane.add(Title);

		/* LABEL - USERNAME_LABEL */
		Username_Label = new JLabel("USERNAME: ");
		Username_Label.setLayout(null);
		Username_Label.setLocation(10, 60);
		Username_Label.setSize(80, 25);
		Username_Label.setForeground(Color.BLACK);
		pane.add(Username_Label);

		/* TEXT FIELD - USERNAME_BOX */
		Username_Box = new JTextField();
		Username_Box.setLayout(null);
		Username_Box.setLocation(120, 60);
		Username_Box.setSize(210, 25);
		pane.add(Username_Box);
		// Variable for the input of the username on the login screen
		String Username_Login_Input = Username_Box.getText();

		/* LABEL - PASSWORD_LABEL */
		Password_Label = new JLabel("PASSWORD: ");
		Password_Label.setLayout(null);
		Password_Label.setLocation(10, 100);
		Password_Label.setSize(80, 25);
		// Password_Label.setForeground(Color.white);
		pane.add(Password_Label);

		/* TEXT FIELD - PASSWORD_BOX */
		Password_Box = new JPasswordField();
		Password_Box.setLayout(null);
		Password_Box.setLocation(120, 100);
		Password_Box.setSize(210, 25);
		// Password_Box.setBackground(Color.BLACK);
		// Password_Box.setForeground(Color.white);
		pane.add(Password_Box);
		// Variable for the input of the password field on login screen
		String Password_Login_Input = Password_Box.getText();

		/* BUTTON - CREATE */
		Create_Button = new JButton("New User? Click Here");
		Create_Button.setLayout(null);
		Create_Button.setLocation(00, 140);
		Create_Button.setSize(200, 25);
		Create_Button.setFocusPainted(false);
		Create_Button.setMargin(new Insets(0, 0, 0, 0));
		Create_Button.setContentAreaFilled(false);
		Create_Button.setBorderPainted(false);
		Create_Button.setOpaque(false);
		Create_Button.setForeground(Color.BLACK);
		Create_Button.setFont(new Font("Arial", Font.ITALIC, 16));
		ClickListener cl = new ClickListener();
		Create_Button.addActionListener(cl);
		pane.add(Create_Button);

		/* BUTTON - LOG IN */
		Login_Button = new JButton("Login");
		Login_Button.setLayout(null);
		Login_Button.setLocation(200, 140);
		Login_Button.setSize(125, 25);
		pane.add(Login_Button);
		//
		// Create_Button = new JButton("First Frame");
		// Create_Button.setFont(new Font( "Arial", Font.BOLD, 18));
		// Create_Button.setBackground(Color.red);
		//
		// Login_Button = new JButton("New Frame");
		// Login_Button.setFont(new Font( "Arial", Font.BOLD, 18));
		// Login_Button.setBackground(Color.green);

		// Container c = getContentPane();
		// FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		// c.setLayout(fl);

		// c.add (Create_Button);
		// c.add (Login_Button);

		ButtonHandler handler = new ButtonHandler(); // creation of a new Object
		Create_Button.addActionListener(handler); // Attach/register handler to
													// Create_Button
		Login_Button.addActionListener(handler); // Attach/register handler to
													// Login_Button

		pane.getRootPane().setDefaultButton(Login_Button);

		// setSize(400, 300);
		setVisible(true);
		// show();
	}

	public static void main(String[] args) throws SQLException {

		// Make frame
		MainFrame f = new MainFrame();

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				// This closes the window and terminates the
				// Java Virtual Machine in the event that the
				// Frame is closed by clicking on X.
				System.out.println("Exit via windowClosing.");
				System.exit(0);
			}
		});
	} // end of main

	// inner class for button event handling
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Create_Button) {
				setVisible(false);
				new NewFrame1();

			}
			if (e.getSource() == Login_Button) {
				setVisible(false);

				// Read Username and Password
				String u_name = Username_Box.getText();
				String p_word = Password_Box.getText();

				// Statement s1 = con.createStatement();
				// s1.executeUpdate
				// ("INSERT INTO all_users VALUES(\'"+ID+"\',"+"4,"+"\'"+f_name+"\',"+"\'"+l_name+"\',"+"\'"+u_name+"\',"+"\'"+p_word+"\',"+a_code+",\'"+g_name+"\'"+");");
				// s1.close();
				new NewFrame2();
			}
		}
	} // end of inner class
} // end of outer class