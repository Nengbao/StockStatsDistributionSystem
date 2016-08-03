package camelinaction;

public class TradingEngine {
	private String name = null;
	private ComponentStat componentStat = null;

	public TradingEngine(String name) {
		this.name = name;
	}

	public TradingEngine(String name, ComponentStat componentStat) {
		this.name = name;
		this.componentStat = componentStat;
	}

	public String getName() {
		return name;
	}

	public ComponentStat getComponentStat() {
		return componentStat;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComponentStat(ComponentStat componentStat) {
		this.componentStat = componentStat;
	}

	public void updateStat(String[] updateMsg) {
		componentStat.update(updateMsg);
	}

	public String report() {
		return ReportingEngine.getReportingEngine().report(componentStat);
	}
}
