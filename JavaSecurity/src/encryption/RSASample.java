package encryption;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

import security.KeyUtil;

public class RSASample {
	
	public static void main(String[] args) throws Exception {
		// 1. 生成 RSA 密鑰對(公/私鑰)
		KeyPair keyPair = KeyUtil.generateRSAKeyPair(); // RSA-2048
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		String originalMessage = "聖誕快樂 X'mas";
		System.out.println("1. 原始訊息:" + originalMessage);
		
		// 2. 利用公鑰進行加密
		byte[] encryptedBytes = KeyUtil.encryptWithPublicKey(publicKey, originalMessage.getBytes());
		System.out.println("2. 加密訊息:" + Arrays.toString(encryptedBytes));
		
		// 3. 轉 base64
		String codingEncryptedBytes = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("3. 加密訊息(Base64):" + codingEncryptedBytes);
		
		//--------------------------------------------------------------------------------------------
		System.out.println("--------------------------------------------------------------------------");
		
		System.out.println("4. 網路上訊息傳遞: " + codingEncryptedBytes);
		Thread.sleep(3000);
		
		System.out.println("--------------------------------------------------------------------------");
		
		// 4. 利用私鑰解密
		System.out.println("5. 解密...");
		Thread.sleep(3000);
		
		// 5. 解密程序
		// 將 codingEncryptedBytes 進行 Base64 解碼
		byte[] decodingEncryptedBytes = Base64.getDecoder().decode(codingEncryptedBytes);
		// 將解碼後的資料透過 privateKey 私鑰解密
		byte[] decryptedBytes = KeyUtil.decryptWithPrivateKey(privateKey, decodingEncryptedBytes);
		String message = new String(decryptedBytes); 
		System.out.println("6. 解密後的訊息: " + message); 
		
	}
	
}
