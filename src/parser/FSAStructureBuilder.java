/**
 * 
 */
package parser;

import grammaticalObject.Feature;
import grammaticalObject.Type;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Object in charge of building the inner structure of a Finite State Automata (FSA object).
 * The structure is built according to its definition in an XML file. This file must satisfy
 * the requirements of the DTD '/javaNLParsing/grammars/FSA.dtd'.
 * A FSAStructureBuilder object takes that XML definition and build a correspondent list of
 * arcs (object Arc): the transitions between states that shape the automata.
 * 
 * @author benjamin
 *
 */
public class FSAStructureBuilder {
	
	private State initialState;
	private Arc[] arcs;
	private Document doc;
	
	/**
	 * Creates a FSAStructureBuilder.
	 *  
	 */
	
	public FSAStructureBuilder(){
		
	}

	/**
	 * Creates the FSA structure from its XML declaration.
	 * @param file  The path to the XML file from which the structure must be created.
	 * @return The structure of the FSA (a set of transitions between states).
	 */
	
	public Arc[] createStructureFromXMLFile(String file){
		
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			createNestedStructure();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arcs;
	}	

	/**
	 * Retrieves a state of the FSA that has been identified as the initial state.
	 * @return The state of the FSA that has been identified as the initial state.
	 */
	
	public State getInitialState(){
		
		return initialState;
	}
	
	private void createNestedStructure(){

		NodeList arcsDOM = doc.getElementsByTagName("arc");			
		NodeList statesDOM = doc.getElementsByTagName("state");
		NodeList typesDOM = doc.getElementsByTagName("type");			
		
		arcs = new Arc[arcsDOM.getLength()];
		
		for (int i = 0; i < arcsDOM.getLength(); i++) {
			 
			Element arcDOM = (Element) arcsDOM.item(i);
			String initialStateId = arcDOM.getAttribute("initialState");
			String finalStateId = arcDOM.getAttribute("finalState");
			String tagId = arcDOM.getAttribute("tag");
			State initialState = getState(statesDOM,initialStateId);
			State finalState = getState(statesDOM,finalStateId);
			Type tag = getTag(typesDOM,tagId);
			
			arcs[i] = new Arc(initialState,tag,finalState);
		}
	}

	
	private State getState(NodeList statesDOM,String desiredId){
		
		State state = null;
		boolean found = false;
		
		for (int i = 0; i < statesDOM.getLength() & !found; i++) {
			 
			Element stateDOM = (Element) statesDOM.item(i);
			String foundId = stateDOM.getAttribute("id");
			
			if (foundId.equals(desiredId)){
			
				boolean isFinalState = Boolean.valueOf(stateDOM.getAttribute("isFinalState"));
				boolean isInitialState = Boolean.valueOf(stateDOM.getAttribute("isInitialState"));
				int id = Integer.valueOf(stateDOM.getAttribute("id"));
				String message = stateDOM.getAttribute("message");
				state = new State(id,message,isFinalState);
			
				if 	(isInitialState){
					initialState = state;	
				}
			}
		}
		return state;
	}
	
	private Type getTag(NodeList typesDOM, String desiredId){
		
		Type type = null;
		boolean found = false;
		
		for (int i = 0; i < typesDOM.getLength() & !found; i++) {
			 
			Element typeDOM = (Element) typesDOM.item(i);
			String foundId = typeDOM.getAttribute("id");
			
			if (foundId.equals(desiredId)){

				String name = typeDOM.getAttribute("name");
				Feature[] features = getFeatures(typeDOM);
				type = new Type(name,features);
				found = true;
			}
		}
		
		return type;
	}
	
	private Feature[] getFeatures(Element typeDOM){
	
		NodeList featuresDOM = typeDOM.getElementsByTagName("feature");
		Feature[] features = new Feature[featuresDOM.getLength()];

		for (int i = 0; i < featuresDOM.getLength(); i++) {

			Element featureDOM = (Element) featuresDOM.item(i);
			String nameF = featureDOM.getAttribute("name");
			String[] values = getFeatureValues(featureDOM);
			Feature feature = new Feature(values,nameF);
			features[i] = feature;
		}
		return features;
	}

	private String[] getFeatureValues(Element featureDOM){
		
		NodeList valuesDOM = featureDOM.getElementsByTagName("value");
		String[] values = new String[valuesDOM.getLength()];

		for (int i = 0; i < valuesDOM.getLength(); i++) {

			Element valueDOM = (Element) valuesDOM.item(i);
			String value = valueDOM.getAttribute("value");
			values[i] = value;
		}
		return values;
	}
}
