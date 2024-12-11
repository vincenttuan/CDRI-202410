package hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import security.KeyUtil;

// 針對 my_salary.txt 產生 hash
public class SalaryHashGenerator {
	public static void main(String[] args) throws IOException {
		String filePath = "src/hash/my_salary.txt";
		String salaryHash = KeyUtil.generateFileHash(filePath);
		System.out.println("my_salary.txt 產生 hash: " + salaryHash);
		// 將 hash 存檔
		String hashFilePath = "src/hash/my_salary_hash.txt";
		Files.writeString(Path.of(hashFilePath), salaryHash, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
}
