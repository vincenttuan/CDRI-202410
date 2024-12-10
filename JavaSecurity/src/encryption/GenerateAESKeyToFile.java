package encryption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

// 將 AESKey 存入到檔案中
public class GenerateAESKeyToFile {
	
	// 建立一個 AES Key(256bits, 32bytes)
	private static final String KEY = "012345678901234567890123456789AB"; // 32 個字
	
	// 金鑰存放位置
	private static final String FKY_FILE_PATH = "aes_key.key";
	
	public static void main(String[] args) throws Exception {
		SecretKeySpec saesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// 保存 key
		saveKeyToFile(saesKeySpec, FKY_FILE_PATH);
		System.out.println("保存 key 成功 !");
	}
	
	private static void saveKeyToFile(SecretKeySpec keySpec, String filePath) throws Exception {
		String keyBase64 = Base64.getEncoder().encodeToString(keySpec.getEncoded());
		Files.writeString(Path.of(filePath), keyBase64, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
}
