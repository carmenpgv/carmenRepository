package base.enums;

public enum GenerosJuegos {
    XXXX("4X"),
    ARENA("Arena"),
    BAZAS("Bazas"),
    COLECCIONSETS("Colección de sets"),
    COLOCACIONLOSETAS("Colocación de losetas"),
    CONQUISTATERRITORIO("Conquista de territorio"),
    CRAWLER("Crawler"),
    CREACIONMAZO("Creación de mazo"),
    DEDUCCIONINVESTIGACION("Deducción e Investigación"),
    DRAFT("Draft"),
    ESCAPERROM("Escape room"),
    EVOLUCIONCIVILIZACION("Evolución de Civilización"),
    EXPLORACIONAVENTURA("Exploración y Aventura"),
    FLIPWRITE("Flip & Write"),
    GESTION("Gestión"),
    PALABRAS("Juegos de palabras"),
    HABILIDAD("Habilidad"),
    LCG("LCG"),
    LEGACY("Legacy"),
    MATEMATICAS("Matemáticas"),
    MEMORIA("Memoria"),
    PICKDELIVER("Pick & Deliver"),
    PREGUNTASYRESPUESTAS("Preguntas y respuestas"),
    PUZZLE("Puzzle"),
    ROLESOCULTOS("Roles ocultos"),
    ROL("Rol"),
    TIRAYMUEVE("Tira y Mueve"),
    WARGAME("Wargame");

    private String valor;

    GenerosJuegos(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }

}
