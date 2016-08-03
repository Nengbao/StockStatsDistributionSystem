package camelinaction;

public abstract class ComponentStat {
	protected String name;

	public ComponentStat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void add(ComponentStat componentStat);

	public abstract void remove(ComponentStat componentStat);

	public abstract String print();

	public abstract void update(String[] updateMsg);

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ComponentStat)) {
			return false;
		}
		return name.equals(((ComponentStat) obj).getName());
	}
}
