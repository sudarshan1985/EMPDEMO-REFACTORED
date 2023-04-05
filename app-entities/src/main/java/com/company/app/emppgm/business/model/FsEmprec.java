package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Filler;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.ZonedType;
/**
 * Data simplifier entity FsEmprec.
 * 
 * <p>About 'empno' field, <br>
 * </p>
 * 
 * <p>About 'efname' field, <br>
 * </p>
 * 
 * <p>About 'elname' field, <br>
 * </p>
 * 
 * <p>About 'eaddr' field, <br>
 * </p>
 * 
 * <p>About 'ephno' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class FsEmprec extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary empno = new Elementary(root,new ZonedType(6, 0, false));
	private final Elementary efname = new Elementary(root,new AlphanumericType(10));
	private final Elementary elname = new Elementary(root,new AlphanumericType(10));
	private final Elementary eaddr = new Elementary(root,new AlphanumericType(10));
	private final Elementary ephno = new Elementary(root,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler = new Filler(root,new AlphanumericType(34)," ");
	

	/**
	 * Instantiate a new FsEmprec with a default record.
	 * @param configuration the configuration
	 */
	public FsEmprec(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new FsEmprec bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public FsEmprec(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute empno.
	 * @return the empno attribute reference
	 */
	public ElementaryRangeReference getEmpnoReference() {
		return empno.getReference();
	}

	/**
	 * Getter for empno attribute.
	 * @return empno attribute
	 */
	public int getEmpno() {
		return empno.getValue();
	}

	/**
	 * Setter for empno attribute.
	 * @param empno the new value of empno
	 */
	public void setEmpno(int empno) {
		this.empno.setValue(empno);
	}
	/**
	 * Gets the reference for attribute efname.
	 * @return the efname attribute reference
	 */
	public ElementaryRangeReference getEfnameReference() {
		return efname.getReference();
	}

	/**
	 * Getter for efname attribute.
	 * @return efname attribute
	 */
	public String getEfname() {
		return efname.getValue();
	}

	/**
	 * Setter for efname attribute.
	 * @param efname the new value of efname
	 */
	public void setEfname(String efname) {
		this.efname.setValue(efname);
	}
	/**
	 * Gets the reference for attribute elname.
	 * @return the elname attribute reference
	 */
	public ElementaryRangeReference getElnameReference() {
		return elname.getReference();
	}

	/**
	 * Getter for elname attribute.
	 * @return elname attribute
	 */
	public String getElname() {
		return elname.getValue();
	}

	/**
	 * Setter for elname attribute.
	 * @param elname the new value of elname
	 */
	public void setElname(String elname) {
		this.elname.setValue(elname);
	}
	/**
	 * Gets the reference for attribute eaddr.
	 * @return the eaddr attribute reference
	 */
	public ElementaryRangeReference getEaddrReference() {
		return eaddr.getReference();
	}

	/**
	 * Getter for eaddr attribute.
	 * @return eaddr attribute
	 */
	public String getEaddr() {
		return eaddr.getValue();
	}

	/**
	 * Setter for eaddr attribute.
	 * @param eaddr the new value of eaddr
	 */
	public void setEaddr(String eaddr) {
		this.eaddr.setValue(eaddr);
	}
	/**
	 * Gets the reference for attribute ephno.
	 * @return the ephno attribute reference
	 */
	public ElementaryRangeReference getEphnoReference() {
		return ephno.getReference();
	}

	/**
	 * Getter for ephno attribute.
	 * @return ephno attribute
	 */
	public String getEphno() {
		return ephno.getValue();
	}

	/**
	 * Setter for ephno attribute.
	 * @param ephno the new value of ephno
	 */
	public void setEphno(String ephno) {
		this.ephno.setValue(ephno);
	}
}
