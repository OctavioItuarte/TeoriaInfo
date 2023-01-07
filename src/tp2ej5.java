import java.util.ArrayList;
import java.util.List;

public class tp2ej5 {
    private static int primer_simb(){
        List<Double> acum= new ArrayList<Double>();
        acum.add(0.33);
        acum.add(0.66);
        acum.add(1.0);
        double x= Math.random();
        //System.out.println(x);
        for(int i=0; i<=2; i++) {
            if (x < acum.get(i))
                return i;
        }
        return -1;
    }

    private static boolean converge(double[] prob_ant, double[] prob_act){
        for(int i=0; i<3; i++) {
            if (Math.abs(prob_ant[i] - prob_act[i]) > 0.00001) {
                return false;
            }
        }
        return true;
    }

    private static int sig_random(int s, double[][] m_Acum){
        double x= Math.random();
        //System.out.println(x);
        for(int i=0; i<3; i++)
            if (x < m_Acum[i][s])
                return i;
        return -1;
    }

    private static double [] crear_vt(double [] emisiones, double cant_dias){
        double[] resultado = new double [3];
        for(int i=0; i<3; i++){
            resultado[i]= emisiones[i]/cant_dias;
        }
        return resultado;
    }

    private static double [] calcular_vector_estacionario(double [][] m_Acum){
        double [] emisiones = {0,0,0};
        double [] vt = {0,0,0};
        double [] vt_ant = {-1,0,0};
        double cant_dias=0;
        int s = primer_simb();
        while (!converge(vt, vt_ant) || cant_dias < 10){
            s = sig_random(s, m_Acum);
            emisiones[s]++;
            cant_dias++;
            vt_ant = vt;
            vt = crear_vt(emisiones,cant_dias);
        }
        return vt;
    }

    public static void main(String[] args) {
         double [][] M_Acum = new double[3][3];
         M_Acum[0][0]=0.0; M_Acum[0][1]=0.25; M_Acum[0][2]=0.25;
         M_Acum[1][0]=0.5; M_Acum[1][1]=0.75; M_Acum[1][2]=0.5;
         M_Acum[2][0]=1.0; M_Acum[2][1]=1.0; M_Acum[2][2]=1.0;
         double[] vt= new double[3];
         vt=calcular_vector_estacionario(M_Acum);
         System.out.println("vector resultado");
         for(int i=0;i<3;i++)
            System.out.println(vt[i]);
        //------------------------------------
        double [][] M_Acum2 = new double[3][3];
        M_Acum2[0][0]=0.5; M_Acum2[0][1]=0.33; M_Acum2[0][2]=0.0;
        M_Acum2[1][0]=0.5; M_Acum2[1][1]=0.33; M_Acum2[1][2]=1.0;
        M_Acum2[2][0]=0.0; M_Acum2[2][1]=0.33; M_Acum2[2][2]=0.0;
        double[] vt2 = new double[3];
        vt2[0] = 0.33; vt2[1] = 0.5; vt2[2] = 0.17;
        System.out.println("EJ 4");
        System.out.println("La probabilidad para el simbolo 1 es: " + (vt2[0] * M_Acum2[0][0]));
        System.out.println("La probabilidad para el simbolo 2 es: " + (vt2[1] * M_Acum2[1][1]));
        System.out.println("La probabilidad para el simbolo 3 es: " + (vt2[2] * M_Acum2[2][2]));

    }
}
