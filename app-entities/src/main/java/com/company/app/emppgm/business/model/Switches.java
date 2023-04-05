package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ConditionReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.ConditionName;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
/**
 * Data simplifier entity Switches.
 * 
 * <p>About 'validDataSw' field, <br>
 * </p>
 * 
 * <p>About 'validData' field, <br>
 * </p>
 * 
 * <p>About 'empFoundSw' field, <br>
 * </p>
 * 
 * <p>About 'empFound' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class Switches extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary validDataSw = new Elementary(root,new AlphanumericType(1),"Y");
	private final ConditionName validData=new ConditionName(validDataSw,"Y");
	private final Elementary empFoundSw = new Elementary(root,new AlphanumericType(1),"Y");
	private final ConditionName empFound=new ConditionName(empFoundSw,"Y");
	

	/**
	 * Instantiate a new Switches with a default record.
	 * @param configuration the configuration
	 */
	public Switches(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new Switches bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public Switches(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute validDataSw.
	 * @return the validDataSw attribute reference
	 */
	public ElementaryRangeReference getValidDataSwReference() {
		return validDataSw.getReference();
	}

	/**
	 * Getter for validDataSw attribute.
	 * @return validDataSw attribute
	 */
	public String getValidDataSw() {
		return validDataSw.getValue();
	}

	/**
	 * Setter for validDataSw attribute.
	 * @param validDataSw the new value of validDataSw
	 */
	public void setValidDataSw(String validDataSw) {
		this.validDataSw.setValue(validDataSw);
	}
	/**
	 * Gets the reference for attribute validData.
	 * @return the validData attribute reference
	 */
	public ConditionReference getValidDataReference() {
		return validDataSw.getCondition(validData);	
	}

	/**
	 * Getter for validData attribute.
	 * @return validData attribute
	 */
	public boolean isValidData() {
		return getValidDataReference().getValue();	
	}

	/**
	 * Setter for validData attribute.
	 * @param validData the new value of validData
	 */
	public void setValidData(boolean validData) {
		getValidDataReference().setValue(validData);	
	}
	/**
	 * Gets the reference for attribute empFoundSw.
	 * @return the empFoundSw attribute reference
	 */
	public ElementaryRangeReference getEmpFoundSwReference() {
		return empFoundSw.getReference();
	}

	/**
	 * Getter for empFoundSw attribute.
	 * @return empFoundSw attribute
	 */
	public String getEmpFoundSw() {
		return empFoundSw.getValue();
	}

	/**
	 * Setter for empFoundSw attribute.
	 * @param empFoundSw the new value of empFoundSw
	 */
	public void setEmpFoundSw(String empFoundSw) {
		this.empFoundSw.setValue(empFoundSw);
	}
	/**
	 * Gets the reference for attribute empFound.
	 * @return the empFound attribute reference
	 */
	public ConditionReference getEmpFoundReference() {
		return empFoundSw.getCondition(empFound);	
	}

	/**
	 * Getter for empFound attribute.
	 * @return empFound attribute
	 */
	public boolean isEmpFound() {
		return getEmpFoundReference().getValue();	
	}

	/**
	 * Setter for empFound attribute.
	 * @param empFound the new value of empFound
	 */
	public void setEmpFound(boolean empFound) {
		getEmpFoundReference().setValue(empFound);	
	}
}
