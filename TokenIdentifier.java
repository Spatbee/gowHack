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
        double maxConsiderationFromCenter = ((double) Math.min(image.getHeight(), image.getWidth())) / 2;
        ColorAverage colorAverage = new ColorAverage();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rbg = image.getRGB(x, y);
                double weight = Math.max(maxConsiderationFromCenter - Math.sqrt(Math.pow(((double) image.getWidth()) / 2 - x, 2) + Math.pow(((double) image.getHeight()) / 2 - y, 2)), 0) / maxConsiderationFromCenter;
                colorAverage.addWeightedColorDatum((rbg >> 16) & 0xFF, (rbg >> 8) & 0xFF, rbg & 0xFF, weight);
            }
        }
        return colorAverage;
    }

    public static Token identifyToken(BufferedImage image) throws IOException {
        Token identifiedToken = null;
        Double shortestDistance = Double.MAX_VALUE;
        ColorAverage colorAverage = getColorAverageWeightedAroundCenter(image);
        for(Token token : Token.values()) {
            double distance = getTokenColorAverageMap().get(token).getDistanceTo(colorAverage);
            if(distance < shortestDistance) {
                shortestDistance = distance;
                identifiedToken = token;
            }
        }
        System.out.println(String.format("shortest distance: %.2f", shortestDistance));
        return identifiedToken;
    }

}
