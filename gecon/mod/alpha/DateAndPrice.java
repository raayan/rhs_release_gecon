package gecon.mod.alpha;

import java.sql.Date;

public class DateAndPrice {
	private Date date;
	private double price;
	public DateAndPrice(Date date,double price){
		this.date = date;
		this.price = price;
	}
	public double getPrice(){return price;}
	public Date getDate(){return date;}

}
