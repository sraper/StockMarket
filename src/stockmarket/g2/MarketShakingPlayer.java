/**
 * 
 */
package stockmarket.g2;

import java.util.ArrayList;
import java.util.Random;

import stockmarket.sim.EconomicIndicator;
import stockmarket.sim.Portfolio;
import stockmarket.sim.Stock;
import stockmarket.sim.Trade;

/**
 * @author Anne
 *
 */
public class MarketShakingPlayer extends stockmarket.sim.Player {
	private Stock minValueStock;
	private boolean buyRound = true;
	
	public MarketShakingPlayer(){
		name = "Market Shaking Player";
	}
	
	@Override
	public void learn(ArrayList<EconomicIndicator> indicators,
			ArrayList<Stock> stocks) {
		
	}

	@Override
	public ArrayList<Trade> placeTrade(int currentRound,
			ArrayList<EconomicIndicator> indicators, ArrayList<Stock> stocks, Portfolio portfolioCopy) {
		
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		System.out.println("TRADING");
		if (buyRound) {
			minValueStock = stocks.get(0);
			for (Stock s : stocks) {
				if (s.currentPrice() < minValueStock.currentPrice()) {
					minValueStock = s;
				}
			}
			
			int numToBuy = (int) (((int) portfolioCopy.getCapital() - 5) / minValueStock.currentPrice());
			trades.add(new Trade(Trade.BUY, minValueStock, numToBuy));
			buyRound = !buyRound;
		} else {
			trades.add(new Trade(Trade.SELL, minValueStock, portfolioCopy.getSharesOwned(minValueStock)));
			buyRound = !buyRound;
		}
		
		return trades;
	}
}
