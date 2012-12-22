package example.com.main;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import example.com.resoures.Client;
import example.com.resoures.ClientInterface;
import example.com.resoures.HibernateClientManager;

public class Main 
{
    public static void main( String[] args )
    {
       
    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
    	
    	Session session = (factory).openSession();
    	
    	ClientInterface<Client> hib = new HibernateClientManager(session);
    	
    	Client p = new Client("Jan", "Kowalski", "Podwale", 501233987);
    	
    	p.getName();
    	
    	hib.save(p);
    	
    	
    	
    	
    }

	
}
