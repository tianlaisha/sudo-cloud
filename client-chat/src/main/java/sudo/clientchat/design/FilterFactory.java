package sudo.clientchat.design;

import sudo.clientchat.chain.BaseFilter;
import sudo.clientchat.chain.GameFilter;
import sudo.clientchat.chain.ServiceFilter;

/**
 * @author glz
 * @version 1.0
 * @description  工厂模式：需要进一步改进-进一步封装
 * @date 2021/11/923:45
 */
public class FilterFactory implements Factory<Boolean,BaseFilter> {


    public static ServiceFilter serviceFilter = new ServiceFilter();

    @Override
    public BaseFilter get(Boolean mode) {
        if(mode){
            return serviceFilter;
        }  else {
            return new GameFilter();
        }
    }


}
