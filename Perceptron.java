
import java.util.Random;
import java.util.Scanner;

class Perceptron {
    double[][] data = new double[8][11]; 
    int gen;
    double[][] fitness;
    double w1, w2, w3;
    double x1, x2, x3;
    double threshold;
    double avgfitness;


    Perceptron(double data[][]) {
        this.data = data;
    }

    public void percept1(Perceptron pp) {
        double y0 = 0.0;
        int y = 0;

        double minw1=0.4;
       double minw2=0.0;
       double minw3=0.2;
       double minth=2.5;

        double maxw1=0.6;
       double maxw2=0.2;
       double maxw3=0.4;
       double maxth=3.0;
        
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            pp.data[i][4] = w1 = minw1+(maxw1-minw1)*random.nextDouble();
            pp.data[i][5] = w2 = minw2+(maxw2-minw2)*random.nextDouble();
            pp.data[i][6] = w3 =minw3+(maxw3-minw3)*random.nextDouble();
            pp.data[i][7] = threshold = minth+(maxth-minth)*random.nextDouble();
        }
        for (int i = 0; i < 8; i++) {
            y0 =pp. data[i][0] * pp.data[i][4] + pp.data[i][1] * pp.data[i][5] + pp.data[i][2] * pp.data[i][6] - pp.data[i][7];
            pp.data[i][8] = y0;
            y = pp.predict(y0);
            pp.data[i][9] = y;
            pp.data[i][10] = pp.fit(y, i);
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        double sum=0;

        for (int i = 0; i < data.length; i++){sum=sum+data[i][10];}
        avgfitness=sum/8;
        System.out.println(avgfitness);
    }

    public void percept2(Perceptron o1,Perceptron o2) {
        double y0 = 0.0;
        int y = 0;

        double[] arr=new double[o1.data.length];
        for(int i=0;i<o1.data.length;i++){
            arr[i]=o1.data[i][10];
        }
       double minw1=o1.data[min(arr)][4];
       double minw2=o1.data[min(arr)][5];
       double minw3=o1.data[min(arr)][6];
       double minth=o1.data[min(arr)][7];

      arr[min(arr)]=arr[min(arr)]+1;

        double maxw1=o1.data[min(arr)][4];
       double maxw2=o1.data[min(arr)][5];
       double maxw3=o1.data[min(arr)][6];
       double maxth=o1.data[min(arr)][7];
       
        
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            o2.data[i][4] = w1 = minw1+(maxw1-minw1)*random.nextDouble();
            o2.data[i][5] = w2 = minw2+(maxw2-minw2)*random.nextDouble();
            o2.data[i][6] = w3 = minw3+(maxw3-minw3)*random.nextDouble();
            o2.data[i][7] = threshold = minth+(maxth-minth)*random.nextDouble() * 3;
        }
        for (int i = 0; i < 8; i++) {
            y0 = o2.data[i][0] * o2.data[i][4] + o2.data[i][1] * o2.data[i][5] + o2.data[i][2] * o2.data[i][6] - o2.data[i][7];
            o2.data[i][8] = y0;
            y =o2. predict(y0);
            o2.data[i][9] = y;
            o2.data[i][10] = o2.fit(y, i);
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int predict(double y0) {
        if (y0 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int  min(double arr[]){
        int min=0;
        for(int i=0;i<arr.length;i++){
            if(arr[min]>arr[i]){
                min =i;
            }
        }
        return min;
    }

    public double fit(int x, int i) {
        return Math.pow((data[i][10] - data[i][4]), 2);
    }

    public static void main(String[] args) {
        double[][] data0 = {
                {0, 0, 0, 1,0,0,0,0,0,0,0},
                {0, 0, 1, 0,0,0,0,0,0,0,0},
                {0, 1, 0, 0,0,0,0,0,0,0,0},
                {0, 1, 1, 0,0,0,0,0,0,0,0},
                {1, 0, 0, 1,0,0,0,0,0,0,0},
                {1, 0, 1, 0,0,0,0,0,0,0,0},
                {1, 1, 0, 1,0,0,0,0,0,0,0},
                {1, 1, 1, 1,0,0,0,0,0,0,0}
        };
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of generations: ");
        int gen = scanner.nextInt();

        double []arr=new double[gen];

        Perceptron []pp=new Perceptron[gen];
        for(int i=0;i<gen;i++){
            pp[i]=new Perceptron(data0);
        }

        for(int i=0;i<gen;i++){
            if(i==0){
                pp[i].percept1(pp[i]);
            }
            else{
                pp[i].percept2(pp[i],pp[i-1]);
            }
            
        }

        double[] arr1=new double[pp[gen-1].data.length];
        for(int i=0;i<pp[gen-1].data.length;i++){
            arr1[i]=pp[gen-1].data[i][10];
        }
        System.out.println("Weight 1   ::>>"+pp[gen-1].data[min(arr1)][4]);
        System.out.println("Weight 2   ::>>"+pp[gen-1].data[min(arr1)][5]);
        System.out.println("Weight 3   ::>>"+pp[gen-1].data[min(arr1)][6]);
        System.out.println("Threshold  ::>>"+pp[gen-1].data[min(arr1)][7]);
        System.out.println("Fitness    ::>>"+pp[gen-1].data[min(arr1)][10]);
    }
}