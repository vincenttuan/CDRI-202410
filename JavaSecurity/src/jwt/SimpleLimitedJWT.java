package jwt;

import java.util.Date;

import com.nimbusds.jwt.JWTClaimsSet;

import security.KeyUtil;

/**
 * SimpleLimitedJWT 示例。
 * 
 * 這個類別展示如何創建具有有限時效性（即過期時間）的 JWT。當一個 JWT 有設定過期時間，
 * 它在該時間點後就不應再被接受或認為是有效的。
 * 
 * 主要流程如下：
 * 1. 生成一個簽名密鑰。
 * 2. 定義 JWT 的聲明 (claims)，其中包括令牌的主題、發行者、一些額外的資料，以及重要的過期時間。
 * 3. 使用密鑰對 JWT 進行簽名。
 * 4. 模擬讓令牌過期的情境（在這裡是等待 11 秒）。
 * 5. 嘗試驗證令牌的簽名以及檢查它是否已過期。
 * 
 * 這個範例展示了 JWT 的一個常見應用場景：給予令牌一定的生命週期，並在它過期後拒絕其存取權限。
 * 
 */
public class SimpleLimitedJWT {
	public static void main(String[] args) throws Exception {
		// 有效期間(10秒)
		Date expirationTime = new Date(new Date().getTime() + 10_000); // 現在時刻 + 10 秒
		
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
				.expirationTime(expirationTime) // 設定有效期間
				.build();
		System.out.println("payload:" + claimsSet);
		
		// 3. 進行簽名(將 claimsSet(資料主體) 進行簽名) 得到 token
		String token = KeyUtil.signJWT(claimsSet, signingSecret);
		System.out.println("Token(JWT):" + token);
		
		
		
		// 4. 驗證 token(JWT)
		if(KeyUtil.verifyJWTSignature(token, signingSecret)) {
			System.out.println("驗證成功");
			// 讀取 token 中的 payload 資料
			JWTClaimsSet claims = KeyUtil.getClaimsFromToken(token);
			System.out.println("讀取 subject:" + claims.getSubject());
			System.out.println("讀取 issure:" + claims.getIssuer());
			System.out.println("讀取 action:" + claims.getStringClaim("action"));
			System.out.println("讀取 machine:" + claims.getStringClaim("machine"));
			System.out.println("讀取 ip:" + claims.getStringClaim("ip"));
			
		} else {
			System.out.println("驗證失敗");
		}
	}
}
