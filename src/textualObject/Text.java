/**
 * 
 */
package textualObject;


/**
 * @author benjamin
 *
 */
public class Text implements WithNestedElements {

	private Segment[] segments;
	
	public Text(Segment[] listOfSegments) {
		
		segments = listOfSegments;
	}
	
	public Segment[] getElementsAsList(){
		
		return segments;
	}
}
