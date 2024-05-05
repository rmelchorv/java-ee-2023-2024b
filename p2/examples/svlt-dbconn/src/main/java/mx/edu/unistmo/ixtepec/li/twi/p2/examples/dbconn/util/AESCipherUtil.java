package mx.edu.unistmo.ixtepec.li.twi.p2.examples.dbconn.util;

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

  public static String decrypt(String strEncoded, String strKey)
      throws BadPaddingException, IllegalBlockSizeException,
             InvalidKeyException, NoSuchAlgorithmException,
             NoSuchPaddingException {
    Cipher cipher = Cipher.getInstance("AES");
    Key key = generateKey(strKey);

    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] bytesDecoded = Base64.getDecoder().decode(strEncoded);
    byte[] bytesBuffer = cipher.doFinal(bytesDecoded);

    return new String(bytesBuffer);
  }

  private static Key generateKey(String key) {
    return new SecretKeySpec(key.getBytes(), "AES");
  }
}
