package base.enums;

public enum ProductosEditoriales {
    TABLERO("Juegos de tablero"),
    CARTAS("Juegos de cartas"),
    WARGAMES("Wargames"),
    MINIATURAS("Juegos de miniaturas"),
    DADOS("Juegos de dados");

    private String valor;

    ProductosEditoriales(String valor) {
        this.valor = valor;
    }
    public String getValor(){
        return valor;
    }
}
