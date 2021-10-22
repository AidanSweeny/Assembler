public class Assembler{
    private LLStack stk;
    private Postfix postfix;
    public Assembler(){
        this.postfix = new Postfix();
        this.stk = new LLStack();
    }
    public String Assemble(){
        String[] operator = {"+", "-", "*", "/"};
        String right;
        String left;
        String oper;
        for (int i=0; i < postfix.length(); i++){
            if(operator.contains(this.postfix[i])){
                right = stk.pop();
                left = stk.pop();
                oper = 
                push(oper);
            }
            else{
                push()
            }
        }
        return (String)stk.pop();

    }

    private String evalute(String operator){
        // Return operation 
        // load right
        // do operation to left
        // Return both the temp variable and the string representing the assembly language
        // 
    }

    public static void main(String args[]){
        if (args[1]){
            String filename = args[1];
            // open and read 
        }
         
    }
}