package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Arrays;

public class NYCStatFilter extends StatFilter {

	public NYCStatFilter(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	public void process(Exchange exchange) throws Exception {
		String[] tokens = exchange.getIn().getBody(String.class).trim().split("\\s+");
		if (tokens.length != 13) {
			throw new IllegalStateException("Invalid message: " + Arrays.toString(tokens) + ", 13 entries expected.");
		}

		String stockName = tokens[0];
		String bidPriceMean = tokens[1];
		String bidPriceVariance = tokens[2];
		String bidPriceStdDev = tokens[3];

		String[] updateMsg = new String[]{stockName, bidPriceMean, bidPriceVariance, bidPriceStdDev};
		tradingEngine.updateStat(updateMsg);

		exchange.getIn().setBody(tradingEngine.report(), String.class);
	}
}
