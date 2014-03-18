/**
 * 
 */
package grammaticalObject;


/**
 * @author benjamin
 *
 */
public class Type 	implements Instance {

	private Feature[] features;
	private String name;
	
	public Type(String name, Feature[] listOfFeatures){
		
		this.name = name;
		this.features = listOfFeatures;
	}

	public String getName() {

		return name;
	}

	public Feature[] getFeaturesAsList() {

		return features;
	}
}
