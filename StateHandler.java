import java.util.LinkedList;

public class StateHandler {
	
	LinkedList<State> stateList = new LinkedList<State>();
	
	public void addObject(State object) {
		this.stateList.add(object);
	}

	public void removeObject(State object) {

		this.stateList.remove(object);

	}
	
	public State getObjectByID(StateID id) {
		
		for (int i = 0; i < stateList.size(); i++) {
			State tempObject = stateList.get(i);
			if (tempObject.id == id) {
				return tempObject;
			}
		}
		System.out.println("Object Not Found");
		return null;
		
		
	}
	
	
}
