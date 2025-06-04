package br.com.codegroup.config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

/**
 * Cucumber plugin that logs the name of the current step being executed.
 * Stores the current step name in a ThreadLocal variable for access during execution.
 */
public class Logger implements ConcurrentEventListener {

    /**
     * Stores the name of the current step being executed in a thread-safe way.
     */
    private static final ThreadLocal<String> currentStep = new ThreadLocal<>();

    /**
     * Retrieves the name of the current executing step.
     *
     * @return the current step name or null if none is set
     */
    public static String getCurrentStepName() {
        return currentStep.get();
    }

    /**
     * Registers the event handler for when a test step starts.
     * Called automatically by Cucumber to set up the event listener.
     *
     * @param publisher event publisher provided by Cucumber
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
    }

    /**
     * Handles the event when a test step starts.
     * If the step is a PickleStepTestStep, its name is stored.
     *
     * @param event the test step started event
     */
    private void handleStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepName = step.getStep().getKeyword() + step.getStep().getText();
            currentStep.set(stepName);
        }
    }
}
