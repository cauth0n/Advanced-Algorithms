package individual;

import java.util.HashMap;

public class Rock extends RockPaperScissorChoice {

	public Rock() {
		super(new HashMap<String, Boolean>());
		properties.put("Rock", true);
		properties.put("Paper", false);
		properties.put("Scissor", false);
	}
}
