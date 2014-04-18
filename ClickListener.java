import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/practice", "root", "mysql");
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("INSERT INTO newaccount Values('Spongebob', 'Squarepants', 'sb', 'pineapple00');");
			statement.executeUpdate();
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
}