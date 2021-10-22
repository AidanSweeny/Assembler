//Katie Andre, Aidan Sweeny
import java.util.ArrayList;


public class Postfix{
    private LLStack stk;
    public Postfix(){
        this.stk = new LLStack();
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

    public static void main(String args[]){
        Postfix pf = new Postfix();
        String postfix;
        postfix = pf.inToPost("( ( A + ( B * C ) ) / ( D - E ) )");
        System.out.println("The postfix is: ");
        System.out.println(postfix);
    }
}