package camelinaction;

import org.apache.camel.Exchange;

import java.util.Arrays;

public class TokyoStatFilter extends StatFilter {

	public TokyoStatFilter(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	public void process(Exchange exchange) throws Exception {
		String[] tokens = exchange.getIn().getBody(String.class).trim().split("\\s+");
		if (tokens.length != 13) {
			throw new IllegalStateException("Invalid message: " + Arrays.toString(tokens) + ", 13 entries expected.");
		}

		String stockName = tokens[0];
		String bidQuantMean = tokens[4];
		String bidQuantVariance = tokens[5];
		String bidQuantStdDev = tokens[6];

		String[] updateMsg = new String[]{stockName, bidQuantMean, bidQuantVariance, bidQuantStdDev};
		tradingEngine.updateStat(updateMsg);

		exchange.getIn().setBody(tradingEngine.report());

	}
}
