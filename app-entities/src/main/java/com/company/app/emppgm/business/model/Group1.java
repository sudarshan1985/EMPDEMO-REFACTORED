package com.company.app.emppgm.business.model;

import com.netfective.bluage.gapwalk.datasimplifier.configuration.Configuration;
import com.netfective.bluage.gapwalk.datasimplifier.data.RecordAdaptable;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Elementary;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Filler;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Group;
import com.netfective.bluage.gapwalk.datasimplifier.data.structure.Union;
import com.netfective.bluage.gapwalk.datasimplifier.entity.ElementaryRangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RangeReference;
import com.netfective.bluage.gapwalk.datasimplifier.entity.RecordEntity;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.AlphanumericType;
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.BinaryType;
/**
 * Data simplifier entity Group1.
 * 
 * <p>About 'empmapi' field, <br>uml entity: com.company.app.emppgm.business.model.Empmapi
 * <br></p>
 * 
 * <p>About 'empmapo' field, <br>uml entity: com.company.app.emppgm.business.model.Empmapo
 * <br></p>
 * 
 * @see RecordEntity
 */
public class Group1 extends RecordEntity {

	private final Group root = new Group(getData()); 
	private final Union union = new Union(root);
	private final Group empmapi = new Group(union);
	@SuppressWarnings("unused")
	private final Filler filler = new Filler(empmapi,new AlphanumericType(12));
	private final Elementary tranidl = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union1 = new Union(empmapi);
	private final Elementary tranidf = new Elementary(union1,new AlphanumericType(1));
	private final Group group = new Group(union1);
	private final Elementary tranida = new Elementary(group,new AlphanumericType(1));
	private final Elementary tranidi = new Elementary(empmapi,new AlphanumericType(4));
	private final Elementary datel = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union2 = new Union(empmapi);
	private final Elementary datef = new Elementary(union2,new AlphanumericType(1));
	private final Group group1 = new Group(union2);
	private final Elementary datea = new Elementary(group1,new AlphanumericType(1));
	private final Elementary datei = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary timel = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union3 = new Union(empmapi);
	private final Elementary timef = new Elementary(union3,new AlphanumericType(1));
	private final Group group2 = new Group(union3);
	private final Elementary timea = new Elementary(group2,new AlphanumericType(1));
	private final Elementary timei = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary empnol = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union4 = new Union(empmapi);
	private final Elementary empnof = new Elementary(union4,new AlphanumericType(1));
	private final Group group3 = new Group(union4);
	private final Elementary empnoa = new Elementary(group3,new AlphanumericType(1));
	private final Elementary empnoi = new Elementary(empmapi,new AlphanumericType(6));
	private final Elementary efnamel = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union5 = new Union(empmapi);
	private final Elementary efnamef = new Elementary(union5,new AlphanumericType(1));
	private final Group group4 = new Group(union5);
	private final Elementary efnamea = new Elementary(group4,new AlphanumericType(1));
	private final Elementary efnamei = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary elnamel = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union6 = new Union(empmapi);
	private final Elementary elnamef = new Elementary(union6,new AlphanumericType(1));
	private final Group group5 = new Group(union6);
	private final Elementary elnamea = new Elementary(group5,new AlphanumericType(1));
	private final Elementary elnamei = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary eaddrl = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union7 = new Union(empmapi);
	private final Elementary eaddrf = new Elementary(union7,new AlphanumericType(1));
	private final Group group6 = new Group(union7);
	private final Elementary eaddra = new Elementary(group6,new AlphanumericType(1));
	private final Elementary eaddri = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary ephnol = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union8 = new Union(empmapi);
	private final Elementary ephnof = new Elementary(union8,new AlphanumericType(1));
	private final Group group7 = new Group(union8);
	private final Elementary ephnoa = new Elementary(group7,new AlphanumericType(1));
	private final Elementary ephnoi = new Elementary(empmapi,new AlphanumericType(10));
	private final Elementary messagel = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union9 = new Union(empmapi);
	private final Elementary messagef = new Elementary(union9,new AlphanumericType(1));
	private final Group group8 = new Group(union9);
	private final Elementary messagea = new Elementary(group8,new AlphanumericType(1));
	private final Elementary messagei = new Elementary(empmapi,new AlphanumericType(79));
	private final Elementary dummyl = new Elementary(empmapi,new BinaryType(4, 0, "STD", false, false, true));
	private final Union union10 = new Union(empmapi);
	private final Elementary dummyf = new Elementary(union10,new AlphanumericType(1));
	private final Group group9 = new Group(union10);
	private final Elementary dummya = new Elementary(group9,new AlphanumericType(1));
	private final Elementary dummyi = new Elementary(empmapi,new AlphanumericType(1));
	private final Group empmapo = new Group(union);
	@SuppressWarnings("unused")
	private final Filler filler1 = new Filler(empmapo,new AlphanumericType(12));
	@SuppressWarnings("unused")
	private final Filler filler2 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary tranido = new Elementary(empmapo,new AlphanumericType(4));
	@SuppressWarnings("unused")
	private final Filler filler3 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary dateo = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler4 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary timeo = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler5 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary empnoo = new Elementary(empmapo,new AlphanumericType(6));
	@SuppressWarnings("unused")
	private final Filler filler6 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary efnameo = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler7 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary elnameo = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler8 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary eaddro = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler9 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary ephnoo = new Elementary(empmapo,new AlphanumericType(10));
	@SuppressWarnings("unused")
	private final Filler filler10 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary messageo = new Elementary(empmapo,new AlphanumericType(79));
	@SuppressWarnings("unused")
	private final Filler filler11 = new Filler(empmapo,new AlphanumericType(3));
	private final Elementary dummyo = new Elementary(empmapo,new AlphanumericType(1));
	

	/**
	 * Instantiate a new Group1 with a default record.
	 * @param configuration the configuration
	 */
	public Group1(Configuration configuration) {
		super(configuration);
		setupRoot(root);
	}
	/**
	 * Instantiate a new Group1 bound to the provided record.
	 * @param configuration the configuration
	 * @param record the existing record to bind
	 */
	public Group1(Configuration configuration, RecordAdaptable record) {
		super(configuration);
		setupRoot(root, record);
	}

	
	/**
	 * Gets the reference for attribute empmapi.
	 * @return the empmapi attribute reference
	 */
	public RangeReference getEmpmapiReference() {
		return empmapi.getReference();
	}	
				
	/**
	 * Setter for empmapi .
	 */
   	public void setEmpmapi(RangeReference reference) {
       	empmapi.getReference().setBytes(reference.getBytes());
   	}
 
	/**
	 * Gets the reference for attribute tranidl.
	 * @return the tranidl attribute reference
	 */
	public ElementaryRangeReference getTranidlReference() {
		return tranidl.getReference();
	}

	/**
	 * Getter for tranidl attribute.
	 * @return tranidl attribute
	 */
	public int getTranidl() {
		return tranidl.getValue();
	}

	/**
	 * Setter for tranidl attribute.
	 * @param tranidl the new value of tranidl
	 */
	public void setTranidl(int tranidl) {
		this.tranidl.setValue(tranidl);
	}
	/**
	 * Gets the reference for attribute tranidf.
	 * @return the tranidf attribute reference
	 */
	public ElementaryRangeReference getTranidfReference() {
		return tranidf.getReference();
	}

	/**
	 * Getter for tranidf attribute.
	 * @return tranidf attribute
	 */
	public String getTranidf() {
		return tranidf.getValue();
	}

	/**
	 * Setter for tranidf attribute.
	 * @param tranidf the new value of tranidf
	 */
	public void setTranidf(String tranidf) {
		this.tranidf.setValue(tranidf);
	}
	/**
	 * Gets the reference for attribute tranida.
	 * @return the tranida attribute reference
	 */
	public ElementaryRangeReference getTranidaReference() {
		return tranida.getReference();
	}

	/**
	 * Getter for tranida attribute.
	 * @return tranida attribute
	 */
	public String getTranida() {
		return tranida.getValue();
	}

	/**
	 * Setter for tranida attribute.
	 * @param tranida the new value of tranida
	 */
	public void setTranida(String tranida) {
		this.tranida.setValue(tranida);
	}
	/**
	 * Gets the reference for attribute tranidi.
	 * @return the tranidi attribute reference
	 */
	public ElementaryRangeReference getTranidiReference() {
		return tranidi.getReference();
	}

	/**
	 * Getter for tranidi attribute.
	 * @return tranidi attribute
	 */
	public String getTranidi() {
		return tranidi.getValue();
	}

	/**
	 * Setter for tranidi attribute.
	 * @param tranidi the new value of tranidi
	 */
	public void setTranidi(String tranidi) {
		this.tranidi.setValue(tranidi);
	}
	/**
	 * Gets the reference for attribute datel.
	 * @return the datel attribute reference
	 */
	public ElementaryRangeReference getDatelReference() {
		return datel.getReference();
	}

	/**
	 * Getter for datel attribute.
	 * @return datel attribute
	 */
	public int getDatel() {
		return datel.getValue();
	}

	/**
	 * Setter for datel attribute.
	 * @param datel the new value of datel
	 */
	public void setDatel(int datel) {
		this.datel.setValue(datel);
	}
	/**
	 * Gets the reference for attribute datef.
	 * @return the datef attribute reference
	 */
	public ElementaryRangeReference getDatefReference() {
		return datef.getReference();
	}

	/**
	 * Getter for datef attribute.
	 * @return datef attribute
	 */
	public String getDatef() {
		return datef.getValue();
	}

	/**
	 * Setter for datef attribute.
	 * @param datef the new value of datef
	 */
	public void setDatef(String datef) {
		this.datef.setValue(datef);
	}
	/**
	 * Gets the reference for attribute datea.
	 * @return the datea attribute reference
	 */
	public ElementaryRangeReference getDateaReference() {
		return datea.getReference();
	}

	/**
	 * Getter for datea attribute.
	 * @return datea attribute
	 */
	public String getDatea() {
		return datea.getValue();
	}

	/**
	 * Setter for datea attribute.
	 * @param datea the new value of datea
	 */
	public void setDatea(String datea) {
		this.datea.setValue(datea);
	}
	/**
	 * Gets the reference for attribute datei.
	 * @return the datei attribute reference
	 */
	public ElementaryRangeReference getDateiReference() {
		return datei.getReference();
	}

	/**
	 * Getter for datei attribute.
	 * @return datei attribute
	 */
	public String getDatei() {
		return datei.getValue();
	}

	/**
	 * Setter for datei attribute.
	 * @param datei the new value of datei
	 */
	public void setDatei(String datei) {
		this.datei.setValue(datei);
	}
	/**
	 * Gets the reference for attribute timel.
	 * @return the timel attribute reference
	 */
	public ElementaryRangeReference getTimelReference() {
		return timel.getReference();
	}

	/**
	 * Getter for timel attribute.
	 * @return timel attribute
	 */
	public int getTimel() {
		return timel.getValue();
	}

	/**
	 * Setter for timel attribute.
	 * @param timel the new value of timel
	 */
	public void setTimel(int timel) {
		this.timel.setValue(timel);
	}
	/**
	 * Gets the reference for attribute timef.
	 * @return the timef attribute reference
	 */
	public ElementaryRangeReference getTimefReference() {
		return timef.getReference();
	}

	/**
	 * Getter for timef attribute.
	 * @return timef attribute
	 */
	public String getTimef() {
		return timef.getValue();
	}

	/**
	 * Setter for timef attribute.
	 * @param timef the new value of timef
	 */
	public void setTimef(String timef) {
		this.timef.setValue(timef);
	}
	/**
	 * Gets the reference for attribute timea.
	 * @return the timea attribute reference
	 */
	public ElementaryRangeReference getTimeaReference() {
		return timea.getReference();
	}

	/**
	 * Getter for timea attribute.
	 * @return timea attribute
	 */
	public String getTimea() {
		return timea.getValue();
	}

	/**
	 * Setter for timea attribute.
	 * @param timea the new value of timea
	 */
	public void setTimea(String timea) {
		this.timea.setValue(timea);
	}
	/**
	 * Gets the reference for attribute timei.
	 * @return the timei attribute reference
	 */
	public ElementaryRangeReference getTimeiReference() {
		return timei.getReference();
	}

	/**
	 * Getter for timei attribute.
	 * @return timei attribute
	 */
	public String getTimei() {
		return timei.getValue();
	}

	/**
	 * Setter for timei attribute.
	 * @param timei the new value of timei
	 */
	public void setTimei(String timei) {
		this.timei.setValue(timei);
	}
	/**
	 * Gets the reference for attribute empnol.
	 * @return the empnol attribute reference
	 */
	public ElementaryRangeReference getEmpnolReference() {
		return empnol.getReference();
	}

	/**
	 * Getter for empnol attribute.
	 * @return empnol attribute
	 */
	public int getEmpnol() {
		return empnol.getValue();
	}

	/**
	 * Setter for empnol attribute.
	 * @param empnol the new value of empnol
	 */
	public void setEmpnol(int empnol) {
		this.empnol.setValue(empnol);
	}
	/**
	 * Gets the reference for attribute empnof.
	 * @return the empnof attribute reference
	 */
	public ElementaryRangeReference getEmpnofReference() {
		return empnof.getReference();
	}

	/**
	 * Getter for empnof attribute.
	 * @return empnof attribute
	 */
	public String getEmpnof() {
		return empnof.getValue();
	}

	/**
	 * Setter for empnof attribute.
	 * @param empnof the new value of empnof
	 */
	public void setEmpnof(String empnof) {
		this.empnof.setValue(empnof);
	}
	/**
	 * Gets the reference for attribute empnoa.
	 * @return the empnoa attribute reference
	 */
	public ElementaryRangeReference getEmpnoaReference() {
		return empnoa.getReference();
	}

	/**
	 * Getter for empnoa attribute.
	 * @return empnoa attribute
	 */
	public String getEmpnoa() {
		return empnoa.getValue();
	}

	/**
	 * Setter for empnoa attribute.
	 * @param empnoa the new value of empnoa
	 */
	public void setEmpnoa(String empnoa) {
		this.empnoa.setValue(empnoa);
	}
	/**
	 * Gets the reference for attribute empnoi.
	 * @return the empnoi attribute reference
	 */
	public ElementaryRangeReference getEmpnoiReference() {
		return empnoi.getReference();
	}

	/**
	 * Getter for empnoi attribute.
	 * @return empnoi attribute
	 */
	public String getEmpnoi() {
		return empnoi.getValue();
	}

	/**
	 * Setter for empnoi attribute.
	 * @param empnoi the new value of empnoi
	 */
	public void setEmpnoi(String empnoi) {
		this.empnoi.setValue(empnoi);
	}
	/**
	 * Gets the reference for attribute efnamel.
	 * @return the efnamel attribute reference
	 */
	public ElementaryRangeReference getEfnamelReference() {
		return efnamel.getReference();
	}

	/**
	 * Getter for efnamel attribute.
	 * @return efnamel attribute
	 */
	public int getEfnamel() {
		return efnamel.getValue();
	}

	/**
	 * Setter for efnamel attribute.
	 * @param efnamel the new value of efnamel
	 */
	public void setEfnamel(int efnamel) {
		this.efnamel.setValue(efnamel);
	}
	/**
	 * Gets the reference for attribute efnamef.
	 * @return the efnamef attribute reference
	 */
	public ElementaryRangeReference getEfnamefReference() {
		return efnamef.getReference();
	}

	/**
	 * Getter for efnamef attribute.
	 * @return efnamef attribute
	 */
	public String getEfnamef() {
		return efnamef.getValue();
	}

	/**
	 * Setter for efnamef attribute.
	 * @param efnamef the new value of efnamef
	 */
	public void setEfnamef(String efnamef) {
		this.efnamef.setValue(efnamef);
	}
	/**
	 * Gets the reference for attribute efnamea.
	 * @return the efnamea attribute reference
	 */
	public ElementaryRangeReference getEfnameaReference() {
		return efnamea.getReference();
	}

	/**
	 * Getter for efnamea attribute.
	 * @return efnamea attribute
	 */
	public String getEfnamea() {
		return efnamea.getValue();
	}

	/**
	 * Setter for efnamea attribute.
	 * @param efnamea the new value of efnamea
	 */
	public void setEfnamea(String efnamea) {
		this.efnamea.setValue(efnamea);
	}
	/**
	 * Gets the reference for attribute efnamei.
	 * @return the efnamei attribute reference
	 */
	public ElementaryRangeReference getEfnameiReference() {
		return efnamei.getReference();
	}

	/**
	 * Getter for efnamei attribute.
	 * @return efnamei attribute
	 */
	public String getEfnamei() {
		return efnamei.getValue();
	}

	/**
	 * Setter for efnamei attribute.
	 * @param efnamei the new value of efnamei
	 */
	public void setEfnamei(String efnamei) {
		this.efnamei.setValue(efnamei);
	}
	/**
	 * Gets the reference for attribute elnamel.
	 * @return the elnamel attribute reference
	 */
	public ElementaryRangeReference getElnamelReference() {
		return elnamel.getReference();
	}

	/**
	 * Getter for elnamel attribute.
	 * @return elnamel attribute
	 */
	public int getElnamel() {
		return elnamel.getValue();
	}

	/**
	 * Setter for elnamel attribute.
	 * @param elnamel the new value of elnamel
	 */
	public void setElnamel(int elnamel) {
		this.elnamel.setValue(elnamel);
	}
	/**
	 * Gets the reference for attribute elnamef.
	 * @return the elnamef attribute reference
	 */
	public ElementaryRangeReference getElnamefReference() {
		return elnamef.getReference();
	}

	/**
	 * Getter for elnamef attribute.
	 * @return elnamef attribute
	 */
	public String getElnamef() {
		return elnamef.getValue();
	}

	/**
	 * Setter for elnamef attribute.
	 * @param elnamef the new value of elnamef
	 */
	public void setElnamef(String elnamef) {
		this.elnamef.setValue(elnamef);
	}
	/**
	 * Gets the reference for attribute elnamea.
	 * @return the elnamea attribute reference
	 */
	public ElementaryRangeReference getElnameaReference() {
		return elnamea.getReference();
	}

	/**
	 * Getter for elnamea attribute.
	 * @return elnamea attribute
	 */
	public String getElnamea() {
		return elnamea.getValue();
	}

	/**
	 * Setter for elnamea attribute.
	 * @param elnamea the new value of elnamea
	 */
	public void setElnamea(String elnamea) {
		this.elnamea.setValue(elnamea);
	}
	/**
	 * Gets the reference for attribute elnamei.
	 * @return the elnamei attribute reference
	 */
	public ElementaryRangeReference getElnameiReference() {
		return elnamei.getReference();
	}

	/**
	 * Getter for elnamei attribute.
	 * @return elnamei attribute
	 */
	public String getElnamei() {
		return elnamei.getValue();
	}

	/**
	 * Setter for elnamei attribute.
	 * @param elnamei the new value of elnamei
	 */
	public void setElnamei(String elnamei) {
		this.elnamei.setValue(elnamei);
	}
	/**
	 * Gets the reference for attribute eaddrl.
	 * @return the eaddrl attribute reference
	 */
	public ElementaryRangeReference getEaddrlReference() {
		return eaddrl.getReference();
	}

	/**
	 * Getter for eaddrl attribute.
	 * @return eaddrl attribute
	 */
	public int getEaddrl() {
		return eaddrl.getValue();
	}

	/**
	 * Setter for eaddrl attribute.
	 * @param eaddrl the new value of eaddrl
	 */
	public void setEaddrl(int eaddrl) {
		this.eaddrl.setValue(eaddrl);
	}
	/**
	 * Gets the reference for attribute eaddrf.
	 * @return the eaddrf attribute reference
	 */
	public ElementaryRangeReference getEaddrfReference() {
		return eaddrf.getReference();
	}

	/**
	 * Getter for eaddrf attribute.
	 * @return eaddrf attribute
	 */
	public String getEaddrf() {
		return eaddrf.getValue();
	}

	/**
	 * Setter for eaddrf attribute.
	 * @param eaddrf the new value of eaddrf
	 */
	public void setEaddrf(String eaddrf) {
		this.eaddrf.setValue(eaddrf);
	}
	/**
	 * Gets the reference for attribute eaddra.
	 * @return the eaddra attribute reference
	 */
	public ElementaryRangeReference getEaddraReference() {
		return eaddra.getReference();
	}

	/**
	 * Getter for eaddra attribute.
	 * @return eaddra attribute
	 */
	public String getEaddra() {
		return eaddra.getValue();
	}

	/**
	 * Setter for eaddra attribute.
	 * @param eaddra the new value of eaddra
	 */
	public void setEaddra(String eaddra) {
		this.eaddra.setValue(eaddra);
	}
	/**
	 * Gets the reference for attribute eaddri.
	 * @return the eaddri attribute reference
	 */
	public ElementaryRangeReference getEaddriReference() {
		return eaddri.getReference();
	}

	/**
	 * Getter for eaddri attribute.
	 * @return eaddri attribute
	 */
	public String getEaddri() {
		return eaddri.getValue();
	}

	/**
	 * Setter for eaddri attribute.
	 * @param eaddri the new value of eaddri
	 */
	public void setEaddri(String eaddri) {
		this.eaddri.setValue(eaddri);
	}
	/**
	 * Gets the reference for attribute ephnol.
	 * @return the ephnol attribute reference
	 */
	public ElementaryRangeReference getEphnolReference() {
		return ephnol.getReference();
	}

	/**
	 * Getter for ephnol attribute.
	 * @return ephnol attribute
	 */
	public int getEphnol() {
		return ephnol.getValue();
	}

	/**
	 * Setter for ephnol attribute.
	 * @param ephnol the new value of ephnol
	 */
	public void setEphnol(int ephnol) {
		this.ephnol.setValue(ephnol);
	}
	/**
	 * Gets the reference for attribute ephnof.
	 * @return the ephnof attribute reference
	 */
	public ElementaryRangeReference getEphnofReference() {
		return ephnof.getReference();
	}

	/**
	 * Getter for ephnof attribute.
	 * @return ephnof attribute
	 */
	public String getEphnof() {
		return ephnof.getValue();
	}

	/**
	 * Setter for ephnof attribute.
	 * @param ephnof the new value of ephnof
	 */
	public void setEphnof(String ephnof) {
		this.ephnof.setValue(ephnof);
	}
	/**
	 * Gets the reference for attribute ephnoa.
	 * @return the ephnoa attribute reference
	 */
	public ElementaryRangeReference getEphnoaReference() {
		return ephnoa.getReference();
	}

	/**
	 * Getter for ephnoa attribute.
	 * @return ephnoa attribute
	 */
	public String getEphnoa() {
		return ephnoa.getValue();
	}

	/**
	 * Setter for ephnoa attribute.
	 * @param ephnoa the new value of ephnoa
	 */
	public void setEphnoa(String ephnoa) {
		this.ephnoa.setValue(ephnoa);
	}
	/**
	 * Gets the reference for attribute ephnoi.
	 * @return the ephnoi attribute reference
	 */
	public ElementaryRangeReference getEphnoiReference() {
		return ephnoi.getReference();
	}

	/**
	 * Getter for ephnoi attribute.
	 * @return ephnoi attribute
	 */
	public String getEphnoi() {
		return ephnoi.getValue();
	}

	/**
	 * Setter for ephnoi attribute.
	 * @param ephnoi the new value of ephnoi
	 */
	public void setEphnoi(String ephnoi) {
		this.ephnoi.setValue(ephnoi);
	}
	/**
	 * Gets the reference for attribute messagel.
	 * @return the messagel attribute reference
	 */
	public ElementaryRangeReference getMessagelReference() {
		return messagel.getReference();
	}

	/**
	 * Getter for messagel attribute.
	 * @return messagel attribute
	 */
	public int getMessagel() {
		return messagel.getValue();
	}

	/**
	 * Setter for messagel attribute.
	 * @param messagel the new value of messagel
	 */
	public void setMessagel(int messagel) {
		this.messagel.setValue(messagel);
	}
	/**
	 * Gets the reference for attribute messagef.
	 * @return the messagef attribute reference
	 */
	public ElementaryRangeReference getMessagefReference() {
		return messagef.getReference();
	}

	/**
	 * Getter for messagef attribute.
	 * @return messagef attribute
	 */
	public String getMessagef() {
		return messagef.getValue();
	}

	/**
	 * Setter for messagef attribute.
	 * @param messagef the new value of messagef
	 */
	public void setMessagef(String messagef) {
		this.messagef.setValue(messagef);
	}
	/**
	 * Gets the reference for attribute messagea.
	 * @return the messagea attribute reference
	 */
	public ElementaryRangeReference getMessageaReference() {
		return messagea.getReference();
	}

	/**
	 * Getter for messagea attribute.
	 * @return messagea attribute
	 */
	public String getMessagea() {
		return messagea.getValue();
	}

	/**
	 * Setter for messagea attribute.
	 * @param messagea the new value of messagea
	 */
	public void setMessagea(String messagea) {
		this.messagea.setValue(messagea);
	}
	/**
	 * Gets the reference for attribute messagei.
	 * @return the messagei attribute reference
	 */
	public ElementaryRangeReference getMessageiReference() {
		return messagei.getReference();
	}

	/**
	 * Getter for messagei attribute.
	 * @return messagei attribute
	 */
	public String getMessagei() {
		return messagei.getValue();
	}

	/**
	 * Setter for messagei attribute.
	 * @param messagei the new value of messagei
	 */
	public void setMessagei(String messagei) {
		this.messagei.setValue(messagei);
	}
	/**
	 * Gets the reference for attribute dummyl.
	 * @return the dummyl attribute reference
	 */
	public ElementaryRangeReference getDummylReference() {
		return dummyl.getReference();
	}

	/**
	 * Getter for dummyl attribute.
	 * @return dummyl attribute
	 */
	public int getDummyl() {
		return dummyl.getValue();
	}

	/**
	 * Setter for dummyl attribute.
	 * @param dummyl the new value of dummyl
	 */
	public void setDummyl(int dummyl) {
		this.dummyl.setValue(dummyl);
	}
	/**
	 * Gets the reference for attribute dummyf.
	 * @return the dummyf attribute reference
	 */
	public ElementaryRangeReference getDummyfReference() {
		return dummyf.getReference();
	}

	/**
	 * Getter for dummyf attribute.
	 * @return dummyf attribute
	 */
	public String getDummyf() {
		return dummyf.getValue();
	}

	/**
	 * Setter for dummyf attribute.
	 * @param dummyf the new value of dummyf
	 */
	public void setDummyf(String dummyf) {
		this.dummyf.setValue(dummyf);
	}
	/**
	 * Gets the reference for attribute dummya.
	 * @return the dummya attribute reference
	 */
	public ElementaryRangeReference getDummyaReference() {
		return dummya.getReference();
	}

	/**
	 * Getter for dummya attribute.
	 * @return dummya attribute
	 */
	public String getDummya() {
		return dummya.getValue();
	}

	/**
	 * Setter for dummya attribute.
	 * @param dummya the new value of dummya
	 */
	public void setDummya(String dummya) {
		this.dummya.setValue(dummya);
	}
	/**
	 * Gets the reference for attribute dummyi.
	 * @return the dummyi attribute reference
	 */
	public ElementaryRangeReference getDummyiReference() {
		return dummyi.getReference();
	}

	/**
	 * Getter for dummyi attribute.
	 * @return dummyi attribute
	 */
	public String getDummyi() {
		return dummyi.getValue();
	}

	/**
	 * Setter for dummyi attribute.
	 * @param dummyi the new value of dummyi
	 */
	public void setDummyi(String dummyi) {
		this.dummyi.setValue(dummyi);
	}
	
	/**
	 * Gets the reference for attribute empmapo.
	 * @return the empmapo attribute reference
	 */
	public RangeReference getEmpmapoReference() {
		return empmapo.getReference();
	}	
				
	/**
	 * Setter for empmapo .
	 */
   	public void setEmpmapo(RangeReference reference) {
       	empmapo.getReference().setBytes(reference.getBytes());
   	}
 
	/**
	 * Gets the reference for attribute tranido.
	 * @return the tranido attribute reference
	 */
	public ElementaryRangeReference getTranidoReference() {
		return tranido.getReference();
	}

	/**
	 * Getter for tranido attribute.
	 * @return tranido attribute
	 */
	public String getTranido() {
		return tranido.getValue();
	}

	/**
	 * Setter for tranido attribute.
	 * @param tranido the new value of tranido
	 */
	public void setTranido(String tranido) {
		this.tranido.setValue(tranido);
	}
	/**
	 * Gets the reference for attribute dateo.
	 * @return the dateo attribute reference
	 */
	public ElementaryRangeReference getDateoReference() {
		return dateo.getReference();
	}

	/**
	 * Getter for dateo attribute.
	 * @return dateo attribute
	 */
	public String getDateo() {
		return dateo.getValue();
	}

	/**
	 * Setter for dateo attribute.
	 * @param dateo the new value of dateo
	 */
	public void setDateo(String dateo) {
		this.dateo.setValue(dateo);
	}
	/**
	 * Gets the reference for attribute timeo.
	 * @return the timeo attribute reference
	 */
	public ElementaryRangeReference getTimeoReference() {
		return timeo.getReference();
	}

	/**
	 * Getter for timeo attribute.
	 * @return timeo attribute
	 */
	public String getTimeo() {
		return timeo.getValue();
	}

	/**
	 * Setter for timeo attribute.
	 * @param timeo the new value of timeo
	 */
	public void setTimeo(String timeo) {
		this.timeo.setValue(timeo);
	}
	/**
	 * Gets the reference for attribute empnoo.
	 * @return the empnoo attribute reference
	 */
	public ElementaryRangeReference getEmpnooReference() {
		return empnoo.getReference();
	}

	/**
	 * Getter for empnoo attribute.
	 * @return empnoo attribute
	 */
	public String getEmpnoo() {
		return empnoo.getValue();
	}

	/**
	 * Setter for empnoo attribute.
	 * @param empnoo the new value of empnoo
	 */
	public void setEmpnoo(String empnoo) {
		this.empnoo.setValue(empnoo);
	}
	/**
	 * Gets the reference for attribute efnameo.
	 * @return the efnameo attribute reference
	 */
	public ElementaryRangeReference getEfnameoReference() {
		return efnameo.getReference();
	}

	/**
	 * Getter for efnameo attribute.
	 * @return efnameo attribute
	 */
	public String getEfnameo() {
		return efnameo.getValue();
	}

	/**
	 * Setter for efnameo attribute.
	 * @param efnameo the new value of efnameo
	 */
	public void setEfnameo(String efnameo) {
		this.efnameo.setValue(efnameo);
	}
	/**
	 * Gets the reference for attribute elnameo.
	 * @return the elnameo attribute reference
	 */
	public ElementaryRangeReference getElnameoReference() {
		return elnameo.getReference();
	}

	/**
	 * Getter for elnameo attribute.
	 * @return elnameo attribute
	 */
	public String getElnameo() {
		return elnameo.getValue();
	}

	/**
	 * Setter for elnameo attribute.
	 * @param elnameo the new value of elnameo
	 */
	public void setElnameo(String elnameo) {
		this.elnameo.setValue(elnameo);
	}
	/**
	 * Gets the reference for attribute eaddro.
	 * @return the eaddro attribute reference
	 */
	public ElementaryRangeReference getEaddroReference() {
		return eaddro.getReference();
	}

	/**
	 * Getter for eaddro attribute.
	 * @return eaddro attribute
	 */
	public String getEaddro() {
		return eaddro.getValue();
	}

	/**
	 * Setter for eaddro attribute.
	 * @param eaddro the new value of eaddro
	 */
	public void setEaddro(String eaddro) {
		this.eaddro.setValue(eaddro);
	}
	/**
	 * Gets the reference for attribute ephnoo.
	 * @return the ephnoo attribute reference
	 */
	public ElementaryRangeReference getEphnooReference() {
		return ephnoo.getReference();
	}

	/**
	 * Getter for ephnoo attribute.
	 * @return ephnoo attribute
	 */
	public String getEphnoo() {
		return ephnoo.getValue();
	}

	/**
	 * Setter for ephnoo attribute.
	 * @param ephnoo the new value of ephnoo
	 */
	public void setEphnoo(String ephnoo) {
		this.ephnoo.setValue(ephnoo);
	}
	/**
	 * Gets the reference for attribute messageo.
	 * @return the messageo attribute reference
	 */
	public ElementaryRangeReference getMessageoReference() {
		return messageo.getReference();
	}

	/**
	 * Getter for messageo attribute.
	 * @return messageo attribute
	 */
	public String getMessageo() {
		return messageo.getValue();
	}

	/**
	 * Setter for messageo attribute.
	 * @param messageo the new value of messageo
	 */
	public void setMessageo(String messageo) {
		this.messageo.setValue(messageo);
	}
	/**
	 * Gets the reference for attribute dummyo.
	 * @return the dummyo attribute reference
	 */
	public ElementaryRangeReference getDummyoReference() {
		return dummyo.getReference();
	}

	/**
	 * Getter for dummyo attribute.
	 * @return dummyo attribute
	 */
	public String getDummyo() {
		return dummyo.getValue();
	}

	/**
	 * Setter for dummyo attribute.
	 * @param dummyo the new value of dummyo
	 */
	public void setDummyo(String dummyo) {
		this.dummyo.setValue(dummyo);
	}
}
