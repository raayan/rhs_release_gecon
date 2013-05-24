package gecon.mod.alpha;

import net.minecraft.item.ItemStack;

public class BankItem {
	public ItemStack[] items = new ItemStack[64];
	public String name;
	public int quantity;
	public int ID;
	
	public BankItem(ItemStack par1Item){
		for(ItemStack x: items){
			if(x == null){
				x = par1Item;
			}
		}
		ID = par1Item.itemID;
		if(par1Item.getDisplayName().length() < 6){
			this.name = par1Item.getDisplayName();
		}else{
			this.name = par1Item.getDisplayName().substring(0, 6);
		}
	}
	public BankItem(ItemStack[] par1Items){
		int i = 0;
		for(int j = 0; j < items.length; j++){
			if (i < par1Items.length)
				if (items[j] == null){
					items[j] = par1Items[i];
					i++;
			}else{
				break;
			}
		}
	}
	public void add(ItemStack x){
		for(ItemStack j: items){
			if(j == null){
				j = x;
			}
		}
	}
	public void add(BankItem x){
		for(ItemStack j: items){
			if(j == null){
				j = new ItemStack(j.itemID, x.getSize(), 0);
			}
		}
	}
	public int getSize(){
		int i = 0;
		for(ItemStack x: items){
			i += x.stackSize;
		}
		return i;
	}
}
