/**
 * 
 */
package grammaticalObject;


/**
 * 
 * Object that represents a linguistic sign by means of a feature
 * structure. It has a name and a list of features (object Feature)
 * @author benjamin
 *
 */

public class Type 	implements Instance {

	private Feature[] features;
	private String name;

	/**
	 * Creates a Type object
	 * @param name  Name of the type
	 * @param listOfFeatures  List of features that characterize the type
	 */
	
	public Type(String name, Feature[] listOfFeatures){
		
		this.name = name;
		this.features = listOfFeatures;
	}

	/**
	 * Retrieves the name of the type
	 * @return The name of the type
	 * 
	 */
	
	public String getName() {

		return name;
	}

	/**
	 * Retrieves the list of features of the type
	 * @return List of features of the type
	 */
	
	public Feature[] getFeaturesAsList() {

		return features;
	}
}
