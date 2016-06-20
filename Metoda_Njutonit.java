package Detyrat;

public class Metoda_Njutonit {
	
		public static void main(String[] args) {
			double[] x = { 0.1, 0.1, -0.1 };
			double[] result = newton(3, x, 1E-10, 100);
			System.out.println(result[0] + ",   " + result[1] + ",   " + result[2]);
		}

		public static double[] newton(int n, double[] x, double Tol, int N) {

			int k = 1;
			while (k <= N) {
				System.out.println(x[0] + "    " + x[1] + "   " + x[2]);
				double[][] J = Jakobi(x);
				double[] F = funksioni(x);
				for (int i = 0; i < n; i++) {
					F[i] = -F[i];
				}
				double[] y = prodhimi_m(invert(J), F);
				for (int i = 0; i < n; i++) {
					x[i] = x[i] + y[i];
				}
				if (norma_infinit(y) < Tol) {
					return x;
				} else {
					k++;
				}
			}
			return null;
		}

		public static double[][] Jakobi(double[] x) {
			double[][] result = { { 3.0, x[2] * Math.sin(x[1] * x[2]), x[1] * Math.sin(x[1] * x[2]) },
					{ 2 * x[0], -162 * (x[1] + 0.1), Math.cos(x[2]) },
					{ -x[1] * Math.pow(Math.E, -x[0] * x[1]), -x[0] * Math.pow(Math.E, -x[0] * x[1]), 20.0 } };
			return result;
		}

		public static double[] funksioni(double[] x) {
			double[] result = { (3.0 * x[0] - Math.cos(x[1] * x[2]) - (1.0 / 2)),
					(x[0] * x[0] - 81 * (x[1] + 0.1) * (x[1] + 0.1) + Math.sin(x[2]) + 1.06),
					(Math.pow(Math.E, -x[0] * x[1]) + 20 * x[2] + (10 * Math.PI - 3) / 3.0) };
			return result;
		}

		public static double norma_infinit(double[] x) {
			int n = x.length;
			double max = 0;
			for (int i = 0; i < n; i++) {
				if (max < Math.abs(x[i])) {
					max = Math.abs(x[i]);
				}
			}
			return max;
		}

		public static double[] prodhimi_m(double A[][], double B[]) {
			double result[] = null;
			if (A[0].length == B.length) {
				result = new double[A.length];
				for (int m = 0; m < A.length; m++) {

					double s = 0;
					for (int n = 0; n < B.length; n++) {
						s = s + A[m][n] * B[n];
					}
					result[m] = s;

				}
				return result;
			} else {

				System.out.println("Prodhimi nuk munde te llogaritet");

				return null;
			}

		}
	///////////////metodat per gjetjen e matrices inverze////////////////////////
		public static double[][] invert(double[][] a1) {
			double a[][] = new double[a1.length][a1[0].length];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					a[i][j] = a1[i][j];
				}
			}
			int n = a.length;
			double x[][] = new double[n][n];
			double b[][] = new double[n][n];
			int index[] = new int[n];
			for (int i = 0; i < n; ++i)
				b[i][i] = 1;

			// Transformojme matricen ne trekendesh te siperm
			gaussian(a, index);

			// Update the matrix b[i][j] with the ratios stored
			for (int i = 0; i < n - 1; ++i)
				for (int j = i + 1; j < n; ++j)
					for (int k = 0; k < n; ++k)
						b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

			// performojme ndarjen nnga prapa
			for (int i = 0; i < n; ++i) {
				x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
				for (int j = n - 2; j >= 0; --j) {
					x[j][i] = b[index[j]][i];
					for (int k = j + 1; k < n; ++k) {
						x[j][i] -= a[index[j]][k] * x[k][i];
					}
					x[j][i] /= a[index[j]][j];
				}
			}
			return x;
		}

		public static void gaussian(double a[][], int index[]) {
			int n = index.length;
			double c[] = new double[n];
			for (int i = 0; i < n; ++i)
				index[i] = i;
			for (int i = 0; i < n; ++i) {
				double c1 = 0;
				for (int j = 0; j < n; ++j) {
					double c0 = Math.abs(a[i][j]);
					if (c0 > c1)
						c1 = c0;
				}
				c[i] = c1;
			}

			// Kerkon elementin pivot nga secila kolone
			int k = 0;
			for (int j = 0; j < n - 1; ++j) {
				double pi1 = 0;
				for (int i = j; i < n; ++i) {
					double pi0 = Math.abs(a[index[i]][j]);
					pi0 /= c[index[i]];
					if (pi0 > pi1) {
						pi1 = pi0;
						k = i;
					}
				}

				// Ndrrojme rreshta duke u bazuar ne renditjen e pivotave
				int itmp = index[j];
				index[j] = index[k];
				index[k] = itmp;
				for (int i = j + 1; i < n; ++i) {
					double pj = a[index[i]][j] / a[index[j]][j];
					a[index[i]][j] = pj;

					// Modifikoni elementet tjera
					for (int l = j + 1; l < n; ++l)
						a[index[i]][l] -= pj * a[index[j]][l];
				}
			}
		}
	}

