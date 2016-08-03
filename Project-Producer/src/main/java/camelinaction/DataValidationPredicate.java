package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class DataValidationPredicate implements Predicate {

	public boolean matches(Exchange exchange) {
		String processedMessage = exchange.getIn().getBody(String.class).trim();
		String[] tokens = processedMessage.substring(1, processedMessage.length() - 1).split("\\s+");
		// check number of tokens, 5 expected
		if (tokens.length != 5) {
			return false;
		}
		
		// check stock name
/*
		String[] stockNames = new String[]{"MSFT", "IBM", "ORCL"};
		boolean isExpectedStock = true;
		for (int i = 0; i < stockNames.length; i++) {
			isExpectedStock = isExpectedStock && tokens[0].toUpperCase().equals(stockNames[i]);
		}
		if (!isExpectedStock) {
			return false;
		}
*/

		// check bid price, bid quantity, ask price, ask quantity
		try {
			Double.parseDouble(tokens[1]);
			Integer.parseInt(tokens[2]);
			Double.parseDouble(tokens[3]);
			Integer.parseInt(tokens[4]);
		} catch (Exception e) {
			return false;
		}

		// get rid of '[' and ']' at the both ends
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			stringBuilder.append(tokens[i]).append("\t");
		}
		exchange.getIn().setBody(stringBuilder.toString(), String.class);

		return true;
	}

}
