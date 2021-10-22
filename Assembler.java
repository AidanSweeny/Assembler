//Katie Andre, Aidan Sweeny
import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Assembler{
    private LLStack stk;
    private Postfix postfix;
    private int temp_num;
    private String file_name;
    public FileWriter fw;

    public Assembler(FileWriter fw){
        this.postfix = new Postfix();
        this.stk = new LLStack();
        this.temp_num = 1;
        this.fw = fw;

    }
    public String assemble(String infix){
        String pf = this.postfix.inToPost(infix);
        String[] characters = pf.split(" ");
        String operators = "+-*/";
        String right;
        String left;
        String oper;
        for (int i=0; i < characters.length; i++){
            if(operators.contains(characters[i])){
                right = (String)stk.pop();
                left = (String)stk.pop();
                oper = this.evaluate(left, right, characters[i]);
                stk.push(oper);
            }
            else{
                stk.push(characters[i]);
            }
        }
        return (String)stk.pop();

    }

    private String evaluate(String left, String right, String operator){
        String temp = "TEMP" + this.temp_num;
        String evaluated = "";
        this.temp_num++;
        if (operator.equals("+")){
             try{    
                evaluated += "LD " + left + "\n";
                evaluated += "AD " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
            System.out.println("Success...");  
        }
        else if (operator.equals("-")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "SB " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
            System.out.println("Success...");  
        }
        else if (operator.equals("*")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "ML " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
            System.out.println("Success...");  
        }
        else if (operator.equals("/")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "DV " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
            System.out.println("Success...");  
        }
        else{
            System.out.print("Not a Valid Operation");
            return null;
        }
        return temp; 
    }

    public static void main(String args[])throws IOException{
        //  Assembler as = new Assembler();
        //  String pf = "( ( AX + ( BY * C ) ) / ( D4 - E ) ) ;";
        //  String sol = as.assemble(pf);
        //  System.out.println(sol);
        try{
            FileWriter fw = new FileWriter("output.txt");
            if (args.length > 0){
                Assembler as = new Assembler(fw);
                Path fileName = Path.of((String)args[0]);
                String pf = Files.readString(fileName);
                fw.write(pf + "\n");
                String sol = as.assemble(pf);
                fw.close();
            }
            else{
                System.out.println("Must provide the filename with the given infix expression");
        }
    }
        catch(Exception e){System.out.println(e);}
        
    }
}