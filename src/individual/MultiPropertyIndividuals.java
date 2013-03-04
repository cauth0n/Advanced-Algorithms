package individual;

import java.util.Map;

public class MultiPropertyIndividuals implements Individual {
	protected Map<String, Boolean> properties;

	public MultiPropertyIndividuals(Map<String, Boolean> properties) {
		this.properties = properties;
	}

	@Override
	public Map<String, Boolean> getProperties() {
		return properties;
	}

}
