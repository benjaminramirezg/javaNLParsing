/**
 * 
 */
package parser;

import grammaticalObject.Feature;
import grammaticalObject.Type;
import textualObject.Token;

/**
 * 
 * This class defines an object intended to change a list of String tags into
 * a correspondent list of textual objects (objects Token). These textual objects
 * are an acceptable input to the parsing of a FSA object.
 * 
 * @author benjamin
 *
 */
public class InputParserBuilder {

	private Token[] inputParser; 
	
	/**
	 * Creates an InputParserBuilder
	 *  
	 */
	
	public InputParserBuilder(){
		
	}
	
	/**
	 * Performs the map between an input list of Strings and an output list of 
	 * textual objects
	 * @param inputIPB  A list of String tags
	 * @return A list of textual objects acceptable to a FSA parsing.
	 */
	
	public Token[] buildInputParser(String[] inputIPB){
			
		inputParser = new Token[inputIPB.length];

		for (int i = 0; i < inputIPB.length; i++){
			
			String tag = inputIPB[i];
			String typeName = getTypeName(tag);
			Feature[] typeFeatures = getTypeFeatures(tag);
			Type type = buildInputType(typeName,typeFeatures);
			inputParser[i] = buildInputToken(type);
		}
		
		return inputParser;
	}

	private Token buildInputToken(Type type){
		
		return new Token(type);
	}

	private Type buildInputType(String name, Feature[] features){
		
		return new Type(name, features);
	}
	
	private String getTypeName(String tag){
		
		return tag;		
	}
	
	private Feature[] getTypeFeatures(String tag){
		
		String[] featuresValues = tag.split("");
		Feature[] features = new Feature[featuresValues.length - 1];
		
		for (int i = 0; i < features.length; i++){
			
			String[] featureValue = {featuresValues[i + 1]};
			String featureName = Integer.toString(i);
			features[i] = new Feature(featureValue, featureName);
		}
		
		return features;
	}
}
