/**
 * 
 */
package textualObject;

import grammaticalObject.Type;

/**
 * @author benjamin
 *
 */
public class Segment implements NestedElement, WithNestedElements {

	private NestedElement[] nestedElements;
	private Type analysis;
	
	public Segment(NestedElement[] listOfNestedElements) {
		
		nestedElements = listOfNestedElements;
	}
	

	public NestedElement[] getElementsAsList(){
		
		return nestedElements;
	}

	public Type getAnalysis(){
		
		return analysis;
	}
}
