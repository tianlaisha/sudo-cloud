package sudo.client_user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sudo.client_user.controller.UserController;
import sudo.client_user.entity.User;
import sudo.client_user.mapper.UserMapper;
import sudo.client_user.service.UserService;

import javax.annotation.Resource;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/612:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserMapper userMapper;

    @Override
    public User selectUser(Integer id) {
        logger.debug("service begin");
        return userMapper.selectUser(id);
    }

    @Override
    public void login(String username, String password) {

        JSONObject jwsHeader = new JSONObject();
        jwsHeader.fluentPut("alg","HS256");
        jwsHeader.fluentPut("typ","JWT");

        JSONObject jwsPayLoad = new JSONObject();
        jwsPayLoad.fluentPut("username",username);
        jwsPayLoad.fluentPut("password",password);
        jwsPayLoad.fluentPut("exp",1000);

        String prrkey = "abcd";


        String base64JwsHeader = Base64.encodeBase64URLSafeString(jwsHeader.toJSONString().getBytes());
        String base64JwsPayload  = Base64.encodeBase64URLSafeString(jwsPayLoad.toJSONString().getBytes());

        String sign = base64JwsHeader + "." +base64JwsPayload;
        String jwsSign = signBySHA256WithRSA(sign,prrkey);

        String finalJwsSign = base64JwsHeader + ".."+ jwsSign;

    }


    //SHA256加密
    public String signBySHA256WithRSA(String content, String privateKey){
        if(StringUtils.isBlank(privateKey)){
            return null;
        }
        try {
            //处理私钥
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(priPKCS8);
            //加密
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("UTF-8"));
            return Base64.encodeBase64URLSafeString(signature.sign());
        } catch (Exception e) {
            return null;
        }
    }


    public Boolean getKey(String publicKey,String sign,String  content){
        try {
            //处理公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
            //解密
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(pubKey);
            //获取Base64url jwsHeader（越界优化啥的俺就暂时不管了）
            String jwsHeaderBase64 =  sign.substring(0,sign.indexOf(".."));
            String jwsSignBase64 = sign.substring(sign.indexOf("..") + 1);
            sign = jwsHeaderBase64 + '.' + Base64.encodeBase64URLSafeString(content.getBytes());
            signature.update(sign.getBytes("UTF-8"));
            return signature.verify(Base64.decodeBase64(jwsSignBase64));
        } catch (Exception e) {
            return false;
        }
    }
}
