package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
/**
 * Data simplifier entity Dfhcommarea.
 * 
 * <p>About 'dfhEmpno' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class Dfhcommarea extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary dfhEmpno = new Elementary(root,new AlphanumericType(6));
	

	/**
	 * Instantiate a new Dfhcommarea with a default record.
	 * @param configuration the configuration
	 */
	public Dfhcommarea(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new Dfhcommarea bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public Dfhcommarea(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute dfhEmpno.
	 * @return the dfhEmpno attribute reference
	 */
	public ElementaryRangeReference getDfhEmpnoReference() {
		return dfhEmpno.getReference();
	}

	/**
	 * Getter for dfhEmpno attribute.
	 * @return dfhEmpno attribute
	 */
	public String getDfhEmpno() {
		return dfhEmpno.getValue();
	}

	/**
	 * Setter for dfhEmpno attribute.
	 * @param dfhEmpno the new value of dfhEmpno
	 */
	public void setDfhEmpno(String dfhEmpno) {
		this.dfhEmpno.setValue(dfhEmpno);
	}
}
