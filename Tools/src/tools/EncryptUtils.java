package tools;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.math.BigInteger;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;


public class EncryptUtils
{
    public static KeyPair generateKeyPair(String publicKeyFile, String privateKeyFile) throws Exception
    {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

        KeyPair kp = kpg.genKeyPair();

        KeyFactory fact = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);
        RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(), RSAPrivateKeySpec.class);

        IOUtils.saveKeyToFile(publicKeyFile, pub.getModulus(), pub.getPublicExponent());
        IOUtils.saveKeyToFile(privateKeyFile, priv.getModulus(), priv.getPrivateExponent());

        return kp;
    }

    public static PublicKey readKeyFromString(String keyString) throws IOException
    {
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(keyString.getBytes("UTF-8"))));

        try
        {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = fact.generatePublic(keySpec);

            return pubKey;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Spurious serialisation error", e);
        }
        finally
        {
            oin.close();
        }
    }

    public static String rsaEncrypt(String data, Key key) throws Exception
    {
        byte[] rawBytes = data.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherData = cipher.doFinal(rawBytes);

        return new String(cipherData);
    }

    public static String rsaDecrypt(String cipherData, Key key) throws Exception
    {
        byte[] cipherBytes = cipherData.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] data = cipher.doFinal(cipherBytes);

        return new String(data);
    }
}
