package driver;

public abstract class Inputter {
	protected String file;
	protected String[] data;

	public Inputter(String file) {
		this.file = file;
	}

	public abstract String[] input();
}
