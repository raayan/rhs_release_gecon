package gecon.mod.alpha.gui;

import java.util.ArrayList;

import gecon.mod.alpha.BankItem;
import gecon.mod.alpha.BankItemDuo;
import gecon.mod.alpha.gECON;
import gecon.mod.alpha.block.BlockBank;
import gecon.mod.alpha.container.ContainerGECON;
import gecon.mod.alpha.misc.Searcher;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiMarketAnalysis extends GuiContainer {
	
	/**
	 * Stores a reference to the player using the gui
	 */
	public EntityPlayer player;
    private GuiTextField searchField;
    private String searching = "";
	/**
	 * Constructs the gui window
	 * @param par1Player player using the gui
	 * @param par2World world in which the player is located
	 * @param x the x-coordinate of the location
	 * @param y the y-coordinate of the location
	 * @param z the z-coordinate of the location
	 */
    public ArrayList<BankItem> bankStoredItems;

	private int index = 0;
	private int xSize = 256;
	private int ySize = 136;
	private int currentPage;
	private double pages;
	private int qty = 1;
	private BankItem currentItem;
	public GuiMarketAnalysis(EntityPlayer par1Player, World par2World, int x, int y, int z) {
		super(new ContainerGECON(par1Player, par2World, x, y, z));
		
		this.player = par1Player;
	}
	public void initGui(){
		
		int screenPosX = (this.width - this.xSize) / 2;
		int screenPosY = (this.height - this.ySize) / 2;
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
    	this.searchField = new GuiTextField(this.fontRenderer, scaledRes.getScaledWidth()/2 + 68, scaledRes.getScaledHeight()/2 - 60, 53, 10);
	    this.searchField.setMaxStringLength(6);
        this.searchField.setFocused(false);
		int x = scaledRes.getScaledWidth()/2;
        int y = scaledRes.getScaledHeight()/2;
		this.buttonList.add(new GuiButton(8, x + 80, y + 49, 15, 15, "<"));
		this.buttonList.add(new GuiButton(9, x + 95, y + 49, 15, 15, ">"));

		int buttX = 58;
		int buttY = y - 35;
		this.buttonList.add(new GuiButton(0, x + buttX, buttY, 10, 19, "*"));
		this.buttonList.add(new GuiButton(1, x + buttX, buttY + 19, 10, 19, "*"));
		this.buttonList.add(new GuiButton(2, x + buttX, buttY + 38, 10, 19, "*"));
		this.buttonList.add(new GuiButton(3, x + buttX, buttY + 57, 10, 19, "*"));
	}
	/**
	 * Draws the background layer of the gui containing the .png with a ModalRectangle frame over it
	 */
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);

		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/marketAnalysis.png");
		this.drawTexturedModalRect((scaledRes.getScaledWidth() - this.xSize)/2, (scaledRes.getScaledHeight() - this.ySize)/2, 0, 0, this.xSize, this.ySize);
	}

	public void drawScreen(int x, int y, float f){
		super.drawScreen(x, y, f);
        this.searchField.drawTextBox();

		

		
	}
	public void updateScreen(){
		if(searchField.getText().length() > 0){
			searching = searchField.getText();
		}
	}
	public void mouseClicked(int i, int j, int k){
		super.mouseClicked(i, j, k);
		searchField.mouseClicked(i, j, k);
	}
	
	/**
	 * Method to grab keys that are typed
	 */
	public void keyTyped(char c, int i){
		if(c != 'e')
			super.keyTyped(c, i);
		searchField.textboxKeyTyped(c, i);
		searching = searchField.getText();
		collateItems();
		index = 0;
	}
	
	
	public void actionPerformed(GuiButton button){
		int i = button.id;
		
		if(button.id == 9)
			if(bankStoredItems.size() > index + 4)
				index += 4;
			
		if(button.id == 8)
			if(index >= 4)
				index -= 4;
		
		
		if(button.id == 0 || button.id == 1 || button.id == 2 || button.id == 3){
			if(i + index < bankStoredItems.size() )
				currentItem = bankStoredItems.get(i + index);
			else
				currentItem = null;
		}
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int x = scaledRes.getScaledWidth()/2;
        int y = scaledRes.getScaledHeight()/2;
		this.fontRenderer.drawString("Market Analysis ", x - 104,y - 59, 0xFFFFFF); //10, 9

		if(currentItem != null){
			this.fontRenderer.drawString("Item Name: " + currentItem.name , x - 100,y - 44, 0xFFFFFF); //10, 9
		}else{
			this.fontRenderer.drawString("Item Name: N/A", x - 100,y - 44, 0xFFFFFF); //10, 9
		}
		
		this.collateItems();
		this.drawItems();

	}
	
	/**
	 * Draws the foreground layer containing all the components of the gui
	 */
	public void drawGuiContainerForegroundLayer() {
		
	}
	private void drawItems(){
		int itemWidth = 53;
		int itemHeight = 19;
		int locFull = 136;
		int locNull = 155;
		int up = 4;
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int x = (scaledRes.getScaledWidth() - 53)/2;
        int y = (scaledRes.getScaledHeight())/2;
//System.out.println(y);
        
        
//      Legacy Code
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank/marketAnalysis.png");

		for (int i = index; i < index + up; i++){
			try{
				if(this.bankStoredItems.get(i) != null){
					this.drawTexturedModalRect(x + 95 , y - 35 + (i%4) * 19, 0, locFull, itemWidth, itemHeight);
				}
				
			}catch(IndexOutOfBoundsException e){
				this.drawTexturedModalRect(x + 95 , y - 35 + (i%4) * 19, 0, locNull, itemWidth, itemHeight);

			}catch(NullPointerException e){

			}
		}
		
		
		for (int i = index; i < index + up; i++){
			try{
			if(bankStoredItems.get(i) != null){
				this.fontRenderer.drawString(this.bankStoredItems.get(i).name, x + 99,  y - 30 + (i%4) * 19, 0xFFFFFF);
				this.fontRenderer.drawString(Integer.toString(this.bankStoredItems.get(i).getSize()), x + 135,  y - 30  + (i%4) * 19, 0xFFFFFF);


			}
			}catch(IndexOutOfBoundsException e){
				this.fontRenderer.drawString("", x + 135,  y - 30  + (i%4) * 19, 0xFFFFFF);
			}catch(NullPointerException e){

			}
		} 
	}
	public void collateItems(){
		ArrayList<BankItem> list = new ArrayList<BankItem>();
		boolean turp = false;
			for(ItemStack x: BlockBank.bankList){
				turp = false;
				for(BankItem y: list){
					if(x.itemID == y.ID){
						y.add(x);
						turp = true;
						break;
					}
				}
				if(!turp){
					list.add(new BankItem(x));
				}
			}

		bankStoredItems = list;
		if(searching.length() > 0){
			bankStoredItems = Searcher.recomb(bankStoredItems, searching);
		}
	}
}
