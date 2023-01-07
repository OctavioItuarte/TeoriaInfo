import java.util.ArrayList;
import java.util.List;

public class ej8 {
    private static boolean converge(float prob_ant, float prob_act, float N){
        if(Math.abs(prob_ant - prob_act) < 0.000001 && N>5) {
            System.out.println("converge: " + Math.abs(prob_ant - prob_act));
            return true;
        }
        System.out.println("not converge: "+Math.abs(prob_ant - prob_act));
        return false;
    }
    private static int primera_extraccion(){
        List<Double> acum= new ArrayList<Double>();
        acum.add(0.05325443787);
        acum.add(0.2662721893);
        acum.add(0.6213017751);
        acum.add(0.9053254438);
        acum.add(1.0);
        double x= Math.random();
        System.out.println(x);
        for(int i=0; i<=4; i++) {
            if (x < acum.get(i))
                return i;
        }
        return -1;

    }

    public static void main(String[] args) {
        float exitos=0; float N=0;
        float prob_ant=-1; float prob_act=0;
        while (!converge(prob_ant, prob_act, N)){
            int f1= primera_extraccion();

            System.out.println("f1: "+f1);
            if(f1==2)
                exitos++;
            N++;
            prob_ant= prob_act;

            prob_act= exitos/N;
        }
        System.out.println(exitos);
        System.out.println(N);
        System.out.println(prob_act);
    }
}
