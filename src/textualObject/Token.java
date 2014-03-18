/**
 * 
 */
package textualObject;

import grammaticalObject.Type;

/**
 * @author benjamin
 *
 */
public class Token implements NestedElement {

	private Type analysis;
	
	public Token(Type analysis) {
		
		this.analysis = analysis;
	}

	public Type getAnalysis(){
		
		return analysis;
	}
}
