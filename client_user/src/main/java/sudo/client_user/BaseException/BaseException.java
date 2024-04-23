package sudo.client_user.BaseException;

import lombok.Data;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/1223:22
 */
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected String errorCode;
    protected Object data;
    protected Object context;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause, String errorCode, Object data, Object context) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
        this.context = context;
    }

}
