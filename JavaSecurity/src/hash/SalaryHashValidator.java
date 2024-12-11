package hash;

import java.nio.file.Files;
import java.nio.file.Path;

import security.KeyUtil;

public class SalaryHashValidator {
	public static void main(String[] args) throws Exception {
		// 讀檔
		String filePath = "src/hash/my_salary.txt";
		String content = Files.readString(Path.of(filePath));
		System.out.println(content);
		// 原始 hash
		String hashFilePath = "src/hash/my_salary_hash.txt";
		String salaryHash = Files.readString(Path.of(hashFilePath));
		System.out.println("原始:" + salaryHash);
		System.out.println("----------------------------------------");
		// 重新針對 filePath 計算 hash
		String newSalaryHash = KeyUtil.generateFileHash(filePath);
		System.out.println("新的:" + newSalaryHash);
		// 比較 salaryHash.equals(newSalaryHash);
		if(salaryHash.equals(newSalaryHash)) {
			System.out.println("資料正確");
		} else {
			System.err.println("資料有被竄改");
		}
		
	}
}
