public enum CategoriasVideo {
    Ação(1), Animação(2), Aventura(3), Comédia(4), Documentário(5), Drama(6), Fantasia(7), Ficção(8), Musical(9), Romance(10), Suspense(11), Terror(12);

    private double idCategoria;


    private CategoriasVideo(int idCategoria){
        this.idCategoria = idCategoria;
    }

    public static String getRandom(){
        return (CategoriasVideo.values()[(int)(Math.random() * values().length)]).toString();
    }
}