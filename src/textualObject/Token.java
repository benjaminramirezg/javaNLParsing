/**
 * 
 */
package textualObject;

import grammaticalObject.Type;

/**
 * 
 * Implements the atomic textual unit of analysis: a token.
 * @author benjamin
 *
 */
public class Token implements NestedElement {

	private Type analysis;
	
	/**
	 * Takes an analysis (a Type) and creates a token with it
	 * @param analysis  The linguistic description of the token
	 */
	
	public Token(Type analysis) {
		
		this.analysis = analysis;
	}
	
	/**
	 * Retrieves the linguistic analysis of the token
	 * @return The linguistic analysis of the token
	 */

	public Type getAnalysis(){
		
		return analysis;
	}
}
