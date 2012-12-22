package example.com.resoures;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "Klient")
@NamedQueries({
	@NamedQuery(
			name="Client.all",
			query="from Client p"
			),
	@NamedQuery(
			name="Client.id",
			query="from Client p where id= :id"
			),
	@NamedQuery(
			name="Client.delete",
			query="Delete from Client p where id=:id"
			)
		
		
})

public class Client {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="Imie")
	private String name;
	
	private String surname;
	private String adress;
    private int phone;	
	
	public Client(String name, String surname, String adress, int phone) {
		this.name=name;
		this.surname=surname;
		this.adress=adress;
		this.phone=phone;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress){
		this.adress = adress;
	}

	public int getPhone() {
	    return phone;
	}
	
	public void setPhone(int phone){
		this.phone = phone;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}
