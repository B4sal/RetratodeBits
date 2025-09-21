import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;

public class RetratoDeBits {

    private static final String DENSITY_RAMP = "@#S%?*+;:,. ";

    private String imagePath;
    private int asciiWidth;

    public RetratoDeBits(String imagePath, int asciiWidth) {
        this.imagePath = imagePath;
        this.asciiWidth = asciiWidth;
    }

    public void imprimirAscii() {
        try {
            File inputFile = new File(this.imagePath);
            BufferedImage originalImage = ImageIO.read(inputFile);

            if (originalImage == null) {
                System.out.println("Error: No se pudo cargar la imagen. Revisa la ruta: " + this.imagePath);
                return;
            }

            BufferedImage resizedImage = resizeImage(originalImage, this.asciiWidth);
            
            System.out.println(convertToAscii(resizedImage));

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de imagen: " + e.getMessage());
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth) {
        double aspectRatio = (double) originalImage.getHeight() / originalImage.getWidth();
        int newHeight = (int) (newWidth * aspectRatio * 0.55);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }

    private String convertToAscii(BufferedImage image) {
        StringBuilder asciiBuilder = new StringBuilder();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                double brightness = (0.299 * pixelColor.getRed() + 0.587 * pixelColor.getGreen() + 0.114 * pixelColor.getBlue());
                int rampIndex = (int) (brightness / 255 * (DENSITY_RAMP.length() - 1));
                asciiBuilder.append(DENSITY_RAMP.charAt(rampIndex));
            }
            asciiBuilder.append("\n");
        }
        return asciiBuilder.toString();
    }
    
    public static void main(String[] args) {
        // Para usar una imagen personalizada, cambia la ruta: "C:/Users/TuUsuario/Desktop/mi_imagen.png"
        RetratoDeBits miImagen = new RetratoDeBits("Imagenes/foto1.jpg", 100);
        miImagen.imprimirAscii();
    }
}