package example.com.resoures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresClientManager implements ClientInterface<Client> {

	Connection connection;
	private String url="jdbc:postgresql://localhost:5432/postgres";
	
	String createTable="CREATE TABLE Klient (id serial primary key, name varchar(30), surname varchar(30), adress varchar(30), phone int(30))";
	
	
	Statement statement;
	PreparedStatement saveClient;
	PreparedStatement deleteClient;
	PreparedStatement getAllClient;
	PreparedStatement getClient;
	
	public PostgresClientManager()
	{
		try {
			connection=DriverManager.getConnection(url, "postgres", "kuba22");
			statement = connection.createStatement();
		
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			
			while(rs.next())
			{
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("Client"))
				{
					tableExists=true;
					break;
				}
				
			}
			if(!tableExists)
			{
				statement.executeUpdate(createTable);
			}
		
            getAllClient=connection.prepareStatement("" + "Select * From Klient");
            deleteClient=connection.prepareStatement("" + "DELETE From Klient where name=?");
            getClient=connection.prepareStatement("" + "SELECT * From Klient where id=?");
            
			}catch (SQLException e){
				
				e.printStackTrace();
			}
		
	}
		
	@Override
	public Client get(int id) {
		Client result=null;
		try {
			getClient.setInt(1, id);
			ResultSet rs = getClient.executeQuery();
			while(rs.next()){
				result = new Client(rs.getString("name"),rs.getString("surnme"),rs.getString("adress"),rs.getInt("phone"));
				break;
			}
			return result;
			}catch (SQLException e){
				e.printStackTrace();
			    return null;
			}
	}

	@Override
	public List<Client> getAll() {
		List<Client> result = new ArrayList<Client>();
		
		try 
		{
			ResultSet rs = getAllClient.executeQuery();
			while(rs.next())
				result.add(new Client(rs.getString("name"),rs.getString("surname"),rs.getString("adress"),rs.getInt("phone")));
			return result;
			
		} catch (SQLException e){
			e.printStackTrace();
		    return null;
		}
	}

	@Override
	public boolean save(Client obj) {
		try 
		{
			saveClient = connection.prepareStatement("" + "INSERT INTO Klient(name,surname,adress,phone)" + "VALUES(?,?,?,?)");
			saveClient.setString(1, obj.getName());
			saveClient.setString(2, obj.getSurname());
			
			return saveClient.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
	        return false;
		}
	}

	@Override
	public boolean delete(Client obj) {
		try 
		{
			deleteClient.setString(1, obj.getName());
			deleteClient.executeUpdate();
			return true;
			
		}catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
	}

}
