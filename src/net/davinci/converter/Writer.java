package net.davinci.converter;

import java.util.StringJoiner;
import java.awt.image.BufferedImage;

public class Writer {
    public static String writePBM(final BufferedImage image) {
        final StringJoiner buffer = new StringJoiner(" ");
        buffer.add("P1");
        buffer.add(Integer.toString(image.getWidth()));
        buffer.add(Integer.toString(image.getHeight()));
        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                final int clr = image.getRGB(x, y);
                final int r = (clr & 0xFF0000) >> 16;
                final int g = (clr & 0xFF00) >> 8;
                final int b = clr & 0xFF;
                final double mono = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                final int c = (mono < 128.0) ? 1 : 0;
                buffer.add(Integer.toString(c));
            }
        }
        return buffer.toString();
    }

	public static String writeDithPBM(BufferedImage image) {
		BufferedImage dithImage = DitheringUtils.dithering(image);
		
		final StringJoiner buffer = new StringJoiner(" ");
		buffer.add("P1");
		buffer.add(Integer.toString(dithImage.getWidth()));
		buffer.add(Integer.toString(dithImage.getHeight()));
        for (int y = 0; y < dithImage.getHeight(); ++y) {
            for (int x = 0; x < dithImage.getWidth(); ++x) {
                final int clr = dithImage.getRGB(x, y);
                final int r = (clr & 0xFF0000) >> 16;
                final int g = (clr & 0xFF00) >> 8;
                final int b = clr & 0xFF;
                
                if(765==(r+g+b)) {
                	buffer.add(Integer.toString(0));
                } else {
                	buffer.add(Integer.toString(1));
                }
            }
        }
		
		return buffer.toString();
	}
}
