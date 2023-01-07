import java.util.ArrayList;
import java.util.List;

public class tpespecial {

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

    private static boolean converge(double[][] prob_ant, double[][] prob_act){
        for(int i=0; i<3; i++) {
            for (int j=0; j<5; j++)
                if (Math.abs(prob_ant[i][j] - prob_act[i][j]) > 0.00000001) {
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

    private static double [] crear_vt(double [] emisiones, double cant){
        double[] resultado = new double [3];
        for(int i=0; i<3; i++){
            resultado[i]= emisiones[i]/cant;
        }
        return resultado;
    }
    private static boolean v_converge(double[] prob_ant, double[] prob_act){
        for(int i=0; i<3; i++) {
            if (Math.abs(prob_ant[i] - prob_act[i]) > 0.0001) {
                return false;
            }
        }
        return true;
    }
    private static double [] calcular_vector_estacionario(double [][] m_Acum){
        double [] emisiones = {0,0,0};
        double [] vt = {0,0,0};
        double [] vt_ant = {-1,0,0};
        double cant=0;
        int s = primer_simb();
        while (!v_converge(vt, vt_ant) || cant < 10000){
            s = sig_random(s, m_Acum);
            emisiones[s]++;
            cant++;
            vt_ant = vt;
            vt = crear_vt(emisiones,cant);
        }
        return vt;
    }

    private static void p_recurrenciaCaracteres(double [][] m_Acum) {
        double[][] retornos = new double[3][5];
        double[] total_retornos = new double[3];
        int[] ult_ret = new int[3];
        int t_actual = 0;
        double[][] ant_first_rec = new double[3][5];
        double[][] first_rec = new double[3][5];
        int paso=0;
        for (int i = 0; i < 3; i++) {
            total_retornos[i] = 0;
            ult_ret[i] = -1;
            for (int j = 0; j < 5; j++) {
                retornos[i][j] = 0; first_rec[i][j] = 0; ant_first_rec[i][j]=-1;
            }
        }
        int s = primer_simb();

        while (!converge(first_rec, ant_first_rec) || t_actual < 10) {
            //System.out.println("entro");
            s = sig_random(s, m_Acum);

            if(ult_ret[s]!=-1) {
                paso = t_actual - ult_ret[s] -1;
                ult_ret[s] = t_actual;
                total_retornos[s]++;
                if (paso < 5)
                    retornos[s][paso]++;
                for (int j = 0; j < 5; j++) {
                    ant_first_rec[s][j] =first_rec[s][j];
                    first_rec[s][j] = retornos[s][j] / total_retornos[s];
                }
            }
            else{
                ult_ret[s]=t_actual;
            }
            t_actual++;
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 5; j++)
                System.out.println("posicion: "+ i +" " +  j + ": "+first_rec[i][j]);
    }

    public static void main(String[] args){
        double[][] M_Acum = new double[3][3];
        M_Acum[0][0] = 0.5; M_Acum[0][1] = 0.33; M_Acum[0][2] = 0.0;
        M_Acum[1][0] = 1.0; M_Acum[1][1] = 0.66; M_Acum[1][2] = 1.0;
        M_Acum[2][0] = 1.0; M_Acum[2][1] = 1.00; M_Acum[2][2] = 1.0;
        double[] vt = new double[3];
        p_recurrenciaCaracteres(M_Acum);

        vt = calcular_vector_estacionario(M_Acum);
        System.out.println("vector resultado");
        for (int i = 0; i < 3; i++)
            System.out.println(vt[i]);
    }
}
