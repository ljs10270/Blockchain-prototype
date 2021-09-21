package core;

import java.util.ArrayList;

import util.Util;

public class BlockChainStarter {

	public static void main(String[] args) {
		
		/*
		int nonce = 0; //정답
		
		// 난이도: 2^24, 즉 int(4byte) 6개 = 24byte = 000000(6자리)
		// 채굴 시작
		while(true) {
			// 해시된 값의 앞 6자리가 모두 0이면 정답을 찾은 것, 확률은 대략 1/2^24
			if(Util.getHash(nonce + "").substring(0, 6).equals("000000")) {
				System.out.println("정답: " + nonce);
				System.out.println("해시 값: " + Util.getHash(nonce + ""));
				break;
			}
			nonce++;
		}*/
		
		// 블록1
		Block block1 = new Block(1, null, 0, new ArrayList());
		block1.mine(); //채굴 수행
		block1.getInformation();
		
		// 블록2
		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
		block2.addTransaction(new Transaction("이재선1", "이재선2", 1.5));
		block2.addTransaction(new Transaction("이재선3", "이재선2", 2.1));
		block2.mine(); //채굴 수행
		block2.getInformation();
		
		// 블록3
		Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
		block3.addTransaction(new Transaction("이재선4", "이재선5", 8.5));
		block3.addTransaction(new Transaction("이재선2", "이재선1", 0.4));
		block3.mine(); //채굴 수행
		block3.getInformation();
		
		// 블록4
		Block block4 = new Block(4, block3.getBlockHash(), 0, new ArrayList());
		block4.addTransaction(new Transaction("이재선5", "이재선4", 0.1));
		block4.mine(); //채굴 수행
		block4.getInformation();
		
	}

}
