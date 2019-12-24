public enum CategoriasMusica {
    Blues(1), Chillout(2), Clássico(3), Country(4), Eletrónica(5), Fado(6), Funk(7), Gospel(8), HardCore(9), HipHop(10), House(11), Infantil(12), Instrumental(13), Jazz(14), K_Pop(15), Kizomba(16), Metal(17), Ópera(18), Pimba(19), Pop(20), Rap(21), Reaggae(22), Reggaeton(23), Rock(24), Soul(25), Trance(26), Tropical(27) ;

    private double idCategoria;

    private CategoriasMusica(int idCategoria){
        this.idCategoria = idCategoria;
    }

    public static String getRandom(){
        return (CategoriasMusica.values()[(int)(Math.random() * values().length)]).toString();
    }
}
