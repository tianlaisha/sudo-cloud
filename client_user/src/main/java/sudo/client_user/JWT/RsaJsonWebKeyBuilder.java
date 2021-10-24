package sudo.client_user.JWT;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;

import java.util.Random;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2416:29
 */
public class RsaJsonWebKeyBuilder {

    private static volatile RsaJsonWebKey rsaJsonWebKey;

    private RsaJsonWebKeyBuilder() {
    }

    public static RsaJsonWebKey getRasJsonWebKeyInstance() {
        if (rsaJsonWebKey == null) {
            synchronized (RsaJsonWebKey.class) {
                if (rsaJsonWebKey == null) {
                    try {
                        rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
                        rsaJsonWebKey.setKeyId(String.valueOf(new Random().nextLong()));
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return rsaJsonWebKey;
    }

}
