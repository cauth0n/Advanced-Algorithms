package individual;

import java.util.HashMap;

public class Paper extends RockPaperScissorChoice {
	
	public Paper(){
		super(new HashMap<String, Boolean>());
		properties.put("Rock", false);
		properties.put("Paper", true);
		properties.put("Scissor", false);
	}
	
	

}
