package camelinaction;

public class Stock {
	private String name = null;
	Request bid = new Request("bid");
	Request ask = new Request("ask");

	public Stock(String name) {
		this.name = name;
	}

	public void addTick(String tick) throws Exception {
		String[] tokens = tick.trim().split("\\s+");

		// check
		if (tokens.length != 5) {
			throw new IllegalStateException("5 entries expected: stockName, bidPrice, bidQuantity, askPrice, askQuantity.");
		}
		String stockName = tokens[0];
		if (name != null && !name.equals(stockName)) {
			throw new IllegalStateException("This tick is for stock" + stockName + ", but the current stock is " + name);
		}

		// parse data
		double bidPrice = Double.parseDouble(tokens[1]);
		int bidQuantity = Integer.parseInt(tokens[2]);
		double askPrice = Double.parseDouble(tokens[3]);
		int askQuantity = Integer.parseInt(tokens[4]);

		// udpate fields
		bid.setNum(bid.getNum() + 1);
		bid.setQuantSum(bid.getQuantSum() + bidQuantity);
		bid.setQuantSqrSum(bid.getQuantSqrSum() + (int)Math.pow(bidQuantity, 2));
		bid.setPriceXquantSum(bid.getPriceXquantSum() + bidPrice * bidQuantity);
		bid.setPriceSqrXquantSum(bid.getPriceSqrXquantSum() + Math.pow(bidPrice, 2) * bidQuantity);

		ask.setNum(ask.getNum() + 1);
		ask.setQuantSum(ask.getQuantSum() + askQuantity);
		ask.setQuantSqrSum(ask.getQuantSqrSum() + (int)Math.pow(askQuantity, 2));
		ask.setPriceXquantSum(ask.getPriceXquantSum() + askPrice * askQuantity);
		ask.setPriceSqrXquantSum(ask.getPriceSqrXquantSum() + Math.pow(askPrice, 2) * askQuantity);
	}

	public double getBidQuantityStat(Stat stat) {
		return stat.calculateQuantStat(bid);
	}

	public double getBidPriceStat(Stat stat) {
		return stat.calculatePriceStat(bid);
	}

	public double getAskQuantityStat(Stat stat) {
		return stat.calculateQuantStat(ask);
	}

	public double getAskPriceStat(Stat stat) {
		return stat.calculatePriceStat(ask);
	}

	public String getName() {
		return name;
	}

	public Request getBid() {
		return bid;
	}

	public Request getAsk() {
		return ask;
	}
}
