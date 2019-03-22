import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import java.util.ArrayList; 

public class DrawBasesTest {	
    public static void main(String[] args) {
		JFrame frame = new JFrame("my frame"); //
		frame.setSize(1500, 1000); // set windows Size 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set exit operation
		frame.setVisible(true); // display windows
		
		ArrayList<int[]> list = new ArrayList<int[]>();  
		int[] firNums = {30, 60, 100, 200}; 
		int[] secNums = {116, 40, 100, 2};
        
		list.add(firNums);
		list.add(secNums);
        
		Color[] a = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
		
		JPanel panel = new JPanel() {      //initialize a canvas
			private static final long serialVersionUID = 1L; // 
			@Override
 			public void paint(Graphics g) {	//rewrite pait method
				super.paint(g);   //
				
				for (int i=0 ;i< list.size(); i++) {
					int[] exampleA = list.get(i);
					int sa = 800 - sum(exampleA) ;
					for (int j=0; j<exampleA.length; j++){
					
						if (j>0){
						    sa = sa + exampleA[j-1];
						}
						g.setColor(a[j]);      //set paint color	
						g.fillRect(5 + i*50,sa,35,exampleA[j]);  //fill rectangle
					}
		        }
				//g.drawLine(80, 200, 50, 50);   //draw line	
				// g.setColor(Color.BLUE);        //set paint color	
				// g.drawOval(10, 10, 30, 20);    //draw Oval circle
			}
		};

		frame.setContentPane(panel);	// add canvas to window
	}
	static int sum(int[] nums){
		int sumN = 0;
		for(int n=0;n<nums.length;n++){
			sumN += nums[n];
		}
		return sumN;
	}
}



