package camelinaction;

public class StdDeviationStat extends Stat {
	@Override
	public double calculateQuantStat(Request request) {
		return Math.sqrt(new VarianceStat().calculateQuantStat(request));
	}

	@Override
	public double calculatePriceStat(Request request) {
		return Math.sqrt(new VarianceStat().calculatePriceStat(request));
	}
}
