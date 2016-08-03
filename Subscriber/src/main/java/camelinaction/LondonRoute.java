package camelinaction;

public class LondonRoute extends AbstractRoute {

	public LondonRoute(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	@Override
	public void configure() throws Exception {
		from("jms:topic:project_topic_orcl")
				.log("SUBSCRIBER RECEIVED: jms ORCL queue: ${body}")
				.process(new LondonStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());

		from("jms:topic:project_topic_ibm")
				.log("SUBSCRIBER RECEIVED: jms IBM queue: ${body}")
				.process(new LondonStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());

	}
}
