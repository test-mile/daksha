package daksha.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import daksha.core.utils.ExceptionBatteries;

public class BlowFishHandler {
	private byte[] encKey = null;

	public BlowFishHandler (byte[] encKey){
		this.encKey = encKey;
	}

	public byte[] encrypt(ZipArray compressed) throws Exception {
		try {
			SecretKeySpec secretkeySpec = new SecretKeySpec(this.encKey,"BlowFish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, secretkeySpec);
			byte[] encrypted = cipher.doFinal(compressed.byteArray, 0, compressed.length);
			return encrypted;

		}
		catch (Exception e) {
			ExceptionBatteries.getStackTraceAsString(e);
			throw e;
		}
	}
	
	public byte[] decrypt(byte[] decoded) throws Exception {
		try {
			SecretKeySpec secretkeySpec = new SecretKeySpec(this.encKey,"BlowFish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretkeySpec);

			byte[] decrypted = cipher.doFinal(decoded);
			return decrypted;

		}
		catch (Exception e) {
			ExceptionBatteries.getStackTraceAsString(e);
			throw e;
		}
	}	
}

