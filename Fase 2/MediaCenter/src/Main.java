

public class Main {

    public static void main(String[] args) {

        try {
            Conteudo c1 = new Conteudo("/Users/lazaropinheiro/Downloads/Bocejo_SONY_Animação.mp4");
            Conteudo c2 = new Conteudo("/Users/lazaropinheiro/Downloads/musica.mp3");
            Playlist playlist = new Playlist();
            playlist.addConteudo(c1);
            playlist.addConteudo(c2);
            playlist.reproduzirPlaylist();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}



















