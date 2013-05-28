package gecon.mod.alpha.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public class ContainerGECON extends Container {
	

	
	public ContainerGECON(EntityPlayer player, World world, int x, int y, int z) {

	}
	
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
}
