package encryption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

// AES 對稱式加密
public class AESSample {
	
	// 建立一個 AES Key(256bits, 32bytes)
	private static final String KEY = "012345678901234567890123456789AB"; // 32 個字

	// 建立一個 SecureRandom
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	
	public static void main(String[] args) throws Exception {
		String originalText = "剛剛已經有下雨了"; // 明文
		System.out.println("原始明文: " + originalText);
		System.out.println("----------------------------");
		
		// 利用 AES 進行加密
		// 流程: 明文 -> 加密(encryptedECB) -> 編碼(codingECBBase64)
		//      解碼(decodingECBBase64) -> 解密(decryptedECB) -> 明文
		// 1. 建立 AES 密鑰規範
		//SecretKeySpec aseKeySpec = new SecretKeySpec(KEY.getBytes(), "AES"); // 金鑰
		SecretKeySpec aseKeySpec = loadKeyFromFile("aes_key.key"); // 從檔案中讀取金鑰
		// 2. 選擇 ECB 模式進行對明文的加密 (透過金鑰加密)
		byte[] encryptedECB = KeyUtil.encryptWithAESKey(aseKeySpec, originalText);
		System.out.println("加密後: " + Arrays.toString(encryptedECB));
		// byte[] 轉 Base64 (編碼)
		String codingECBBase64 = Base64.getEncoder().encodeToString(encryptedECB);
		System.out.println("加密後(Base64): " + codingECBBase64);
		System.out.println("----------------------------");
		
		// 利用 AES 進行解密
		// Base64 轉 byte[] (解碼)
		byte[] decodingECBBase64 = Base64.getDecoder().decode(codingECBBase64);
		// 進行解密 (透過金鑰解密)
		String decryptedECB = KeyUtil.decryptWithAESKey(aseKeySpec, decodingECBBase64);
		System.out.println("解密後: " + decryptedECB);
		
	}
	
	// 讀取金鑰檔案
	private static SecretKeySpec loadKeyFromFile(String filePath) throws Exception {
		String keyBase64 = Files.readString(Path.of(filePath));
		byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
		return new SecretKeySpec(keyBytes, "AES");
	}
}
