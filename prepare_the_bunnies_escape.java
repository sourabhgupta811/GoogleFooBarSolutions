import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
class Solution{
	static class Block { 
    	int i, j; 
    	int wallBefore;
    	public Block(int i, int j, int wallBefore){
    		this.i = i; 
    		this.j = j; 
    		this.wallBefore = wallBefore;     	
    	}

        public boolean equals(Object object) {
	    	if(object instanceof Block){
	            return ((Block)object).i==i && ((Block)object).j==j && ((Block)object).wallBefore==wallBefore;
	        }else{
	        	return super.equals(object);
	        }
        }
    } 
	public static void main(String[] arg){
		int[][] maze = {
				        {0, 1, 1, 0},
				        {0, 0, 0, 1},
				        {1, 1, 0, 0},
				        {1, 1, 1, 0}
				    };
		System.out.println(solution(maze));

	}

    private static int solution(int[][] maze){
        int heightOfMaze = maze.length;
        int widthOfMaze = maze[0].length;
        List<Block> alreadyVisitedBlocks = new ArrayList<>();
        int stepsToReachEnd = 1;
        Queue<Block> path = new LinkedList<>();
        path.add(new Block(0,0,0));
        while(path.peek()!=null){
            int size = path.size();
            for(int i=0;i<size;i++){
                Block block = path.poll();
                if(block.i == heightOfMaze - 1 && block.j == widthOfMaze - 1){
                    return stepsToReachEnd;
                }
                if (alreadyVisitedBlocks.contains(block)){
                    continue;
                }
                if((block.wallBefore & maze[block.i][block.j])==1){
                    continue;
                }
                alreadyVisitedBlocks.add(new Block(block.i, block.j, block.wallBefore | maze[block.i][block.j]));
                if(block.i < heightOfMaze - 1){
                	Block block1 = new Block(block.i+1, block.j, block.wallBefore | maze[block.i][block.j]);
                    path.add(block1);
                }
                if(block.j < widthOfMaze - 1){
                    Block block1 = new Block(block.i, block.j+1, block.wallBefore | maze[block.i][block.j]);
                    path.add(block1);
                }
                if(block.i>0){
                	Block block1 = new Block(block.i-1, block.j, block.wallBefore | maze[block.i][block.j]);
                    path.add(block1);
                }
                if(block.j>0){
                	Block block1 = new Block(block.i, block.j-1, block.wallBefore | maze[block.i][block.j]);
                    path.add(block1);
                }
            }
            stepsToReachEnd+=1;
        }
        return stepsToReachEnd;
    }

    // def answer(maze):
    // h = len(maze)
    // w = len(maze[0])

    // visit = set()
    // step = 1
    // queue = [(0, 0, 0)]
    // while queue:
    //     size = len(queue)
    //     for _ in range(size):
    //         i, j, wallBefore = queue.pop(0)
    //         # Reach the end
    //         if i == h - 1 and j == w - 1:
    //             return step
    //         # The position with status is visited, do nothing
    //         if (i, j, wallBefore) in visit:
    //             continue
    //         # The position is a wall and we have broken the other wall before, do nothing
    //         if wallBefore & maze[i][j]:
    //             continue
    //         # Record the position and status
    //         visit.add((i, j, wallBefore | maze[i][j]))
    //         # Add next positions into the queue
    //         if i < h - 1:
    //             queue.append([i+1, j, wallBefore | maze[i][j]])
    //         if j < w - 1:
    //             queue.append([i, j+1, wallBefore | maze[i][j]])
    //         if i > 0:
    //             queue.append([i-1, j, wallBefore | maze[i][j]])
    //         if j > 0:
    //             queue.append([i, j-1, wallBefore | maze[i][j]])
    //     step += 1
    // return step
}