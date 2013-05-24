package gecon.mod.alpha.block;

import gecon.mod.alpha.BankItem;
import gecon.mod.alpha.container.ContainerBank;
import gecon.mod.alpha.container.TileEntityBank;
import gecon.mod.alpha.gui.GuiBank;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBank extends BlockGECON {
	private Minecraft mc;
	public static ArrayList<BankItem> bankList = new ArrayList<BankItem>();
	
	public BlockBank(int id, Material material) {
		super(id, material);
		bankList.add(new BankItem(new ItemStack(1, 62, 0)));
		bankList.add(new BankItem(new ItemStack(3, 22, 0)));
		bankList.add(new BankItem(new ItemStack(4, 12, 0)));
		bankList.add(new BankItem(new ItemStack(38, 2, 0)));
		bankList.add(new BankItem(new ItemStack(84, 2, 0)));
		bankList.add(new BankItem(new ItemStack(90, 2, 0)));


	}

	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int f, float a, float b, float c) {		
		if (player instanceof EntityPlayerMP) {
			ModLoader.serverOpenWindow((EntityPlayerMP) player, new ContainerBank(player, world, x, y, z), 30, x, y, z);
		} else {
			ModLoader.openGUI((EntityPlayerSP) player, new GuiBank(player, world, x, y, z));
		}
		
		return true;
	}
	
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBank();
	}
}
