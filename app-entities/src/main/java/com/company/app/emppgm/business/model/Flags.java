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
 * Data simplifier entity Flags.
 * 
 * <p>About 'displayFlag' field, <br>
 * </p>
 * 
 * <p>About 'displayNewEmp' field, <br>
 * </p>
 * 
 * <p>About 'displaySpaces' field, <br>
 * </p>
 * 
 * <p>About 'displayLowValues' field, <br>
 * </p>
 * 
 * <p>About 'sendFlag' field, <br>
 * </p>
 * 
 * <p>About 'sendErase' field, <br>
 * </p>
 * 
 * <p>About 'sendDataonly' field, <br>
 * </p>
 * 
 * <p>About 'sendError' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class Flags extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary displayFlag = new Elementary(root,new AlphanumericType(1));
	private final ConditionName displayNewEmp=new ConditionName(displayFlag,"1");
	private final ConditionName displaySpaces=new ConditionName(displayFlag,"2");
	private final ConditionName displayLowValues=new ConditionName(displayFlag,"3");
	private final Elementary sendFlag = new Elementary(root,new AlphanumericType(1));
	private final ConditionName sendErase=new ConditionName(sendFlag,"1");
	private final ConditionName sendDataonly=new ConditionName(sendFlag,"2");
	private final ConditionName sendError=new ConditionName(sendFlag,"3");
	

	/**
	 * Instantiate a new Flags with a default record.
	 * @param configuration the configuration
	 */
	public Flags(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new Flags bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public Flags(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute displayFlag.
	 * @return the displayFlag attribute reference
	 */
	public ElementaryRangeReference getDisplayFlagReference() {
		return displayFlag.getReference();
	}

	/**
	 * Getter for displayFlag attribute.
	 * @return displayFlag attribute
	 */
	public String getDisplayFlag() {
		return displayFlag.getValue();
	}

	/**
	 * Setter for displayFlag attribute.
	 * @param displayFlag the new value of displayFlag
	 */
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag.setValue(displayFlag);
	}
	/**
	 * Gets the reference for attribute displayNewEmp.
	 * @return the displayNewEmp attribute reference
	 */
	public ConditionReference getDisplayNewEmpReference() {
		return displayFlag.getCondition(displayNewEmp);	
	}

	/**
	 * Getter for displayNewEmp attribute.
	 * @return displayNewEmp attribute
	 */
	public boolean isDisplayNewEmp() {
		return getDisplayNewEmpReference().getValue();	
	}

	/**
	 * Setter for displayNewEmp attribute.
	 * @param displayNewEmp the new value of displayNewEmp
	 */
	public void setDisplayNewEmp(boolean displayNewEmp) {
		getDisplayNewEmpReference().setValue(displayNewEmp);	
	}
	/**
	 * Gets the reference for attribute displaySpaces.
	 * @return the displaySpaces attribute reference
	 */
	public ConditionReference getDisplaySpacesReference() {
		return displayFlag.getCondition(displaySpaces);	
	}

	/**
	 * Getter for displaySpaces attribute.
	 * @return displaySpaces attribute
	 */
	public boolean isDisplaySpaces() {
		return getDisplaySpacesReference().getValue();	
	}

	/**
	 * Setter for displaySpaces attribute.
	 * @param displaySpaces the new value of displaySpaces
	 */
	public void setDisplaySpaces(boolean displaySpaces) {
		getDisplaySpacesReference().setValue(displaySpaces);	
	}
	/**
	 * Gets the reference for attribute displayLowValues.
	 * @return the displayLowValues attribute reference
	 */
	public ConditionReference getDisplayLowValuesReference() {
		return displayFlag.getCondition(displayLowValues);	
	}

	/**
	 * Getter for displayLowValues attribute.
	 * @return displayLowValues attribute
	 */
	public boolean isDisplayLowValues() {
		return getDisplayLowValuesReference().getValue();	
	}

	/**
	 * Setter for displayLowValues attribute.
	 * @param displayLowValues the new value of displayLowValues
	 */
	public void setDisplayLowValues(boolean displayLowValues) {
		getDisplayLowValuesReference().setValue(displayLowValues);	
	}
	/**
	 * Gets the reference for attribute sendFlag.
	 * @return the sendFlag attribute reference
	 */
	public ElementaryRangeReference getSendFlagReference() {
		return sendFlag.getReference();
	}

	/**
	 * Getter for sendFlag attribute.
	 * @return sendFlag attribute
	 */
	public String getSendFlag() {
		return sendFlag.getValue();
	}

	/**
	 * Setter for sendFlag attribute.
	 * @param sendFlag the new value of sendFlag
	 */
	public void setSendFlag(String sendFlag) {
		this.sendFlag.setValue(sendFlag);
	}
	/**
	 * Gets the reference for attribute sendErase.
	 * @return the sendErase attribute reference
	 */
	public ConditionReference getSendEraseReference() {
		return sendFlag.getCondition(sendErase);	
	}

	/**
	 * Getter for sendErase attribute.
	 * @return sendErase attribute
	 */
	public boolean isSendErase() {
		return getSendEraseReference().getValue();	
	}

	/**
	 * Setter for sendErase attribute.
	 * @param sendErase the new value of sendErase
	 */
	public void setSendErase(boolean sendErase) {
		getSendEraseReference().setValue(sendErase);	
	}
	/**
	 * Gets the reference for attribute sendDataonly.
	 * @return the sendDataonly attribute reference
	 */
	public ConditionReference getSendDataonlyReference() {
		return sendFlag.getCondition(sendDataonly);	
	}

	/**
	 * Getter for sendDataonly attribute.
	 * @return sendDataonly attribute
	 */
	public boolean isSendDataonly() {
		return getSendDataonlyReference().getValue();	
	}

	/**
	 * Setter for sendDataonly attribute.
	 * @param sendDataonly the new value of sendDataonly
	 */
	public void setSendDataonly(boolean sendDataonly) {
		getSendDataonlyReference().setValue(sendDataonly);	
	}
	/**
	 * Gets the reference for attribute sendError.
	 * @return the sendError attribute reference
	 */
	public ConditionReference getSendErrorReference() {
		return sendFlag.getCondition(sendError);	
	}

	/**
	 * Getter for sendError attribute.
	 * @return sendError attribute
	 */
	public boolean isSendError() {
		return getSendErrorReference().getValue();	
	}

	/**
	 * Setter for sendError attribute.
	 * @param sendError the new value of sendError
	 */
	public void setSendError(boolean sendError) {
		getSendErrorReference().setValue(sendError);	
	}
}
