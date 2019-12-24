public enum FormatosConteudo {
    avi,mp3,mp4,wav,mov,wma;


    public static boolean validaFormato(String formato) {
        for(FormatosConteudo f : values()){
            if(f.name().equals(formato)){
                return true;
            }
        }
        return false;
    }
}