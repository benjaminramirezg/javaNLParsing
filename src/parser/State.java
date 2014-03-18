/**
 * 
 */
package parser;


/**
 * 
 * Defines a state in a Finite State Automata. This state, in the FSA, must have an unambiguous identifier. Besides,
 * it could be either final or non final state (one of the states of acceptance in the FSA). Finally, the state 's' bears
 * a message. That message is the label that an hypothetical analysis must assign to a textual item whose presence triggers 
 * a transition in the FSA until the state 's'.
 * 
 * @author benjamin
 *
 */
public class State {

	private boolean finalState;
	private String message;
	private int id;
	
	/**
	 * Creates a state.
	 * @param id  Unambiguous identifier in a FSA
	 * @param message  Tag that this state assign to a textual item
	 * @param finalState  Identification of the state as either final or non final state (true/false respectively).
	 */
	
	public State(int id, String message, boolean finalState){
		
		this.finalState = finalState;
		this.message = message;
		this.id = id;
	}

	/**
	 * Says if the state is final
	 * @return a boolean value: true if the state is final, false if it isn't.
	 */
	
	public boolean isFinalState(){
		
		return finalState;
	}

	/**
	 * Retrieves the label in the FSA output identified with the state
	 * @return The String label
	 */
	
	public String getMessage(){
		
		return message;
	}

    /**
     * Retrieves the unambiguous identifier of the state
     * @return The identifier of the state
     */
	
	public int getId(){
		
		return id;
	}
}
