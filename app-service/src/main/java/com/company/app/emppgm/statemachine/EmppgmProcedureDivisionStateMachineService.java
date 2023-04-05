
package com.company.app.emppgm.statemachine;

import com.company.app.emppgm.business.context.EmppgmContext;
import com.company.app.emppgm.service.EmppgmProcess;
import com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController.Events;
import com.netfective.bluage.gapwalk.bluesam.BluesamManager;
import com.netfective.bluage.gapwalk.datasimplifier.data.Record;
import com.netfective.bluage.gapwalk.datasimplifier.utils.DataUtils;
import com.netfective.bluage.gapwalk.io.shared.bluesam.BluesamManagerProvider;
import com.netfective.bluage.gapwalk.rt.call.ExecutionController;
import com.netfective.bluage.gapwalk.rt.jics.ResponseCode;
import com.netfective.bluage.gapwalk.rt.jics.control.HandleConditionBuilder;
import com.netfective.bluage.gapwalk.rt.statemachine.StateMachineController;
import com.netfective.bluage.gapwalk.rt.utils.ArgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Service class containing states process used to drive state machine "EmppgmProcedureDivisionStateMachine".
 */
@Service("com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineService")
@Lazy
public class EmppgmProcedureDivisionStateMachineService {
	
	/**
	 * The logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmppgmProcedureDivisionStateMachineService.class);

	/**
	 * The associated state machine controller.
	 */
	@Autowired
	@Qualifier("com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController")	
	private StateMachineController<Events> instanceStateMachineController;
	

	/**
	 * Bluesam Manager.
	 */
	private BluesamManager bluesamManager = BluesamManagerProvider.getBluesamManager();
	
	/**
	 * Service used by the state machine.
	 */
	@Autowired
	@Qualifier("com.company.app.emppgm.service.EmppgmProcess")
	private EmppgmProcess instanceEmppgmProcess;
	
	
	

	/**
	 * State process operation _000MainPara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _000MainPara(EmppgmContext ctx, ExecutionController ctrl) {
		ctx.getDfheiblk().bind(ArgUtils.get(ctx, 0));
		ctx.getDfhcommarea().bind(ArgUtils.get(ctx, 1));
		instanceStateMachineController.registerSignalHandler(Events.TO__0000_TERMINATE_PROGRAM, "!CONDITION_NOTFND");
		instanceStateMachineController.registerSignalHandler(Events.TO__0000_TERMINATE_PROGRAM, "!CONDITION_ERROR");
		HandleConditionBuilder.newInstance(ctx.getDfheiblk(), ctx).enable(ResponseCode.NOTFND).enable(ResponseCode.ERROR).execute().handleException();
		instanceEmppgmProcess._050Asktimepara(ctx, ctrl);
		ctx.getFsEmprec().getField().initialize();
		if (ctx.getDfheiblk().getEibcalen() == 0) {
			ctx.getCommareaArea().setBytes(Record.LOW_VALUES);
			ctx.getGroup1().getEmpmapiReference().setBytes(Record.LOW_VALUES);
			ctx.getFlags().setSendErase(true);
			instanceEmppgmProcess._500SendPara(ctx, ctrl);
		} else {
			if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf1Reference()) == 0) {
				instanceEmppgmProcess._100Readpara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf2Reference()) == 0) {
				instanceEmppgmProcess._200Writepara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf3Reference()) == 0) {
				instanceEmppgmProcess._300Exitpara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf4Reference()) == 0) {
				instanceEmppgmProcess._400Updatepara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf5Reference()) == 0) {
				instanceEmppgmProcess._600Deletepara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf6Reference()) == 0) {
				instanceEmppgmProcess._600Clearpara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf7Reference()) == 0) {
				instanceEmppgmProcess._700Readprevpara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf8Reference()) == 0) {
				instanceEmppgmProcess._800Readnextpara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhenterReference()) == 0) {
				instanceEmppgmProcess._000Browsepara(ctx, ctrl);
			} else if (DataUtils.compare(ctx.getDfheiblk().getEibaidReference(), ctx.getDfhaid().getDfhpf10Reference()) == 0) {
				instanceEmppgmProcess._450ConfUpdate(ctx, ctrl);
			} else {
				DataUtils.setToBlank(ctx.getGroup1().getMessageoReference());
				ctx.getGroup1().setMessageo("Invalid Key Pressed");
				ctx.getFlags().setSendError(true);
				ctx.getGroup1().setEmpnol(-1);
				instanceEmppgmProcess._500SendPara(ctx, ctrl);
			}
		}
		instanceEmppgmProcess._800ReturnPara(ctx, ctrl);
		if (ctx.isMainProgram()) {
			ctrl.stopRunUnit();
		} 
		instanceStateMachineController.sendEvent(Events.TO_FINAL);

	}
	/**
	 * State process operation _0000TerminateProgram.
	 * 
	 *     MOVE EIBRESP  TO ERR-RESP.
	 *     MOVE EIBRESP2 TO ERR-RESP2.
	 *     MOVE EIBTRNID TO ERR-TRNID.
	 *     MOVE EIBRSRCE TO ERR-RSRCE.
	 *     EXEC CICS
	 *         XCTL PROGRAM('SYSERR')
	 *              COMMAREA(ERROR-PARAMETERS)
	 *     END-EXEC.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _0000TerminateProgram(EmppgmContext ctx, ExecutionController ctrl) {
		ctx.getGroup1().getEmpmapoReference().setBytes(Record.LOW_VALUES);
		ctx.getGroup1().setMessageo("No Record To Browse ");
		ctx.getFlags().setSendError(true);
		ctx.getGroup1().setEmpnol(-1);
		instanceEmppgmProcess._500SendPara(ctx, ctrl);

	}
}
