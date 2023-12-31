package Monkeys;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Monkeys.Model.page;

public class DatabaseController {
    
    //Erstellen der Tabellen falls noch nicht geschehen.
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

    //In dieser Methode wird ein Objekt in der Datenbank geupdatet. 
    public void edit(String thema, int id, page page ) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE '"+thema+"' SET titel='"+page.getTitel()+"', imgLink1='"+page.getImgLink1()+"', text1='"+page.getText1()+"', imgLink2='"+page.getImgLink2()+"', text2='"+page.getText2()+"', imgLink3='"+page.getImgLink3()+"', text3='"+page.getText3()+"' WHERE id='"+id+"'");
        }
    }

    //Hier wird eine Objekt gelöscht
    public void deletePage(String thema, int id) throws SQLException{
        Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM '"+thema+"' WHERE id='"+id+"'");
			closeConnection(connection);
		}
    }

    //Hinzufügen von Inhalten aus dem MonkeysController in die Datenbank
    public void addTechnGrundlagen(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO technGrundlagen(titel, imgLink1, text1, imgLink2, text2, imgLink3, text3) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }
    
    //Hier wird ein bestimmtes Objekt aus der Datenbank geholt
    public page getTechnGrundlagen(int id) throws SQLException {
        Connection connection = connect();
        page page = null;
        if(connection != null) {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM technGrundlagen WHERE id='"+id+"'");
			while(res.next()){
				page = new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));
                System.out.println(page);

                page.setId(res.getInt(1));
			}
			closeConnection(connection);
        }

       return page; 
    }

    //Hier werden alle Objekte von einem Thema geholt
    public ArrayList<page> getAllTechnGrundlagen()  throws SQLException{
		ArrayList<page> technGrundlagen = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM TECHNGRUNDLAGEN ORDER BY titel ASC");
			while(res.next()){
				technGrundlagen.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getInt(1)));
                  
                
			}
			closeConnection(connection);
		}
		
		return technGrundlagen;
	}

    //Hinzufügen von Inhalten aus dem MonkeysController in die Datenbank
    public void addSicherheitVerfahren(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO sicherheitVerfahren(titel, imgLink1, text1, imgLink2, text2, imgLink3, text3) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    //Hier wird ein bestimmtes Objekt aus der Datenbank geholt
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

    //Hier werden alle Objekte von einem Thema geholt
    public ArrayList<page> getAllSicherheitVerfahren()  throws SQLException{
		ArrayList<page> sicherheitVerfahren = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM SICHERHEITVERFAHREN ORDER BY titel ASC");
			while(res.next()){
				sicherheitVerfahren.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getInt(1)));
			}
			closeConnection(connection);
		}
		
		return sicherheitVerfahren;
	}

    //Hinzufügen von Inhalten aus dem MonkeysController in die Datenbank
    public void addKomplexeVerfahren(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO komplexeVerfahren(titel, imgLink1, text1, imgLink2, text2, imgLink3, text3) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    //Hier wird ein bestimmtes Objekt aus der Datenbank geholt
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

    //Hier werden alle Objekte von einem Thema geholt
    public ArrayList<page> getAllKomplexeVerfahren()  throws SQLException{
		ArrayList<page> komplexeVerfahren = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM KOMPLEXEVERFAHREN ORDER BY titel ASC");
			while(res.next()){
				komplexeVerfahren.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getInt(1)));
			}
			closeConnection(connection);
		}
		
		return komplexeVerfahren;
	}

    //Hinzufügen von Inhalten aus dem MonkeysController in die Datenbank
    public void addAngriffe(page page) throws SQLException {
        Connection connection = connect();
        if(connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO angriffe(titel, imgLink1, text1, imgLink2, text2, imgLink3, text3) values ('"+page.getTitel()+"','"+page.getImgLink1()+"','"+page.getText1()+"','"+page.getImgLink2()+"','"+page.getText2()+"','"+page.getImgLink3()+"','"+page.getText3()+"')");

            closeConnection(connection);
        }
    }

    //Hier wird ein bestimmtes Objekt aus der Datenbank geholt
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

    //Hier werden alle Objekte von einem Thema geholt
    public ArrayList<page> getAllAngriffe()  throws SQLException{
		ArrayList<page> angriffe = new ArrayList<>();

		Connection connection = connect();
		if(connection != null){
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM ANGRIFFE ORDER BY titel ASC");
			while(res.next()){
				angriffe.add(new page(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getInt(1)));

                
			}
			closeConnection(connection);
		}
		
		return angriffe;
	}

    

    
}
