package gecon.mod.alpha;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class BankItem {
	public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public String name;
	public int ID;
	
	public BankItem(ItemStack par1Item){
		items.add(par1Item);
		ID = par1Item.itemID;
		setName(par1Item);
		
		
		
	}
	public void setName(ItemStack par1Item){
		if(par1Item.getDisplayName().length() < 6){
			this.name = par1Item.getDisplayName();
		}else{
			this.name = par1Item.getDisplayName().substring(0, 6);
		}
	}
	public void add(ItemStack par1Item){
		int i = par1Item.stackSize;
		ItemStack x = items.get(items.size() - 1);
		if(x.stackSize < 64){
			i = i - (64 - x.stackSize);
			x.stackSize = 64;
			items.add(new ItemStack(par1Item.stackSize, i, 0));
		}else{
			items.add(new ItemStack(par1Item.stackSize, i, 0));
		}
	}
	public int getSize(){
		int i = 0;
		for(ItemStack x: items){
			i += x.stackSize;
		}
		return i;
	}
	public void decr(int q){
			int i = items.size() - 1;
			
			while(i >= 0){
				ItemStack x = items.get(i);
				if(x.stackSize >= q){
					x.stackSize -= q;
					break;
				}else if(i > 1){
					q -= x.stackSize;
					items.remove(x);
					i--;
					x = items.get(i);
					x.stackSize -= q;
					break;
				}
			}
	}
	public void incr(int q){
		int i = items.size() -1;
		while(i >= 0){
			ItemStack x = items.get(i);
			if(x.stackSize + q <= 64){
				x.stackSize += q;
				break;
			}else{
				items.add(new ItemStack(this.ID, q, 0));
			}
		}
}
}