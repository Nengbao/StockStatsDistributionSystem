package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Arrays;

public class LondonStatFilter extends StatFilter {

	public LondonStatFilter(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	public void process(Exchange exchange) throws Exception {
		String[] tokens = exchange.getIn().getBody(String.class).trim().split("\\s+");
		if (tokens.length != 13) {
			throw new IllegalStateException("Invalid message: " + Arrays.toString(tokens) + ", 13 entries expected.");
		}

		String stockName = tokens[0];
		String askPriceMean = tokens[7];
		String askPriceVariance = tokens[8];
		String askPriceStdDev = tokens[9];

		String[] updateMsg = new String[]{stockName, askPriceMean, askPriceVariance, askPriceStdDev};
		tradingEngine.updateStat(updateMsg);

		exchange.getIn().setBody(tradingEngine.report());
	}
}
