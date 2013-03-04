package individual;

import java.util.HashMap;

public class Scissor extends RockPaperScissorChoice {

	public Scissor() {
		super(new HashMap<String, Boolean>());
		properties.put("Rock", false);
		properties.put("Paper", false);
		properties.put("Scissor", true);
	}
}
