package uz.onteach.onteachuz.utils;

public enum Errors {
    SUCCESS(0, "");
    private final int code;
    private final String msg;

    Errors(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
