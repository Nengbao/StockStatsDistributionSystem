package camelinaction;

import org.apache.camel.Processor;

public abstract class StatFilter implements Processor{
	protected TradingEngine tradingEngine = null;

	public StatFilter(TradingEngine tradingEngine) {
		this.tradingEngine = tradingEngine;
	}

	public TradingEngine getTradingEngine() {
		return tradingEngine;
	}

	public void setTradingEngine(TradingEngine tradingEngine) {
		this.tradingEngine = tradingEngine;
	}
}
