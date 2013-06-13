package gecon.mod.alpha.misc;

public class MethodsMarketAnalysis {
	
	/**
	 * Returns the number of a specific item stored in all banks
	 * @param mcItemID the minecraft ID of the item
	 * @return the number of a specific item stored in all banks
	 */
	public static int getNumItemsInBanks(int mcItemID) {
		return 0;
	}
	
	/**
	 * Returns the new calculated price for an item based on recent market transactions
	 * @param mcItemID the minecraft ID of the item
	 * @return the new price for an item
	 */
	public static int getNewItemPrice(int mcItemID) {
		return 0;
	}
	
	/**
	 * Returns a 10x2 two-dimensional array containing the price and corresponding date of the last 10 transactions of a specific item
	 * @param mcItemID mcItemID the minecraft ID of the item
	 * @return 10x2 array of prices and items
	 */
	public static String[][] getLastTenTransactions(int mcItemID) {
		String[][] pricesAndDates = new String[10][2];
		
		return pricesAndDates;
	}
	
	/**
	 * Returns the String name of a specific item
	 * @param mcItemID mcItemID the minecraft ID of the item
	 * @return the String name of a specific item
	 */
	public static String getItemName(int mcItemID) {
		return "";
	}
	
	/**
	 * Returns the gECON suggestion of a specific item
	 * @param mcItemID mcItemID mcItemID the minecraft ID of the item
	 * @return the gECON suggestion of a specific item
	 */
	public static String getGECONSuggestion(int mcItemID) {
		return "";
	}
}
