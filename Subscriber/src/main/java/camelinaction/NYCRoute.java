package camelinaction;

public class NYCRoute extends AbstractRoute {
	public NYCRoute(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	@Override
	public void configure() throws Exception {
		from("jms:topic:project_topic_msft")
				.log("SUBSCRIBER RECEIVED: jms MSFT queue: ${body}")
				.process(new NYCStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());

		from("jms:topic:project_topic_orcl")
				.log("SUBSCRIBER RECEIVED: jms ORCL queue: ${body}")
				.process(new NYCStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());
	}
}
