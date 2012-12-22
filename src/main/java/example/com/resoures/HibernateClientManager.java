package example.com.resoures;

import java.util.List;

import org.hibernate.Session;
 
public class HibernateClientManager implements ClientInterface<Client>
{
	
	Session session;
	
	public HibernateClientManager(Session session)
	{
		this.session=session;
	}
	
	@Override
	public Client get(int id){
		
		List<Client> result=session.getNamedQuery("Client.id").setInteger("id", id).list();
		
		if(result.size()==0)
		return null;
		
		Client returnValue = new Client(
				result.get(0).getName(),
				result.get(0).getSurname(),
				result.get(0).getAdress(),
				result.get(0).getPhone());
		returnValue.setId(result.get(0).getId());
		return returnValue;
      }
	
	@Override
	
	public List<Client> getAll() {
		List<Client> result = session.getNamedQuery("Client.all").list();
		return result;
		
	}
	
	@Override
	public boolean save(Client obj)
	{
		try {
			session.beginTransaction();
			session.persist(obj);
			session.getTransaction().commit();
			return true;
		}catch(Exception ex){}
		
		return false;
		
	}
	
	@Override
	public boolean delete(Client obj)
	{
	
		try
		{
			session.beginTransaction();
			session.getNamedQuery("Client.delete").setInteger("id", (Integer) obj.getId()).executeUpdate();
			
			session.getTransaction().commit();
			return true;
		}catch(Exception es){}
		
		return false;
		
	}
	
	
}