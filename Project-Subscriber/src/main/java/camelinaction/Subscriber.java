package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import java.util.ArrayList;

public class Subscriber {
	public static void main(String[] args) throws Exception {
		TradingEngine nycTradingEngine = setNycTradingEngine();
		TradingEngine londonTradingEngine = setLondonTradingEngine();
		TradingEngine tokyoTradingEngine = setTokyoTradingEngine();

		RouteBuilder nycRoute = new NYCRoute(nycTradingEngine);
		RouteBuilder londonRoute = new LondonRoute(londonTradingEngine);
		RouteBuilder tokyoRoute = new TokyoRoute(tokyoTradingEngine);

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		ConnectionFactory connectionFactory =
				new ActiveMQConnectionFactory("tcp://localhost:61616");

		CamelContext nycContext = new DefaultCamelContext();
		nycContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		nycContext.addRoutes(nycRoute);

		CamelContext londonContext = new DefaultCamelContext();
		londonContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		londonContext.addRoutes(londonRoute);

		CamelContext tokyoContext = new DefaultCamelContext();
		tokyoContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		tokyoContext.addRoutes(tokyoRoute);

		// start the route and let it do its work
		nycContext.start();
		londonContext.start();
		tokyoContext.start();
		Thread.currentThread().join();

//		Thread.sleep(60000);

		// stop the CamelContext
//		nycContext.stop();
//		londonContext.stop();
//		tokyoContext.stop();
	}

	private static TradingEngine setTokyoTradingEngine() {
		ComponentStat parentPorfolioStat = new PorfolioStat("TokyoPorfolioStatParent");
		ComponentStat childPorfolioStat = new PorfolioStat("TokyoPorfolioStatChild1");

		ArrayList<String> statNames = new ArrayList<String>();
		statNames.add("bidQuantMean");
		statNames.add("bidQuantVariance");
		statNames.add("bidQuantStdDev");
		ComponentStat msftStockStat = new StockStat("MSFT", statNames);
		ComponentStat ibmStockStat = new StockStat("IBM", statNames);

		childPorfolioStat.add(msftStockStat);
		parentPorfolioStat.add(childPorfolioStat);
		parentPorfolioStat.add(ibmStockStat);

		return new TradingEngine("Tokyo", parentPorfolioStat);
	}

	private static TradingEngine setLondonTradingEngine() {
		ComponentStat parentPorfolioStat = new PorfolioStat("LondonPorfolioStatParent");
		ComponentStat childPorfolioStat = new PorfolioStat("LondonPorfolioStatChild1");

		ArrayList<String> statNames = new ArrayList<String>();
		statNames.add("askPriceMean");
		statNames.add("askPriceVariance");
		statNames.add("askPriceStdDev");
		ComponentStat orclStockStat = new StockStat("ORCL", statNames);
		ComponentStat ibmStockStat = new StockStat("IBM", statNames);

		childPorfolioStat.add(orclStockStat);
		parentPorfolioStat.add(childPorfolioStat);
		parentPorfolioStat.add(ibmStockStat);

		return new TradingEngine("London", parentPorfolioStat);
	}

	private static TradingEngine setNycTradingEngine() {
		ComponentStat parentPorfolioStat = new PorfolioStat("NYCPorfolioStatParent");
		ComponentStat childPorfolioStat = new PorfolioStat("NYCPorfolioStatChild1");

		ArrayList<String> statNames = new ArrayList<String>();
		statNames.add("bidPriceMean");
		statNames.add("bidPriceVariance");
		statNames.add("bidPriceStdDev");
		ComponentStat msftStockStat = new StockStat("MSFT", statNames);
		ComponentStat orclStockStat = new StockStat("ORCL", statNames);

		childPorfolioStat.add(msftStockStat);
		parentPorfolioStat.add(childPorfolioStat);
		parentPorfolioStat.add(orclStockStat);

		return new TradingEngine("NYC", parentPorfolioStat);
	}
}
