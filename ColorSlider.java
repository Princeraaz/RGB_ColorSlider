// 
/*********************************************************/
/* Java Programme: ColorSlider.java                      */
/* Written By:    Fiona Delaney                          */
/* Course:  BN509                                        */
/* Language:   Java                                      */
/* Date Started:25/3/2016                                */
/* Last Update: 25/3/2016                                */
/* Programme Description: ColorSlider to display         */
/* interactive colorwheel, changes reported in JLabel    */ 
/* and JTextArea                                         */
/*********************************************************/
//
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics;
import java.awt.Dimension;


class ColorSlider extends JPanel 
  {
    // declare components and variables
    JLabel rgbValue = new JLabel("HEX Ref");
    JLabel diamValue = new JLabel("Diameter");
    JTextArea detail = new JTextArea("Dimensions", 3, 45);
    JSlider sliderD, sliderR, sliderG, sliderB;
    Double pi = 3.14;
    Wheel oval = new Wheel();
    JPanel panel = new JPanel();


    public ColorSlider() 
      {
        //setLayout with GridLayout and add JPanel to the NORTH
        panel.setLayout(new GridLayout(6, 1, 16, 16));
        add(panel, BorderLayout.NORTH);

        //call getSlider to define attributes of each slider
        sliderD = getSlider(0, 300, 50, 150, 50);
        sliderR = getSlider(0, 255, 0, 50, 5);
        sliderG = getSlider(0, 255, 0, 50, 5);
        sliderB = getSlider(0, 255, 0, 50, 5);

        // add sliders to panel
        panel.add(sliderD); //add diameter slider
        panel.add(sliderR); //add red value slider
        panel.add(sliderG); //add green value slider
        panel.add(sliderB); //add blue value slider

        // add JLabel to display Hex Ref
        rgbValue.setBackground(Color.white);
        rgbValue.setForeground(Color.black);
        rgbValue.setOpaque(true);
        panel.add(rgbValue);

        // add JLabel to display diameter
        diamValue.setBackground(Color.white);
        diamValue.setForeground(Color.black);
        diamValue.setOpaque(true);
        panel.add(diamValue);

        // add oval to display colourwheel
        add(oval);
        add(detail); // add JTextArea to display dimensions
      } // ends ColorSlider method

      // public class sets all slider attributes
  public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) 
      {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(mjrTkSp);
        slider.setMinorTickSpacing(mnrTkSp);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderListener());
        return slider;
      } // ends getSlider

      // class to define Circle panel
  class Wheel extends JPanel
        {
          //declare variables
        int redValue, greenValue, blueValue;
        private int diam = 64; // default diameter 
        
        // draw and fill circle of specified diameter 
        @Override
        public void paintComponent(Graphics g)
        {
        super.paintComponent(g);
        g.fillOval(32, 32, diam, diam); 
        }
        // validate and set diameter, then repaint 
        public void setDiam(int newDiam)
        {
        // if diameter invalid, default to 64
        diam = (newDiam >= 0 ? newDiam : 64);
        repaint(); // repaint panel
        } 
        // get diameter
        public int getDiam()
          {
            return diam;
          } 
        // get prefered circle size
        public Dimension getPreferredSize()
          {
            return new Dimension(380, 380);
          }
        // set minimum size
        public Dimension getMinimumSize()
          {
            return getPreferredSize();
          } 
        // set foreground colour with red, green and blue values
        public void setForegroundColor() 
          {
            Color color = new Color(redValue, greenValue, blueValue);
            setForeground(color);
          } 
        // get foreground colour and return as Hex Ref
        public String getForegroundColorAsHex() 
          {
            Color color = new Color(redValue, greenValue, blueValue);
            return Integer.toString(color.getRGB() & 0xffffff, 16);
          }
      } // ends class Wheel

  class SliderListener implements ChangeListener 
      {

        public void stateChanged(ChangeEvent e) 
        {
          JSlider slider = (JSlider) e.getSource();

          if (slider == sliderD) {
            oval.diam = slider.getValue();
            displayDiam();
          } else if (slider == sliderR) {
            oval.redValue = slider.getValue();
            displayRGBColor();
          } else if (slider == sliderG) {
            oval.greenValue = slider.getValue();
            displayRGBColor();
          } else if (slider == sliderB) {
            oval.blueValue = slider.getValue();
            displayRGBColor();
          } // ends final if statement of stateChanged Listener

          oval.repaint();

          detail.setText("Diameter:  " + oval.diam + "\n" 
            + "Circumference:  " + oval.diam * pi + "\n"
            + "Area:  " + (oval.diam * oval.diam) * pi);
        } // ends stateChanged


        public void displayRGBColor() 
          {
            oval.setForegroundColor();

            rgbValue.setText("HEX: "+ oval.getForegroundColorAsHex());
          } // ends displayRGBColor

 
        public void displayDiam() 
         {
            oval.setDiam(sliderD.getValue());
            diamValue.setText("Diameter: "+ oval.diam);
          }

      } // ends SliderListener
  } // ends class ColorSlider