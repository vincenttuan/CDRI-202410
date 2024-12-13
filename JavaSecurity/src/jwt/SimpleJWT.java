package jwt;

import com.nimbusds.jwt.JWTClaimsSet;

import security.KeyUtil;

public class SimpleJWT {
	public static void main(String[] args) throws Exception {
		// 1. 生成簽名密鑰
		// JWK: 產生簽名用的密鑰(32bytes)
		String signingSecret = KeyUtil.generateSecret(32);
		System.out.println("密鑰:" + signingSecret);
		
		// 2. 創建 JWT 的聲明(claim)
		// JWT: 這是我們要進行簽名的部分(資料主體)
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("iot") // 主題
				.issuer("https://iot.com") // 發行單位
				.claim("action", "open") // 額外的聲明: 開(open)關(close)動作
				.claim("machine", "Air condition") // 額外的聲明: 機器(Air condition, Fan ...)
				.claim("ip", "192.168.1.1") // 額外的聲明: 裝置位置
				.build();
		System.out.println("payload:" + claimsSet);
		
		// 3. 進行簽名(將 claimsSet(資料主體) 進行簽名) 得到 token
		String token = KeyUtil.signJWT(claimsSet, signingSecret);
		System.out.println("Token(JWT):" + token);
		
	}
}
