package sudo.clientchat.chain;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/11/821:12
 */
public class ServiceFilter implements BaseFilter<String, String, ApplicationFilterChain> {


    @Override
    public void doFilter(String req, String res, ApplicationFilterChain applicationFilterChain) {
        System.out.println("this is ServiceFilter do Filter begin");
        applicationFilterChain.doFilter(req, res, applicationFilterChain);
    }
}
