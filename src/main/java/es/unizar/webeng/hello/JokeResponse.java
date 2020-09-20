package es.unizar.webeng.hello;


public class JokeResponse {
    private int id;
    private String type;
    private String setup;
    private String punchline;

    public JokeResponse() {}
    public JokeResponse(int id, String type, String setup, String punchline){
        this.id = id;
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
    }

    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getSetup(){
        return setup;
    }
    public String getPunchline(){
        return punchline;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setSetup(String setup){
        this.setup = setup;
    }
    public void setPunchline(String punchline){
        this.punchline = punchline;
    }
    public String toString(){
        return "[" + id + "]:(+ " + setup + ", - " + punchline + ")";
    }
}