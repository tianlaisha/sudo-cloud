package sudo.clientchat.chain;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/11/821:14
 */
public class GameFilter implements BaseFilter<String, String, ApplicationFilterChain> {

    @Override
    public void doFilter(String req, String res, ApplicationFilterChain applicationFilterChain) {
        System.out.println("this is GameFilter do Filter begin");
        applicationFilterChain.doFilter(req, res, applicationFilterChain);
    }
}
