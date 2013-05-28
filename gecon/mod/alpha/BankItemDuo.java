package gecon.mod.alpha;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class BankItemDuo {
	public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public String name;
	public int ID;
	public BankItem leftItem = null;
	public BankItem rightItem = null;
	public int leftQty = 0;
	public int rightQty = 0;
	public BankItemDuo(BankItem par1BankItem, BankItem par2BankItem){
		if(par1BankItem != null){
			leftQty = par1BankItem.getSize();
			leftItem = par1BankItem;
			name = par1BankItem.name;
			ID = par1BankItem.ID;
		}
		if(par2BankItem != null){
			rightQty = par2BankItem.getSize();
			rightItem = par2BankItem;
			name = par2BankItem.name;
			ID = par2BankItem.ID;
		}
		
		
	}
	public void setName(ItemStack par1Item){
		if(par1Item.getDisplayName().length() < 6){
			this.name = par1Item.getDisplayName();
		}else{
			this.name = par1Item.getDisplayName().substring(0, 6);
		}
	}
	public void setLeftItem(BankItem item){
		leftItem = item;
		leftQty = item.getSize();
	}
	public void setRightItem(BankItem item){
		rightItem = item;
		rightQty = item.getSize();
	}
	
}
