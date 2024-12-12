package mac;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.crypto.SecretKey;

import security.KeyUtil;

/*
 * 員工已知或得到的資訊
 * 薪資檔案位置   : src/mac/my_salary.txt
 * macKey檔案位置: src/mac/macKey.key
 * HR 發佈的 macHexValue:31846399dc0095accb10364c41bbe55a7a9479bb2c67f1e96d8653069319ff22
 * 
 * 如何得知 my_salary.txt 是由 HR 所發佈的 ? (來源端確認)
 * */
public class SalaryProtectionVerify {
	public static void main(String[] args) throws Exception {
		String filePath = "src/mac/my_salary.txt";
		String keyPath = "src/mac/mackey.key";
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入 HR 發佈的 macHexValue: ");
		String macHexValueFromHR = scanner.nextLine();
		SecretKey macKey = KeyUtil.getSecretKeyFromFile("HmacSHA256", keyPath);
		
		// 生成 computedMacHexValue
		String computedMacHexValue = KeyUtil.generateMac("HmacSHA256", macKey, filePath);
		
		// 驗證 HR 發佈 macHexValueFromHR == 自行算出 computedMacHexValue ?
		if(macHexValueFromHR.equals(computedMacHexValue)) {
			System.out.println("驗證成功, 來自於 HR");
			// 讀取檔案內容
			System.out.println(Files.readString(Paths.get(filePath)));
		} else {
			System.out.println("驗證失敗, 來自於 HR");
		}
		
	}
}
