package br.com.codegroup.config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

/**
 * Cucumber event listener to track the error of the currently executed step.
 */
public class Listener implements ConcurrentEventListener {

    private static final ThreadLocal<Throwable> currentStepError = new ThreadLocal<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

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
     * @return Throwable of the current step or null if no error
     */
    public static Throwable getCurrentStepError() {
        return currentStepError.get();
    }
}
