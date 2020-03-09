class test{
	public static void main(String[] arg){
		int[] array1 = {14,27,1,4,2,50,3,1};
		int[] array2 = {2,4,-4,3,1,1,14,27,50};
		System.out.println(solution(array1,array2)+"");
	}
    private static int solution(int[] x,int[] y){
        sort(x);
        sort(y);
        boolean checkX = true;
        int lengthToLoop = y.length;
        if(x.length<y.length){
            checkX = false;
            lengthToLoop = x.length;
        }
        for(int i=0;i<lengthToLoop;i++){
            if(x[i]!=y[i]){
                if(checkX){
                    return x[i+1]==y[i]?x[i]:y[i];
                }else{
                    if(x[i]==y[i+1]){
                        return y[i];
                    }else{
                        return x[i];
                    }
                }
            }
        }
        return checkX?x[x.length-1]:y[y.length-1];
    }

    public static void sort(int[] values) {
        quicksort(values,0, values.length - 1);
    }

    private static void quicksort(int[] numbers,int low, int high) {
        int i = low, j = high;
        int pivot = numbers[low + (high-low)/2];
        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }
            if (i <= j) {
		        int temp = numbers[i];
		        numbers[i] = numbers[j];
		        numbers[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quicksort(numbers,low, j);
        if (i < high)
            quicksort(numbers,i, high);
    }
}