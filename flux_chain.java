import java.util.Arrays;
class test{
	public static void main(String[] arg){
		int q[] = {1,2,3,11,5,6,7};
		int h = 4;
		int[] sol = new int[q.length];
		int itemInFlux = getFluxChainHeight(h);
		for(int i=0;i<q.length;i++){
			sol[i] = getParentConverter(itemInFlux,q[i]);
		}
		System.out.println(Arrays.toString(sol));
	}
	public static int getParentConverter(int totalItemInFlux,int targetNode){
		if(targetNode==totalItemInFlux){
			return -1;
		}
		int previous = 0;
		while(true){
			totalItemInFlux = totalItemInFlux/2;
			int leftNode = previous+totalItemInFlux;
			int rightNode = leftNode + totalItemInFlux;
			if(leftNode==targetNode ||rightNode==targetNode)
				return rightNode+1;
			if(targetNode>leftNode){
				previous = leftNode;
			}
		}
	}

	public static int getFluxChainHeight(int h){
		int itemInFlux = 1;
		while(h!=0){
			itemInFlux = 2*itemInFlux;
			--h;
		}
		return itemInFlux-1;
	}
}