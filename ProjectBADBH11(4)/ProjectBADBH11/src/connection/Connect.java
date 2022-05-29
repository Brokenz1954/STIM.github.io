package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import main.Developer;
import main.Player;

public class Connect {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE_NAME = "stim";
	private final String HOST = "localhost:3306";
	
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE_NAME);
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static Connect instance = null;
	
	public static Connect getInstance() {
		if (instance == null) instance = new Connect();
		return instance;
	}
	
	private Connect () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Connection!");
			System.exit(0);
		}
	}
	public ResultSet executeQuery(String query) {
		rs = null;
		try {
		   rs = stmt.executeQuery(query);
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return rs;
	}
	
	public void updateQuantityGame(int quantity, String gameId) {
		try {
			ps = con.prepareStatement("UPDATE `game` SET `quantity`=? WHERE gameId =?");
			ps.setInt(1, quantity);
			ps.setString(2, gameId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertNewUser(String username, String password, String gender, String country, String role) {
		try {
			ps = con.prepareStatement("INSERT INTO `user`(`username`, `password`, `gender`, `country`, `role`) VALUES (?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, gender);
			ps.setString(4, country);
			ps.setString(5, role);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUsernameExists(String username) {
		try {
			ps = con.prepareStatement("SELECT username FROM `user` WHERE username = '" + username + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<Vector<Object>> getGenreData(){
		Vector<Vector<Object>> data = new Vector<>();
		
		try {
			ps = con.prepareStatement("SELECT * FROM `genre` WHERE 1");
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void insertNewGenre(String genreId, String genreName) {
		try {
			ps = con.prepareStatement("INSERT INTO `genre`(`genreId`, `genreName`) VALUES (?,?)");
			ps.setString(1, genreId);
			ps.setString(2, genreName);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String generateGenreId(String genreId) {
		Integer num;
		try {
			ps = con.prepareStatement("SELECT MAX(RIGHT(`genreId`, 3)) AS count FROM `genre`");
			rs = ps.executeQuery();
			if (rs.next()) {
				num = Integer.parseInt(rs.getString("count"));
				num++;
				if (num < 10) {
					genreId = "GEN" + "0" + "0" + num;
				}else if (num >= 10 && num <= 99) {
					genreId = "GEN" + "0" + num;
				}else if (num > 100 && num <= 999) {
					genreId = "GEN" + num;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genreId;
	}
	
	public void updateGenre(String genreId, String genreName) {
		try {
			ps = con.prepareStatement("UPDATE `genre` SET `genreName`= ? WHERE `genreId`= ?");
			ps.setString(1, genreName);
			ps.setString(2, genreId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGenre(String genreId) {
		try {
			ps = con.prepareStatement("DELETE FROM `genre` WHERE `genreId`= ?");
			ps.setString(1, genreId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkGenreNameExists(String genreName) {
		try {
			ps = con.prepareStatement("SELECT `genreName` FROM `genre` WHERE  `genreName` = '" + genreName + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<Vector<Object>> getGameData(){
		Vector<Vector<Object>> data = new Vector<>();
		try {
			ps = con.prepareStatement("SELECT gameId, name, price, genreName, quantity FROM `game` ga JOIN `genre` ge ON ga.genreId = ge.genreId");
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getInt(3));
				row.add(rs.getString(4));
				row.add(rs.getInt(5));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public ArrayList<String> viewGenre(ArrayList<String> genreArr) {
		try {
			ps = con.prepareStatement("SELECT `genreName` FROM `genre`");
			rs = ps.executeQuery();
			while (rs.next()) {
				genreArr.add(rs.getString("genreName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genreArr;
	}
	
	public void insertNewGame(String gameId, String name, Integer price, String genreId, Integer quantity) {
		try {
			ps = con.prepareStatement("INSERT INTO `game`(`gameId`, `name`, `price`, `genreId`, `quantity`) VALUES (?,?,?,?,?)");
			ps.setString(1, gameId);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setString(4, genreId);
			ps.setInt(5, quantity);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGame(String gameId, String name, Integer price, String genreId, Integer quantity) {
		try {
			ps = con.prepareStatement("UPDATE `game` SET `name`=?,`price`=?,`genreId`=?,`quantity`=? WHERE `gameId`=?");
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setString(3, genreId);
			ps.setInt(4, quantity);
			ps.setString(5, gameId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGame(String gameId) {
		try {
			ps = con.prepareStatement("DELETE FROM `game` WHERE `gameId` = ?");
			ps.setString(1, gameId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String generateGameId(String gameId) {
		Integer num;
		try {
			ps = con.prepareStatement("SELECT MAX(RIGHT(`gameId`, 3)) AS count FROM `game`");
			rs = ps.executeQuery();
			if (rs.next()) {
				num = Integer.parseInt(rs.getString("count"));
				num++;
				if (num < 10) {
					gameId = "GAME" + "0" + "0" + num;
				}else if (num >= 10 && num <= 99) {
					gameId = "GAME" + "0" + num;
				}else if (num > 100 && num <= 999) {
					gameId = "GAME" + num;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gameId;
	}
	
	public boolean checkGameNameExists(String name) {
		try {
			ps = con.prepareStatement("SELECT `name` FROM `game` WHERE  `name` = '" + name + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getGenreName(String genreName) {
		String genreId = "";
		try {
			ps = con.prepareStatement("SELECT `genreId` FROM `genre` WHERE  `genreName` = '" + genreName + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				genreId = rs.getString("genreId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genreId;
	}
	
	public void insertTransaction(Integer userId, String gameId, Integer quantity) {
		try {
			ps = con.prepareStatement("INSERT INTO `transaction`(`userId`, `gameId`, `gameQuantity`) VALUES (?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, gameId);
			ps.setInt(3, quantity);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getPrice(String gameId) {
		Integer price = 0;
		try {
			ps = con.prepareStatement("SELECT `price` FROM `game` WHERE `gameId` = '" + gameId +"'");
			rs = ps.executeQuery();
			if (rs.next()) {
				price = rs.getInt("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	
	public String getUserId (String userId) {
		try {
			ps = con.prepareStatement("SELECT `userId` FROM `user` WHERE `usernmae` = '" + userId + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getString("userId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public Integer checkQuantity(String gameId) {
		int quantity = 0;
		try {
			ps = con.prepareStatement("SELECT `quantity`FROM `game` WHERE `gameId` = '" + gameId + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
	public Vector<Vector<Object>> getOwnedGame(int userId){
		Vector<Vector<Object>> data = new Vector<>();
		try {
			ps = con.prepareStatement("SELECT ga.gameId, name, ge.genreName, gameQuantity, price FROM `game` ga JOIN `transaction` t ON ga.gameId = t.gameId JOIN `genre` ge ON ga.genreId = ge.genreId JOIN `user` u ON t.userId = u.userId WHERE t.userId = '" + userId + "'");
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getInt(4));
				row.add(rs.getInt(5));
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Integer totalPrice(int userId) {
		Vector<Integer> temp = new Vector<Integer>();
		try {
			ps = con.prepareStatement("SELECT SUM(price) AS totalPrice FROM `game`g JOIN `transaction` t ON g.gameId = t.gameId JOIN `user` u ON t.userId = u.userId WHERE u.userId = '" + userId + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				int totalPrice = rs.getInt("totalPrice");
				temp.add(totalPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int temp2 = 0;
		for (int i = 0; i < temp.size(); i++) {
			temp2 += temp.get(i);
		}
		return temp2;		
	}
}
