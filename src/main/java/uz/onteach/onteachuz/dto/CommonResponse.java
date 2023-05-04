package uz.onteach.onteachuz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse <T>{
    private T data;
    private String message;
    private int code;
}
