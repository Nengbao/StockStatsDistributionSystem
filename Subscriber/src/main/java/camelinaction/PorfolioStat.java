package camelinaction;

import java.util.ArrayList;
import java.util.Iterator;

public class PorfolioStat extends ComponentStat{
	private ArrayList<ComponentStat> componentStats = new ArrayList<ComponentStat>();

	public PorfolioStat(String name) {
		super(name);
	}

	public PorfolioStat(String name, ArrayList<ComponentStat> componentStats) {
		super(name);
		this.componentStats = componentStats;
	}

	public ArrayList<ComponentStat> getComponentStats() {
		return componentStats;
	}

	public void setComponentStats(ArrayList<ComponentStat> componentStats) {
		this.componentStats = componentStats;
	}

	@Override
	public void add(ComponentStat componentStat) {
		componentStats.add(componentStat);
	}

	@Override
	public void remove(ComponentStat componentStat) {
		componentStats.remove(componentStat);
	}

	@Override
	public String print() {
		StringBuilder stringBuilder = new StringBuilder();

/*
		for (int i = 0; i < componentStats.size(); i++) {
			stringBuilder.append(componentStats.get(i).print());
		}
*/
		Iterator<ComponentStat> componentStatIterator = componentStats.iterator();
		while (componentStatIterator.hasNext()) {
			stringBuilder.append(componentStatIterator.next().print());
		}

		return stringBuilder.toString();
	}

	@Override
	public void update(String[] updateMsg) {
/*
		for (int i = 0; i < componentStats.size(); i++) {
			componentStats.get(i).update(updateMsg);
		}
*/

		Iterator<ComponentStat> componentStatIterator = componentStats.iterator();
		while (componentStatIterator.hasNext()) {
			componentStatIterator.next().update(updateMsg);
		}
	}
}
