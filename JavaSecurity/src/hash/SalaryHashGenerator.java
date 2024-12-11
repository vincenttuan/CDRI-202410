package hash;

import security.KeyUtil;

// 針對 my_salary.txt 產生 hash
public class SalaryHashGenerator {
	public static void main(String[] args) {
		String filePath = "src/hash/my_salary.txt";
		String salaryHash = KeyUtil.generateFileHash(filePath);
		System.out.println("my_salary.txt 產生 hash: " + salaryHash);
	}
	
}
