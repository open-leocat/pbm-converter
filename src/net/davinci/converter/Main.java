package net.davinci.converter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {
    public static void main(String[] args) {
    	boolean ditheringEnabled = false;
    	
        if (args.length != 0) {
            for (final String s : args) {
                if (s.equals("-help") || s.equals("-h")) {
                    System.out.print("Help:\n<command> <input> <output>\nFor no output use: -print\nTo enable Dithering use: -dith\n");
                    return;
                } else if (s.equals("-version") || s.equals("-v")) {
                    System.out.println("Leon Hoffmann 2021 PBM-Converter 1.0");
                    return;
                }
                
                if(s.equals("-dith")) {
                	ditheringEnabled = true;
                }
            }
        }
        if (args.length < 2) {
            System.err.println("Too few arguments!");
            return;
        }
        File file = new File(args[0]);
        
        if (!file.exists()) {
            System.err.println("The file doesn't exist!");
            return;
        }
        BufferedImage in = null;
        
        try {
            in = ImageIO.read(new File(args[0]));
        } catch (Exception e) {
            System.err.println("Failed to load image!");
            e.printStackTrace();
        }
        
        String s2 = "";
        
        if(ditheringEnabled) {
        	s2 = Writer.writeDithPBM(in);
        } else {
        	s2 = Writer.writePBM(in);
        }
        
        if (args[1].equals("-print")) {
            System.out.println(s2);
            return;
        }
        
        try {
            Files.writeString(Paths.get(args[1], new String[0]), s2, new OpenOption[0]);
        } catch (IOException e2) {
            System.err.println("Failed to write image!");
            e2.printStackTrace();
        
        }
        
        System.out.println("Successfully wrote image!");
    }
}
