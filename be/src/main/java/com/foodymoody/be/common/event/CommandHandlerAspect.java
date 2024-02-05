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

    /**
     * application 하위패키지 service 하위패키지의 Service 클래스의 Transactional 어노테이션이 붙어있는 메서드에 대해 Aspect 를 적용한다.
     */
    @Before("execution(* com.foodymoody.be.*.application.service.*Service.*(..))"
            + "&& (@annotation(org.springframework.transaction.annotation.Transactional)"
            + "|| @within(org.springframework.transaction.annotation.Transactional))")
    public void beforeTransactionalCommandExecutionNew() {
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

    private class EventTransactionSynchronization implements TransactionSynchronization {

        private final MessagePublisher messagePublisher;

        public EventTransactionSynchronization(MessagePublisher messagePublisher) {
            this.messagePublisher = messagePublisher;
        }

        @Override
        public void afterCommit() {
            EventManager.getEvents()
                    .forEach(messagePublisher::publish);
        }

        @Override
        public void afterCompletion(int status) {
            EventManager.clear();
        }
    }
}
