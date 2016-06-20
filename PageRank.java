package Detyrat;

import javax.swing.*;

	public class PageRank {

		static String a = JOptionPane.showInputDialog("Shkruani numrin e pikave: ");
		static int nr = new Integer(a).intValue();
		static int[][] v = new int[nr][nr];
		static double[][] vT = new double[nr][nr];

		public static int shuma(int x) {
			int rez = 0;
			for (int i = 0; i < nr; i++) {
				rez = rez + v[x][i];
			}
			return rez;
		}

		public static void main(String[] args) {
			String s = JOptionPane.showInputDialog("Nr. i lidhjeve : ");
			int k1 = new Integer(s).intValue();

			for (int i = 0; i < k1; i++) {
				String p1 = JOptionPane.showInputDialog("Lidhni: ");
				String p2 = JOptionPane.showInputDialog("Me: ");
				int t1 = new Integer(p1).intValue();
				int t2 = new Integer(p2).intValue();
				
				v[t1][t2]++;
			}
			for (int i = 0; i < nr; i++) {
				for (int j = 0; j < nr; j++) {
					int l = shuma(i);
					if (l == 0) {
						l = 1;
					}
					vT[i][j] = v[i][j] * (90.0 / l) + 2;
					System.out.print(vT[i][j] + "      ");
					if (j == nr - 1) {
						System.out.println();
					}
				}
			}
		}
	}

