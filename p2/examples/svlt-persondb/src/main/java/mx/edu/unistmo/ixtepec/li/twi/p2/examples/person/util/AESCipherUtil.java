package mx.edu.unistmo.ixtepec.li.twi.p2.examples.person.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCipherUtil {
  private AESCipherUtil() {
  }

  public static String decrypt(String encodedStr, String strKey)
      throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException,
      NoSuchAlgorithmException, NoSuchPaddingException {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    Key key = generateKey(strKey, "AES");

    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] decodedValue = Base64.getDecoder().decode(encodedStr);
    byte[] decodedStr = cipher.doFinal(decodedValue);

    return new String(decodedStr);
  }

  private static Key generateKey(String key, String algorithm) {
    return new SecretKeySpec(key.getBytes(), algorithm);
  }
}
