package sudo.client_user.union;

import java.util.Date;

/**
 * @author glz
 * @version 1.0
 * @description 全局唯一键生成器
 * @date 2021/11/20:29
 */
public class UnionGenerator {

    static String num2char = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static String NumToBHex(int num, int n){
        String numStr = "";
        while(num != 0){
            int m = num%n;
            numStr = num2char.indexOf(m) +numStr;
            num = num /n;
        }
        return numStr;
    }

    // 生成指定数量的唯一键
    public static String generateN_Num(){
        return NumToBHex((int) (System.currentTimeMillis() /1000000),36);
    }



}
