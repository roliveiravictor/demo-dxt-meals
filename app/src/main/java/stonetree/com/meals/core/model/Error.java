package stonetree.com.meals.core.model;

import stonetree.com.meals.constants.Constants;

public class Error {

    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CharSequence getToastMessage() {
        return this.getMessage();
    }

    public static Error getDefault() {
        return new Error(Constants.DUMMY, "Avoiding Crash - Please Retry");
    }

    public void setup(Throwable throwable) {
        this.code = Constants.DUMMY;
        this.message = throwable.getMessage();
    }

}
