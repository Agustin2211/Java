package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprite {
    public final int[] pixeles;
    private final int ancho;
    private final int alto;

    public HojaSprite(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho * alto];

        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprite.class.getResource(ruta));

            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
}
