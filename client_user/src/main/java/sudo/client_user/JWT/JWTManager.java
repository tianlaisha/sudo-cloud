package sudo.client_user.JWT;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import sudo.client_user.entity.User;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/2416:28
 */
public class JWTManager {

    public static String generateToken(User user, int expiration) throws Exception {
        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setIssuer(user.getPassWord());
        jwtClaims.setAudience("RS256");
        jwtClaims.setExpirationTimeMinutesInTheFuture(expiration);
        jwtClaims.setGeneratedJwtId();
        jwtClaims.setIssuedAtToNow();
        jwtClaims.setNotBeforeMinutesInThePast(2);
        jwtClaims.setSubject("LOGIN");

        JsonWebSignature jsonWebSignature = new JsonWebSignature();
        jsonWebSignature.setPayload(jwtClaims.toJson());
        jsonWebSignature.setKey(RsaJsonWebKeyBuilder.getRasJsonWebKeyInstance().getPrivateKey());
        jsonWebSignature.setKeyIdHeaderValue(RsaJsonWebKeyBuilder.getRasJsonWebKeyInstance().getKeyId());
        jsonWebSignature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = jsonWebSignature.getCompactSerialization();

        return "LOGIN " + jwt;
    }

    public static boolean verifyToken(String token, String password) {
        String tokenContent = token.substring(6);
        JwtConsumer consumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setMaxFutureValidityInMinutes(5256000)
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer(password)
                .setExpectedAudience("RS256")
                .setVerificationKey(RsaJsonWebKeyBuilder.getRasJsonWebKeyInstance().getPublicKey())
                .build();
        try {
            JwtClaims claims = consumer.processToClaims(tokenContent);
            return true;
        } catch (InvalidJwtException e) {
            return false;
        }
    }

}
