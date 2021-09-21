package core;

import java.util.ArrayList;

import util.Util;

public class Block {
	
	private int blockID;
	private String previousBlockHash; //이전 블록의 해시 값, 이전 해시 값으로 블록들의 연결 여부
	private int nonce; //정답
	private ArrayList<Transaction> transactionList; //블록 데이터(트랜잭션들의 정보)
	
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	
	public Block(int blockID, String previousBlockHash, int nonce, ArrayList transactionList) {
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		this.transactionList = transactionList;
	}
	
	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
	}

	// 블록의 정보 출력 함수
	public void getInformation() {
		System.out.println("--------------------------------------");
		System.out.println("블록 번호: " + getBlockID());
		System.out.println("이전 해시: " + getPreviousBlockHash());
		System.out.println("채굴 변수 값: " + getNonce());
		System.out.println("트랜잭션 개수: " + transactionList.size() + "개");
		
		for(int i = 0; i < transactionList.size(); i++) {
			//transactionList[i].getInformation()
			System.out.println(transactionList.get(i).getInformation());
		}
		
		System.out.println("블록 해시: " + getBlockHash());
		System.out.println("--------------------------------------");
	}
	
	// 이전 블록의 정답과 데이터(트랜잭션), 이전 블록의 해시 값으로 넘어온 현재 블록의 해시 값 리턴
	public String getBlockHash() {
		String transactionInformation = "";
		
		for(int i = 0; i < transactionList.size(); i++) {
			//transactionList[i].getInformation()
			transactionInformation += transactionList.get(i).getInformation();
		}
		
		return Util.getHash(nonce + transactionInformation + previousBlockHash);
	}
	
	// 채굴 수행
	public void mine() {
		// 난이도: 2^16, SHA-256으로 인해 16진수로 해시된 값의 앞 4자리가 0000으로 조건,
		// 16진수는 4bit씩 묶으므로 16진수의 한 자리가 0이 되려면 0000(bit)이 되어야 한다.
		// 0과 1로 구성된 2진수인 bit에서 16진수의 한 자리가 0이 될 확률(경우의 수)는 2^4 = 16
		// 16진수의 총 4자리가 0이 되어야 하므로 2^4*2^4*2^4*2^4 = 2^16 = 65536
		
		// 채굴 시작
		while(true) {
			// 해시된 값의 앞 4자리가 모두 0이면 정답을 찾은 것, 확률은 대략 1/2^16
			if(getBlockHash().substring(0, 4).equals("0000")) {
				System.out.println(blockID + "번째 블록의 채굴을 성공");
				break;
			}
			nonce++;
		}
	}
}
