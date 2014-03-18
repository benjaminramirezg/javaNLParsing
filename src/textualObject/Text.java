/**
 * 
 */
package textualObject;


/**
 * 
 * Root textual element. Represents a text, a collection of sentences (Segment).
 * @author benjamin
 *
 */
public class Text implements WithNestedElements {

	private Segment[] segments;
	
	/**
	 * Creates a text from a list of sentences
	 * @param listOfSegments List of sentences
	 */
	
	public Text(Segment[] listOfSegments) {
		
		segments = listOfSegments;
	}
	
	/**
	 * Retrieves the list of nested segments
	 * @return List of nested segments
	 */
	
	public Segment[] getElementsAsList(){
		
		return segments;
	}
}
