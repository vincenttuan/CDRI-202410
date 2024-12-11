package hash;

import java.nio.file.Files;
import java.nio.file.Path;

import security.KeyUtil;

public class SalaryHashValidator {
	public static void main(String[] args) throws Exception {
		String filePath = "src/hash/my_salary.txt";
		String content = Files.readString(Path.of(filePath));
		System.out.println(content);
		String salaryHash = "4d3910e5b62e48b26033dac3af0dbe9f09f8f4e985cbf07c9f72dfb1412b70ee";
		
		// 重新針對 filePath 計算 hash
		String newSalaryHash = KeyUtil.generateFileHash(filePath);
		// 比較 salaryHash.equals(newSalaryHash);
		if(salaryHash.equals(newSalaryHash)) {
			System.out.println("資料正確");
		} else {
			System.err.println("資料有被竄改");
		}
		
	}
}
