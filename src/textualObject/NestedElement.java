/**
 * 
 */

package textualObject;

import grammaticalObject.Type;

/**
 * 
 * Common methods in objects that represents not root textual objects
 * (tokens, chunks, segments...)
 * @author benjamin
 *
 */

public interface NestedElement {

	/**
	 * Retrieves the linguistic analysis of a textual item
	 * @return Lingüistic analysis of a textual item
	 */
	
	public Type getAnalysis();
}
