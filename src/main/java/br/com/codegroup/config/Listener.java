package br.com.codegroup.config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

/**
 * Cucumber event listener to track the error of the currently executed step.
 */
public class Listener implements ConcurrentEventListener {

    /**
     * Stores the error (Throwable) of the current step being executed in a thread-safe way.
     */
    private static final ThreadLocal<Throwable> currentStepError = new ThreadLocal<>();

    /**
     * Registers the event handler for test step completion.
     * Called automatically by Cucumber to set up the event listener.
     *
     * @param publisher event publisher provided by Cucumber
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    /**
     * Handles the event when a test step finishes.
     * If the step had an error, it is stored; otherwise, the error is cleared.
     *
     * @param event the test step finished event
     */
    private void handleTestStepFinished(TestStepFinished event) {
        Throwable error = event.getResult().getError();
        if (error != null) {
            currentStepError.set(error);
        } else {
            currentStepError.remove();
        }
    }

    /**
     * Returns the Throwable (error) of the currently executed step.
     *
     * @return Throwable of the current step or null if no error
     */
    public static Throwable getCurrentStepError() {
        return currentStepError.get();
    }
}
