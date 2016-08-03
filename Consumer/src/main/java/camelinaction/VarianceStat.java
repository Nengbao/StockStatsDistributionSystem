package camelinaction;

public class VarianceStat extends Stat {
	@Override
	public double calculateQuantStat(Request request) {
		return request.getQuantSqrSum() / request.getNum() - Math.pow(new MeanStat().calculateQuantStat(request), 2);
	}

	@Override
	public double calculatePriceStat(Request request) {
		return Math.abs(request.getPriceSqrXquantSum() / request.getQuantSum() - Math.pow(new MeanStat().calculatePriceStat(request), 2));
	}
}
