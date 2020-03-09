import java.util.*;
class Solution {
    static class Block { 
    	int x, y; 
    	int dis; 
    	public Block(int x, int y, int dis){ 
    		this.x = x; 
    		this.y = y; 
    		this.dis = dis;     	
    	} 	
    } 
    public static void main(String[] arg) {
    	int src=0;
    	 int dest=1;
    	int startPos[] = convertToCoordinates(src); 
    	int endPos[] = convertToCoordinates(dest); 
    	System.out.println(provideMinimumStepToReachTarget(startPos, endPos));
    }

    static boolean isInside(int x, int y) 
    { 
    	if (x >= 1 && x <= 8 && y >= 1 && y <= 8) 
    		return true; 
    	return false; 
    } 
    static int provideMinimumStepToReachTarget(int startPos[], int endPos[]){
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        List<Block> potentialMovements = new ArrayList<>();
        potentialMovements.add(new Block(startPos[0], startPos[1], 0));
        Block currentBlock;
        int xPosAfterMovement, yPosAfterMovement;
        boolean visit[][] = new boolean[9][9];
        for (int i = 1; i <= 8; i++)
            for (int j = 1; j <= 8; j++)
                visit[i][j] = false;
        visit[startPos[0]][startPos[1]] = true;
        while (!potentialMovements.isEmpty())
        {
            currentBlock = potentialMovements.remove(0);
            if (currentBlock.x == endPos[0] && currentBlock.y == endPos[1])
                return currentBlock.dis;
            for (int i = 0; i < 8; i++)
            {
                xPosAfterMovement = currentBlock.x + dx[i];
                yPosAfterMovement = currentBlock.y + dy[i];
                if (isInside(xPosAfterMovement, yPosAfterMovement) && !visit[xPosAfterMovement][yPosAfterMovement])
                {
                    visit[xPosAfterMovement][yPosAfterMovement] = true;
                    potentialMovements.add(new Block(xPosAfterMovement, yPosAfterMovement, currentBlock.dis + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
 
    public static int[] convertToCoordinates(int n){
    	return new int[]{(n % 8) + 1, (n / 8) + 1};
    }
}