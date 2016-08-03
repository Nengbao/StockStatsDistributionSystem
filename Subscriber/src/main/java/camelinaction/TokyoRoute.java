package camelinaction;

public class TokyoRoute extends AbstractRoute {

	public TokyoRoute(TradingEngine tradingEngine) {
		super(tradingEngine);
	}

	@Override
	public void configure() throws Exception {
		from("jms:topic:project_topic_msft")
				.log("SUBSCRIBER RECEIVED: jms MSFT queue: ${body}")
				.process(new TokyoStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());

		from("jms:topic:project_topic_ibm")
				.log("SUBSCRIBER RECEIVED: jms IBM queue: ${body}")
				.process(new TokyoStatFilter(tradingEngine))
				.to("jms:queue:project_queue_trading_engine_" + tradingEngine.getName());

	}
}
