package br.com.codegroup.config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

/**
 * Cucumber plugin that logs the name of the current step being executed.
 * Stores the current step name in a ThreadLocal variable for access during execution.
 */
public class Logger implements ConcurrentEventListener {

    private static final ThreadLocal<String> currentStep = new ThreadLocal<>();

    /**
     * Retrieves the name of the current executing step.
     * @return the current step name or null if none set
     */
    public static String getCurrentStepName() {
        return currentStep.get();
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
    }

    private void handleStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepName = step.getStep().getKeyword() + step.getStep().getText();
            currentStep.set(stepName);
        }
    }
}
