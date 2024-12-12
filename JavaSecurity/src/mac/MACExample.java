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
		byte[] macValue = KeyUtil.generateMac(macKey, message); // 預設 HmacSHA256
		
		// 4. 將 macValue 轉 16 進位字串印出
		String macHexValue = KeyUtil.bytesToHex(macValue);
		System.out.println("MAC(Hex):" + macHexValue);
		
		// 5. 在實際應用中, 接收方會收到訊息(message) 與 macHexValue
		//    此時 message 要與 macKey(雙方都要有的密鑰) 產生出 computedMacHexValue 值進行與 macHexValue 的比對
		String receivedMessage = message; // 收到的訊息
		byte[] computedMacValue = KeyUtil.generateMac(macKey, receivedMessage);
		String computedMacHexValue = KeyUtil.bytesToHex(computedMacValue);
		
		// 6. 比較 macHexValue 值是否相同 ?
		if(macHexValue.equals(computedMacHexValue)) {
			System.out.println("MAC 驗證成功, 來源正確");
		} else {
			System.out.println("MAC 驗證失敗, 來源有誤");
		}
		
	}
	
}
