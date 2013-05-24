package gecon.mod.alpha.gui;

import gecon.mod.alpha.BankItem;
import gecon.mod.alpha.gECON;
import gecon.mod.alpha.block.BlockBank;
import gecon.mod.alpha.container.ContainerBank;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiBank extends GuiContainer {
	/**
	 * The inventory of the player
	 */
	public InventoryPlayer inventoryPlayer;
	/**
	 * The player
	 */
	public EntityPlayer player;
	/**
	 * The reference of the container
	 */
	public TileEntity tileEntity;
	/**
	 * The textbox used for refining a search
	 */
    private GuiTextField searchField;
    /**
     * An arrayList of the 4 items to be displayed.
     */
    public ArrayList<BankItem> currentlyDisplayedItems;
   
    public ArrayList<BankItem> bankStoredItems;
    /**
     * an index for where to begin the item search
     */
	private int index = 0;
	private int xSize = 195;
	private int ySize = 136;
	private int currentPage;
	private double pages;
	private int qty = 1;
	
	
	public GuiBank(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerBank(player, world, x, y, z));		
		this.player = player;
		this.collateItems();

	

	}
	public void initGui(){
		int screenPosX = (this.width - this.xSize) / 2;
		int screenPosY = (this.height - this.ySize) / 2;
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int x = scaledRes.getScaledWidth()/2;
        int y = scaledRes.getScaledHeight()/2;
        
        
		this.buttonList.add(new GuiButton(2, x - 20, y + 44, 20, 20, "<"));
		this.buttonList.add(new GuiButton(1, x, y + 44, 20, 20, ">"));
		
		//Incrementation Buttons
		//Set A
		this.buttonList.add(new GuiButton(9, x - 88, y - 35, 10, 10, "+"));
		this.buttonList.add(new GuiButton(10, x - 88, y - 25, 10, 9, "-"));
		
		//Set B
		this.buttonList.add(new GuiButton(3, x - 88, y - 16, 10, 10, "+"));
		this.buttonList.add(new GuiButton(4, x - 88, y - 6, 10, 9, "-"));
		
		//Set C
		this.buttonList.add(new GuiButton(5, x - 88, y + 3, 10, 10, "+"));
		this.buttonList.add(new GuiButton(6, x - 88, y + 13, 10, 9, "-"));
		
		//Set D
		this.buttonList.add(new GuiButton(7, x - 88, y + 22, 10, 10, "+"));
		this.buttonList.add(new GuiButton(8, x - 88, y + 32, 10, 9, "-"));
		

	
		
	}
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);

		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/bank.png");

		this.drawTexturedModalRect((scaledRes.getScaledWidth() - this.xSize)/2, (scaledRes.getScaledHeight() - this.ySize)/2, 0, 0, this.xSize, this.ySize);
	}

	public void drawScreen(int x, int y, float f){
		super.drawScreen(x, y, f);
		
		

		
	}
	
	
	@Override
	public void updateScreen(){
	
	}
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		
		this.searchField = new GuiTextField(this.fontRenderer, scaledRes.getScaledWidth()/2 + 35, scaledRes.getScaledHeight()/2 - 60, 53, this.fontRenderer.FONT_HEIGHT);
	    this.searchField.setMaxStringLength(15);
	    this.searchField.setTextColor(16777215);
        this.searchField.drawTextBox();
        this.searchField.isFocused();
        int x = scaledRes.getScaledWidth()/2;
        int y = scaledRes.getScaledHeight()/2;
		this.fontRenderer.drawString(player.getEntityName() +"'s Bank", x - 88,y - 59, 0xFFFFFF); //10, 9
		pages = Math.ceil(BlockBank.bankList.size()/4.0);
		currentPage = (int)Math.floor((double)(index/4 + 1));
		this.fontRenderer.drawString("Page " + currentPage + " of " + (int)pages, x - 90, y + 50, 0xFFFFFF);

		this.fontRenderer.drawString("Name", x - 74,  y - 45, 0xFFFFFF);
		this.fontRenderer.drawString("Bank",  x - 4,  y - 45 , 0xFFFFFF);
		this.fontRenderer.drawString("Player", x + 40,  y - 45, 0xFFFFFF);
		
		int screenPosX = (this.width - this.xSize) / 2;
		int screenPosY = (this.height - this.ySize) / 2;
		
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/bank.png");
		


		this.drawItems();
	}
	
	
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 1)
			if(currentPage < (int)pages)
				index += 4;
			
		if(button.id == 2)
			if(index >= 4)
				index -= 4;
		
		
		//Incrementation

	}
	
	private void drawItems(){
		int itemWidth = 153;
		int itemHeight = 19;
		int locFull = 136;
		int locNull = 155;
		int up = 4;
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int x = (scaledRes.getScaledWidth() - 153)/2;
        int y = (scaledRes.getScaledHeight())/2;
		
        
        
//      Legacy Code
		for (int i = index; i < index + up; i++){
			try{
				if(this.currentlyDisplayedItems.get(i) != null){
					this.drawTexturedModalRect(x, y - 34 + (i%4) * 19, 0, locFull, itemWidth, itemHeight);
				}else{
					this.drawTexturedModalRect(x, y - 34 + (i%4) * 19, 0, locNull, itemWidth, itemHeight);
				}
				
			}catch(IndexOutOfBoundsException e){
				this.drawTexturedModalRect(x, y - 34 + (i%4) * 19, 0, locNull, itemWidth, itemHeight);

			}catch(NullPointerException e){

			}
		}
		
		
		for (int i = index; i < index + up; i++){
			try{
			if(BlockBank.bankList.get(i) != null){
				this.fontRenderer.drawString(this.currentlyDisplayedItems.get(i).name, x + 4,  y - 30  + (i%4) * 19, 0xFFFFFF);
//				this.fontRenderer.drawString((Integer.toString(BlockBank.bankList.get(i).stackSize)), x + 74,  y - 30  + (i%4) * 19, 0xFFFFFF);
//				this.fontRenderer.drawString(Integer.toString(getNumberInPlayer(BlockBank.bankList.get(i))), x + 118, y - 30 + (i%4) * 19, 0xFFFFFF);


			}
			}catch(IndexOutOfBoundsException e){

			}catch(NullPointerException e){
				this.fontRenderer.drawString("0", x + 118, y - 30 + (i%4) * 19, 0xFFFFFF);

			}
		}
	}
	public void collateItems(){
		ArrayList<BankItem> list = new ArrayList<BankItem>();
		try{
		for(BankItem x: BlockBank.bankList){
			for(BankItem y: list){
				if(y.ID == x.ID){
					y.quantity += x.quantity;
				}else{
					list.add(x);
				}
			}
		}
		for(ItemStack x: player.inventory.mainInventory){
			int i = 0;
			for(BankItem y: BlockBank.bankList){				
				if(y.ID == x.itemID){
					y.add(x);
					i = 1;
				}
				if(i==0){
					list.add(new BankItem(x));
				}
				
			}
		}
		}catch (NullPointerException e){
			
		}
		for(BankItem x: list){
			System.out.println(x.name + x.quantity);
		}
		bankStoredItems = list;
	}
	public int getNumberInPlayer(ItemStack item){
		int i = 0;

		for(ItemStack x: player.inventory.mainInventory){
			if(x != null)
				if(x.itemID == item.itemID)
					i += x.stackSize;
				
		}
		

		return i;
	}
}
