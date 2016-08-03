package camelinaction;

import org.apache.camel.builder.RouteBuilder;

public abstract class AbstractRoute extends RouteBuilder{
	protected TradingEngine tradingEngine = null;

	public AbstractRoute(TradingEngine tradingEngine) {
		this.tradingEngine = tradingEngine;
	}

	public TradingEngine getTradingEngine() {
		return tradingEngine;
	}

	public void setTradingEngine(TradingEngine tradingEngine) {
		this.tradingEngine = tradingEngine;
	}
}
