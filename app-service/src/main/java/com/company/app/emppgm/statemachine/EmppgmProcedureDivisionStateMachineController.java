package com.company.app.emppgm.statemachine;

import com.company.app.emppgm.business.context.EmppgmContext;
import com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController.Events;
import com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController.States;
import com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineService;
import com.netfective.bluage.gapwalk.rt.call.ExecutionController;
import com.netfective.bluage.gapwalk.rt.context.RuntimeContext;
import com.netfective.bluage.gapwalk.rt.statemachine.SimpleStateMachineController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.StateConfigurer;
import org.springframework.stereotype.Component;

/**
 * Controller managing the state machine "EmppgmProcedureDivisionStateMachine" execution.
 */
@Component("com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineController")
@Import({
com.company.app.emppgm.statemachine.EmppgmProcedureDivisionStateMachineService.class
})
@Lazy
public class EmppgmProcedureDivisionStateMachineController extends SimpleStateMachineController<States, Events> {
	
	/**
	 * State machine states.
	 */
	public enum States {
		_000_MAIN_PARA_1, _000_MAIN_PARA, _0000_TERMINATE_PROGRAM, FINAL, LOCAL_FINAL
	}

	/**
	 * State machine events.
	 */
	public enum Events {
		TO__000_MAIN_PARA_1, TO__000_MAIN_PARA, TO__0000_TERMINATE_PROGRAM, TO_FINAL, TO_LOCAL_FINAL
	}

	/**
	 * State machine state process service provider.
	 */
	@Autowired
	@Lazy
	private EmppgmProcedureDivisionStateMachineService stateProcess;

	@Override
	protected void configureStateMachine(StateMachineStateConfigurer<States, Events> states, StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		throw new UnsupportedOperationException("Please use the four arguments configureStateMachine method instead: configureStateMachine(StateMachineStateConfigurer<States, Events> states, StateMachineTransitionConfigurer<States, Events> transitions, RuntimeContext ctx, ExecutionController ctrl)");
	}
	
	@Override
	protected void configureStateMachine(StateMachineStateConfigurer<States, Events> states, StateMachineTransitionConfigurer<States, Events> transitions, RuntimeContext ctx, ExecutionController ctrl) throws Exception {
		StateConfigurer<States, Events> configurer = states.withStates();
		configurer.initial(States._000_MAIN_PARA_1).end(States.FINAL);
		configurer.state(States._000_MAIN_PARA_1);	
		configurer.state(States.FINAL);
		
		StateConfigurer<States, Events> subConfigurer = states.withStates().parent(States._000_MAIN_PARA_1);
		subConfigurer.initial(States._000_MAIN_PARA).end(States.LOCAL_FINAL);
		EmppgmContext lctx = (EmppgmContext) ctx;
		subConfigurer.state(States._000_MAIN_PARA, buildAction(() -> {stateProcess._000MainPara(lctx, ctrl);}), null);
		subConfigurer.state(States._0000_TERMINATE_PROGRAM, buildAction(() -> {stateProcess._0000TerminateProgram(lctx, ctrl);}), null);
		
		configureTransitions(transitions);
	}
	
	/**
	 * Declare state machine transitions.
	 * @param transitions the transitions configuration helper
	 */
	private void configureTransitions(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions.withLocal().source(States._000_MAIN_PARA_1).target(States._0000_TERMINATE_PROGRAM).event(Events.TO__0000_TERMINATE_PROGRAM);
		transitions.withExternal().source(States._000_MAIN_PARA).target(States.FINAL).event(Events.TO_FINAL);
	}
}
