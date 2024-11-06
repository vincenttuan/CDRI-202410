package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


// 了解各種不同 URL 與參數的傳遞接收
@RestController // 免去撰寫 @ResponseBody, 但若有要回傳 jsp 則不可使用
@RequestMapping("/api") // 統一 URL 前綴
public class ApiController {
	
	/** 
	 * 1.歡迎頁 
	 * 路徑: /welcome
	 * 路徑: /home
	 * 網址: http://localhost:8080/api/welcome
	 * 網址: http://localhost:8080/api/home
	 */
	@GetMapping(value = {"/welcome", "/home"})
	public String welcome() {
		return "Welcome";
	}
	
	/**
	 * 2. ?帶參數
	 * 路徑: /greet?name=John&age=18
	 * 路徑: /greet?name=Mary
	 * 網址: http://localhost:8080/api/greet?name=John&age=18
	 * 結果: Hi John, 18 (成年)
	 * 網址: http://localhost:8080/api/greet?name=Mary
	 * 結果: Hi Mary, 0 (未成年)
	 * 限制: name 參數一定要加, age 參數可不加(若沒有加 age 參數則會給初始值 0)
	 * */
	@GetMapping("/greet")
	public String greet(@RequestParam(value = "name", required = true) String username,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer userage) {
		
		return String.format("Hi %s, %d (%s)", username, userage, userage >= 18?"成年":"未成年");
		
	}
	
	// 3. 上述 2 的精簡寫法
	@GetMapping("/greet2")
	public String greet2(@RequestParam String name,
						 @RequestParam(defaultValue = "0") Integer age) {
		
		return String.format("Hi %s, %d (%s)", name, age, age >= 18?"成年":"未成年");
		
	}
	
	/**
	 * 4. Lab 練習 I
	 * 路徑: /bmi?h=170&w=60
	 * 網址: http://localhost:8080/api/bmi?h=170&w=60
	 * 執行結果: bmi = 20.76
	 * */
	@GetMapping("/bmi")
	public String bmi(@RequestParam Double h, @RequestParam Double w) {
		double bmi = w / Math.pow(h/100, 2);
		//return String.format("bmi = %.2f", bmi);
		return """
				{
				 "bmi": %.2f
				}
				""".formatted(bmi); // 手動拼寫 json 格式
	}
	
	/**
	 * 5. 同名多筆的資料
	 * 路徑: /age?age=17&age=21&age=20
	 * 網址: http://localhost:8080/api/age?age=17&age=21&age=20
	 * 計算出平均年齡
	 */
	//@GetMapping("/age")
	@GetMapping(value = "/age", produces = "text/plain;charset=utf-8")
	public String getAverageOfAge(@RequestParam("age") List<Integer> ages) {
		double avgOfAge = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("平均年齡: %.1f", avgOfAge);
	}
	
	/*
	 * 6. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 網址: http://localhost:8080/api/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數=?、不及格=?
	 * (支援中文字印出) 
	 * 提示: IntSummaryStatistics, Collectors.partitioningBy
	 * */
	
}



