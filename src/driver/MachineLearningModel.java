package driver;

/***
 * Interface that defines a general model used in machine learning. A model has
 * a structure, and a method to build that structure.
 * 
 * @author cauth0n
 * 
 */
public interface MachineLearningModel {

	/**
	 * getter method to get the structure of a machine learning model.
	 * 
	 * @return structure of machine learning model
	 */
	public MachineLearningModelStructure getModelStructure();

	/**
	 * method to build the model's structure. At this point in time, every model
	 * needs a structure. This may or may not continue to be true.
	 * 
	 */
	public void buildModelStructure();

}
