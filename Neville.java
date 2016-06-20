
public class Neville {
	public static void metoda(double x, double[] X, double[] F){
		double[][] Q = new double[X.length][X.length];
		for(int i = 0; i < Q.length; i++){
			Q[i][0] = F[i];
		}
		for(int i = 0; i < X.length; i++){
			for(int j = 1; j <= i; j++){
				Q[i][j] = ((x-X[i-j])*Q[i][j-1]-(x-X[i])*Q[i-1][j-1])/(X[i]-X[i-j]);
			}
		}
		for(int i = 0; i < Q.length; i++){
			System.out.println();
			for(int j = 0; j < Q.length; j++){
				System.out.print(Q[i][j] + "   ");
			}
		}
	}
	public static void main (String[] args){
		double[] X = {3.0 , 8.0 , 9.0, 6.0, 11.0, 1};
		double[] F = {30.0, 57.0, 64.0, 43.0, 59.0, 20};
		metoda(10.0 , X, F);
		
	}
}
