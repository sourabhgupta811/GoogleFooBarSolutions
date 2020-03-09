import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Solution{
    //using the mirrorer room approach to solve the problem
    public static List<List<Integer>> getMirroredRoomArray(int[] pos, int[] dimension, int distance){
        List<List<Integer>> mirrorNodeList = new ArrayList<>();
        for(int i=0;i<pos.length;i++){
            List<Integer> singleNode = new ArrayList<>();
            for(int j=-(distance/dimension[i])-1;j<distance/dimension[i]+2;j++){
                singleNode.add(getMirror(j,pos[i],dimension[i]));
            }
            mirrorNodeList.add(singleNode);
        }
        return mirrorNodeList;
    }
    public static void main(String[] a){
        System.out.println(solution(new int[]{3,2},new int[]{2,1},new int[]{1,1},4));
    }

    public static int getMirror(int mirror, int coordinates, int dimensions){
        int mirroredPosition = coordinates;
        int[] mirrorRotation = {2*coordinates,2*(dimensions-coordinates)};
        if(mirror<0){
            for(int i=mirror;i<0;i++){
                mirroredPosition = mirroredPosition-mirrorRotation[Math.abs(((i+1)%2))];
            }
        }else{
            for(int i=mirror;i>0;i--){
                mirroredPosition= mirroredPosition+mirrorRotation[Math.abs(i%2)];
            }
        }
        return mirroredPosition;
    }

    public static int solution(int[] dimension, int[] yourPosition,int[] guardPosition, int distance){
        List<List<List<Integer>>> mirroredRooms = new ArrayList<>();
        mirroredRooms.add(getMirroredRoomArray(yourPosition,dimension,distance));
        mirroredRooms.add(getMirroredRoomArray(guardPosition,dimension,distance));
        Set<Double> differentDirectionToShoot = new HashSet<>();
        HashMap<Double,Double> anglesDistance = new HashMap<>();
        for(int i=0;i<mirroredRooms.size();i++){
            for(int j:mirroredRooms.get(i).get(0)){
                for(int k:mirroredRooms.get(i).get(1)){
                    double beam = Math.atan2(yourPosition[1]-k,yourPosition[0]-j);
                    double beamDistance = Math.sqrt(Math.pow(yourPosition[0]-j,2)+Math.pow(yourPosition[1]-k,2));
                    if(!(j==yourPosition[0] && k==yourPosition[1]) && beamDistance<=distance){
                        if((anglesDistance.containsKey(beam) && anglesDistance.get(beam)>beamDistance) || !anglesDistance.containsKey(beam)){
                            if(i==0){
                                anglesDistance.put(beam,beamDistance);
                            }else{
                                anglesDistance.put(beam,beamDistance);
                                differentDirectionToShoot.add(beam);
                            }
                        }
                    }
                }
            }
        }
        return differentDirectionToShoot.size();
    }
}