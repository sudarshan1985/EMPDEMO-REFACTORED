package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.BinaryType;
/**
 * Data simplifier entity Respcode.
 * 
 * <p>About 'respcode' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class Respcode extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary respcode = new Elementary(root,new BinaryType(8, 0, "STD", false, false, true));
	

	/**
	 * Instantiate a new Respcode with a default record.
	 * @param configuration the configuration
	 */
	public Respcode(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new Respcode bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public Respcode(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute respcode.
	 * @return the respcode attribute reference
	 */
	public ElementaryRangeReference getRespcodeReference() {
		return respcode.getReference();
	}

	/**
	 * Getter for respcode attribute.
	 * @return respcode attribute
	 */
	public int getRespcode() {
		return respcode.getValue();
	}

	/**
	 * Setter for respcode attribute.
	 * @param respcode the new value of respcode
	 */
	public void setRespcode(int respcode) {
		this.respcode.setValue(respcode);
	}
}
