package uz.onteach.onteachuz.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralException extends Exception{
    private Errors errors;
}
