
package com.company.app.emppgm.service.impl;

import com.company.app.emppgm.business.context.EmppgmContext;
import com.company.app.emppgm.service.EmppgmProcess;
import com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController;
import com.netfective.bluage.gapwalk.datasimplifier.data.Record;
import com.netfective.bluage.gapwalk.datasimplifier.utils.DataUtils;
import com.netfective.bluage.gapwalk.datasimplifier.utils.NumberUtils;
import com.netfective.bluage.gapwalk.rt.call.ExecutionController;
import com.netfective.bluage.gapwalk.rt.jics.ResponseCode;
import com.netfective.bluage.gapwalk.rt.jics.bms.ReceiveMapBuilder;
import com.netfective.bluage.gapwalk.rt.jics.bms.SendControlBuilder;
import com.netfective.bluage.gapwalk.rt.jics.bms.SendMapBuilder;
import com.netfective.bluage.gapwalk.rt.jics.bms.SendTextBuilder;
import com.netfective.bluage.gapwalk.rt.jics.control.ReturnBuilder;
import com.netfective.bluage.gapwalk.rt.jics.internal.JicsTimeBuilder;
import com.netfective.bluage.gapwalk.rt.jics.io.JicsIsolationLevel;
import com.netfective.bluage.gapwalk.rt.jics.io.internal.JicsFileBuilder;
import com.netfective.bluage.gapwalk.runtime.statements.InspectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

/**
 * Class EmppgmProcessImpl
 * 
 * Defines application services for EmppgmProcess
 * @see EmppgmProcess
 */
@Service("com.company.app.emppgm.service.EmppgmProcess")
@Import({
com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController.class 
}) 	
public class EmppgmProcessImpl implements EmppgmProcess {

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmppgmProcessImpl.class);

	@Autowired
	private EmppgmProcedureDivisionStateMachineController emppgmProcedureDivisionStateMachineRunner;


	/**
	 * Process operation emppgm.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void emppgm(final EmppgmContext ctx, final ExecutionController ctrl) {
		emppgmProcedureDivisionStateMachineRunner.run(ctx,ctrl);
	}

	/**
	 * Process operation _050Asktimepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _050Asktimepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		JicsTimeBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.askTime()
		.absoluteTime(ctx.getCurrentTime().getTstampReference())
		.execute()
		.handleException();
		JicsTimeBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.formatTime()
		.time(ctx.getCurrentTime().getOuttimeReference())
		.absoluteTime(ctx.getCurrentTime().getTstampReference())
		.formattedDate(ctx.getCurrentTime().getOutdateReference(),"YYYYMMDD")
		.datesep("/")
		.timesep(":")
		.execute()
		.handleException();
	}

	/**
	 * Process operation _050Movepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _050Movepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().setTranido(ctx.getDfheiblk().getEibtrnid());
		ctx.getGroup1().setDateo(ctx.getCurrentTime().getOutdate());
		ctx.getGroup1().setTimeo(ctx.getCurrentTime().getOuttime());
	}

	/**
	 * Process operation _000Browsepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _000Browsepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		_110ReceivePara(ctx, ctrl);
		_120ValidateEmpno(ctx, ctrl);
		if (ctx.getSwitches().isValidData()) {
			
			/* 
			Do nothing */
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		_010StartEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			_020ReadNextEmp(ctx, ctrl);
		} 
		_030EndEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			ctx.getFlags().setSendDataonly(true);
			ctx.getCommareaArea().setCaEmpno(ctx.getFsEmprec().getEmpnoReference().getValue(String.class));
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _100Readpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _100Readpara(final EmppgmContext ctx, final ExecutionController ctrl) {
		DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
		_110ReceivePara(ctx, ctrl);
		ctx.getSwitches().setValidDataSw("Y");
		_120ValidateEmpno(ctx, ctrl);
		if (ctx.getSwitches().isValidData()) {
			
			/* 
			Do nothing */
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.read()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
			ctx.getFlags().setSendDataonly(true);
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getGroup1().setMessageo("Record Not found");
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _110ReceivePara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _110ReceivePara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().getEmpmapiReference().setBytes(Record.LOW_VALUES);
		ctx.getFsEmprec().setBytes(Record.LOW_VALUES);
		ctx.getCommareaArea().getCaEmpnoReference().setBytes(Record.LOW_VALUES);
		ReceiveMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withMap("EMPMAP")
		.withMapset("EMPMSET")
		.into(ctx.getGroup1().getEmpmapiReference())
		.execute()
		.handleException();
		InspectBuilder.newInstance(ctx.getGroup1().getEmpnoiReference())
			.replace()
			.all("_")
			.by(" ")
			.apply();
	}

	/**
	 * Process operation _120ValidateEmpno.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _120ValidateEmpno(final EmppgmContext ctx, final ExecutionController ctrl) {
		if (ctx.getGroup1().getEmpnol() == 0 || DataUtils.isBlank(ctx.getGroup1().getEmpnoiReference())) {
			ctx.getSwitches().setValidDataSw("N");
			ctx.getGroup1().getEmpmapoReference().setBytes(Record.LOW_VALUES);
			ctx.getGroup1().setMessageo("You must enter a EMP number.");
		}
	}

	/**
	 * Process operation _200Writepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _200Writepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
		_110ReceivePara(ctx, ctrl);
		ctx.getSwitches().setValidDataSw("Y");
		_120ValidateEmpno(ctx, ctrl);
		if (ctx.getSwitches().isValidData()) {
			
			/* 
			Do nothing */
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.read()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getGroup1().setMessageo("Record Already Exists");
			ctx.getFlags().setSendDataonly(true);
			ctx.getCommareaArea().setCaEmpno(ctx.getFsEmprec().getEmpnoReference().getValue(String.class));
			_500SendPara(ctx, ctrl);
		} else {
			if (ctx.getRespcode().getRespcode() == ResponseCode.NOTFND.getIntValue()) {
				_150ValidateInput(ctx, ctrl);
				if (ctx.getSwitches().isValidData()) {
					ctx.getFsEmprec().getField().initialize();
					_170MoveMapRecPara(ctx, ctrl);
					JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
					.write(false)
					.from(ctx.getFsEmprec())
					.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
					.execute();
					ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
					if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
						ctx.getGroup1().setMessageo("Record Write Sucessful.");
					} else {
						ctx.getGroup1().setMessageo("Record Write Unsucessful.");
					}
				} 
			} 
		}
		ctx.getFlags().setSendError(true);
		ctx.getGroup1().setEmpnol(-1);
		_500SendPara(ctx, ctrl);
	}

	/**
	 * Process operation _150ValidateInput.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _150ValidateInput(final EmppgmContext ctx, final ExecutionController ctrl) {
		InspectBuilder.newInstance(ctx.getGroup1().getEmpmapiReference())
			.replace()
			.all("_")
			.by(" ")
			.apply();
		if (ctx.getGroup1().getEfnamel() == 0 || DataUtils.isBlank(ctx.getGroup1().getEfnameiReference())) {
			ctx.getGroup1().setMessageo("Please enter a valid first name.");
			ctx.getGroup1().setEfnamel(-1);
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		} 
		if (ctx.getGroup1().getElnamel() == 0 || DataUtils.isBlank(ctx.getGroup1().getElnameiReference())) {
			ctx.getSwitches().setValidDataSw("N");
			ctx.getGroup1().setMessageo("Please enter a valid last name.");
			ctx.getGroup1().setElnamel(-1);
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		} 
		if (ctx.getGroup1().getEaddrl() == 0 || DataUtils.isBlank(ctx.getGroup1().getEaddriReference())) {
			ctx.getSwitches().setValidDataSw("N");
			ctx.getGroup1().setMessageo("You must enter a Valid Emp Address.   ");
			ctx.getGroup1().setEaddrl(-1);
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		} 
		if (ctx.getGroup1().getEphnol() == 0 || DataUtils.isBlank(ctx.getGroup1().getEphnoiReference())) {
			ctx.getSwitches().setValidDataSw("N");
			ctx.getGroup1().setMessageo("You must enter a Valid Phone Number.  ");
			ctx.getGroup1().setEphnol(-1);
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _170MoveMapRecPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _170MoveMapRecPara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		ctx.getFsEmprec().setEfname(ctx.getGroup1().getEfnamei());
		ctx.getFsEmprec().setElname(ctx.getGroup1().getElnamei());
		ctx.getFsEmprec().setEaddr(ctx.getGroup1().getEaddri());
		ctx.getFsEmprec().setEphno(ctx.getGroup1().getEphnoi());
	}

	/**
	 * Process operation _300Exitpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _300Exitpara(final EmppgmContext ctx, final ExecutionController ctrl) {
		SendTextBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withData(ctx.getExitMsg().getExitMsgReference())
		.withErase()
		.execute()
		.handleException();
		SendControlBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withFreeKB()
		.execute()
		.handleException();
		ReturnBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.execute()
		.handleException();
	}

	/**
	 * Process operation _400Updatepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _400Updatepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
		_110ReceivePara(ctx, ctrl);
		ctx.getSwitches().setValidDataSw("Y");
		_120ValidateEmpno(ctx, ctrl);
		if (ctx.getSwitches().isValidData()) {
			
			/* 
			Do nothing */
		} else {
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		}
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.read()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getGroup1().setMessageo("Make changes then press 'F10' to Update");
			ctx.getFlags().setSendDataonly(true);
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getGroup1().setMessageo("Record dose Not Exists");
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _450ConfUpdate.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _450ConfUpdate(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().getEmpmapiReference().setBytes(Record.LOW_VALUES);
		_470SetMdt(ctx, ctrl);
		ReceiveMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withMap("EMPMAP")
		.withMapset("EMPMSET")
		.into(ctx.getGroup1().getEmpmapiReference())
		.execute()
		.handleException();
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.read()
		.setIsolationLevel(JicsIsolationLevel.UPDATE)
		.updateNoToken()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		_170MoveMapRecPara(ctx, ctrl);
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.rewrite()
		.from(ctx.getFsEmprec())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getGroup1().getEmpmapoReference().setBytes(Record.LOW_VALUES);
			ctx.getGroup1().setMessageo("Record Sucessfully Updated");
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getGroup1().setMessageo("Error in rewrite ");
			ctx.getFlags().setSendError(true);
			_500SendPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _470SetMdt.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _470SetMdt(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().setEfnamea(ctx.getDfhbmsca().getDfhbmfse());
		ctx.getGroup1().setElnamea(ctx.getDfhbmsca().getDfhbmfse());
		ctx.getGroup1().setEaddra(ctx.getDfhbmsca().getDfhbmfse());
		ctx.getGroup1().setEphnoa(ctx.getDfhbmsca().getDfhbmfse());
	}

	/**
	 * Process operation _500SendPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _500SendPara(final EmppgmContext ctx, final ExecutionController ctrl) {
		if (ctx.getFlags().isSendErase()) {
			_050Movepara(ctx, ctrl);
			ctx.getGroup1().setEmpnol(-1);
			SendMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
			.withMap("EMPMAP")
			.withMapset("EMPMSET")
			.withErase()
			.withFreeKB()
			.withData(ctx.getGroup1().getEmpmapoReference())
			.execute()
			.handleException();
			_800ReturnPara(ctx, ctrl);
		} else if (ctx.getFlags().isSendDataonly()) {
			ctx.getGroup1().setEmpnol(-1);
			ctx.getGroup1().setEmpnoo(ctx.getFsEmprec().getEmpnoReference().getValue(String.class));
			ctx.getGroup1().setEfnameo(ctx.getFsEmprec().getEfname());
			ctx.getGroup1().setElnameo(ctx.getFsEmprec().getElname());
			ctx.getGroup1().setEaddro(ctx.getFsEmprec().getEaddr());
			ctx.getGroup1().setEphnoo(ctx.getFsEmprec().getEphno());
			_050Movepara(ctx, ctrl);
			SendMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
			.withMap("EMPMAP")
			.withMapset("EMPMSET")
			.withFreeKB()
			.withDataOnly()
			.withData(ctx.getGroup1().getEmpmapoReference())
			.execute()
			.handleException();
			_800ReturnPara(ctx, ctrl);
		} else if (ctx.getFlags().isSendError()) {
			_050Movepara(ctx, ctrl);
			SendMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
			.withMap("EMPMAP")
			.withMapset("EMPMSET")
			.withErase()
			.withCursor()
			.withFreeKB()
			.withData(ctx.getGroup1().getEmpmapoReference())
			.execute()
			.handleException();
			_800ReturnPara(ctx, ctrl);
		} else {
			_800ReturnPara(ctx, ctrl);
		}
	}

	/**
	 * Process operation _600Deletepara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _600Deletepara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ReceiveMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withMap("EMPMAP")
		.withMapset("EMPMSET")
		.into(ctx.getGroup1().getEmpmapiReference())
		.execute()
		.handleException();
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.read()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
			.delete()
			.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
			.execute()
			.handleException();
			ctx.getGroup1().setMessageo("Record Deleted Sucessfully");
		} else {
			ctx.getGroup1().setMessageo("Record dose not Exists");
		}
		ctx.getFlags().setSendError(true);
		ctx.getGroup1().setEmpnol(-1);
		_500SendPara(ctx, ctrl);
	}

	/**
	 * Process operation _600Clearpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _600Clearpara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().getEmpmapoReference().setBytes(Record.LOW_VALUES);
		ctx.getGroup1().getMessageoReference().setBytes(Record.LOW_VALUES);
		_050Movepara(ctx, ctrl);
		SendMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withMap("EMPMAP")
		.withMapset("EMPMSET")
		.withFreeKB()
		.withData(ctx.getGroup1().getEmpmapoReference())
		.execute()
		.handleException();
	}

	/**
	 * Process operation _800ReturnPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _800ReturnPara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ReturnBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withTransID("EMP1")
		.withCommarea(ctx.getCommareaArea())
		.execute()
		.handleException();
	}

	/**
	 * Process operation _700Readprevpara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _700Readprevpara(final EmppgmContext ctx, final ExecutionController ctrl) {
		ctx.getGroup1().getEmpmapiReference().setBytes(Record.LOW_VALUES);
		ReceiveMapBuilder.newInstance(ctx.getDfheiblk(), ctx)
		.withMap("EMPMAP")
		.withMapset("EMPMSET")
		.into(ctx.getGroup1().getEmpmapiReference())
		.execute()
		.handleException();
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		_010StartEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			_020ReadNextEmp(ctx, ctrl);
			_040ReadPrevEmp(ctx, ctrl);
			_040ReadPrevEmp(ctx, ctrl);
		} 
		_030EndEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			ctx.getFlags().setSendDataonly(true);
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
		ctx.getCommareaArea().setCaEmpno(ctx.getFsEmprec().getEmpnoReference().getValue(String.class));
	}

	/**
	 * Process operation _010StartEmpBrowse.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _010StartEmpBrowse(final EmppgmContext ctx, final ExecutionController ctrl) {
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.startBrowse()
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getSwitches().setEmpFoundSw("Y");
			DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
		} else {
			if (ctx.getRespcode().getRespcode() == ResponseCode.NOTFND.getIntValue()) {
				ctx.getSwitches().setEmpFoundSw("N");
				ctx.getGroup1().setMessageo("There are no EMPs in the file.");
				ctx.getGroup1().getEmpmapoReference().setBytes(Record.LOW_VALUES);
				ctx.getFlags().setSendError(true);
				ctx.getGroup1().setEmpnol(-1);
				_500SendPara(ctx, ctrl);
			} 
		}
	}

	/**
	 * Process operation _020ReadNextEmp.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _020ReadNextEmp(final EmppgmContext ctx, final ExecutionController ctrl) {
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.readNext()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getSwitches().setEmpFoundSw("Y");
		} else {
			if (ctx.getRespcode().getRespcode() == ResponseCode.ENDFILE.getIntValue()) {
				ctx.getSwitches().setEmpFoundSw("N");
				ctx.getGroup1().setMessageo("There are no more records in the file.");
				ctx.getFlags().setSendError(true);
				ctx.getGroup1().setEmpnol(-1);
				_500SendPara(ctx, ctrl);
			} 
		}
	}

	/**
	 * Process operation _030EndEmpBrowse.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _030EndEmpBrowse(final EmppgmContext ctx, final ExecutionController ctrl) {
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.endBrowse()
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
	}

	/**
	 * Process operation _040ReadPrevEmp.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _040ReadPrevEmp(final EmppgmContext ctx, final ExecutionController ctrl) {
		JicsFileBuilder.newInstance("EMPFIL1", ctx.getDfheiblk(), ctx, ctrl.getExecutionContext())
		.readPrev()
		.into(ctx.getFsEmprec())
		.recordIndentificationField(ctx.getFsEmprec().getEmpnoReference())
		.execute();
		ctx.getRespcode().setRespcode(ctx.getDfheiblk().getEibresp());
		if (ctx.getRespcode().getRespcode() == ResponseCode.NORMAL.getIntValue()) {
			ctx.getSwitches().setEmpFoundSw("Y");
		} else {
			if (ctx.getRespcode().getRespcode() == ResponseCode.ENDFILE.getIntValue()) {
				ctx.getSwitches().setEmpFoundSw("N");
				ctx.getGroup1().setMessageo("There are no more records in the file.");
			} 
		}
	}

	/**
	 * Process operation _800Readnextpara.
	 * 
	 * ********************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	@Override
	public void _800Readnextpara(final EmppgmContext ctx, final ExecutionController ctrl) {
		_110ReceivePara(ctx, ctrl);
		_120ValidateEmpno(ctx, ctrl);
		if (ctx.getSwitches().isValidData()) {
			
			/* 
			Do nothing */
		} else {
			ctx.getFlags().setSendError(true);
			ctx.getGroup1().setEmpnol(-1);
			_500SendPara(ctx, ctrl);
		}
		ctx.getFsEmprec().setEmpno(NumberUtils.convert(ctx.getGroup1().getEmpnoi()).intValue());
		_010StartEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			_020ReadNextEmp(ctx, ctrl);
			_020ReadNextEmp(ctx, ctrl);
		} 
		_030EndEmpBrowse(ctx, ctrl);
		if (ctx.getSwitches().isEmpFound()) {
			ctx.getFlags().setSendDataonly(true);
			ctx.getCommareaArea().setCaEmpno(ctx.getFsEmprec().getEmpnoReference().getValue(String.class));
			_500SendPara(ctx, ctrl);
		} else {
			ctx.getFlags().setSendDataonly(true);
			_500SendPara(ctx, ctrl);
		}
	}

}
