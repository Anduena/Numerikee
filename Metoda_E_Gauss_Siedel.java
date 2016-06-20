package Detyrat;

public class Metoda_E_Gauss_Siedel {
	public static double[] GS(double[][] A, double[] x0, double tol, int N) {
		int n = A.length;
		int k = 1;
		while (k <= N) {
			double[] x = new double[n];
			for (int i = 0; i < n; i++) {
				x[i] = (-shuma(0, i - 1, A, x, i) - shuma(i + 1, n - 1, A, x0, i) + A[i][n]) / A[i][i];
			}
			if ((norma_infinit(zbrit_vektoret(x, x0)) / norma_infinit(x)) < tol) {
				return x;
			} else {
				k++;
			}
			System.out.println("k = " + (k - 1));
			for (int i = 0; i < n; i++) {
				System.out.println("X[" + (i + 1) + "] = " + x[i] + "  ");
				x0[i] = x[i];
			}
			System.out.println();
		}
		System.out.println("Metoda nuk arriti perafrimin brenda " + N + " iteracionesh");
		return null;
	}

	public static double shuma(int f, int n, double[][] A, double[] x0, int i) {
		double shuma = 0;
		for (int j = f; j <= n; j++) {
			shuma += A[i][j] * x0[j];
		}
		return shuma;
	}

	public static double[] zbrit_vektoret(double[] A, double[] B) {
		double[] C = new double[A.length];
		for (int i = 0; i < A.length; i++) {
			C[i] = A[i] - B[i];
		}
		return C;
	}

	public static double norma_infinit(double[] x) {
		double max = 0;
		for (int i = 0; i < x.length; i++) {
			if (max < Math.abs(x[i])) {
				max = Math.abs(x[i]);
			}
		}
		return max;
	}

	public static void main(String[] a) {
		double[][] A = { { 10, -1, 2, 0, 6 }, { -1, 11, -1, 3, 25 }, { 2, -1, 10, -1, -11 }, { 0, 3, -1, 8, 15 } };
		double x0[] = { 0, 0, 0, 0 };
		GS(A, x0, 1E-3, 1000);
	}
}
