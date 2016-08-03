package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.DecimalFormat;

public class StockStatGenetateProcessor implements Processor {
	DecimalFormat meanFormatter = new DecimalFormat("###.###");
	DecimalFormat varianceFormatter = new DecimalFormat("##.####");
	private Stock stock = null;

	public StockStatGenetateProcessor(Stock stock) {
		this.stock = stock;
	}

	public void process(Exchange exchange) throws Exception {
		stock.addTick(exchange.getIn().getBody(String.class));

		String newBody = stock.getName() + "\t"
				+ meanFormatter.format(stock.getBidPriceStat(new MeanStat())) + "\t"
				+ varianceFormatter.format(stock.getBidPriceStat(new VarianceStat())) + "\t"
				+ varianceFormatter.format(stock.getBidPriceStat(new StdDeviationStat())) + "\t"
				+ meanFormatter.format(stock.getBidQuantityStat(new MeanStat())) + "\t"
				+ varianceFormatter.format(stock.getBidQuantityStat(new VarianceStat())) + "\t"
				+ varianceFormatter.format(stock.getBidQuantityStat(new StdDeviationStat())) + "\t"

				+ meanFormatter.format(stock.getAskPriceStat(new MeanStat())) + "\t"
				+ varianceFormatter.format(stock.getAskPriceStat(new VarianceStat())) + "\t"
				+ varianceFormatter.format(stock.getAskPriceStat(new StdDeviationStat())) + "\t"
				+ meanFormatter.format(stock.getAskQuantityStat(new MeanStat())) + "\t"
				+ varianceFormatter.format(stock.getAskQuantityStat(new VarianceStat())) + "\t"
				+ varianceFormatter.format(stock.getAskQuantityStat(new StdDeviationStat())) + "\t";
		exchange.getIn().setBody(newBody, String.class);
	}
}
