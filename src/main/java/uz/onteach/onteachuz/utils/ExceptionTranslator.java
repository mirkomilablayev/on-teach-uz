package uz.onteach.onteachuz.utils;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import uz.onteach.onteachuz.dto.CommonResult;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(GeneralException.class)
    public Mono<CommonResult<?>> generalExceptionHandler(GeneralException ex) {
        Errors errors = ex.getErrors();
        return Mono.just(new CommonResult<>(errors.getCode(), errors.getMsg(), null));
    }

}
