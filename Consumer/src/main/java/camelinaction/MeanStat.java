package camelinaction;

public class MeanStat extends Stat {
	@Override
	public double calculateQuantStat(Request request) {
		return request.getQuantSum() / request.getNum();
	}

	@Override
	public double calculatePriceStat(Request request) {
		return request.getPriceXquantSum() / request.getQuantSum();
	}
}
