package com.company.app.emppgm.business.context;
import com.company.app.emppgm.business.model.CommareaArea;
import com.company.app.emppgm.business.model.CurrentTime;
import com.company.app.emppgm.business.model.Dfhaid;
import com.company.app.emppgm.business.model.Dfhbmsca;
import com.company.app.emppgm.business.model.Dfhcommarea;
import com.company.app.emppgm.business.model.Dfheiblk;
import com.company.app.emppgm.business.model.ExitMsg;
import com.company.app.emppgm.business.model.Flags;
import com.company.app.emppgm.business.model.FsEmprec;
import com.company.app.emppgm.business.model.Group1;
import com.company.app.emppgm.business.model.Respcode;
import com.company.app.emppgm.business.model.Switches;
import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.rt.jics.context.JicsRuntimeContext;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Data simplifier context EmppgmContext.
 * 
 * <p>About 'onCode' field, <br>
 * </p>
 * 
 * <p>About 'onChar' field, <br>
 * </p>
 * 
 * <p>About 'group1' field, <br>uml entity: com.company.app.emppgm.business.model.Group1
 * <br></p>
 * 
 * <p>About 'dfhbmsca' field, <br>uml entity: com.company.app.emppgm.business.model.Dfhbmsca
 * <br></p>
 * 
 * <p>About 'dfhaid' field, <br>uml entity: com.company.app.emppgm.business.model.Dfhaid
 * <br></p>
 * 
 * <p>About 'fsEmprec' field, <br>uml entity: com.company.app.emppgm.business.model.FsEmprec
 * <br></p>
 * 
 * <p>About 'switches' field, <br>uml entity: com.company.app.emppgm.business.model.Switches
 * <br></p>
 * 
 * <p>About 'flags' field, <br>uml entity: com.company.app.emppgm.business.model.Flags
 * <br></p>
 * 
 * <p>About 'exitMsg' field, <br>uml entity: com.company.app.emppgm.business.model.ExitMsg
 * <br></p>
 * 
 * <p>About 'commareaArea' field, <br>uml entity: com.company.app.emppgm.business.model.CommareaArea
 * <br></p>
 * 
 * <p>About 'currentTime' field, <br>uml entity: com.company.app.emppgm.business.model.CurrentTime
 * <br></p>
 * 
 * <p>About 'respcode' field, <br>uml entity: com.company.app.emppgm.business.model.Respcode
 * <br></p>
 * 
 * <p>About 'dfheiblk' field, <br>uml entity: com.company.app.emppgm.business.model.Dfheiblk
 * <br></p>
 * 
 * <p>About 'dfhcommarea' field, <br>uml entity: com.company.app.emppgm.business.model.Dfhcommarea
 * <br></p>
 * 
 */
@Component("com.company.app.emppgm.business.context.EmppgmContext")
@Lazy
@Scope("prototype")
public class EmppgmContext extends JicsRuntimeContext {
	
	private Group1 group1;
	private Dfhbmsca dfhbmsca;
	private Dfhaid dfhaid;
	private FsEmprec fsEmprec;
	private Switches switches;
	private Flags flags;
	private ExitMsg exitMsg;
	private CommareaArea commareaArea;
	private CurrentTime currentTime;
	private Respcode respcode;
	private Dfheiblk dfheiblk;
	private Dfhcommarea dfhcommarea;

	private List<RecordEntity> recordEntities;

	/**
	 * Default constructor.
	 * @param configuration the datasimplifier configuration
	 */
	public EmppgmContext (@Qualifier("EmppgmContextConfiguration") Configuration configuration) {
		super(configuration);
		initWorking(configuration);
		initLinkage(configuration);
		initRecordEntities();
	}
	
	

	/**
	 * Getter for group1.
	 * @return the group1
	 */
	public Group1 getGroup1() {
		return this.group1;
	}

	/**
	 * Setter for group1.
	 * @param reference the new value for group1
	 */
	public void setGroup1(RangeReference reference) {
		this.group1.setBytes(reference.getBytes());
	}

	/**
	 * Getter for dfhbmsca.
	 * @return the dfhbmsca
	 */
	public Dfhbmsca getDfhbmsca() {
		return this.dfhbmsca;
	}

	/**
	 * Setter for dfhbmsca.
	 * @param reference the new value for dfhbmsca
	 */
	public void setDfhbmsca(RangeReference reference) {
		this.dfhbmsca.setBytes(reference.getBytes());
	}

	/**
	 * Getter for dfhaid.
	 * @return the dfhaid
	 */
	public Dfhaid getDfhaid() {
		return this.dfhaid;
	}

	/**
	 * Setter for dfhaid.
	 * @param reference the new value for dfhaid
	 */
	public void setDfhaid(RangeReference reference) {
		this.dfhaid.setBytes(reference.getBytes());
	}

	/**
	 * Getter for fsEmprec.
	 * @return the fsEmprec
	 */
	public FsEmprec getFsEmprec() {
		return this.fsEmprec;
	}

	/**
	 * Setter for fsEmprec.
	 * @param reference the new value for fsEmprec
	 */
	public void setFsEmprec(RangeReference reference) {
		this.fsEmprec.setBytes(reference.getBytes());
	}

	/**
	 * Getter for switches.
	 * @return the switches
	 */
	public Switches getSwitches() {
		return this.switches;
	}

	/**
	 * Setter for switches.
	 * @param reference the new value for switches
	 */
	public void setSwitches(RangeReference reference) {
		this.switches.setBytes(reference.getBytes());
	}

	/**
	 * Getter for flags.
	 * @return the flags
	 */
	public Flags getFlags() {
		return this.flags;
	}

	/**
	 * Setter for flags.
	 * @param reference the new value for flags
	 */
	public void setFlags(RangeReference reference) {
		this.flags.setBytes(reference.getBytes());
	}

	/**
	 * Getter for exitMsg.
	 * @return the exitMsg
	 */
	public ExitMsg getExitMsg() {
		return this.exitMsg;
	}

	/**
	 * Setter for exitMsg.
	 * @param reference the new value for exitMsg
	 */
	public void setExitMsg(RangeReference reference) {
		this.exitMsg.setBytes(reference.getBytes());
	}

	/**
	 * Getter for commareaArea.
	 * @return the commareaArea
	 */
	public CommareaArea getCommareaArea() {
		return this.commareaArea;
	}

	/**
	 * Setter for commareaArea.
	 * @param reference the new value for commareaArea
	 */
	public void setCommareaArea(RangeReference reference) {
		this.commareaArea.setBytes(reference.getBytes());
	}

	/**
	 * Getter for currentTime.
	 * @return the currentTime
	 */
	public CurrentTime getCurrentTime() {
		return this.currentTime;
	}

	/**
	 * Setter for currentTime.
	 * @param reference the new value for currentTime
	 */
	public void setCurrentTime(RangeReference reference) {
		this.currentTime.setBytes(reference.getBytes());
	}

	/**
	 * Getter for respcode.
	 * @return the respcode
	 */
	public Respcode getRespcode() {
		return this.respcode;
	}

	/**
	 * Setter for respcode.
	 * @param reference the new value for respcode
	 */
	public void setRespcode(RangeReference reference) {
		this.respcode.setBytes(reference.getBytes());
	}

	/**
	 * Getter for dfheiblk.
	 * @return the dfheiblk
	 */
	public Dfheiblk getDfheiblk() {
		return this.dfheiblk;
	}

	/**
	 * Setter for dfheiblk.
	 * @param reference the new value for dfheiblk
	 */
	public void setDfheiblk(RangeReference reference) {
		this.dfheiblk.setBytes(reference.getBytes());
	}

	/**
	 * Getter for dfhcommarea.
	 * @return the dfhcommarea
	 */
	public Dfhcommarea getDfhcommarea() {
		return this.dfhcommarea;
	}

	/**
	 * Setter for dfhcommarea.
	 * @param reference the new value for dfhcommarea
	 */
	public void setDfhcommarea(RangeReference reference) {
		this.dfhcommarea.setBytes(reference.getBytes());
	}

	@Override 
	public void cleanUp(){
	}

	@Override
	protected void doReset() {
		cleanUp();
	    // reset the working
		recordEntities.stream().forEach(e -> e.reset());
	}

	
	private void initWorking(Configuration configuration) {
		group1 = new Group1(configuration);
		dfhbmsca = new Dfhbmsca(configuration);
		dfhaid = new Dfhaid(configuration);
		fsEmprec = new FsEmprec(configuration);
		switches = new Switches(configuration);
		flags = new Flags(configuration);
		exitMsg = new ExitMsg(configuration);
		commareaArea = new CommareaArea(configuration);
		currentTime = new CurrentTime(configuration);
		respcode = new Respcode(configuration);
	}
	
	private void initLinkage(Configuration configuration) {
		dfheiblk = new Dfheiblk(configuration);
		dfhcommarea = new Dfhcommarea(configuration);
	
	}

	private void initRecordEntities() {
		recordEntities = Arrays.asList(group1, dfhbmsca, dfhaid, fsEmprec, switches, flags, exitMsg, commareaArea, currentTime, respcode);
	}

	@Override
	public String toString(){
		StringBuilder toSB = new StringBuilder("\nEmppgmContext:\n");
		if(!this.recordEntities.isEmpty()){
			this.recordEntities.forEach(e -> toSB.append(e.getClass().getSimpleName()).append(" : [").append(e.toString()).append("]\n"));
		}
		toSB.append("Dfheiblk : [").append(dfheiblk.toString()).append("]\n");
		toSB.append("Dfhcommarea : [").append(dfhcommarea.toString()).append("]\n");
		return toSB.toString();
	}

}
