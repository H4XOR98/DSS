import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private List<Conteudo> playlist;

    public Playlist(){
        this.playlist=new ArrayList<>();
    }


    public void addConteudo(Conteudo conteudo){
        this.playlist.add(conteudo);
    }


    public  void reproduzirPlaylist() throws IOException {
        for(Conteudo c : this.playlist){
            c.reproduzirConteudo();
        }
    }

}

