/**
 * 
 */
package parser;

import grammaticalObject.Type;

/**
 * 
 * This class defines an object that represents a possible transition in a Finite State Automata
 * (a FSA object). These transitions join two states (object State) and are labeled by a tag (object
 * Type). The first state is considered as an initial state ('i'), and the second state as a destination state ('d').
 * An arc defines that the FSA can move from 'i' to 'd' given the presence of a tag (object Type).
 * 
 * @author benjamin
 *
 */
public class Arc {

	private Type tag;
	private State initialState;
	private State finalState;
	
	/**
	 * Creates an object Arc, a transition between two states given the presence of a tag.
	 * @param initialState  The initial state of the arc
	 * @param tag  The tag that allows the transition from the initial state.
	 * @param finalState  The destination state of the transition.
	 */
	
	public Arc(State initialState, Type tag, State finalState){
	
		this.initialState = initialState;
		this.tag = tag;
		this.finalState = finalState; 
	}
	
	/**
	 * Retrieves the tag of the arc.
	 * @return The tag of the arc.
	 */
	
	public Type getTag(){
		
		return tag;
	}

	/**
	 * Retrieves the initial state of the transition
	 * @return The initial state of the transition
	 */
	
	public State getInitialState(){
		
		return initialState;
	}

	/**
	 * Retrieves the final state of the transition
	 * @return The final state of the transition
	 */
	
	public State getFinalState(){
		
		return finalState;
	}
	
}
