package ru.korchinskiy.parking.booking.infrastructure.config.transaction;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.korchinskiy.parking.booking.application.annotation.UseCase;

@Aspect
@RequiredArgsConstructor
public class TransactionalUseCaseAspect {

    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;

    @Pointcut("@annotation(useCase)")
    public void callAt(UseCase useCase) {}

    @Around(value = "callAt(useCase)", argNames = "proceedingJoinPoint,useCase")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, UseCase useCase) {
        if (useCase.readOnly()) {
            return transactionalUseCaseExecutor.executeInReadTransaction(() -> proceed(proceedingJoinPoint));
        } else {
            return transactionalUseCaseExecutor.executeInWriteTransaction(() -> proceed(proceedingJoinPoint));
        }
    }

    @SneakyThrows
    private Object proceed(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint.proceed();
    }
}
