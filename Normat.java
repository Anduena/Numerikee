package Detyrat;

import javax.swing.*;
public class Normat {
			// Norma vektoriale l-2
		public static double norma_2(double[] X) {
			double norma = 0;
			for (int i = 0; i < X.length; i++) {
				norma += Math.pow(X[i], 2);
			}
			return Math.sqrt(norma);
		}

		// Norma vektoriale l-infinit
		public static double norma_infinit(double[] X) {
			double max = 0;
			for (int i = 0; i < X.length; i++) {
				if (max < Math.abs(X[i])) {
					max = Math.abs(X[i]);
				}
			}
			return max;
		}

		// Metoda per zbritjen e dy vektoreve A dhe B; (A-B)
		public static double[] zbrit_vektoret(double[] A, double[] B) {
			if (A.length != B.length) {
				System.out.println("Nuk mund te kryhet zbritja");
				return null;
			}
			double[] C = new double[A.length];
			for (int i = 0; i < A.length; i++) {
				C[i] = A[i] - B[i];
			}
			return C;
		}

		// Metoda per mbledhjen e dy vektoreve A dhe B; (A+B)
		public static double[] mbledh_vektoret(double[] A, double[] B) {
			if (A.length != B.length) {
				System.out.println("Nuk mund te kryhet mbledhja");
				return null;
			}
			double[] C = new double[A.length];
			for (int i = 0; i < A.length; i++) {
				C[i] = A[i] + B[i];
			}
			return C;
		}

		// Distanca euklidiane ||A-B||2
		public static double distanca_euklidiane(double[] A, double[] B) {
			return norma_2(zbrit_vektoret(A, B));
		}

		// Distanca maksimale ||A-B||infinit
		public static double distanca_maksimale(double[] A, double[] B) {
			return norma_infinit(zbrit_vektoret(A, B));
		}

		// Norma infinit matricore
		public static double norma_m_infinit(double[][] A) {
			double max = 0;
			for (int i = 0; i < A.length; i++) {
				double temp = 0;
				for (int j = 0; j < A[0].length; j++) {
					temp += Math.abs(A[i][j]);
				}
				if (max < temp) {
					max = temp;
				}
			}
			return max;
		}

		// Norma frobenius
		public static double normaF(double[][] A) {
			double norma = 0;
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[0].length; j++) {
					norma += A[i][j] * A[i][j];
				}
			}
			return Math.sqrt(norma);
		}

		// Metoda per formimin e vektorit
		public static double[] krijo_vektorin() {
			int n = new Integer(JOptionPane.showInputDialog(null, "Shtype numrin e rreshtave te vektorit")).intValue();
			double[] A = new double[n];
			for (int i = 0; i < n; i++) {
				A[i] = new Double(JOptionPane.showInputDialog(null, "Shtype elementin " + (i + 1) + " te vektorit"))
						.doubleValue();
			}
			// Afishimi i vektorit
			for (int i = 0; i < A.length; i++) {
				System.out.println(A[i]);
			}
			System.out.println();
			return A;
		}

		// Metoda per formimin e matrices
		public static double[][] krijo_matricen() {
			int n = new Integer(JOptionPane.showInputDialog(null, "Shtype numrin e rreshtave te matrices")).intValue();
			int m = new Integer(JOptionPane.showInputDialog(null, "Shtype numrin e shtyllave te matrices")).intValue();
			double[][] A = new double[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					A[i][j] = new Double(
							JOptionPane.showInputDialog(null, "Shtype elementin " + (i + 1) + (j + 1) + " te matrices"))
									.doubleValue();
				}
			}
			// Afishimi i matrices
			for (int i = 0; i < A.length; i++) {
				System.out.println();
				for (int j = 0; j < A[0].length; j++) {
					System.out.print(A[i][j] + "   ");
				}
			}
			System.out.println();
			return A;
		}

		public static void main(String[] args) {
			double[] X = krijo_vektorin();
			double[] Y = krijo_vektorin();
			double[][] A = krijo_matricen();
			System.out.println();
			System.out.println("Norma 2 per vektorin X = " + norma_2(X));
			System.out.println("Norma infinit per vektorin X = " + norma_infinit(X));
			System.out.println("Norma e infinit per matricen A = " + norma_m_infinit(A));
			System.out.println("Norma e Frobenius per matricen A = " + normaF(A));
			System.out.println("Distanca maksimale " + distanca_maksimale(X, Y));
			System.out.println("Distanca euklidiane " + distanca_euklidiane(X, Y));
		}
	}


