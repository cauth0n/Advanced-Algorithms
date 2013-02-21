package individual;

public abstract class SinglePropertyIndividuals implements Individual {
	protected String property;

	public SinglePropertyIndividuals(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
