//import statements here
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NewFrame1 extends JFrame implements ActionListener {
	private JButton Back_Button;
	private JButton Create_Button;
	JPanel pane = new JPanel(); // create pane object
	// JButton Create_Button = new JButton("CREATE");
	// JButton Back_Button = new JButton("Existing User?");
	JTextField FirstName_Box = new JTextField(20);
	JTextField LastName_Box = new JTextField(20);
	JTextField Username_Box = new JTextField(10);
	JPasswordField Password_Box = new JPasswordField(20);
	JPasswordField Password_Veri_Box = new JPasswordField(20);
	JTextField Access_Box = new JTextField(20);
	JLabel FirstName_Label = new JLabel("First Name: ");
	JLabel LastName_Label = new JLabel("Last Name: ");
	JLabel Username_Label = new JLabel("Username: ");
	JLabel Password_Label = new JLabel("Password: ");
	JLabel Password_Veri_Label = new JLabel("Retype Password: ");
	JLabel Access_Label = new JLabel("Access Code : ");

	// initialises the frame and opens it
	public NewFrame1() {
		super("New User Registration");

		// String userpass = Password_Box.getText();
		// String veripass = Password_Veri_Box.getText();

		// JButton open = new JButton("Jason");
		// open.addActionListener(this);
		// add(open);
		/* FRAME */
		setBounds(550, 350, 350, 325);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cont = this.getContentPane();
		cont.add(pane);
		pane.setLayout(null);

		/* LABEL - FIRSTNAME_LABEL */
		FirstName_Label.setLayout(null);
		FirstName_Label.setLocation(10, 20);
		FirstName_Label.setSize(80, 25);
		pane.add(FirstName_Label);

		/* TEXT FIELD - FIRSTNAME_BOX */
		FirstName_Box.setLayout(null);
		FirstName_Box.setLocation(120, 20);
		FirstName_Box.setSize(210, 25);
		pane.add(FirstName_Box);

		/* LABEL - LASTNAME_LABEL */
		LastName_Label.setLayout(null);
		LastName_Label.setLocation(10, 60);
		LastName_Label.setSize(80, 25);
		pane.add(LastName_Label);

		/* TEXT FIELD - USERNAME_BOX */
		LastName_Box.setLayout(null);
		LastName_Box.setLocation(120, 60);
		LastName_Box.setSize(210, 25);
		pane.add(LastName_Box);

		/* LABEL - USERNAME_LABEL */
		Username_Label.setLayout(null);
		Username_Label.setLocation(10, 100);
		Username_Label.setSize(80, 25);
		pane.add(Username_Label);

		/* TEXT FIELD - USERNAME_BOX */
		Username_Box.setLayout(null);
		Username_Box.setLocation(120, 100);
		Username_Box.setSize(210, 25);
		pane.add(Username_Box);

		/* LABEL - PASSWORD_LABEL */
		Password_Label.setLayout(null);
		Password_Label.setLocation(10, 140);
		Password_Label.setSize(80, 25);
		pane.add(Password_Label);

		/* TEXT FIELD - PASSWORD_BOX */
		Password_Box.setLayout(null);
		Password_Box.setLocation(120, 140);
		Password_Box.setSize(210, 25);
		pane.add(Password_Box);

		/* LABEL - Password_Veri_Box_LABEL */
		Password_Veri_Label.setLayout(null);
		Password_Veri_Label.setLocation(10, 180);
		Password_Veri_Label.setSize(120, 25);
		pane.add(Password_Veri_Label);

		/* TEXT FIELD - Password_Veri_Box */
		Password_Veri_Box.setLayout(null);
		Password_Veri_Box.setLocation(120, 180);
		Password_Veri_Box.setSize(210, 25);
		pane.add(Password_Veri_Box);

		/* LABEL - Access_LABEL */
		Access_Label.setLayout(null);
		Access_Label.setLocation(10, 220);
		Access_Label.setSize(100, 25);
		pane.add(Access_Label);

		/* TEXT FIELD - PRIORITY_BOX */
		Access_Box.setLayout(null);
		Access_Box.setLocation(120, 220);
		Access_Box.setSize(210, 25);
		pane.add(Access_Box);

		/* BUTTON - CREATE */
		Create_Button = new JButton("Create Account");
		Create_Button.setLayout(null);
		Create_Button.setLocation(185, 260);
		Create_Button.setSize(125, 25);
		// ClickListener cl = new ClickListener();
		// Create_Button.addActionListener(cl);
		pane.add(Create_Button);

		/* BUTTON - BACK */
		Back_Button = new JButton("Go Back");
		Back_Button.setLayout(null);
		Back_Button.setLocation(25, 260);
		Back_Button.setSize(125, 25);
		// Back_Button.setFocusPainted(false);
		// Back_Button.setMargin(new Insets(0,0,0,0));
		// Back_Button.setContentAreaFilled(false);
		// Back_Button.setBorderPainted(false);
		// Back_Button.setOpaque(false);
		// Back_Button.setForeground(Color.BLACK);
		// Back_Button.setFont(new Font("Arial", Font.ITALIC, 16));
		ClickListener cl = new ClickListener();
		Back_Button.addActionListener(cl);
		pane.add(Back_Button);

		ButtonHandler handler = new ButtonHandler(); // creation of a new Object
		Create_Button.addActionListener(handler); // Attach/register handler to
													// Create_Button
		Back_Button.addActionListener(handler);

		pane.getRootPane().setDefaultButton(Create_Button);

		setVisible(true);
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == Back_Button) {
				setVisible(false);
				try {
					new MainFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}

			// Jtextfield to variables
			String FirstName_Input = FirstName_Box.getText();
			String LastName_Input = LastName_Box.getText();
			String Username_Input = Username_Box.getText();
			String Access_Input = Access_Box.getText();
			String Password_Input = Password_Box.getText();
			String PassVeri_Input = Password_Veri_Box.getText();

			Pattern Letters_Numbers = Pattern.compile("[a-zA-Z0-9]");
			Pattern Letters_Only = Pattern.compile("[a-zA-Z]");
			Pattern Non_White_Spaces = Pattern.compile("\\S");

			boolean FirstName_hasSpecialChar = Letters_Only.matcher(
					FirstName_Input).find();
			boolean LastName_hasSpecialChar = Letters_Only.matcher(
					LastName_Input).find();
			boolean Username_hasSpecialChar = Letters_Numbers.matcher(
					Username_Input).find();
			// boolean Access_hasSpecialChar =
			// Letters_Only.matcher(Access_Input).find();
			boolean Password_hasSpecialChar = Non_White_Spaces.matcher(
					Password_Input).find();

			// System.out.println(FirstName_Input + " " + LastName_Input);
			// System.out.println(Password_Input + " " + FirstName_Input + " " +
			// LastName_Input+ " " + Username_Input +" " + Access_Input);

			// ~~~~~~~~~~~~~~~~~~~~~~~ERROR CHECKING
			// AREA!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			if (FirstName_hasSpecialChar == false
					|| LastName_hasSpecialChar == false)// FirstName_Input.equals("")
														// ||
														// LastName_Input.equals("")
														// ||
			// FirstName_Input.equals(" ") || LastName_Input.equals(" "))
			{
				// Checks to make sure first and last name are present
				JOptionPane.showMessageDialog(null,
						"Please enter a valid first and last name!",
						"Name Error", JOptionPane.ERROR_MESSAGE);
			}

			if (Username_hasSpecialChar == false)// Username_Input.equals("") ||
													// (Username_Input.equals(" ")))
			{
				// Checks to make sure Username present
				JOptionPane.showMessageDialog(null,
						"Please enter a valid username!", "Username Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Checks password and verification password to make sure they are
			// the same.
			if (!(Password_Input.equals(PassVeri_Input))
					|| Password_hasSpecialChar == false) {
				JOptionPane.showMessageDialog(null, "Passwords do not match!",
						"Password Error", JOptionPane.ERROR_MESSAGE);
			}
			// Checks access is correct. This is HARDCODED for now. Code =
			// "teamj2014"
			if (!(Access_Input.equals("teamj2014"))) {
				// Checks to make sure Username present
				JOptionPane.showMessageDialog(null,
						"Please enter the correct access code!",
						"Access Code Error", JOptionPane.ERROR_MESSAGE);
			}

			if ((FirstName_hasSpecialChar == true && LastName_hasSpecialChar == true)
					&& (Username_hasSpecialChar == true)
					&& Access_Input.equals("teamj2014")
					&& (Password_Input.equals(PassVeri_Input))
					&& Password_hasSpecialChar == true)

			{
				if (e.getSource() == Create_Button) {
					setVisible(false);
					String u_name = Username_Box.getText();
					String p_word = Password_Box.getText();
					String f_name = FirstName_Box.getText();
					String l_name = LastName_Box.getText();
					String a_code = Access_Box.getText();

					new NewFrame2();

					// If this line gets printed, checking is correct.
					System.out.println(FirstName_Input + " " + LastName_Input
							+ " " + Username_Input + " " + Password_Input + " "
							+ Access_Input);
				}
			}
			/*
			 * if (e.getSource() == Back_Button) { setVisible(false); new
			 * MainFrame(); return; }
			 */

		}
	} // end of inner class

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}