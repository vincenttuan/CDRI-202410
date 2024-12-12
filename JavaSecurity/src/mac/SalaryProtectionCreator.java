package mac;

import java.io.File;

import javax.crypto.SecretKey;

import security.KeyUtil;

/*
 * 情境描述：
 * 在一家大型企業，HR部門每月都會發送電子薪資明細給員工。
 * 為了確保薪資明細的安全性，HR部門決定對薪資明細檔案進行雜湊和 MAC 保護。
 * 雜湊保護確保薪資明細的完整性，而MAC則確保薪資明細確實來自HR部門，並未被其他部門或外部攻擊者更改。
 * 薪資檔案位置   : src/mac/my_salary.txt
 * macKey檔案位置: src/mac/macKey.key
 * */
public class SalaryProtectionCreator {
	public static void main(String[] args) throws Exception {
		String filePath = "src/mac/my_salary.txt";
		String keyPath = "src/mac/mackey.key";
		
		// 生成 mac key file
		SecretKey macKey = null;
		if(new File(keyPath).exists()) {
			macKey = KeyUtil.getSecretKeyFromFile("HmacSHA256", keyPath);
		} else {
			macKey = KeyUtil.generateKeyForHmac();
			// 儲存
			KeyUtil.saveSecretKeyToFile(macKey, keyPath);
		}
		
		// 得到 macValue(Hex)
		String macHexValue = KeyUtil.generateMac("HmacSHA256", macKey, filePath);
		System.out.println("HR 發布的 macHexValue: " + macHexValue);
	}
}
