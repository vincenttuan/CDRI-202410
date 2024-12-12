package mac;

import javax.crypto.SecretKey;

import security.KeyUtil;

// MAC 訊息驗證碼
public class MACExample {
	
	public static void main(String[] args) throws Exception {
		// 1. 定義訊息
		String message = "2024 聖誕快樂!";
		
		// 2. 產生一把專用於 Hmac 的密鑰
		SecretKey macKey = KeyUtil.generateKeyForHmac(); // 預設 HmacSHA256
		
		// 3. 利用此密鑰(macKey) + 訊息(message) 生成 MAC 值
		byte[] macValue = KeyUtil.generateMac(macKey, message);
		
	}
	
}
