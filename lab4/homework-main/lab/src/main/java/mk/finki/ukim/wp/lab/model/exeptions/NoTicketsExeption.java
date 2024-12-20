package mk.finki.ukim.wp.lab.model.exeptions;

public class NoTicketsExeption extends RuntimeException{
    public  NoTicketsExeption(){
        super("We dont have any more tickets");
    }
}
