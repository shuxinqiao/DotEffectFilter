import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.imageio.ImageIO;



public class Demo {

    public static boolean paint(BufferedImage image, int[][] pixels, File output_image){
        //Graphics g = image.getGraphics();

        int width = image.getWidth();
        int height = image.getHeight();

        Graphics g = image.getGraphics();

        g.setColor(new Color(251,217,29));
        g.fillRect(0,0,width,height);

        g.setColor(Color.BLACK);


        int[][] point_weight = new int[width][height];
        int diameter = 8;
        int gap = (int) Math.round(diameter*1.414);

        for(int i = 0; i < width; i += gap) {
            for (int j = 0; j < height; j += gap){

                int sum = 0;
                //System.out.println("loop: "+i+" "+j);

                if (i+diameter <= width && j+diameter <= height) {
                    for (int x = 0; x < diameter; x++) {
                        for (int y = 0; y < diameter; y++) {
                            sum += pixels[x + i][y + j];
                            //System.out.println("x: " + x + "y: " + y);
                        }
                    }


                }else{
                    continue;
                }

                int draw_diameter = diameter - Math.round((sum / (diameter * 265)));
                g.fillOval(i,j, draw_diameter, draw_diameter);
            }
        }

        for(int i = gap/2; i < width; i += gap) {
            for (int j = gap/2; j < height; j += gap){

                int sum = 0;
                //System.out.println("loop: "+i+" "+j);

                if (i+diameter <= width && j+diameter <= height) {
                    for (int x = 0; x < diameter; x++) {
                        for (int y = 0; y < diameter; y++) {
                            sum += pixels[x + i][y + j];
                            //System.out.println("x: " + x + "y: " + y);
                        }
                    }


                }else{
                    continue;
                }

                int draw_diameter = diameter - Math.round((sum / (diameter * 265)));
                g.fillOval(i,j, draw_diameter, draw_diameter);
            }
        }

        //g.fillOval(26,0, 20, 20);
        //g.fillOval(13,13, 20, 20);
        //g.fillOval(0,26, 20, 20);
        g.dispose();
        boolean val = false;
        try {
            val = ImageIO.write(image,"png", output_image);
            System.out.println("passed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;

    }

    public static int[][] array_access(BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        int[][] pixels = new int[width][height];
        int red = 0;
        int green = 0;
        int blue = 0;

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //System.out.println("i:"+i+" j:"+j);
                Color c = new Color(image.getRGB(i, j));
                red = (int)(c.getRed() * 0.299);
                green = (int)(c.getGreen() * 0.587);
                blue = (int)(c.getBlue() * 0.114);
                pixels[i][j] = red + green + blue;
            }
        }

        return pixels;
    }

    public static void main(String[] args) {

        BufferedImage image = null;

        try{
            File input_image = new File("P:\\Java Projects\\Learning Temp\\src\\Test_image.jpg");
            File output_image = new File("P:\\Java Projects\\Learning Temp\\src\\Test_nobel_output.png");

            image = ImageIO.read(input_image);

            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage out_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            System.out.println("Read Succeed");

            int[][] pixels = array_access(image);
            //int[][] pixelds = [[0]];
            //System.out.println(Arrays.toString(pixels));
            boolean boolImageWrite = paint(out_image, pixels, output_image);
            System.out.println(boolImageWrite);

        } catch(IOException e){
            System.out.println("Error: "+e);
        }

    }

}