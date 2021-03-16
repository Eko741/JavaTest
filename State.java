import java.awt.Font;

public abstract class State {
	
	protected StateID id;
	protected Handler handler;
	protected Font font;
	protected StateHandler stateHandler;
	
	public State (StateID id, Handler handler, StateHandler stateHandler, Font font) {
		this.id = id;
		this.handler = handler;
		this.font = font;
		this.stateHandler = stateHandler;
	}
	public StateID getId() {
		return id;
	}
	public abstract void input(String input);	
	
}
