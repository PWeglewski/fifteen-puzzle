package pl.lodz.p.ai.utility.puzzle;

import java.util.Random;

import pl.lodz.p.ai.puzzle.Direction;
import pl.lodz.p.ai.puzzle.PuzzleState;

public class FifteenPuzzleStateGenerator {
	
	public static PuzzleState generateRandomState(PuzzleState solution ,int i)
	{
		PuzzleState newPuzzleState = solution;
		for(int j = 0; j < i; j++)
		{
			//System.out.println(solution.toString());
			int x = new Random().nextInt(4);
			switch(x)
			{
			case 0:
				newPuzzleState = solution.move(Direction.UP);
				if(newPuzzleState != null)
				{
					solution = newPuzzleState;
				}
				break;
				
			case 1:
				newPuzzleState = solution.move(Direction.RIGHT);
				if(newPuzzleState != null)
				{
					solution = newPuzzleState;
				}
				break;
			case 2:
				newPuzzleState = solution.move(Direction.DOWN);
				if(newPuzzleState != null)
				{
					solution = newPuzzleState;
				}
				break;
			case 3:
				newPuzzleState = solution.move(Direction.LEFT);
				if(newPuzzleState != null)
				{
					solution = newPuzzleState;
				}
				break;
			}
		}
		return solution;
	}
}
