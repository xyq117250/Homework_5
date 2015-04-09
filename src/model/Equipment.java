package model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;



@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

/*above is cited from https://github.com/jannunzi/CS5200/blob/master/JWS_JPA/src/example/Actor.java*/

public class Equipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String brand;
	@XmlAttribute
	private String description;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private double price;

	//bi-directional many-to-one association to Tower
	@ManyToOne(fetch=FetchType.LAZY) //not very sure
	@JoinColumn(name="towerId")  // foreign Key towerId
	@XmlTransient
	private Tower tower;

	public Equipment() {
		super();
	}
	public Equipment(int id, String name, String brand, String description,double price, Tower tower) 
	{
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.tower = tower;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Tower getTower() {
		return this.tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}
	
	/*Do not need Constructor*/

}