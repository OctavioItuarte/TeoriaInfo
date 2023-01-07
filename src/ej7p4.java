import java.util.ArrayList;
import java.util.List;

public class ej7p4 {

    private static boolean converge(float prob_ant, float prob_act, float N){
        if(Math.abs(prob_ant - prob_act) < 0.00001 && N>5) {
            System.out.println("converge: " + Math.abs(prob_ant - prob_act));
            return true;
        }
        System.out.println("not converge: "+Math.abs(prob_ant - prob_act));
        return false;
    }
    private static int primera_extraccion(){
        List<Double> acum= new ArrayList<Double>();
        acum.add(0.995);
        acum.add(1.0);
        double x= Math.random();
        System.out.println(x);
        for(int i=0; i<=1; i++) {
            if (x < acum.get(i))
                return i;  //0 facil, 1 complejo
        }
        return -1;

    }

    private static int segunda_extraccion(int f1){
        List<Double> acum= new ArrayList<Double>();
        if(f1==0) {
            acum.add(0.96); //prob de que de negativo si esta sano
            acum.add(1.0); //prob de que de positivo si esta sano
        }
        else {
            acum.add(0.05); // prob de que de negativo si esta enfermo
            acum.add(1.0); //prob de que de positivo si esta enfermo
        }
        double x= Math.random();
        System.out.println(x);
        for(int i=0; i<=1; i++) {
            if (x < acum.get(i))
                return i;  //0 facil, 1 complejo
        }
        return -1;

    }

    public static void main(String[] args) {
        float exitos=0; float N=0;
        float prob_ant=-1; float prob_act=0;
        while (!converge(prob_ant, prob_act, N)){
            int f1= primera_extraccion();
            int f2= segunda_extraccion(f1);
            System.out.println("f1: "+f1);
            System.out.println("f2: " + f2);
            if(f1==0 && f2==0)
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
