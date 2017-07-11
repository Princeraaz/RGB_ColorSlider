// 
/*********************************************************/
/* Java Programme: ColorSliderTest.java                  */
/* Written By:    Fiona Delaney                          */
/* Course:  BN509                                        */
/* Language:   Java                                      */
/* Date Started:25/3/2016                                */
/* Last Update: 25/3/2016                                */
/* Programme Description:Main ColorSliderTest file       */
/*********************************************************/
//

import javax.swing.JFrame;

public class ColorSliderTest extends JFrame 

	{
  		public ColorSliderTest() 
  			{
    			getContentPane().add(new ColorSlider());
    			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  				setSize(600, 600);
    			setVisible(true);
  			} // ends method ColorSliderTest

public static void main(String arg[]) 
	{
    	new ColorSliderTest();
  	}// end class ColorSliderTest

} // ends class ColorSliderTest
