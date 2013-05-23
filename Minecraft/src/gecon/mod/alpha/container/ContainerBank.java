package gecon.mod.alpha.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public class ContainerBank extends Container {
	
	public static Slot focusSlot;
	public static Slot focusSlot1;
	public static Slot focusSlot2;
	public static TileEntityBank bank;
	
	public ContainerBank(EntityPlayer player, World world, int x, int y, int z) {
		bank = new TileEntityBank();
		focusSlot = new FocusSlot(bank, 0, 130, 121);

	}
	
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
}
