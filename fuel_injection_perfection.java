import java.math.BigInteger;
import java.util.HashMap;
class Solution{
    private static final BigInteger two = new BigInteger("2");
    private static final BigInteger one = new BigInteger("1");
    private static final HashMap<BigInteger,Integer> hashMap = new HashMap<>();
    {
        hashMap.put(new BigInteger("1"),0);
        hashMap.put(new BigInteger("2"),1);
    }
    public static void main(String  arg[]){
        String string = "15";
        BigInteger bigInteger = new BigInteger(string);
        int steps = calculateStepsForPerfection(bigInteger);
        System.out.println(steps+"");
    }
    public static int calculateStepsForPerfection(BigInteger n){
        if(hashMap.containsKey(n)){
            return hashMap.get(n);
        }
        if(n.longValue()==1){
            return 0;
        }else if(n.longValue()==2){
            return 1;
        }
        if(n.divideAndRemainder(two)[1].intValue()==1){
            //one step for adding or subtracting and one step for division
            hashMap.put(n,2+Math.min(calculateStepsForPerfection(n.add(one).divide(two)),calculateStepsForPerfection(n.subtract(one).divide(two))));
        }else{
            //one step for division
            hashMap.put(n,1+calculateStepsForPerfection(n.divide(two)));
        }
        return hashMap.get(n);
    }
}