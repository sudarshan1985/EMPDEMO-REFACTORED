package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
/**
 * Data simplifier entity CommareaArea.
 * 
 * <p>About 'caEmpno' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class CommareaArea extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary caEmpno = new Elementary(root,new AlphanumericType(6));
	

	/**
	 * Instantiate a new CommareaArea with a default record.
	 * @param configuration the configuration
	 */
	public CommareaArea(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new CommareaArea bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public CommareaArea(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute caEmpno.
	 * @return the caEmpno attribute reference
	 */
	public ElementaryRangeReference getCaEmpnoReference() {
		return caEmpno.getReference();
	}

	/**
	 * Getter for caEmpno attribute.
	 * @return caEmpno attribute
	 */
	public String getCaEmpno() {
		return caEmpno.getValue();
	}

	/**
	 * Setter for caEmpno attribute.
	 * @param caEmpno the new value of caEmpno
	 */
	public void setCaEmpno(String caEmpno) {
		this.caEmpno.setValue(caEmpno);
	}
}
