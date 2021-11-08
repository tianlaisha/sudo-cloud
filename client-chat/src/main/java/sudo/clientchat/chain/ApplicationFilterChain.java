package sudo.clientchat.chain;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glz
 * @version 1.0
 * @description  // 需要中间增加一层处理共同属性  实现隔离化
 * @date 2021/11/821:00
 */
@Component
public class ApplicationFilterChain implements BaseFilter<String,String,ApplicationFilterChain> {

    public List<BaseFilter> filterList = new ArrayList<BaseFilter>();

    public BaseFilter baseFilter;

    public ThreadLocal<Integer> counts = new ThreadLocal<Integer>();

    public ApplicationFilterChain(){
        filterList.add(new ServiceFilter());
        filterList.add(new GameFilter());
    }


    // 这个方法里面可以做共有处理，比如日志
    @Override
    public void doFilter(String req, String res, ApplicationFilterChain chain) {
        System.out.println("filters begin doFilter -----  begin -----"+counts.get());
        if(counts.get() == null){counts.set(0);}
        if(filterList.size()== counts.get()) {return;};
        baseFilter = filterList.get(counts.get());
        counts.set(counts.get()+1);
        baseFilter.doFilter(req,res,chain);
    }
}
