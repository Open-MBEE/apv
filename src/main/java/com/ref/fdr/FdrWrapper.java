package com.ref.fdr;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.ref.log.Logador;
import com.ref.parser.activityDiagram.ADParser;
//import com.ref.refinement.activityDiagram.CounterExamples.CounterExType;
import com.ref.ui.CheckingProgressBar;

public class FdrWrapper {

	public static List<String> traceCounterExample;
	
	private static FdrWrapper instance;

	private Map<Integer, List<String>> resultado;

	private URLClassLoader urlCl;

	private Class fdrClass;

	private Class<?> sessionClass;

	private Class<?> assertionClass;

	private Class<?> counterexampleClass;

	private Class<?> traceCounterexampleClass;

	private Class<?> debugContextClass;

	private Class<?> refinementCounterexampleClass;

	private Class<?> behaviourClass;

	private Class<?> TraceBehaviour;

	private Class<?> Node;

	private Class<?> ProcessName;

	private Class<?> Canceller;

	private Class<?> Machine;

	private Class<?> TransitionList;

	private Class<?> Transition;

	private File fdrJar;

	private List<String> classes;

	private Object session;

	private Class<?> deadlockCounterexampleClass;

    private Class<?> determinismCounterexampleClass;

	public boolean loadFDR(String path) {

		File file = new File(path);

		if (file.exists()) {

			fdrJar = file;

			return true;

		} else

			return false;

	}

	private FdrWrapper() {

	}

	public static FdrWrapper getInstance() {
		if (instance == null) {
			instance = new FdrWrapper();
		}

		return instance;
	}

	public void loadClasses() throws MalformedURLException, ClassNotFoundException {

		classes = new ArrayList<String>();

		urlCl = new URLClassLoader(new URL[] { fdrJar.toURI().toURL() }, System.class.getClassLoader());

		fdrClass = urlCl.loadClass("uk.ac.ox.cs.fdr.fdr");

		sessionClass = urlCl.loadClass("uk.ac.ox.cs.fdr.Session");

		assertionClass = urlCl.loadClass("uk.ac.ox.cs.fdr.Assertion");

		counterexampleClass = urlCl.loadClass("uk.ac.ox.cs.fdr.Counterexample");

		traceCounterexampleClass = urlCl.loadClass("uk.ac.ox.cs.fdr.TraceCounterexample");

		debugContextClass = urlCl.loadClass("uk.ac.ox.cs.fdr.DebugContext");

		refinementCounterexampleClass = urlCl.loadClass("uk.ac.ox.cs.fdr.RefinementCounterexample");

		behaviourClass = urlCl.loadClass("uk.ac.ox.cs.fdr.Behaviour");

		urlCl.loadClass("uk.ac.ox.cs.fdr.IrrelevantBehaviour");

		urlCl.loadClass("uk.ac.ox.cs.fdr.CompiledEventList");

		TraceBehaviour = urlCl.loadClass("uk.ac.ox.cs.fdr.TraceBehaviour");

		Node = urlCl.loadClass("uk.ac.ox.cs.fdr.Node");

		ProcessName = urlCl.loadClass("uk.ac.ox.cs.fdr.ProcessName");

		Machine = urlCl.loadClass("uk.ac.ox.cs.fdr.Machine");

		TransitionList = urlCl.loadClass("uk.ac.ox.cs.fdr.TransitionList");

		Transition = urlCl.loadClass("uk.ac.ox.cs.fdr.Transition");

		classes.add(fdrClass.getName());

		classes.add(sessionClass.getName());

		classes.add(assertionClass.getName());

		classes.add(counterexampleClass.getName());

		classes.add(traceCounterexampleClass.getName());

		classes.add(debugContextClass.getName());

		classes.add(refinementCounterexampleClass.getName());

		classes.add(behaviourClass.getName());

		classes.add(TraceBehaviour.getName());

		classes.add(Node.getName());

		classes.add(ProcessName.getName());

		classes.add(TransitionList.getName());

		classes.add(Transition.getName());

		// Classes extras que são usadas como parametro

		Canceller = urlCl.loadClass("uk.ac.ox.cs.fdr.Canceller");

		classes.add(Canceller.getName());

		deadlockCounterexampleClass = urlCl.loadClass("uk.ac.ox.cs.fdr.DeadlockCounterexample");

        determinismCounterexampleClass = urlCl.loadClass("uk.ac.ox.cs.fdr.DeterminismCounterexample");

		urlCl.loadClass("uk.ac.ox.cs.fdr.ProgressReporter");
	}

	public List<String> getClasses() {

		return classes;

	}

	public Map<Integer, List<String>> getCounterExamples() {
		return this.resultado;
	}

	public boolean verify(String filename, String refType) throws Exception{

		this.resultado = new HashMap<Integer, List<String>>();
		List<String> resultParcial = null;
		int iteration = 0;
		boolean hasCounterExample = false;

			Object session;
			try {
				session = sessionClass.newInstance();
				invokeProperty(session.getClass(), session, "loadFile", String.class, filename);
				
				for (Object assertion : (Iterable<?>) invokeProperty(session.getClass(), session, "assertions", null,
						null)) {
					
					invokeProperty(assertion.getClass(), assertion, "execute", Canceller, null);
					
					for (Object counterExample : (Iterable<?>) invokeProperty(assertion.getClass(), assertion,
							"counterexamples", null, null)) {
						
						if (refType.equals("STRICT") || (refType.equals("WEAK") && iteration == 1)) {
							resultParcial = describeCounterExample(session, counterExample);
							this.resultado.put(iteration, resultParcial);
						}
						
						hasCounterExample = true;
					}
					iteration++;
				}
			} catch (InstantiationException e) {
				throw new Exception("Set your fdr path 1");
			} catch (IllegalAccessException e) {
				throw new Exception("Set your fdr path 2");
			} catch (Exception e) {
				Logador logger = Logador.getInstance();
				logger.log("LOG FDRWRAPPER");
				for(StackTraceElement element :e.getStackTrace()){
					logger.log(element.toString());						
				}
				throw new Exception(e.getMessage());
			}

   
		return hasCounterExample;
	}

	public List<String> describeCounterExample(Object session, Object counterExample) throws Exception {

		StringBuilder sb = new StringBuilder();

		List<String> result = new ArrayList<String>();

		// Adiciona o evento que gerou erro
		Object error = invokeProperty(traceCounterexampleClass, counterExample, "errorEvent", null, null);
		String errorEvent = "";
		if ((Long) error == 1 || (Long) error == 0) {

		} else {
			errorEvent = invokeProperty(sessionClass, session, "uncompileEvent", long.class, error).toString();
			result.add(errorEvent);
		}

		// Adiciona o trace do contraExemplo
		if (errorEvent.equals("endInteraction")) {
			if (counterExample.getClass().getName().equals(traceCounterexampleClass.getName())) {

				Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");

				IMPL_LOOKUP.setAccessible(true);

				MethodHandles.Lookup lkp = (MethodHandles.Lookup) IMPL_LOOKUP.get(null);

				MethodHandle h1 = lkp.findSpecial(refinementCounterexampleClass, "specificationBehaviour",
						MethodType.methodType(behaviourClass), traceCounterexampleClass);

				Object behaviour = null;

				try {
					behaviour = h1.invoke(counterExample);
					traceBehaviour(behaviour, sb, session);
					result.add(sb.toString());
					System.out.println(sb.toString());
					sb = new StringBuilder();
					h1 = lkp.findSpecial(refinementCounterexampleClass, "implementationBehaviour",
							MethodType.methodType(behaviourClass), traceCounterexampleClass);
					behaviour = h1.invoke(counterExample);
					traceBehaviour(behaviour, sb, session);
					result.add(sb.toString());
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		} else {

			Constructor[] constructors = debugContextClass.getConstructors();
			Constructor constructor = null;
			for (int i = 0; i < constructors.length; i++) {
				Class[] parameters = constructors[i].getParameterTypes();
				if (parameters[0].getName().equals(refinementCounterexampleClass.getName())) {
					constructor = constructors[i];
				}
			}

			Object debugContext = constructor.newInstance(counterExample, true);
			invokeProperty(debugContextClass, debugContext, "initialise", Canceller, null);
			for (Object behaviour : (Iterable<?>) invokeProperty(debugContextClass, debugContext, "rootBehaviours",
					null, null)) {
				result.add(describeBehaviour(session, behaviour));
				break;
			}
		}
		return result;

	}

	private String describeBehaviour(Object session, Object behaviour) throws Exception {

		StringBuilder sb = new StringBuilder();

		for (Long event : (Iterable<Long>) invokeProperty(behaviourClass, behaviour, "trace", null, null)) {

			if (event != 1 && event != 0) {
				Object result = invokeProperty(sessionClass, session, "uncompileEvent", long.class, event);
				sb.append(result.toString() + ", ");
			}
		}

		return sb.toString();
	}

	public void traceBehaviour(Object behaviour, StringBuilder sb, Object session) throws Exception {
		Object machine = invokeProperty(behaviourClass, behaviour, "machine", null, null);
		Object node = invokeProperty(Machine, machine, "rootNode", null, null);
		Object transitionList;
		while (true) {
			transitionList = invokeProperty(Machine, machine, "transitions", Node, node);
			Object evento = invokeProperty(TransitionList, transitionList, "get", int.class, 0);
			Object eventID = invokeProperty(Transition, evento, "event", null, null);
			Object result = invokeProperty(sessionClass, session, "uncompileEvent", long.class, eventID);
			if (!result.equals("τ")) {
				sb.append(result.toString());
			}
			if (result.toString().equals("endInteraction"))
				break;
			sb.append(", ");
			node = invokeProperty(Transition, evento, "destination", null, null);
		}
	}

	private static Object invokeProperty(Class<?> dsClass, Object ds, String propertyName, Class<?> paramClass,

			Object paramValue) throws Exception {

		Method method;

		try {

			if (paramClass != null) {
				method = dsClass.getMethod(propertyName, paramClass);
				method.setAccessible(true);
				return method.invoke(ds, paramValue);

			} else {

				method = dsClass.getMethod(propertyName);
				method.setAccessible(true);
				return method.invoke(ds);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}
	
	/*  Activity Diagram  */

	public List<String> describeDeadlockCounterExample(Object session, Object counterExample) throws Exception {
		Object behaviour = invokeProperty(deadlockCounterexampleClass, counterExample, "behaviour", null, null);
		List<String> trace = describeBehaviourDeadLock(session, behaviour);

		return trace;
	}

	private List<String> describeBehaviourDeadLock(Object session, Object behaviour) throws Exception {

		List<String> trace = new ArrayList<>();

		for (Long event : (Iterable<Long>) invokeProperty(behaviourClass, behaviour, "trace", null, null)) {

			if (event != 1 && event != 0) {
				Object result = invokeProperty(sessionClass, session, "uncompileEvent", long.class, event);
				trace.add(result.toString());
			}
		}

		return trace;
	}

    public List<String> describeDeterminismCounterExample(Object session, Object counterExample) throws Exception {
        Object behaviour = invokeProperty(determinismCounterexampleClass, counterExample, "specificationBehaviour", null, null);
        List<String> trace = describeBehaviourDeterminism(session, behaviour);

        return trace;
    }

    private List<String> describeBehaviourDeterminism(Object session, Object behaviour) throws Exception {

        List<String> trace = new ArrayList<>();

        for (Long event : (Iterable<Long>) invokeProperty(behaviourClass, behaviour, "trace", null, null)) {

            if (event != 1 && event != 0) {
                Object result = invokeProperty(sessionClass, session, "uncompileEvent", long.class, event);
                trace.add(result.toString());
            }
        }

        return trace;
    }

	public String getErrorEvent(Object counterExample, Object session) {
        String errorEvent = "";
        try {
            Object error = invokeProperty(traceCounterexampleClass, counterExample, "errorEvent", null, null);
            if ((Long) error != 1 && (Long) error != 0) {
                errorEvent = invokeProperty(sessionClass, session, "uncompileEvent", long.class, error).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorEvent;
    }

	
	public List<String> checkDeadlock(String filename, ADParser parser, String nameDiagram, CheckingProgressBar progressBar) throws Exception{
		//returns the trace
		
	/*
	0 = error
	1 = deadlock free
	2 = deadlock detected
	3 = compilation failed
	4 = invalid license
	*/

		progressBar.setProgress(1, "", false);

		int hasError = 0;
		List<String> trace = null;
		try {

			Object fdr = fdrClass.newInstance();
			boolean hasValidLicense = (boolean) invokeProperty(fdr.getClass(), fdr, "hasValidLicense", null , null);

			if (!hasValidLicense) {
				hasError = 4;
			}

			if (hasError == 0) { // has valid license
				try {
					session = sessionClass.newInstance();

					invokeProperty(session.getClass(), session, "loadFile", String.class, filename);

					List<Object> assertions = (List) invokeProperty(session.getClass(), session, "assertions", null, null);
					Object assertion = assertions.get(0);

					progressBar.setProgress(2, "", false);
					invokeProperty(assertion.getClass(), assertion, "execute", Canceller, null);

					for (Object DeadlockCounterExampleObj : (Iterable<?>) invokeProperty(assertion.getClass(), assertion,
							"counterexamples", null, null)) {

						progressBar.setProgress(3, "", false);
						trace = describeDeadlockCounterExample(session, DeadlockCounterExampleObj);
						hasError = 2;
					}

					if (hasError == 0) {
						hasError = 1;
					}

					progressBar.setProgress(4, "", false);

				}catch (InvalidEditingException e) {
					TransactionManager.abortTransaction();
				} catch (Exception e) {
					TransactionManager.abortTransaction();
					hasError = 3;
				}
			}

		} catch (InstantiationException e) {
			throw new Exception("Set your fdr path 1");
		} catch (IllegalAccessException e) {
			throw new Exception("Set your fdr path 2");
		} catch (Exception e) {
			Logador logger = Logador.getInstance();
			logger.log("LOG FDRWRAPPER");
			for(StackTraceElement element :e.getStackTrace()){
				logger.log(element.toString());
			}
		}

		if (hasError == 1) {
			progressBar.setProgress(5, handleLogDeadlock(hasError, nameDiagram), true);
		} else {
			progressBar.setProgress(5, handleLogDeadlock(hasError, nameDiagram), false);
		}
		return trace;
	}

	public int checkLivelock(String filename) throws Exception{

	/*
	0 = error
	1 = deadlock free
	2 = deadlock detected
	3 = compilation failed
	4 = invalid license
	*/

		int hasError = 0;

		try {

			Object fdr = fdrClass.newInstance();
			boolean hasValidLicense = (boolean) invokeProperty(fdr.getClass(), fdr, "hasValidLicense", null , null);

			if (!hasValidLicense) {
				hasError = 4;
			}

			if (hasError == 0) { // has valid license
				try {
					session = sessionClass.newInstance();

					invokeProperty(session.getClass(), session, "loadFile", String.class, filename);

					List<Object> assertions = (List) invokeProperty(session.getClass(), session, "assertions", null, null);
					Object assertion = assertions.get(1);

					invokeProperty(assertion.getClass(), assertion, "execute", Canceller, null);

					if (!((boolean) invokeProperty(assertion.getClass(), assertion,
							"passed", null, null))) {
						hasError = 2;
					}

					for (Object counterExample : (Iterable<?>) invokeProperty(assertion.getClass(), assertion,
							"counterexamples", null, null)) {
						hasError = 1;
						break;
					}

				} catch (Exception e) {
					hasError = 2;
				}
			}

		} catch (InstantiationException e) {
			throw new Exception("Set your fdr path 1");
		} catch (IllegalAccessException e) {
			throw new Exception("Set your fdr path 2");
		} catch (Exception e) {
			Logador logger = Logador.getInstance();
			logger.log("LOG FDRWRAPPER");
			for(StackTraceElement element :e.getStackTrace()){
				logger.log(element.toString());
			}
		}


		return hasError;
	}

	public List<String> checkDeterminism(String filename, ADParser parser, String nameDiagram, CheckingProgressBar progressBar) throws Exception{
		//returns the trace
	/*
	0 = error
	1 = deadlock free
	2 = deadlock detected
	3 = compilation failed
	4 = invalid license
	*/

		progressBar.setProgress(1, "", false);

		int hasError = 0;
		List<String> trace = null;
		
		try {

			Object fdr = fdrClass.newInstance();
			boolean hasValidLicense = (boolean) invokeProperty(fdr.getClass(), fdr, "hasValidLicense", null , null);

			if (!hasValidLicense) {
				hasError = 4;
			}

			if (hasError == 0) {
				try {
					session = sessionClass.newInstance();

					invokeProperty(session.getClass(), session, "loadFile", String.class, filename);

					List<Object> assertions = (List) invokeProperty(session.getClass(), session, "assertions", null, null);
					Object assertion = assertions.get(2);

					progressBar.setProgress(2, "", false);
					invokeProperty(assertion.getClass(), assertion, "execute", Canceller, null);

					for (Object DeterminismCounterexample : (Iterable<?>) invokeProperty(assertion.getClass(), assertion,
							"counterexamples", null, null)) {

						progressBar.setProgress(3, "", false);
						trace = describeDeterminismCounterExample(session, DeterminismCounterexample);
						hasError = 2;
					}

					if (hasError == 0) {
						hasError = 1;
					}

					progressBar.setProgress(4, "", false);

				} catch (InvalidEditingException e) {
					TransactionManager.abortTransaction();
				} catch (Exception e) {
					TransactionManager.abortTransaction();
					hasError = 3;
				}
			}

		} catch (InstantiationException e) {
			throw new Exception("Set your fdr path 1");
		} catch (IllegalAccessException e) {
			throw new Exception("Set your fdr path 2");
		} catch (Exception e) {
			Logador logger = Logador.getInstance();
			logger.log("LOG FDRWRAPPER");
			for(StackTraceElement element :e.getStackTrace()){
				logger.log(element.toString());
			}
		}

		if (hasError == 1) {
			progressBar.setProgress(5, handleLogDeterminism(hasError, nameDiagram), true);
		} else {
			progressBar.setProgress(5, handleLogDeterminism(hasError, nameDiagram), false);
		}

		return trace;
	}

	private String handleLogDeadlock(int hasError, String nameDiagram) {
		String log = "";
		if (hasError == 1) {
			log = nameDiagram + " is deadlock free!";
		} else if (hasError == 2) {
			log = "Deadlock detected in " + nameDiagram;
		} else if (hasError == 3 || hasError == 0) {
			log = "Compilation failed in " + nameDiagram;
		} else if (hasError == 4) {
			log = "FDR license is not valid!\n" +
					"Please activate FDR license and restart Astah.";
		}

		return log;
	}

	private String handleLogDeterminism(int hasError, String nameDiagram) {
		String log = "";
		if (hasError == 1) {
			log = nameDiagram + " is deterministic!";
		} else if (hasError == 2) {
			log = "Non-Determinism detected in " + nameDiagram;
		} else if (hasError == 3 || hasError == 0) {
			log = "Compilation failed in " + nameDiagram;
		} else if (hasError == 4) {
			log = "FDR license is not valid!\n" +
					"Please activate FDR license and restart Astah.";
		}

		return log;
	}
}
