package camelinaction;

public class ReportingEngine {
	private static ReportingEngine reportingEngine = null;

	private ReportingEngine() {
	}

	// singleton
	public static final ReportingEngine getReportingEngine() {
		if (reportingEngine == null) {
			reportingEngine = new ReportingEngine();
			return reportingEngine;
		}

		return reportingEngine;
	}

	// could make it static, and get rid of singleton
	public String report(ComponentStat componentStat) {
		if (componentStat == null) {
			return null;
		}

		return componentStat.print();
	}
}
