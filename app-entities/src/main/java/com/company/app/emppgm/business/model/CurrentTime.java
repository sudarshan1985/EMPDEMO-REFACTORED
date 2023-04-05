package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.BinaryType;
/**
 * Data simplifier entity CurrentTime.
 * 
 * <p>About 'tstamp' field, <br>
 * </p>
 * 
 * <p>About 'outdate' field, <br>
 * </p>
 * 
 * <p>About 'outtime' field, <br>
 * </p>
 * 
 * @see RecordEntity
 */
public class CurrentTime extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Elementary tstamp = new Elementary(root,new BinaryType(14, 0, "STD", false, false, true));
	private final Elementary outdate = new Elementary(root,new AlphanumericType(10));
	private final Elementary outtime = new Elementary(root,new AlphanumericType(8));
	

	/**
	 * Instantiate a new CurrentTime with a default record.
	 * @param configuration the configuration
	 */
	public CurrentTime(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new CurrentTime bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public CurrentTime(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	/**
	 * Gets the reference for attribute tstamp.
	 * @return the tstamp attribute reference
	 */
	public ElementaryRangeReference getTstampReference() {
		return tstamp.getReference();
	}

	/**
	 * Getter for tstamp attribute.
	 * @return tstamp attribute
	 */
	public long getTstamp() {
		return tstamp.getValue();
	}

	/**
	 * Setter for tstamp attribute.
	 * @param tstamp the new value of tstamp
	 */
	public void setTstamp(long tstamp) {
		this.tstamp.setValue(tstamp);
	}
	/**
	 * Gets the reference for attribute outdate.
	 * @return the outdate attribute reference
	 */
	public ElementaryRangeReference getOutdateReference() {
		return outdate.getReference();
	}

	/**
	 * Getter for outdate attribute.
	 * @return outdate attribute
	 */
	public String getOutdate() {
		return outdate.getValue();
	}

	/**
	 * Setter for outdate attribute.
	 * @param outdate the new value of outdate
	 */
	public void setOutdate(String outdate) {
		this.outdate.setValue(outdate);
	}
	/**
	 * Gets the reference for attribute outtime.
	 * @return the outtime attribute reference
	 */
	public ElementaryRangeReference getOuttimeReference() {
		return outtime.getReference();
	}

	/**
	 * Getter for outtime attribute.
	 * @return outtime attribute
	 */
	public String getOuttime() {
		return outtime.getValue();
	}

	/**
	 * Setter for outtime attribute.
	 * @param outtime the new value of outtime
	 */
	public void setOuttime(String outtime) {
		this.outtime.setValue(outtime);
	}
}
