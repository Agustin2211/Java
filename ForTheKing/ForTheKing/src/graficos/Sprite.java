package graficos;

public final class Sprite {
    private final int tamanio;

    private int x;
    private int y;

    public int[] pixeles;
    private final HojaSprite hoja;

    public Sprite(int tamanio, int columna, int fila, int[] pixeles, HojaSprite hoja) {
        this.tamanio = tamanio;
        this.x = columna;
        this.y = fila;
        pixeles = new int[tamanio * tamanio];
        this.hoja = hoja;

        for(int y = 0; y < tamanio; y++){
            for(int x = 0; x < tamanio; x++){
                pixeles[x + (y * tamanio)] = hoja.pixeles[(x + this.x) + ((y + this.y) * hoja.getAncho())];
            }
        }
    }
}
