package pl.lodz.p.ai.utility.puzzle;

import pl.lodz.p.ai.array.Array2D;
import pl.lodz.p.ai.graph.Node;
import pl.lodz.p.ai.utility.array.ArrayUtils;

public class FifteenPuzzleChecker {
	private static int[][] twoDimSolution ={{1, 2, 3, 4},
									  {5, 6, 7, 8},
									  {9, 10, 11, 12},
									  {13, 14, 15, 0}};

	private static Array2D solution = new Array2D(twoDimSolution);

	public static boolean isSoultion(Node node)
	{
		if(node.getPuzzleState().equals(solution))
		{
			return true;
		}
		return false;
	}
	
	//flag 0 to pusty idzie w gore
	//flag 1 to pusty idzie w prawo
	//flag 2 to pusty idzie w dol
	//flag 3 to pusty idzie w lewo
	public static int[][] createNextState(int[][] currentState, int flag)
	{
		//finding position of 0
		int i = 0,j = 0;
		boolean stop = false;
		for(i = 0; i< 4; i++)
		{
			for(j=0; j<4; j++)
			{
				if (currentState[i][j] == 0)
				{
					stop = true;
					break;
				}
			}
			if(stop)
			{
				break;
			}
		}
		
		int[][] result = ArrayUtils.deepCopy(currentState);
		
		switch(flag)
		{
			case 0:
				if(i==0)
					return null;
				ArrayUtils.swap(result, i, j, i-1, j);
				break;
			case 1:
				if(j==3)
					return null;
				ArrayUtils.swap(result, i, j, i, j+1);
				break;
			case 2:
				if(i==3)
					return null;
				ArrayUtils.swap(result, i, j, i+1, j);
				break;
			case 3:
				if(j==0)
					return null;
				ArrayUtils.swap(result, i, j, i, j-1);
				break;
			default:
					return null;
		}
		
		return result;
		
	}
	
}
