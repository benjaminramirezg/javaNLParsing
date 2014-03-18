/**
 * 
 */
package textualObject;

import grammaticalObject.Type;

/**
 * 
 * Represents a segment in a text. It contains subsegments. A segment has been conceived as a recursive
 * object, because the list of nested textual items that contains can be another segment 
 * @author benjamin
 *
 */
public class Segment implements NestedElement, WithNestedElements {

	private NestedElement[] nestedElements;
	private Type analysis;
	
	/**
	 * Takes a list of nested textual elements and creates a segment with them
	 * @param listOfNestedElements  List of nested textual elements 
	 */
	
	public Segment(NestedElement[] listOfNestedElements) {
		
		nestedElements = listOfNestedElements;
	}
	
	/**
	 * Retrieves the list of nested elements in the segment
	 * @return List of nested elements in the segment
	 */

	public NestedElement[] getElementsAsList(){
		
		return nestedElements;
	}

	/**
	 * Retrieves the linguistic analysis of the segment as a whole
	 * @return Linguistic analysis of the segment as a whole
	 */
	
	public Type getAnalysis(){
		
		return analysis;
	}
}
