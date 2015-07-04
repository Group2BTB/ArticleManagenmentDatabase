package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultButtonModel;

import dto.Article;
import dto.User;
import utilities.DBConnection;

public class UserDAO {

	// to insert value in to database
	public boolean insertView(User udto) {
		// connect database using try with multi resources
		try (Connection con = DBConnection.getConnection();
		// preparedStatement for insert Infomaiton of Users
				PreparedStatement ps = con
						.prepareStatement("insert into tbl_user(full_name, email, username, passwd ,type)"
								+ "values(?, ?, ?, ?, ?)");) {
			ps.setString(1, udto.getFullName());
			ps.setString(2, udto.getEmail());
			ps.setString(3, udto.getUsername().toString());
			ps.setString(4, udto.getPassword().toString());
			ps.setString(5, udto.getType());
			// check if it affected row or not
			if (ps.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// getUpdateUsername is used for Update of users informaition
	public boolean Update(int num, User udto) {
		String str = "";
		String strup = "";
		if (num == 1) {
			str = "full_name=?";
			strup = udto.getFullName();
		} else if (num == 2) {
			str = "username=?";
			strup = udto.getUsername();
		}

		else if (num == 3) {
			str = "passwd=?";
			strup = udto.getPassword().toString();
		} else if (num == 4) {
			str = "email=?";
			strup = udto.getEmail();
		} else if (num == 5)
			str = "full_name=? , username=?, passwd=?, email=? ";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con
						.prepareStatement("update tbl_user set " + str
								+ " where id=?");) {
			if (num == 5) {
				ps.setString(1, udto.getFullName());
				ps.setString(2, udto.getUsername());
				ps.setString(3, udto.getPassword().toString());
				ps.setString(4, udto.getEmail().toString());
				ps.setInt(5, udto.getId());

			} else {
				ps.setString(1, strup);
				ps.setInt(2, udto.getId());
			}
			if (ps.executeUpdate() <= 0)
				return false;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// to view user profile detail
	public User viewUser(User udto) throws SQLException {
		// create connection
		Connection con = DBConnection.getConnection();
		// preparedStatement for view Information of Users
		PreparedStatement ps = con
				.prepareStatement("select id, fullname, email, username, type from tbl_user where id=?");
		ps.setInt(1, udto.getId());// set id to PreparedStatement
		ResultSet rs = ps.executeQuery();
		// loop to set the each value
		if (rs.next()) {
			udto.setId(rs.getInt("id"));// set id
			udto.setFullName(rs.getString("fullname"));// set full name
			udto.setEmail(rs.getString("email"));
			udto.setUsername(rs.getString("username"));// set user name
			udto.setType(rs.getString("type"));// set user type
		}
		try {
			return udto;
		} finally {
			// to close ResultSet, PreparedStatement, and connection
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw e;
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					throw e;
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw e;
				}
		}
	}

	// this function is used for delect users from database
	// to delete the user by selected id
	public boolean DeleteUsers(User udto) {
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con
						.prepareStatement("Delete from tbl_user where id=?");) {
			ps.setInt(1, udto.getId());
			if (ps.executeUpdate() <= 0)
				return false;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// to check the user when login with username/password
	public boolean checkUserLogin(User udto) throws SQLException {
		// create connection
		Connection con = DBConnection.getConnection();
		// preparedStatement for view Information of Users
		PreparedStatement ps = con
				.prepareStatement(
						"select active, type from tbl_user where username=? and passwd=?",
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
		// set value to username, and password
		ps.setString(1, udto.getUsername());
		ps.setString(2, udto.getPassword().toString());
		ResultSet rs = ps.executeQuery();
		int active = 0;
		String type = "";
		// get value 'active', 'type' from selected username and password
		while (rs.next()) {
			active = rs.getInt("active");
			type = rs.getString("type");
		}
		// check if user is active or de-active
		if (active == 1) {
			udto.setType(type);
			System.out.println(udto.getType());
			return rs.last();
		} else {
			return false;
		}

	}

	public boolean DeActivedUsers(User udto) {
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con
						.prepareStatement("update tbl_user set active=? where id=?");) {

			ps.setInt(1, 0);
			ps.setInt(2, udto.getId());

			if (ps.executeUpdate() <= 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<User> listUser() throws SQLException {
		ArrayList<User> arrList = new ArrayList<User>();
		ResultSet rs = null;
		Connection con = DBConnection.getConnection();// Connect to database
		Statement stm = con.createStatement();
		String query = "SELECT id,fullname, email, username, create_date, type from tbl_user";
		rs = stm.executeQuery(query);//execute sql query to get data from database
		while (rs.next()) {// loop resultSet of user add to arrayList
			arrList.add(new User(rs.getInt("id"), rs.getString("fullname"), rs
					.getString("email"), rs.getString("username"), rs
					.getDate("create_date"), rs.getString("type")));
		}
		return arrList;//return value of user ArrayList
	}
}
