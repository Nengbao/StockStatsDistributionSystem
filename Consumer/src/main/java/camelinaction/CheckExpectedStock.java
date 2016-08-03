package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class CheckExpectedStock implements Predicate {
	private String stockName = null;

	public CheckExpectedStock(String stockName) {
		this.stockName = stockName;
	}

	public boolean matches(Exchange exchange) {
		String processedMessage = exchange.getIn().getBody(String.class).trim();
		String[] tokens = processedMessage.split("\\s+");
		// get rid of '[' and ']' at the both ends
//		String[] tokens = processedMessage.substring(1, processedMessage.length() - 1).split("\\s+");
		// check number of tokens, 5 expected
/*
		if (tokens.length != 5) {
			return false;
		}
*/

		// check stock name
		return tokens.length > 0 && tokens[0] != null && stockName != null
				&& tokens[0].toLowerCase().equals(stockName.toLowerCase());
	}
}
