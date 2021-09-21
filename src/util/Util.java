package util;

import java.security.MessageDigest;

public class Util {
	
	// 입력 값을 SHA-256 해시화 하여 해시값 반환
	public static String getHash(String input) {
		StringBuffer result = new StringBuffer(); //스트링버퍼 생성해주고
		
		try {
			// 암호화 같은 경우 자바 개발 도구에 자동으로 포함되어 있는 MessageDigest 이용
			MessageDigest md = MessageDigest.getInstance("SHA-256"); //SHA-256 이용할 것
			// 매개변수(사용자 입력)의 byte를 반환하여 MessageDigest로 해시화 
			md.update(input.getBytes());
			
			// 해시된 결과 값을 byte로 받아오고
			byte bytes[] = md.digest();
			
			
			for(int i = 0; i < bytes.length; i++) {
				result.append(
						// 해시된 값들의 byte의 bit를 256(0xff)의 bit로 and 연산하고 16진수 형태인 문자열로 변환
						Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
