package ru.korchinskiy.parking.booking.infrastructure.config.transaction;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.korchinskiy.parking.booking.application.annotation.UseCase;

@Aspect
@RequiredArgsConstructor
public class TransactionalUseCaseAspect {

    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;

    @Pointcut(value = "within(useCase)", argNames = "useCase")
    void inUseCase(UseCase useCase) {}

    Object useCase(ProceedingJoinPoint proceedingJoinPoint, UseCase useCase) {
        if (useCase.readOnly()) {
            return transactionalUseCaseExecutor.executeInReadTransaction(() -> proceed(proceedingJoinPoint));
        } else {
            return transactionalUseCaseExecutor.executeInWriteTransaction(() -> proceed(proceedingJoinPoint));
        }
    }

    @SneakyThrows
    Object proceed(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint.proceed();
    }
}
