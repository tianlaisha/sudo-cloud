package sudo.client_user.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import sudo.client_user.BaseException.BaseException;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/1223:04
 */
public class JSONUtil {

    public static JSONObject transferJsonObject(String jsonStr){
        if(StringUtils.isEmpty(jsonStr)) throw new BaseException("JSON字符串不能为空！");
        try{
            return JSON.parseObject(jsonStr);
        }catch (Exception e){
           throw new BaseException("JSON字符串转换异常！");
        }
    }
}
