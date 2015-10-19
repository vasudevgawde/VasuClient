import java.io.BufferedReader;
import java.io.FileReader;
class Configuration {

        public int row;
        public int col;
        public char grid[][];



        Configuration(int x, int y,char[][] gridTest) {
                this.row = x;
                this.col = y;
                this.grid = gridTest;
                System.out.println("bench");
        }



        public int getLiveCellCountPlayer1() {
                int count = 0;
                for (int i=0; i<row; i++) {
                        for (int j=0; j<col; j++) {
                                if (grid[i][j] == '1')
                                        count++;
                        }
                }
                return count;
        }

        public int getLiveCellCountPlayer2() {
                int count = 0;
                for (int i=0; i<row; i++) {
                        for (int j=0; j<col; j++) {
                                if (grid[i][j] == '2')
                                        count++;
                        }
                }
                return count;
        }

        public int getDeadCellCount() {
                int count = 0;
                for (int i=0; i<row; i++) {
                        for (int j=0; j<col; j++) {
                                if (grid[i][j] == '.')
                                        count++;
                        }
                }
                return count;
        }
}
public class TwoPlayerGameOfLife {

	public static void main(String[] args) {
	Configuration conf = readInputConfigurationFromFile();
	printCurrentConfiguration(conf);
	playUntilSomeoneDies(conf);
	}

	private static void playUntilSomeoneDies(Configuration conf) {
		// the main code goes here with rules
		while (conf.getLiveCellCountPlayer1()>0 && conf.getLiveCellCountPlayer2()>0) {
			// make a copy of grid as all updates are simultaneous
			char gridCopy[][] = new char[conf.row][conf.col];
			
			for (int i=0; i<conf.row; i++)
				for (int j=0; j<conf.col; j++)
					gridCopy[i][j] = conf.grid[i][j];
			
			for (int i=0; i<conf.row; i++) {
				for (int j=0; j<conf.col; j++) {
					char newValue = getUpdatedCellValue(conf, gridCopy, i, j);
					if (newValue != 'X')
						conf.grid[i][j] = newValue;
				}
			}
		}
		
		if (conf.getLiveCellCountPlayer1() > 0) {
			System.out.println("Winner is Player 1");
			System.out.println("Live cells for Player 1: " + conf.getLiveCellCountPlayer1());
		}
		
		if (conf.getLiveCellCountPlayer2() > 0) {
			System.out.println("Winner is Player 2");
			System.out.println("Live cells for Player 2: " + conf.getLiveCellCountPlayer2());
		}
		
		printCurrentConfiguration(conf);
	}

	private static char getUpdatedCellValue(Configuration conf, char[][] gridCopy, int i, int j) {

		int liveNeighbours = 0;
		LiveCells lc = new LiveCells();
		
		if (i<0 || j<0 || i>=conf.row || j>=conf.col)
			return 'X';
		
		//--------------------------------------------------------
		if (i>0) {
			liveNeighbours += isLive(gridCopy[i-1][j], lc) ? 1 : 0;
			if (j > 0)
				liveNeighbours += isLive(gridCopy[i-1][j-1], lc) ? 1 : 0;
			if (j < conf.col-1)
				liveNeighbours += isLive(gridCopy[i-1][j+1], lc) ? 1 : 0;
		}
		if (i < conf.row-1) {
			liveNeighbours += isLive(gridCopy[i+1][j], lc) ? 1 : 0;
			if (j > 0)
				liveNeighbours += isLive(gridCopy[i+1][j-1], lc) ? 1 : 0;
			if (j < conf.col-1)
				liveNeighbours += isLive(gridCopy[i+1][j+1], lc) ? 1 : 0;
		}
		if (j > 0)
			liveNeighbours += isLive(gridCopy[i][j-1], lc) ? 1 : 0;
		if (j < conf.col-1)
			liveNeighbours += isLive(gridCopy[i][j+1], lc) ? 1 : 0;
		//---------------------------------------------------------
		
		// rules
		if (liveNeighbours<2)
			return '.';
		else if (gridCopy[i][j]!='.' && ((liveNeighbours==2) || (liveNeighbours==3)))
			return gridCopy[i][j];
		else if ( gridCopy[i][j]!='.' && liveNeighbours > 3)
			return '.';
		else if ( gridCopy[i][j]=='.' && liveNeighbours == 3)
			return (lc.liveCellsPlayer1 > lc.liveCellsPlayer2) ? '1' : '2';
		
		return 'X';
	}

private static  boolean isLive(char c, LiveCells lc) {
		if (c == '1')
			lc.liveCellsPlayer1++;
		if (c == '2')
			lc.liveCellsPlayer2++;
		return (c=='1' || c=='2') ? true : false;
	}
	
	private static Configuration readInputConfigurationFromFile() 
	{
		
		
		// read the input file and fill the grid here.
		// i am assuming a dummy value for now that is specified in problem statement;
		Configuration conf=null;
		try {
			FileReader reader = new FileReader("MyFile.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
		
            String line="";
            int row=0,column=0;
            char[][] ch=null;
		    int[] grid;
			grid  = new int [2];	   
		    int j= 0,k = 0,t=0;
	    	
            while ((line = bufferedReader.readLine()) != null)
            {
		if(t==0)
		{
            		String[] tempArr= new String[2];
            		tempArr=line.split(" ");
            		row=Integer.parseInt(tempArr[0]);
            		column=Integer.parseInt(tempArr[1]);
            	  	ch= new char[row][column];
			System.out.println("row and column"+row+column);
		}
		else
		{
			t++;
					for(int i=0;i<line.length();i++)
					{
						ch[k][i]=line.charAt(i);
					}
					k++;
		}
	     }
		//Configuration conf = new Configuration(rows, columns);
		conf = new Configuration(row,column,ch);
		
		return conf;
		}
		catch(Exception E)
		{
			System.out.print("Error occurred");
			return null;
		}
		
	}

	private static void printCurrentConfiguration(Configuration conf) {
		System.out.println("The current grid configuration is as follows:"+conf.grid.length);
		
		for (int i=0; i<conf.row; i++) {
			for (int j=0; j<conf.col; j++) {
				System.out.print(conf.grid[i][j] + " ");
			}
			System.out.println();
		}
	}

}
