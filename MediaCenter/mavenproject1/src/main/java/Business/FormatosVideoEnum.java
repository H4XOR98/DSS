package Business;

public enum FormatosVideoEnum {
    avi, mp4, mov, wma;

    public static boolean validaFormato(String formato) {
        for(FormatosVideoEnum f : values()){
            if(f.name().equals(formato)){
                return true;
            }
        }
        return false;
    }
}
