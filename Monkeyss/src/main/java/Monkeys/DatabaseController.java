package Monkeys;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Monkeys.Model.page;

public class DatabaseController {
    
    public void createTable() throws SQLException{
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS technGrundlagen(id INTEGER PRIMARY KEY, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS sicherheitVerfahren(id INTEGER PRIMARY KEY, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS komplexeVerfahren(id INTEGER PRIMARY KEY, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS angriffe(id INTEGER PRIMARY KEY, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT)");

            closeConnection(connection);
        }
    }

    public void testConnection(){
		Connection connection = null;
		try {
            String url = "jdbc:sqlite:memory.db";
            connection = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            closeConnection(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

    public Connection connect(){
		Connection connection = null;
		try {
            String url = "jdbc:sqlite:memory.db";
            connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return connection;
	}

	public void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}

    public void addTechnGrundlagen(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO technGrundlagen(titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    public page getTechnGrundlagen(int id) throws SQLException {
        Connection connection = connect();
        page page = null;
        if(connection != null) {
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM technGrundlagen WHERE id='"+id+"'");
			while(res.next()){
				page = new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));

                page.setId(res.getInt(1));
			}
			closeConnection(connection);
        }

       return page; 
    }

    public ArrayList<page> getAllTechnGrundlagen()  throws SQLException{
		ArrayList<page> technGrundlagen = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM TECHNGRUNDLAGEN ORDER BY titel ASC");
			while(res.next()){
				technGrundlagen.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));

                
                
			}
			closeConnection(connection);
		}
		
		return technGrundlagen;
	}

    public void addSicherheitVerfahren(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO sicherheitVerfahren(titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    public page getSicherheitVerfahren(int id) throws SQLException {
        Connection connection = connect();
        page page = null;
        if(connection != null) {
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM sicherheitVerfahren WHERE id='"+id+"'");
			while(res.next()){
				page = new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));

                page.setId(res.getInt(1));
			}
			closeConnection(connection);
        }

       return page; 
    }

    public ArrayList<page> getAllSicherheitVerfahren()  throws SQLException{
		ArrayList<page> sicherheitVerfahren = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM SICHERHEITVERFAHREN ORDER BY titel ASC");
			while(res.next()){
				sicherheitVerfahren.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
			}
			closeConnection(connection);
		}
		
		return sicherheitVerfahren;
	}

    public void addKomplexeVerfahren(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO komplexeVerfahren(id TEXT, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT) values ('"+page.getId()+"','"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    public page getKomplexeVerfahren(int id) throws SQLException {
        Connection connection = connect();
        page page = null;
        if(connection != null) {
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM komplexeVerfahren WHERE id='"+id+"'");
			while(res.next()){
				page = new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));
                page.setId(res.getInt(1));
			}
			closeConnection(connection);
        }

       return page; 
    }

    public ArrayList<page> getAllKomplexeVerfahren()  throws SQLException{
		ArrayList<page> komplexeVerfahren = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM KOMPLEXEVERFAHREN ORDER BY titel ASC");
			while(res.next()){
				komplexeVerfahren.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
			}
			closeConnection(connection);
		}
		
		return komplexeVerfahren;
	}

    public void addAngriffe(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO angriffe(id TEXT, titel TEXT, imgLink1 TEXT, text1 TEXT, imgLink2 TEXT, text2 TEXT, imgLink3 TEXT, text3 TEXT) values ('"+page.getId()+"','"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    public page getAngriffe(int id) throws SQLException {
        Connection connection = connect();
        page page = null;
        if(connection != null) {
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM angriffe WHERE id='"+id+"'");
			while(res.next()){
				page = new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));

                page.setId(res.getInt(1));
			}
			closeConnection(connection);
        }

       return page; 
    }

    public ArrayList<page> getAllAngriffe()  throws SQLException{
		ArrayList<page> angriffe = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM ANGRIFFE ORDER BY titel ASC");
			while(res.next()){
				angriffe.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
			}
			closeConnection(connection);
		}
		
		return angriffe;
	}
}
