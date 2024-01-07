package com.foodymoody.be.common.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RequiredArgsConstructor
@Aspect
@Component
public class CommandHandlerAspect {

    private final MessagePublisher messagePublisher;

    @Before("execution(* com.foodymoody.be.*.application.*Service.*(..))"
            + "&& @annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalCommandExecution() {
        if (!isEventTransactionSynchronizationRegistered()) {
            var eventTransactionSynchronization = new EventTransactionSynchronization(messagePublisher);
            TransactionSynchronizationManager.registerSynchronization(eventTransactionSynchronization);
        }
    }

    private boolean isEventTransactionSynchronizationRegistered() {
        List<TransactionSynchronization> synchronizations = TransactionSynchronizationManager.getSynchronizations();
        return synchronizations.stream()
                .anyMatch(EventTransactionSynchronization.class::isInstance);
    }

    private static class EventTransactionSynchronization implements TransactionSynchronization {

        private final MessagePublisher messagePublisher;

        public EventTransactionSynchronization(MessagePublisher messagePublisher) {
            this.messagePublisher = messagePublisher;
        }


        @Override
        public void beforeCommit(boolean readOnly) {
            Events.getEvents()
                    .forEach(messagePublisher::publish);
        }

        @Override
        public void afterCompletion(int status) {
            Events.clear();
        }
    }
}
