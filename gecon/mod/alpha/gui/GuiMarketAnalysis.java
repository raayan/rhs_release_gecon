package gecon.mod.alpha.gui;

import gecon.mod.alpha.gECON;
import gecon.mod.alpha.container.ContainerGECON;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiMarketAnalysis extends GuiContainer {
	
	/**
	 * Stores a reference to the player using the gui
	 */
	public EntityPlayer player;
	
	/**
	 * Constructs the gui window
	 * @param par1Player player using the gui
	 * @param par2World world in which the player is located
	 * @param x the x-coordinate of the location
	 * @param y the y-coordinate of the location
	 * @param z the z-coordinate of the location
	 */
	public GuiMarketAnalysis(EntityPlayer par1Player, World par2World, int x, int y, int z) {
		super(new ContainerGECON(par1Player, par2World, x, y, z));
		
		this.player = par1Player;
		this.xSize = 208;
		this.ySize = 222;
	}
	
	/**
	 * Draws the background layer of the gui containing the .png with a ModalRectangle frame over it
	 */
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/marketAnalysis.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	/**
	 * Draws the forground layer containing all the components of the gui
	 */
	public void drawGuiContainerForegroundLayer() {
		
	}
}
