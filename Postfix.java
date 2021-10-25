//Katie Andre, Aidan Sweeny
import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileDescriptor;

public class Postfix{
    private LLStack stk;
    public FileWriter fw;

    public Postfix(FileWriter fw){
        this.stk = new LLStack();
        this.fw = fw;
    }

    public String inToPost(String infix){
        String[] tokens = infix.split(" ");
        int i = 0;
        while (!tokens[i].equals(";")){
            if (tokens[i].equals(")")){
                String right = (String) this.stk.pop();
                String op = (String)this.stk.pop();
                String left = (String)this.stk.pop();
                this.stk.push(left + " "  + right + " " + op);
            }
            else{
                if (!tokens[i].equals("(")){
                    this.stk.push(tokens[i]);
                }
            }
            i++;
        } 
        
        return (String)stk.pop();
    }

    public static void main(String args[])throws IOException{
        /*Postfix pf = new Postfix();
        String postfix;
        String infix = "( ( A + ( B * C ) ) / ( D - E ) ) ; \n ( A + B ) ;";
        String[] split_infix = infix.split("(?<=;)");
        for (int j=0; j < split_infix.length; j++){
            postfix = pf.inToPost(split_infix[j]);
            System.out.println("The postfix is: ");
            System.out.println(postfix);
        }*/
        try{
            if (args.length > 0){
                if (args.length > 1){
                    FileWriter fw = new FileWriter(args[1]);
                    Postfix pf = new Postfix(fw);
                    Path fileName = Path.of((String)args[0]);
                    String infix = Files.readString(fileName);
                    String[] split_infix = infix.split("(?<=;)");
                    String postfix[] = new String[split_infix.length];
                    String post;
                    for (int j=0; j < split_infix.length-1; j++){
                        post = pf.inToPost(split_infix[j]);
                        postfix[j] = post; 
                        fw.write(post + "\n----------------------------------\n");
                    }
                    System.out.println("Output is in file: " + args[1]);
                    fw.close();
                }
                else{
                    FileWriter fw = new FileWriter(FileDescriptor.out); // Outputs to terminal
                    Postfix pf = new Postfix(fw);
                    Path fileName = Path.of((String)args[0]);
                    String infix = Files.readString(fileName);
                    String[] split_infix = infix.split("(?<=;)");
                    String postfix[] = new String[split_infix.length];
                    String post;
                    for (int j=0; j < split_infix.length-1; j++){
                        post = pf.inToPost(split_infix[j]);
                        postfix[j] = post; 
                        fw.write(post + "\n----------------------------------\n");
                    }
                    fw.close();
                }
            }
            else{
                System.out.println("Must provide the filename with the given infix expression");
            }
        }
        catch(Exception e){System.out.println(e);}
      
    }
}