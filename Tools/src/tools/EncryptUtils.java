package tools;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class EncryptUtils {
	public static void generateKeyPair(String publicKeyFile,
			String privateKeyFile) throws Exception {

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.genKeyPair();

		KeyFactory fact = KeyFactory.getInstance("RSA");

		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),
				RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),
				RSAPrivateKeySpec.class);

		IOUtils.saveKeyToFile(publicKeyFile, pub.getModulus(), pub
				.getPublicExponent());
		IOUtils.saveKeyToFile(privateKeyFile, priv.getModulus(), priv
				.getPrivateExponent());

	}
}
