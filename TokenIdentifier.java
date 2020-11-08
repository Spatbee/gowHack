import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TokenIdentifier {

    private static Map<Token, ColorAverage> tokenColorAverageMap;

    private static Map<Token, ColorAverage> getTokenColorAverageMap() throws IOException {
        if (tokenColorAverageMap == null) {
            tokenColorAverageMap = new EnumMap<>(Token.class);
            tokenColorAverageMap.put(Token.BAG, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/bag.png"))));
            tokenColorAverageMap.put(Token.COPPER, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/copper.png"))));
            tokenColorAverageMap.put(Token.EMERALD, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/emerald.png"))));
            tokenColorAverageMap.put(Token.GOLD, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/gold.png"))));
            tokenColorAverageMap.put(Token.RUBY, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/ruby.png"))));
            tokenColorAverageMap.put(Token.SAFE, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/safe.png"))));
            tokenColorAverageMap.put(Token.SILVER, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/silver.png"))));
            tokenColorAverageMap.put(Token.WOOD, getColorAverageWeightedAroundCenter(ImageIO.read(new File("tokens/wood.png"))));
        }
        return tokenColorAverageMap;
    }

    private static ColorAverage getColorAverageWeightedAroundCenter(BufferedImage image) {
        return getColorAverageWeightedAroundCenter(image, 0, 0, image.getWidth(), image.getHeight());
    }

    private static ColorAverage getColorAverageWeightedAroundCenter(BufferedImage image, int xStart, int yStart, int width, int height) {
        double maxConsiderationFromCenter = ((double) Math.min(width, height)) / 2;
        ColorAverage colorAverage = new ColorAverage();
        for (int x = xStart; x < xStart + width; x+=5) {
            for (int y = yStart; y < yStart + height; y+=5) {
                int rbg = image.getRGB(x, y);
                double weight = Math.max(maxConsiderationFromCenter - Math.sqrt(Math.pow(((double) width) / 2 - x + xStart, 2) + Math.pow(((double) height) / 2 - y + yStart, 2)), 0) / maxConsiderationFromCenter;
                colorAverage.addWeightedColorDatum((rbg >> 16) & 0xFF, (rbg >> 8) & 0xFF, rbg & 0xFF, weight);
            }
        }
        return colorAverage;
    }

    public static Token identifyToken(BufferedImage image, int x, int y, int width, int height) throws IOException {
        Token identifiedToken = null;
        Double shortestDistance = Double.MAX_VALUE;
        ColorAverage colorAverage = getColorAverageWeightedAroundCenter(image, x, y, width, height);
        for(Token token : Token.values()) {
            double distance = getTokenColorAverageMap().get(token).getDistanceTo(colorAverage);
            if(distance < shortestDistance) {
                shortestDistance = distance;
                identifiedToken = token;
            }
        }
        //TODO write an exception if the distance is greater than 15, I guess
        return identifiedToken;
    }

}
