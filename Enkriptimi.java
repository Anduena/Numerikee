package Detyrat;

public class Enkriptimi {
	public byte[] m = new byte[26];

	public Enkriptimi() {
		byte j = 0;
		for (byte i = 97; i <= 122; i++)
			m[j++] = i;
	}

	public byte[] Encipher(byte[] message, byte[][] A, byte[] B) {
		byte[] answer = new byte[message.length];
		for (byte i = 0; i < message.length; i += 2) {
			answer[i] = (byte) (m[(A[0][0] * (message[i] - 19) + A[0][1] * (message[i + 1] - 19) + B[0]) % 26]);
			answer[i + 1] = (byte) (m[(A[1][0] * (message[i] - 19) + A[1][1] * (message[i + 1] - 19) + B[1]) % 26]);
		}
		return answer;
	}

	public byte[] Decipher(byte[] message, byte[][] A, byte[] B) {
		byte[] answer = new byte[message.length];
		for (int i = 0; i < message.length; i += 2) {
			answer[i] = (byte) (m[(A[0][0] * (message[i] - B[0] + 7) + A[0][1] * (message[i + 1] - B[1] + 7)) % 26]);
			answer[i + 1] = (byte) (m[(A[1][0] * (message[i] - B[0] + 7) + A[1][1] * (message[i + 1] - B[1] + 7))% 26]);
		}
		return answer;
	}

	public static void main(String args[]) {
		EnkodimiMatricor c = new EnkodimiMatricor();
		// teksi duhet te kete numer qift te karaktereve dhe hapesirat do te
		// dekriptohen si shkonja "n"
		String msg = "enkriptimi";
		byte[][] A = { { 11, 8 }, { 3, 7 } };
		byte[][] I = { { 7, 18 }, { 23, 11 } };
		byte[] B = { 2, 8 };
		byte[] msgArray = msg.getBytes();
		byte[] enc = c.Encipher(msgArray, A, B);
		String enc1 = new String(enc);
		System.out.println("Teksti i enkriptuar: " + enc1);
		byte[] dec = c.Decipher(enc, I, B);
		String dec1 = new String(dec);
		System.out.println("Teksti i dekriptuar: " + dec1);
	}
}

