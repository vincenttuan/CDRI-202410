<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Hash Compare</title>
	</head>
	<body>
		<form method="post" action="/javaweb/hash/compare">
			<fieldset>
				<legend>Hash Compare</legend>
				Username: <input type="text" name="username" placeholder="請輸入帳號" required /><p />
				Password: <input type="password" name="password" placeholder="請輸入密碼" required /><p />
				<button type="reset">Reset</button>
				<button type="submit">Submit</button>
			</fieldset>
		</form>
	</body>
</html>