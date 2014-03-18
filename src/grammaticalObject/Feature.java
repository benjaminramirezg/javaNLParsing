/**
 * 
 */
package grammaticalObject;


/**
 * This class represents a feature inside a type (object Type).
 * A feature describes the type in which it's located. It has
 * an attribute (a literal that identifies the feature) and
 * a value. This object must be used in non-nested feature
 * structures, so its value is not a type but a list of
 * possible literal values
 * 
 * @author benjamin
 *
 */
public class Feature implements Instance {

	private String[] values;
	private String name;
	
	/**
	 * It takes a value and an attribute and creates a feature with them.
	 * @param values  List of possible literal values of the feature
	 * @param name  Attribute that identifies the feature
	 */
	
	public Feature(String[] values, String name){
		
		this.values = values;
		this.name = name;
	}

	/**
	 * Retrieves the list of possible literal values.
	 * @return The list of possible literal values
	 */
	
	public String[] getPossibleValues() {
		
		return values;
	}

	/**
	 * Retrieves the attribute of the feature.
	 * @return The attribute of the feature.
	 */
	
	public String getName() {
		return name;
	}

}
