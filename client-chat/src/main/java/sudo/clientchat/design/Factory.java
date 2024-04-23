package sudo.clientchat.design;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/11/923:36
 */
public interface Factory<T, R> {

    R get(T t);

}
