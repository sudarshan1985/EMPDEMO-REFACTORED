package com.company.app.emppgm.service;

import com.company.app.emppgm.business.context.EmppgmContext;
import com.netfective.bluage.gapwalk.rt.call.ExecutionController;

/**
 * Interface EmppgmProcess.
 * 
 * Defines application services for EmppgmProcess
 */
public interface EmppgmProcess {

	/**
	 * Process operation emppgm.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void emppgm(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _050Asktimepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _050Asktimepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _050Movepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _050Movepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _000Browsepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _000Browsepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _100Readpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _100Readpara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _110ReceivePara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _110ReceivePara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _120ValidateEmpno.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _120ValidateEmpno(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _200Writepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _200Writepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _150ValidateInput.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _150ValidateInput(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _170MoveMapRecPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _170MoveMapRecPara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _300Exitpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _300Exitpara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _400Updatepara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _400Updatepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _450ConfUpdate.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _450ConfUpdate(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _470SetMdt.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _470SetMdt(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _500SendPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _500SendPara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _600Deletepara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _600Deletepara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _600Clearpara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _600Clearpara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _800ReturnPara.
	 * 
	 * *************************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _800ReturnPara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _700Readprevpara.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _700Readprevpara(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _010StartEmpBrowse.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _010StartEmpBrowse(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _020ReadNextEmp.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _020ReadNextEmp(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _030EndEmpBrowse.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _030EndEmpBrowse(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _040ReadPrevEmp.
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _040ReadPrevEmp(final EmppgmContext ctx, final ExecutionController ctrl);

	/**
	 * Process operation _800Readnextpara.
	 * 
	 * ********************************************************
	 * 
	 * @param ctx 
	 * @param ctrl 
	 */
	void _800Readnextpara(final EmppgmContext ctx, final ExecutionController ctrl);

}
