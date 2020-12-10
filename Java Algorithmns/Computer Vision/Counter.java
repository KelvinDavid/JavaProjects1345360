import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ij.*;
import ij.plugin.filter.Filters;
import ij.plugin.filter.GaussianBlur;
import ij.process.AutoThresholder;
import ij.process.ImageProcessor;


//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//This Program takes in a given .jpg image of cells and aims to count the number
//of cells present using a pipeline image process to optimise the viewablility for the
//region detection and counting to be performed. Results of different steps are
//shown at the final GUI output as well as infomation on the cells detected
public class Counter
{
    public static void main(String[] args)
    {
        try
        {
            //Sets up the buffered images for the different parts of the pipeline
            //and displaying the parts of the process
            BufferedImage inImage = ImageIO.read(new File(args[0]));
            BufferedImage outImage = ImageIO.read(new File(args[0]));
            BufferedImage blurImg = ImageIO.read(new File(args[0]));
            BufferedImage piplineEndImg = ImageIO.read(new File(args[0]));
            BufferedImage edgeImg = ImageIO.read(new File(args[0]));
            BufferedImage thresholdImg = ImageIO.read(new File(args[0]));
            BufferedImage dilateImg = ImageIO.read(new File(args[0]));
            BufferedImage output2Img = ImageIO.read(new File(args[0]));
            BufferedImage stg3 = ImageIO.read(new File(args[0]));
            BufferedImage stg2 = ImageIO.read(new File(args[0]));
            //Gets the height and width used to create the 2d array imgArray
            int imgHeight = inImage.getHeight();
            int imgWidth = inImage.getWidth();
            int[][] imgArray = new int[imgHeight][imgWidth];
            System.out.println("height: " + imgHeight + " " + "width: " + imgWidth);

            //Pipeline ===================================================================

            //Uses rescale to adjust the contrast of the image
            RescaleOp rOp = new RescaleOp(5.0f, 0, null);
            //Stores the pipeline
            BufferedImage pipeline = ImageIO.read(new File(args[0]));
            edgeImg = deepCopy(pipeline);
            outImage = deepCopy(pipeline);

            //Sets up the sharpen filter kernel to be used with the ConvolveOp
            float[] sharp = {
                    -1, -1, -1,
                    -1,  9, -1,
                    -1, -1, -1
            };
            //Performs the sharpening operation on the image pipeline
            BufferedImageOp op = new ConvolveOp(new Kernel(3,3, sharp));
            op.filter(pipeline, blurImg);
            //Copys the blur image to the pipeline
            pipeline = deepCopy(blurImg);

            //Average Filter
            //Averages the pixel values to decrease noise in the pipeline
            pipeline = Average(pipeline);
            edgeImg = deepCopy(pipeline);

            //Threshold

            //Creates a Histogram representation of the image
            int[] histogrm2 = computeHistogram(pipeline);
            //Sets up the auto thresholder to determine the treshold for seperating
            //the background from the cells
            AutoThresholder aThresh = new AutoThresholder();
            //Goes through the height and width of image to create a greyscale
            //representation of the intial image
            for(int y=0; y < imgHeight; y++)
            {
                for(int x=0; x < imgWidth; x++)
                {
                  //Reads the current rgb pixel value
                  Color pixel = new Color(pipeline.getRGB(x,y));
                  //Seperates the read pixel value into Red, Green and Blue
                  int r = pixel.getRed();
                  int g = pixel.getGreen();
                  int b = pixel.getBlue();
                  //Sets the pixel value using the weighted intensitys
                  double pVw = (0.2126 * r) + (0.7152*g) + (0.0722*b);
                  imgArray[y][x] = (int)pVw;
                }
            }
            //Finds an auto threshold value using the auto thresholder method
            int threshold = aThresh.getThreshold(AutoThresholder.Method.Huang, histogrm2);
            //Goes through a for loop for the img array length / height
            for(int i=0; i < imgArray.length; i++)
            {
                for(int j = 0; j < imgArray[i].length; j++)
                {
                    //Stores a colour object for setting the RGB colour value
                    Color c;
                    //Reads in the weighted pixel value
                    int sourceP = imgArray[i][j];
                    //Checks if the pixel intensity is greater than or equal to the threshold
                    //meaning it is detected as being a object which should be coloured black
                    if(sourceP >= threshold)
                    {
                        c = Color.BLACK;
                        imgArray[i][j] = c.getRGB();
                    }
                    //Else is bellow the threshold and should be coloured white to represent the
                    //background
                    else
                    {
                        c = Color.WHITE;
                        imgArray[i][j] = c.getRGB();
                    }

                }
            }

            //Goes through and sets the black (0,0,0) and white (255,255,255) values
            //for the x and y cordinates
            for(int y=0; y < imgHeight; y++)
            {
                for(int x=0; x < imgWidth; x++)
                {
                    //Sets the colour value of the pixel at the cordinates of the
                    //pipeline to be the value set in the imageArray
                    pipeline.setRGB(x,y,imgArray[y][x]);
                }
            }
            //Creates a copy of the thesholded image for displaying to the GUI
            //Output
            thresholdImg = deepCopy(pipeline);


            //Re-sharpen

            //Performs ConvolveOp using the sharpening kernel again and assigns
            //to the pipeline
            op = new ConvolveOp(new Kernel(3,3, sharp));
            op.filter(pipeline, stg3);
            pipeline = stg3;


            //Performs dilation and errosion on the image to try seperate the
            //cells which are close to one another
            pipeline = Erosion(pipeline);
            pipeline = Erosion(pipeline);
            pipeline = Erosion(pipeline);
            //Opening
            pipeline = Erosion(pipeline);
            pipeline = Dilate(pipeline);
            //Makes a copy of the erroded / dilated image
            dilateImg = deepCopy(pipeline);
            piplineEndImg = deepCopy(pipeline);

            //Region Counting

            //Creates a table for storing the infomation about the
            //regioning results
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            //Allows for the table to be put into a scrolable UI ellement
            JScrollPane pane = new JScrollPane(table);
            //Sets the columns that the table needs to display for the found cells
            model.addColumn("Label");
            model.addColumn("Area");

            //Region Labeling ================================================================

            //Goes through the height and width of the image
            for(int y=0; y < imgHeight; y++)
            {
                for(int x=0; x < imgWidth; x++)
                {
                  //Reads the current rgb pixel value
                  Color pixel = new Color(pipeline.getRGB(x,y));
                  //Seperates the read pixel value into Red, Green and Blue
                  int r = pixel.getRed();
                  int g = pixel.getGreen();
                  int b = pixel.getBlue();
                  //Sets the pixel value using the weighted intensitys
                  double pVw = (0.2126 * r) + (0.7152*g) + (0.0722*b);
                  imgArray[y][x] = (int)pVw;
                    //Goes through and sets light areas (pixel intensity value
                    //greater than or equal to 100) to be 0 in the image array and
                    //dark areas (pixel intesity less than 100)
                    if(pVw >= 100)
                    {
                        imgArray[y][x] = 0;
                    }
                    else
                    {
                        imgArray[y][x] = 1;
                    }
                }
            }
            //Performs the RegionLabelling methoid using the created imgArray
            imgArray = RegionLabelling(pipeline, imgArray);

            //Relabeling Order

            //Allows for a random colour to be used for the labeling
            Random rand = new Random();
            //Creates the different arrays storing the seen cells and the
            //colours used to display the areas on the final output
            ArrayList<Integer>Seen = new ArrayList<Integer>();
            ArrayList<Integer>Seen2 = new ArrayList<Integer>();
            ArrayList<Integer>PCount = new ArrayList<Integer>();
            ArrayList<Color> PColor = new ArrayList<Color>();
            int label = 1;
            //Goes through the width and the height of the image
            for(int y=0; y < imgHeight; y++)
            {
                for(int x=0; x < imgWidth; x++)
                {
                    //Performs a check to see if the current indexed imgArray value is
                    //not a background and has not already been seen
                    if(imgArray[y][x] != 0 && !Seen.contains(imgArray[y][x]))
                    {
                        //Adds the un-seen label from the image array to the
                        //seen array
                        Seen.add(imgArray[y][x]);
                        //Adds the label to the 2nd seen array
                        Seen2.add(label);
                        //Adds one to the pixel count (For determing if the found
                        //section meets the size criteria to be a cell)
                        PCount.add(1);
                        //Sets a random RGB value to show the cell
                        float r = rand.nextFloat();
                        float g = rand.nextFloat();
                        float b = rand.nextFloat();
                        PColor.add(new Color(r,g,b));
                        //Sets the label to be for the current index
                        imgArray[y][x] = label;
                        //Increments label for the next seen to be a different value
                        label++;
                    }
                    //Otherwise the value has prevously been seen
                    else if(Seen.contains(imgArray[y][x]))
                    {
                        //finds the index of the the current pixel in the seen array
                        int index = Seen.indexOf(imgArray[y][x]);
                        //Sets the pixel to be the label at the index from seen2
                        imgArray[y][x] = Seen2.get(index);
                        //Increment the pixel count of the value
                        PCount.set(index, PCount.get(index)+1);
                    }
                }
            }
            //Initilises the cell count varible
            int count = 0;
            System.out.println("");
            //For each of the labels in the seen2 array
            for(int i=0; i < Seen2.size();i++)
            {
                //Print out the label name
                System.out.print(Seen2.get(i) + ",");
                //Checks that the pixel is greater than the minimum cell size
                //puttting it into the GUI
                if(PCount.get(i) > 50)
                {
                    //Increments count to point at the next value
                    count++;
                    model.addRow(new Object[]{count , PCount.get(i)});
                }
            }
            System.out.println("");

            //Highlighting Images

            //Goes through the height and width of the image
            for(int y=0; y < imgHeight; y++)
            {
                for(int x=0; x < imgWidth; x++)
                {
                    //If the current value indexed in the image array is in seen 2
                    if(Seen2.contains(imgArray[y][x]))
                    {
                        //Find the index and of that result
                        int index = Seen2.indexOf(imgArray[y][x]);
                        //Check the Pixel Count to know if the value is greater
                        //than 50 pixels to eliminate smaller detections from the results
                        if(PCount.get(index) > 50)
                        {
                            //Sets the pixel value to be the PColor indexed colour
                            Color newColor = PColor.get(index);
                            outImage.setRGB(x,y, newColor.getRGB());
                        }
                    }
                }
            }


            //UI Frame =====================================================================
            //Creates an array list of panels for storing ellements of the UI
            ArrayList<JPanel> panels = new ArrayList<JPanel>();
            //Declares the different UI sections
            JFrame frame = new JFrame();
            JPanel panelL = new JPanel();
            JPanel panelC = new JPanel();
            JPanel panelR = new JPanel();
            //Adds the left, right and center panels to the array
            panels.add(panelL);
            panels.add(panelC);
            panels.add(panelR);
            //Sets a border around each of the panels used in the GUI
            for(JPanel jp : panels)
            {
                jp.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
                jp.setLayout(new GridLayout(0,1));
            }

            //Scales the images being shown to fit on the GUI
            int pWidth = inImage.getWidth()/2+65;
            int pHeight = inImage.getHeight()/2+65;
            Image inImageT = inImage.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            Image blurImageT = blurImg.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            Image edgeImageT = edgeImg.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            Image threshImgT = thresholdImg.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            Image dilateImgT = dilateImg.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            Image pipeOutT = piplineEndImg.getScaledInstance(pWidth,pHeight,Image.SCALE_SMOOTH);
            //Puts the images on ellements for the GUI through using JLabels
            JLabel wInImage = new JLabel(new ImageIcon(inImageT));
            JLabel wOutImage = new JLabel(new ImageIcon(outImage));
            JLabel wBlurImage = new JLabel(new ImageIcon(blurImageT));
            JLabel wThreshImage = new JLabel(new ImageIcon(threshImgT));
            JLabel wEdgeImage = new JLabel(new ImageIcon(edgeImageT));
            JLabel wDilateImage = new JLabel(new ImageIcon(dilateImgT));
            JLabel wPipeOutImage = new JLabel(new ImageIcon(pipeOutT));
            //Puts the images, and table on the GUI at the set locations
            panels.get(0).add(wInImage);
            panels.get(0).add(wBlurImage);
            panels.get(0).add(wEdgeImage);
            frame.add(panels.get(0), BorderLayout.WEST);
            panels.get(1).add(pane, GroupLayout.Alignment.CENTER);
            panels.get(1).add(wOutImage);
            frame.add(panels.get(1), BorderLayout.CENTER);
            panels.get(2).add(wThreshImage);
            panels.get(2).add(wDilateImage);
            panels.get(2).add(wPipeOutImage);
            frame.add(panels.get(2), BorderLayout.EAST);
            //Sets the properties of the window
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Cell Count Result:");
            frame.setVisible(true);
            frame.pack();

        }
        //Catch any errors which might happen and print them out to the console
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    //Is a methoid which allows for a image to be passed in and binned by returning
    //int array of values with a reduced number of bins
    public static int[] binnedHistograms(BufferedImage img)
    {
        //Defines the inital number shades for the passed in greyscale image
        int k = 256;
        //Is the end number of bins
        int b = 64;
        //Calulates the factor for determining the bining
        int factor = b / k;
        //Creates a int array for storing the binned histogram
        int[] H = new int[b];
        //Finds the height and the width of the passed in image to go through the
        //loop of reading pixel values
        int w = img.getWidth();
        int h = img.getHeight();
        //For each  indexed pixel value in the height / width of the passed in image
        for(int y=0; y < h; y++)
        {
            for(int x=0; x < w; x++)
            {
                //Get the colour value of the indexed pixel
                Color p = new Color(img.getRGB(x,y));
                //Find the intesity using the weighted values
                double a = (0.2126 * p.getRed()) + (0.7152 * p.getGreen()) + (0.0722*p.getBlue());
                //Calculates the binned intensity using the the calculated factor
                int i = (int)a * factor;
                //Adds one to the indexed bin
                H[i] = H[i] + 1;
            }
        }
        //Returns the calculated histogram
        return  H;
    }

    //Is a method to create a hystogram of grey scale intensity values (0-255)
    //for a passed in image. Returned hystogram is in the form of a int array
    public static int[] computeHistogram(BufferedImage img)
    {
        //Creates a int array with 256 bins for representing the histogram
        int[] H = new int[256];
        //Gets the width and height of the passed in image to determine
        //which cordingates need to be checked
        int w = img.getWidth();
        int h = img.getHeight();

        //Loops through the height and the width of the image
        for(int y=0; y < h; y++)
        {
            for(int x=0; x < w; x++)
            {
                //Reads the colour value and uses the intensity weights to determine
                //what bin needs to be added to
                Color p = new Color(img.getRGB(x,y));
                double i = (0.2126 * p.getRed()) + (0.7152 * p.getGreen()) + (0.0722*p.getBlue());
                //Adds one to the index of the intencity that is pointed at
                H[(int)i] = H[(int)i] + 1;
            }
        }
        //Returns the computed histogram int array
        return H;
    }

    //Is a method used to be able to Erode a passed in image, in order to make
    //the detected cell areas decrease in size
    public static BufferedImage Erosion(BufferedImage img)
    {
        //Assigns the passed in image
        BufferedImage nImg = img;
        //Gets the image Hieght / Width to assign the sizes of the 2d arrays
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        int[][] bImgNew = new int[imgHeight][imgWidth];
        //Reads in the image through looping through the hight and width of the image
        for(int y=0; y < imgHeight; y++)
        {
            for(int x=0; x < imgWidth; x++)
            {
                //Reads the current rgb pixel value
                Color pixel = new Color(img.getRGB(x,y));
                //Seperates the read pixel value into Red, Green and Blue
                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();
                //Sets the pixel value using the weighted intensitys
                double inv = (0.2126 * r) + (0.7152*g) + (0.0722*b);
                //Uses a pre determined threshold to chose if a binned value
                //is either a background (label 1) or a cell (label 0)
                int threshhold = 100;
                if(inv >= threshhold)
                {
                    bImgNew[y][x] = 0;
                }
                else
                {
                    bImgNew[y][x] = 1;
                }
            }
        }
        //Goes through a loop of finding background (label 1) which have areas of
        //cells (label 0) vertical or horizontal of it
        for(int y=0; y < bImgNew.length; y++)
        {
            for(int x=0; x < bImgNew[y].length; x++)
            {
                //If there are surrounding pixels and the pixel is of a cell (label 0)
                if(y < imgHeight-1 && y > 1 && x < imgWidth-1 &&
                        x > 1 && bImgNew[y][x] == 0)
                {
                    //Calculates the cordinates relitive to the pixel
                    int topy = y-1;
                    int boty = y+1;
                    int leftx = x-1;
                    int rightx = x+1;
                    //Checks each of the vertical and horizontal pixels to try
                    //and find one which is not a cell (label 1)
                    if(bImgNew[boty][x] == 1)
                    {
                        bImgNew[boty][x] = 2;
                    }
                    if(bImgNew[y][rightx] == 1)
                    {
                        bImgNew[y][rightx] = 2;
                    }
                    if(bImgNew[topy][x] == 1)
                    {
                        bImgNew[topy][x] = 2;
                    }
                    if(bImgNew[y][leftx] == 1)
                    {
                        bImgNew[y][leftx] = 2;
                    }
                }
            }
        }
        //Go through a loop for each of the int values in the bImgNew array
        //to create a strict black (0,0,0) and white (255,255,255) image of the
        //cells where white is the background (any 0's and 2's) and black is the cells
        //(any 1's)
        for(int y=0; y < imgHeight; y++)
        {
            for(int x=0; x < imgWidth; x++)
            {
                //If value represents the background in the pixel white
                if(bImgNew[y][x] == 0 || bImgNew[y][x] == 2)
                {
                    Color w = Color.WHITE;
                    nImg.setRGB(x,y, w.getRGB());
                }
                //Else value represents a cell so colour black
                else
                {
                    Color w = Color.BLACK;
                    nImg.setRGB(x,y, w.getRGB());
                }
            }
        }
        //After process is complete return the eroded image
        System.out.println("Erosion Done!");
        return nImg;
    }

    //Is a method used to be able to dilate a passed in image, in order to make
    //the detected cell areas increase in size
    public static BufferedImage Dilate(BufferedImage img)
    {
        //Assigns the passed in image
        BufferedImage nImg = img;
        //Gets the image Hieght / Width to assign the sizes of the 2d arrays
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        int[][] bImgNew = new int[imgHeight][imgWidth];

        for(int y=0; y < imgHeight; y++)
        {
            for(int x=0; x < imgWidth; x++)
            {
              //Reads the current rgb pixel value
              Color pixel = new Color(img.getRGB(x,y));
              //Seperates the read pixel value into Red, Green and Blue
                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();
                //Sets the pixel value using the weighted intensitys
                double inv = (0.2126 * r) + (0.7152*g) + (0.0722*b);
                //Uses a pre determined threshold to chose if a binned value
                //is either a background (label 1) or a cell (label 0)
                int threshhold = 100;
                if(inv >= threshhold)
                {
                    bImgNew[y][x] = 0;
                }
                else
                {
                    bImgNew[y][x] = 1;
                }
            }
        }
        //Goes through a loop of finding background (label 1) which have areas of
        //cells (label 0) vertical or horizontal of it
        for(int y=0; y < bImgNew.length; y++)
        {
            for(int x=0; x < bImgNew[y].length; x++)
            {
                //If there are surrounding pixels and the pixel is of background (label 1)
                if(y < imgHeight-1 && y > 1 && x < imgWidth-1 &&
                        x > 1 && bImgNew[y][x] == 1)
                {
                    //Calculates the cordinates relitive to the pixel
                    int topy = y-1;
                    int boty = y+1;
                    int leftx = x-1;
                    int rightx = x+1;
                    //Checks each of the vertical and horizontal pixels to try
                    //and find ones which are not a background (label 0)
                    if(bImgNew[boty][x] == 0)
                    {
                        bImgNew[boty][x] = 2;
                    }
                    if(bImgNew[y][rightx] == 0)
                    {
                        bImgNew[y][rightx] = 2;
                    }
                    if(bImgNew[topy][x] == 0)
                    {
                        bImgNew[topy][x] = 2;
                    }
                    if(bImgNew[y][leftx] == 0)
                    {
                        bImgNew[y][leftx] = 2;
                    }
                }
            }
        }
        //Go through a loop for each of the int values in the bImgNew array
        //to create a strict black (0,0,0) and white (255,255,255) image of the
        //cells where black is the cells (any 1's or 2's) and
        //white is the background (any other numbers)
        for(int y=0; y < imgHeight; y++)
        {
            for(int x=0; x < imgWidth; x++)
            {
                //If value represents a cell colour in the pixel black
                if(bImgNew[y][x] == 1 || bImgNew[y][x] == 2)
                {
                    Color w = Color.BLACK;
                    nImg.setRGB(x,y, w.getRGB());
                }
                //Else value represents the background so colour white
                else
                {
                    Color w = Color.WHITE;
                    nImg.setRGB(x,y, w.getRGB());
                }
            }
        }
        //After completing the process return the Dilated Image
        System.out.println("Dilate Done!");
        return nImg;
    }

    //A method used to be able to make a exact copy of an inputted image in order
    //for it to be edited without the original image being effected
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        //Returns the copied image
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    //Is a methoid which utilises the recursive Flood Fill methoid to identify
    //and label different cells, accepts a 2d array of ints to identify areas
    //where 1's are the the cells and 0's are the background, the
    //returned array uses int's to identify the different areas
    public static int[][] RegionLabelling(BufferedImage img, int[][] imgA)
    {
        //Stores a array of intergers to
        int[][] image = imgA;
        //Sets the initial labeling (2 as 1 is for a unlabeled pixel)
        int label = 2;
        //Goes through the height and width of the image array
        for(int y=0; y < image.length;y++)
        {
            for(int x=0; x < image[y].length; x++)
            {
                //Reads the
                int curPix = image[y][x];
                if(curPix == 1)
                {
                    //Finds the entire location using the recursive flood fill
                    //methoid on the currently indexed pixel and the current label
                    FloodFillR(img, imgA, y, x, label);
                    //Increments the label to difirentiate from the previous label
                    label++;
                }
            }
        }
        //Return the label array
        return image;
    }

    //Is a recursive flood fill method which is used as part of the region detecting find
    //the cells, Takes in a buffered image for determining the height and width, a int
    //array for providing the
    public static void FloodFillR(BufferedImage img ,int[][] imgA, int y, int x, int label)
    {
        //If the current location being pointed at has other surrounding pixels
        //and is labeled as an outline (label 1)
        if(y < img.getHeight() && y > 0 && x < img.getWidth() &&
                x > 0 && imgA[y][x] == 1)
        {
            //Sets the location to be the passed in label int
            imgA[y][x] = label;
            //Calls the recursive flood fill method on all of the horizontal / vertical
            //surrounding pixels
            FloodFillR(img, imgA, y+1, x, label);
            FloodFillR(img, imgA, y, x+1, label);
            FloodFillR(img, imgA, y, x-1, label);
            FloodFillR(img, imgA, y-1, x, label);
        }
        //Return after all possible recursive operations are complete
        return;
    }

    //Processes a passed in image to using a process to reduce
    //noise, returns the processed image when the process is finished
    public static BufferedImage Average(BufferedImage img)
    {
      //Assigns the passed in image
      BufferedImage nImg = img;
      //Gets the image Hieght / Width to assign the sizes of the 2d arrays
      int imgHeight = img.getHeight();
      int imgWidth = img.getWidth();
      int[][] bImgNew = new int[imgHeight][imgWidth];
      Color[][] imageNewC = new Color[imgHeight][imgWidth];
      //Reads in the image through looping through the hight and width of the image
        for(int y=0; y < imgHeight; y++)
        {
            for(int x=0; x < imgWidth; x++)
            {
              //Reads the current rgb pixel value
              Color pixel = new Color(img.getRGB(x,y));
              //Seperates the read pixel value into Red, Green and Blue
              int r = pixel.getRed();
              int g = pixel.getGreen();
              int b = pixel.getBlue();
              //Sets the pixel value using the weighted intensitys
              double pVw = (0.2126 * r) + (0.7152*g) + (0.0722*b);
              bImgNew[y][x] = (int)pVw;
            }
        }

        for(int y=0; y < bImgNew.length; y++)
        {
            for(int x=0; x < bImgNew[y].length; x++)
            {
                if(y < imgHeight-1 && y > 1 && x < imgWidth-1 && x > 1)
                {
                    //Calculates the cordinates relitive to the pixel
                    int topy = y-1;
                    int boty = y+1;
                    int leftx = x-1;
                    int rightx = x+1;
                    //Sums each of the pixel values together
                    int total =
                            bImgNew[y][x] +
                                    bImgNew[boty][x] +
                                    bImgNew[y][rightx] +
                                    bImgNew[topy][x] +
                                    bImgNew[y][leftx] +
                                    bImgNew[boty][leftx] +
                                    bImgNew[boty][rightx] +
                                    bImgNew[topy][leftx] +
                                    bImgNew[topy][rightx];
                    //Divides the total by 9 (number of pixels)
                    int average = total / 9;
                    //Sets the middle pixel to be the average value
                    imageNewC[y][x] = new Color(average,average,average);
                }
            }
        }
        //Goes through each of the average colour values in theimageNewC 2d array
        //and changes the pixel value of the passed in image
        for(int y=2; y < imageNewC.length - 1; y++)
        {

            for(int x=2; x < imageNewC[y].length - 1; x++)
            {
                //Sets the RGB value of the image by getting the RGB value from
                //the colour array
                nImg.setRGB(x,y, imageNewC[y][x].getRGB());
            }
        }
        //Returns the modified image
        System.out.println("Average Done!");
        return nImg;
    }
}
