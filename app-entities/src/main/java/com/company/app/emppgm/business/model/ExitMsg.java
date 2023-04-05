package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
/**
 * Data simplifier entity ExitMsg.
 * 
 * <p>About 'exitMsg' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class ExitMsg extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary exitMsg = new Elementary(root,new AlphanumericType(34),"CESF LOGOFF PRESS ENTER FOR LOGOFF");
	

	/**
	 * Instantiate a new ExitMsg with a default record.
	 * @param configuration the configuration
	 */
	public ExitMsg(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new ExitMsg bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public ExitMsg(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute exitMsg.
	 * @return the exitMsg attribute reference
	 */
	public ElementaryRangeReference getExitMsgReference() {
		return exitMsg.getReference();
	}

	/**
	 * Getter for exitMsg attribute.
	 * @return exitMsg attribute
	 */
	public String getExitMsg() {
		return exitMsg.getValue();
	}

	/**
	 * Setter for exitMsg attribute.
	 * @param exitMsg the new value of exitMsg
	 */
	public void setExitMsg(String exitMsg) {
		this.exitMsg.setValue(exitMsg);
	}
}
