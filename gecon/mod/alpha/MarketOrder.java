package gecon.mod.alpha;

import java.sql.Date;

import net.minecraft.item.ItemStack;

public class MarketOrder {
	public int quantity;
	public String playerName;
	public boolean buyOrder;
	public BankItem item;
	public double price;
	public Date date;
	public int orderID;
	public MarketOrder(String MCItemID, int q, double p, String user, boolean buy, Date day, int id){
		quantity = q;
		item = new BankItem(new ItemStack(Integer.parseInt(MCItemID), q, 0));
		date = day;
		price = p;
		orderID = id;
		buyOrder = buy;
		playerName = user;
	}
	public int getOrderID() {
		return orderID;
	}
	public String getOrderType() {
		// TODO Auto-generated method stub
		if(buyOrder)
			return "BuyOrder";
		else
			return "SellOrder";
	}
	
}
