package camelinaction;

import java.util.ArrayList;

public class StockStat extends ComponentStat {
	private ArrayList<String> statNames = new ArrayList<String>();
	private ArrayList<Double> statValues = new ArrayList<Double>();

	public StockStat(String name, ArrayList<String> statNames) {
		super(name);
		this.statNames = statNames;

		for (int i = 0; i < statNames.size(); i++) {
			statValues.add(0.0);
		}
	}

	public StockStat(String name, ArrayList<String> statNames, ArrayList<Double> statValues) {
		super(name);

		// check statNames has the same size as statValues
		if (statNames.size() != statValues.size()) {
			throw new IllegalStateException("statNames and statValues have different size.");
		}
		this.statNames = statNames;
		this.statValues = statValues;
	}

	public ArrayList<String> getStatNames() {
		return statNames;
	}

	public ArrayList<Double> getStatValues() {
		return statValues;
	}

	public void setStatNames(ArrayList<String> statNames) {
		this.statNames = statNames;
	}

	public void setStatValues(ArrayList<Double> statValues) {
		this.statValues = statValues;
	}

	public void addStatName(String name) {
		statNames.add(name);
	}

	public void addStatValue(String value) {
		statValues.add(Double.parseDouble(value));
	}

	@Override
	public void add(ComponentStat componentStat) {
		throw new IllegalStateException("Add method is not allowed in StockStat");
	}

	@Override
	public void remove(ComponentStat componentStat) {
		throw new IllegalStateException("Add method is not allowed in StockStat");
	}

	@Override
	public String print() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(name).append(System.lineSeparator());
		for (int i = 0; i < statNames.size(); i++) {
			stringBuilder.append(statNames.get(i)).append("\t");
		}
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < statValues.size(); i++) {
			stringBuilder.append(statValues.get(i)).append("\t");
		}
		stringBuilder.append(System.lineSeparator());

		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

	@Override
	public void update(String[] updateMsg) {
		if (updateMsg == null || updateMsg.length == 0 || !name.equalsIgnoreCase(updateMsg[0]) || updateMsg.length != (statNames.size() + 1)) { // updateMsg[0] is stockName
			return;
		}

		for (int i = 0; i < statValues.size(); i++) {
			statValues.set(i, Double.parseDouble(updateMsg[i + 1]));
		}
	}
}
