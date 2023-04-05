import java.nio.charset.Charset
import com.netfective.bluage.gapwalk.datasimplifier.data.ByteArrayRecord
import com.netfective.bluage.gapwalk.datasimplifier.metadata.type.BinaryType
import com.netfective.bluage.gapwalk.datasimplifier.configuration.ConfigurationBuilder
import com.netfective.bluage.gapwalk.rt.call.MainProgramRunner
import com.netfective.bluage.gapwalk.rt.call.ProgramExecutionResult
import com.netfective.bluage.gapwalk.rt.io.support.FileConfiguration
import com.netfective.bluage.gapwalk.rt.call.exception.GroovyExecutionException
import com.netfective.bluage.gapwalk.rt.call.exception.GroovyExecutionResult
import com.netfective.bluage.gapwalk.rt.shared.AbendException
import com.netfective.bluage.gapwalk.rt.job.support.JobExecutionTracker

//********************************************************************************************************

//Get TimeZone
def getTimeZone(){
	return TimeZone.getTimeZone("Europe/Paris")
}

// Get encoding
def getEncoding() {
	return "CP1047";
}

//********************************************************************************************************

// Replace parameters for a line
def replaceParameters (Map params = [:], line) {
  def resolvedLine = line
  params.reverseEach {
    resolvedLine = resolvedLine.replaceAll("&"+it.key+"\\.",it.value)
    resolvedLine = resolvedLine.replaceAll("&"+it.key,it.value)
    resolvedLine = resolvedLine.replaceAll("@"+it.key+"\\.",it.value)
    resolvedLine = resolvedLine.replaceAll("@"+it.key,it.value)
  }
  return resolvedLine
}

//********************************************************************************************************

// Return timestamp
def now () {
  return new Date().format("yyyy-MM-dd_HH-mm-ss-SSS", getTimeZone())
}


//Get time difference in readable string
def getTimeDiff (Date dateOne, Date dateTwo) {
	long elapsedDays = 0, elapsedHours = 0, elapsedMinutes = 0, elapsedSeconds = 0;
	if(!dateOne.equals(dateTwo)){
		def timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
		def secondsInMilli = 1000;
		def minutesInMilli = secondsInMilli * 60;
		def hoursInMilli = minutesInMilli * 60;
		def daysInMilli = hoursInMilli * 24;
		elapsedDays = timeDiff / daysInMilli;
		timeDiff = timeDiff % daysInMilli;
		elapsedHours = timeDiff / hoursInMilli;
		timeDiff = timeDiff % hoursInMilli;
		elapsedMinutes = timeDiff / minutesInMilli;
		timeDiff = timeDiff % minutesInMilli;
		elapsedSeconds = timeDiff / secondsInMilli;
	}
	if(elapsedDays == 0 && elapsedHours == 0 && elapsedMinutes == 0 && elapsedSeconds == 0) {
		elapsedSeconds = 1l;
	}
	return String.format("%02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
}

//********************************************************************************************************

// Display start step
def displayStartStep (String  stepName){
  displayExec("STEP",stepName,"Started")
}

// Display end step
def displayEndStep (String stepName){
  displayExec("STEP",stepName,"Ended")
}

// Display start job
def displayStartJob (String jobName){
  displayExec("JOB",jobName,"Started")
}

// Display end job
def displayEndJob (String jobName){
    displayExec("JOB",jobName,"Ended")
}

// Display start proc
def displayStartProc (String procName){
  displayExec("PROC",procName,"Started")
}

// Display end proc
def displayEndProc (String procName){
  displayExec("PROC",procName,"Ended")
}

// Display 
def displayExec (String type, String name, String what){
  displayMessage("["+type+"] "+name+" - "+what)
}

// Display message
def displayMessage (String message){
  println now()+" | "+message
}

//********************************************************************************************************

// Check program result
def checkProgramResult (Object programResult){
  if(!programResult.isProgramFound()){
    displayMessage("Program not found => not executed !")
    //throw new RuntimeException ("Stop execution because a program is missing !")
    programResult = programResult.success(-1)
  }else{
    displayMessage("Program return code is "+programResult.getReturnCode())
  }
  return programResult
}

// Build resolved file
def buildResolvedFile (String jobName, String stepName, String procName){
  return new File(jobName+"_"+stepName+"_"+procName+"_"+now()+'.groovy')
}

// Execute a groovy file passing application context
def execGroovy (Object applicationContext, TreeMap mapTransfo, File file, Object jobContext = null, 
	Map<String,FileConfiguration> fcmap = null){
  displayMessage("[EXEC GROOVY FILE] "+file.canonicalPath+" - Started")
  Binding binding = new Binding()
  binding.setVariable("applicationContext", applicationContext)
  binding.setVariable("mapTransfo", mapTransfo)
  binding.setVariable("jobContext", jobContext)
  binding.setVariable("fcmap", fcmap)
  GroovyShell shell = new GroovyShell(binding)
  def programResults = shell.evaluate(file)
  displayMessage("[EXEC GROOVY FILE] "+file.canonicalPath+" - Ended")
  return programResults
}

// Display comment
def comment (String message){
  displayMessage(message)
}

// Execute a step : collect program result
def execStep (String stepName, Map programResults, Closure exec){
  JobExecutionTracker.setStepName(stepName)
  displayStartStep(stepName)
  programResults[stepName+"/start"] = new Date()
  def programResult = exec.call()
  programResults[stepName+"/end"] = new Date()
  programResult = checkProgramResult(programResult)
  programResults[stepName] = programResult
  displayEndStep(stepName)
  JobExecutionTracker.setStepName("")
  if (binding.hasVariable('jobContext') && jobContext!=null) {
    jobContext.addProgramResult(stepName, programResult)
  }
  return programResult
}

// Execute a step : collect program result
def execStepWithException (String stepName, Map programResults, Closure exec){
	try {
	  JobExecutionTracker.setStepName(stepName)
	  displayStartStep(stepName)
	  programResults[stepName+"/start"] = new Date()
	  def programResult = exec.call()
	  programResults[stepName+"/end"] = new Date()
	  programResult = checkProgramResult(programResult)
	  programResults[stepName] = programResult
	  displayEndStep(stepName)
	  JobExecutionTracker.setStepName("")
	  if (binding.hasVariable('jobContext') && jobContext!=null) {
		  jobContext.addProgramResult(stepName, programResult)
	  }
	  return programResult
	} catch (RuntimeException re) {
		if (programResults.get("GroovyExecutionResult") instanceof GroovyExecutionResult) {
			throw new GroovyExecutionException(programResults.get("GroovyExecutionResult"), re)
		} else {
			throw re
		}
	}
}

// Execute a step : collect program result and set GroovyExecutionResult
def execStep (String stepName, String programName, Map programResults, Closure exec) {
	try {
	  JobExecutionTracker.setStepName(stepName)
	  displayStartStep(stepName)
	  programResults[stepName+"/start"] = new Date()
	  def programResult = exec.call()
	  programResults[stepName+"/end"] = new Date()
	  programResult = checkProgramResult(programResult)
	  programResults[stepName] = programResult
	  displayEndStep(stepName)
	  JobExecutionTracker.setStepName("")
	  if (binding.hasVariable('jobContext') && jobContext!=null) {
	    jobContext.addProgramResult(stepName, programResult)
	  }
	  
	  def groovyExecutionResult = new GroovyExecutionResult(stepName, programName, programResult.getReturnCode())
	  programResults.put("GroovyExecutionResult", groovyExecutionResult);
	  
	  return programResult
	} catch (AbendException e) {
		throw new 	GroovyExecutionException(stepName, programName, -2, e)
	} catch (Throwable t) {
		throw new 	GroovyExecutionException(stepName, programName, -1, t)
	}
}

// Execute a simple step 
def execStepSimple (String stepName, Closure exec){
  JobExecutionTracker.setStepName(stepName)
  displayStartStep(stepName)
  def programResult = exec.call()
  displayEndStep(stepName)
  JobExecutionTracker.setStepName("")
  return programResult
}

// Execute a simple step : collect program result and set GroovyExecutionResult
def execStepSimple (String stepName, Map programResults, Closure exec ){
  JobExecutionTracker.setStepName(stepName)
  displayStartStep(stepName)
  def res = exec.call()
  displayEndStep(stepName)
  JobExecutionTracker.setStepName("")
  if (res instanceof Map) {
	  programResults["GroovyExecutionResult"] = res["GroovyExecutionResult"]
  } 
  programResults[stepName] = res
}

//Display steps from program result map
def displayStepTimes (Map programResults) {
	TreeMap startTimes = getTimes(programResults,"/start")
	TreeMap endTimes = getTimes(programResults,"/end")
	displayMessage "-----------------------------------------------------------------------------------------"
	displayMessage String.format("| %-20s | %10s | %10s | %10s | %10s | %10s |", "STEP EXECUTION TIMES", "START DATE", "START TIME", " END  DATE", " END  TIME", " EXEC TIME")
	displayMessage "-----------------------------------------------------------------------------------------"
	startTimes.each { stepName, start ->
		def end = endTimes.get(stepName)
		if(end != null){
			def startDate = start.format("yyyy-MM-dd", getTimeZone())
			def startTime = start.format("HH:mm:ss", getTimeZone())
			def endDate = end.format("yyyy-MM-dd", getTimeZone())
			def endTime = end.format("HH:mm:ss", getTimeZone())
			displayMessage String.format("| %-20s | %10s | %10s | %10s | %10s | %10s |", stepName, startDate, startTime, endDate, endTime, getTimeDiff(end,start))
		}
	}
	displayMessage "-----------------------------------------------------------------------------------------"
}

//Get times from program results map
def getTimes (Map programResults, String suffix) {
	TreeMap times = [:]
	programResults.each { step, time ->
		if(step.endsWith(suffix)){
			def stepName = step.substring(0,step.lastIndexOf(suffix))
			times.put(stepName,time)
		}
	}
	return times
}

//Return a sucess program result with return code
def success (Integer returnCode) {
	return ProgramExecutionResult.success(returnCode)
}

//********************************************************************************************************

// Get parm as ByteArrayRecord
def getParm (value, valueSize=0) {
	def finalValue = value
	if(valueSize > 0){
		finalValue = String.format('%-'+valueSize+'s', finalValue)
	}
	def charset = Charset.forName(getEncoding())
	def lengthType = new BinaryType(4, 0, 'STD', false, false, true)
	lengthType.setConfiguration(new ConfigurationBuilder().encoding(charset).build())
	def partLength = lengthType.encode(value.length())
	def partData = finalValue.getBytes(charset)
	def record = new ByteArrayRecord (partLength.length + partData.length)
	record.setBytes(partLength, 0, partLength.length)
	record.setBytes(partData, partLength.length, partData.length)
	return record
}

def checkValidProgramResults(Map map) {
	boolean retValue = true
	for (Object val : map.values()) {
		if(val instanceof ProgramExecutionResult){
			ProgramExecutionResult per = (ProgramExecutionResult)val
			if (per.getAbend()) {
				retValue = false
				break
			}
		}
	}
	return retValue;
}

def checkOnlyProgramResults(Map map) {
	boolean retValue = false
	for (Object val : map.values()) {
		if(val instanceof ProgramExecutionResult){
			ProgramExecutionResult per = (ProgramExecutionResult)val
			if (per.getAbend()) {
				retValue = true
				break
			}
		}
	}
	return retValue;
}

def checkAllPreviousProgramResults(Map map, String op, int value) {
	for (Object val : map.values()) {
		if(val instanceof ProgramExecutionResult){
			ProgramExecutionResult per = (ProgramExecutionResult)val
			switch(op) {
			case "GT":
				if(per.getReturnCode() > value) {
					return false;
				}
				break;
			case "GE":
				if(per.getReturnCode() >= value) {
					return false;
				}
				break;
			case "EQ":
				if(per.getReturnCode() == value) {
					return false;
				}
				break;
			case "LT":
				if(per.getReturnCode() < value) {
					return false;
				}
				break;
			case "LE":
				if(per.getReturnCode() <= value) {
					return false;
				}
				break;
			case "NE":
				if(per.getReturnCode() != value) {
					return false;
				}
				break;
			}
		}
	}
	return true;
}

def getExecutionController(binding, applicationContext, disableNewTransaction) {
	def mpr = null
	if(binding != null && binding.hasVariable("executionController")) {
		mpr = executionController
	} else {
		mpr = applicationContext.getBean("com.netfective.bluage.gapwalk.rt.call.ExecutionController", MainProgramRunner.class)
		mpr = mpr.withContextModifier({ c ->
			if (binding != null && binding.hasVariable("terminal")) {
				mpr.getExecutionContext().setTerminalManager(terminal)
			} else {
				mpr.getExecutionContext().setTerminalManager(null)
			}
		})
	}

	if(disableNewTransaction){
		return mpr.withDisableNewTransactionInRunUnit()
	} 
	return mpr
}

