package sudo.clientchat.chain;

/**
 * @author glz
 * @version 1.0
 * @description // 此接口有待于升级  目前实现责任链  还需要考虑多线程，java8特性，存储异步等相关事项
 * @date 2021/11/822:02
 */
public interface BaseFilter<T, R, E> {
    public void doFilter(T t, R r, E e);
}
