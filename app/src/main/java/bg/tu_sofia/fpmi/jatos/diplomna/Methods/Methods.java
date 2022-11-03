package bg.tu_sofia.fpmi.jatos.diplomna.Methods;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.Terrain;

public class Methods {


    static  public  void eff(Terrain terrain){
        effTR(terrain);
        effWa(terrain);
        effWi(terrain);
    }
    static  public  void effTR(Terrain terrain){
        double[] gravityArray = gravity(terrain.getTemps().length);
        double tempE=matrixMultiplication(terrain.getTemps(),gravityArray);
        double rapdE=matrixMultiplication(terrain.getRadiations(),gravityArray);
        double e2 = (tempE+rapdE)/2;
        double e1 = e2-e2*terrain.getProbabilitie(0);
        double e3 = e2+e2*terrain.getProbabilitie(2);
        terrain.setEff(e1,0,0);
        terrain.setEff(e2,0,1);
        terrain.setEff(e3,0,2);
    }
    static  public  void effWa(Terrain terrain){
        double[] gravityArray = gravity(terrain.getWater().length);
        double e2 = matrixMultiplication(terrain.getWater(),gravityArray);
        double e1 = e2-e2*terrain.getProbabilitie(0);
        double e3 = e2+e2*terrain.getProbabilitie(2);
        terrain.setEff(e1,1,0);
        terrain.setEff(e2,1,1);
        terrain.setEff(e3,1,2);
    }
    static  public  void effWi(Terrain terrain){
        double[] gravityArray = gravity(terrain.getWind().length);
        double e2 = matrixMultiplication(terrain.getWind(),gravityArray);

        double e1 = e2-e2*terrain.getProbabilitie(0);
        double e3 = e2+e2*terrain.getProbabilitie(2);
        terrain.setEff(e1,2,0);
        terrain.setEff(e2,2,1);
        terrain.setEff(e3,2,2);
    }
    static public int sumCosts(int[] cost, int[] index, double[] efficiency, double[]  maxEfficiency){
        int sum = 0;
        for (int i = 0; i < cost.length; i++){
            if (index[i] == 1){
                int p = i + 1;
                int d = cost[i];
                double f = efficiency[i];
                sum += cost[i];
                maxEfficiency[0] += efficiency[i];
            }
        }
        return sum;
    }
    static public double[] gravity(int count){
        double sum = 0;
        double[] gravityArray = new double[count];
        for (int i = 1; i <= count; i++){
            sum += i;
        }
        for (int i = 1; i <= count; i++){
            gravityArray[i - 1] = i / sum;
        }
        return gravityArray;
    }
    static public double matrixMultiplication(double[] a, double[] b){
        double result =0;

        for (int i = 0; i < a.length; i++){
             result += a[i]*b[i];
        }
        return result;
    }
    static public double[] matrixMultiplication(double[][] a, double[] b)
    {
        double[] result = new double[a.length];

        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[0].length; j++)
            {
                result[i] += a[i][j] * b[j];
            }
        }

        return result;
    }
    static public void knapSack(int capital, int[] cost, double[] efficienc, int[] index, int terrainCount){
        double[] effic = new double[efficienc.length];
        long[] efficiency = new long[efficienc.length];
        for (int l = 0; l < efficienc.length; l++){
            effic[l] = efficienc[l];
        }
        for (int f = 0; f < efficienc.length; f++){
            double prop = effic[f];
            effic[f] = prop * 100000;
            if (effic[f] < 0){
                effic[f] = 0;
            }
        }
        for (int f = 0; f < efficienc.length; f++){
            efficiency[f] = (long) effic[f];
        }
        int n = efficiency.length;

        int i;

        int w;

        for (int p = 0; p < terrainCount; p++){
            index[p] = 0;
        }
        long[][] K = new long[n + 1][ capital + 1];

        for (i = 0; i <= n; i++){
            for (w = 0; w <= capital; w++){
                if (i == 0 || w == 0){
                    K[i][w] = 0;
                }
                else if (cost[i - 1] <= w){
                    K[i][w] = Math.max(efficiency[i - 1] + K[i - 1][w - cost[i - 1]], K[i - 1][w]);
                }
                else{
                    K[i][w] = K[i - 1][w];
                }
            }
        }

        long res = K[n][capital];

        w = capital;

        for (i = n; i > 0 && res > 0; i--){
            if (res == K[i - 1][w])
                continue;
            else{
                index[i - 1] = 1;
                res = res - efficiency[i - 1];
                w = w - cost[i - 1];
            }
        }
    }
    public static void MaximumExpectedProfit(Terrain terrain){
        double[] arrAxP =  matrixMultiplication(terrain.getEfficiency(),terrain.getProbabilities());

        double[] max=new double[1];
        int[] indexV=new int[1];

        maxArrValue2(arrAxP,max,indexV);

        terrain.setOptimalEff(max[0]);
        terrain.setOptimalCost(terrain.getCost(indexV[0]));
        terrain.setIndex(indexV[0]);
    }
    public static void MaximumProfitInTheMostProbableCondition(Terrain terrain){
        int[] index=new int[1];
        double[] maxP=new double[1];
        int[] indexV=new int[2];
        double[] max=new double[1];
        maxArrValue2(terrain.getProbabilities(),maxP,index);
        maxArrValue3(terrain.getEfficiency(),max,indexV,index);

        terrain.setOptimalEff(max[0]);
        terrain.setOptimalCost(terrain.getCost(indexV[0]));
        terrain.setIndex(indexV[0]);
    }
    public static void MaximumGuaranteedProfit(Terrain terrain){
        double[] arrMin=new double[3];
        int[] index=new int[3];
        double[] max=new double[1];
        int[] indexV=new int[2];
        minArrValue2d(terrain.getEfficiency(),arrMin,index);
        maxArrValue(arrMin,max,index,indexV);
        terrain.setOptimalEff(max[0]);
        terrain.setOptimalCost(terrain.getCost(indexV[0]));
        terrain.setIndex(indexV[0]);
    }
    public static void minArrValue2d(double[][]arr, double[] arrMin,int[] index){
        double min=0;
        int indexV=0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                if(j==0){
                    min =arr[i][j];
                    indexV=j;
                }else if(min>=arr[i][j]){
                    min =arr[i][j];
                    indexV=j;
                }
            }
            arrMin[i]=min;
            index[i]=indexV;
        }
    }
    public static void maxArrValue(double[]arr, double[] max,int[] index,int[] indexV){
        for (int i = 0; i < arr.length; i++){
            if (i==0){
                max[0]=arr[i];
                indexV[0]=i;
                indexV[1]=index[i];
            }
            else if (max[0]<=arr[i]){
                max[0]=arr[i];
                indexV[0]=i;
                indexV[1]=index[i];
            }
        }
    }
    public static void maxArrValue2(double[]arr, double[] max,int[] index){
        for (int i = 0; i < arr.length; i++){
            if (i==0){
                max[0]=arr[i];
                index[0]=i;
            }
            else if (max[0]<=arr[i]){
                max[0]=arr[i];
                index[0]=i;
            }
        }
    }
    public static void maxArrValue3(double[][]arr, double[] max,int[] indexV,int[] index){
        for (int i = 0; i < arr.length; i++){
            if (i==0){
                max[0]=arr[i][index[0]];
                indexV[0]=i;
                indexV[1]=index[0];
            }
            else if (max[0]<=arr[i][index[0]]){
                max[0]=arr[i][index[0]];
                indexV[0]=i;
            }
        }
    }
}
