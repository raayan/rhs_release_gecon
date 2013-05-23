package gecon.mod.alpha.gui;

import gecon.mod.alpha.BankItem;
import gecon.mod.alpha.gECON;
import gecon.mod.alpha.block.BlockBank;
import gecon.mod.alpha.container.ContainerBank;
import gecon.mod.alpha.container.TileEntityBank;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiBank extends GuiContainer {
	public InventoryPlayer inventoryPlayer;
	public EntityPlayer player;
	public TileEntity tileEntity;
	public ItemStack focusedItem;
    private GuiTextField searchField;
    public ArrayList<BankItem> currentlyDisplayedItems;
	private int index = 0;
 
	
	public GuiBank(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerBank(player, world, x, y, z));		
		this.player = player;
		this.xSize = 195;
		this.ySize = 136;

	

	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/bank.png");
		int screenPosX = (this.width - this.xSize) / 2;
		int screenPosY = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(screenPosX, screenPosY, 0, 0, this.xSize, this.ySize);
		
	}

	@Override
	public void updateScreen(){
	
	}
	
	public String getFocusedSlotInfo(){
		if(this.focusedItem != null){
			return focusedItem.getDisplayName();
		}
		return "No Item";
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		
		
		this.searchField = new GuiTextField(this.fontRenderer, 133, 8, 53, this.fontRenderer.FONT_HEIGHT);
	    this.searchField.setMaxStringLength(15);
	    this.searchField.setTextColor(16777215);
        this.searchField.drawTextBox();

		this.fontRenderer.drawString(player.getEntityName() +"'s Bank", 10, 9, 0xFFFFFF);
		this.fontRenderer.drawString("Name", 28, 23, 0xFFFFFF);
		this.fontRenderer.drawString("Stored", 81, 23, 0xFFFFFF);
		this.fontRenderer.drawString("Holding", 131, 23, 0xFFFFFF);
		
		int screenPosX = (this.width - this.xSize) / 2;
		int screenPosY = (this.height - this.ySize) / 2;
		
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/bank.png");

		this.drawItems();
	}
	
	private void drawItems(){
		int itemWidth = 153;
		int itemHeight = 19;
		int locFull = 136;
		int locNull = 155;
		
		for (int i = 0; i < index + 4; i++){
			try{
			if(BlockBank.bankList.get(i) != null){
				this.drawTexturedModalRect(21, 34 + i * 19, 0, locFull, itemWidth, itemHeight);

			}
			}catch(IndexOutOfBoundsException e){
				this.drawTexturedModalRect(21, 34 + i * 19, 0, locNull, itemWidth, itemHeight);

			}
		}
		for (int i = 0; i < index + 1; i++){
			try{
			if(BlockBank.bankList.get(i) != null){
				this.fontRenderer.drawString(BlockBank.bankList.get(i).getDisplayName().substring(0), 28, 39 + i * 19, 0xFFFFFF);
				this.fontRenderer.drawString((Integer.toString(BlockBank.bankList.get(i).stackSize)), 81, 39 + i * 19, 0xFFFFFF);
				this.fontRenderer.drawString(Integer.toString(getNumberInPlayer(BlockBank.bankList.get(i))), 131, 39 + i * 19, 0xFFFFFF);


			}
			}catch(IndexOutOfBoundsException e){

			}catch(NullPointerException e){
				
			}
		}
		
		
		
	}
	public int getNumberInPlayer(ItemStack item){
		int i = 0;
		for(ItemStack x: player.inventory.mainInventory){
			if(x.itemID == item.itemID)
				i += x.stackSize;
		
		}
		return i;
	}
	
}
