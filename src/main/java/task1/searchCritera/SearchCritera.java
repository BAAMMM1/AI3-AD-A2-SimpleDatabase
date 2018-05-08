package searchCritera;

public class SearchCritera {

    private ColumneName columneName;
    private int start;
    private int end;

    public SearchCritera(ColumneName columne, int start, int end){

        this.columne = columne;
        this.start = start;
        this.end = end;

    }

    public SearchCritera(Columne columne, int value){
        this.(columne, value, value);

    }



}
