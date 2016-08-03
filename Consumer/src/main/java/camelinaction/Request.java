package camelinaction;

public class Request {
//	private Stat stat = null;
	private String name = null;
	private int num = 0;
	private int quantSum = 0;
	private int quantSqrSum = 0;
	private double priceXquantSum = 0.0;
	private double priceSqrXquantSum = 0.0;

	public Request(String name) {
		this.name = name;
	}

	public double getQuantStat(Stat stat) {
		return stat.calculateQuantStat(this);
	}

	public double getPriceStat(Stat stat) {
		return stat.calculatePriceStat(this);
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public int getQuantSum() {
		return quantSum;
	}

	public int getQuantSqrSum() {
		return quantSqrSum;
	}

	public double getPriceXquantSum() {
		return priceXquantSum;
	}

	public double getPriceSqrXquantSum() {
		return priceSqrXquantSum;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setQuantSum(int quantSum) {
		this.quantSum = quantSum;
	}

	public void setQuantSqrSum(int quantSqrSum) {
		this.quantSqrSum = quantSqrSum;
	}

	public void setPriceXquantSum(double priceXquantSum) {
		this.priceXquantSum = priceXquantSum;
	}

	public void setPriceSqrXquantSum(double priceSqrXquantSum) {
		this.priceSqrXquantSum = priceSqrXquantSum;
	}
}
