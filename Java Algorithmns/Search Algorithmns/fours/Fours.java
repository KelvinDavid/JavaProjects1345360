//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//This Program takes in a number 'n' and uses Breadth First Search to
//detemine the shortest mathmatical expression which evaluates to the
//passed in number 'n'

public class Fours
{
    //This class was sourced from the given linked in LAB2 for
    //parsing expressions
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }


    //First in First out queue
    public static class queue
    {
        Node head;
        Node tail;

        private class Node
        {
            Node prev;
            String state;
            public Node(String s)
            {
                state = s;
            }
        }

        //Adds to the tail
        public void add(String s)
        {
            Node nNew = new Node(s);
            if(head == null)
            {
                head = nNew;
                tail = nNew;
                return;
            }
            tail.prev = nNew;
            tail = tail.prev;
            return;
        }

        //remove from head
        public void remove()
        {
            head = head.prev;
            return;
        }

        //prints content of the queue
        public void dump()
        {
            Node cur = head;
            while(cur != null)
            {
                System.out.println(cur.state + " = " + eval(cur.state));
                cur = cur.prev;
            }
        }

        //gets current expression head
        public String getHeadExpression() { return head.state; }
        //get current head's sum value
        public double getHeadValue(){return eval(head.state); }
    }

    public static void main(String[] args)
    {
        //double
        //String state
        //String goal = (state == n)

        //DEBUGING: THIS WAS USED TO TEST 1 - 100 VALUES
//        for(int i=0; i <= 100; i++)
//        {
//            double n = i;
//
//            String start = "4";
//
//            queue Fifo = new queue();
//
//            for(int d=0; d < 7; d++)
//            {
//                Fifo.add(move(d,start));
//            }
//            //Fifo.dump();
//
//            Double value = Fifo.getHeadValue();
//            while(value != n)
//            {
//                //System.out.println("Head: " + Fifo.getHeadExpression() + " = " + Fifo.getHeadValue());
//                String curE = Fifo.getHeadExpression();
//                for(int j=0; j < 7; j++)
//                {
//                    if((isDecimal(curE) && j == 5 ||
//                            curE.substring(curE.length()-1).compareTo(")") == 0 && j == 6 ||
//                            curE.substring(curE.length()-1).compareTo(")") == 0 && j == 4 ||
//                            curE.substring(curE.length()-1).compareTo(")") == 0 && j == 5))
//                    {
//
//                    }
//                    else
//                    {
//                        Fifo.add(move(j, curE));
//                    }
//                }
//                Fifo.remove();
//                value = Fifo.getHeadValue();
//                //Fifo.dump();
//                //System.out.println("--");
//            }
//            System.out.println(Fifo.getHeadExpression() + " = " + Fifo.getHeadValue() + " is found for " + n);
//        }
//
        //Get N from standard input
        double n = Double.parseDouble(args[0]);
        //Starting state
        String start = "4";
        queue Fifo = new queue();

        //Adding the first initial 7 states
        for(int i=0; i < 7; i++)
        {
            Fifo.add(move(i,start));
        }
        //Fifo.dump();

        Double value = Fifo.getHeadValue();
        //Loop until Solution for n is found
        while(value != n)
        {
            System.out.println("Head: " + Fifo.getHeadExpression() + " = " + Fifo.getHeadValue());
            String curE = Fifo.getHeadExpression();
            for(int i=0; i < 7; i++)
            {
                //Catching for errors like ".4.4", "().4", "(())"
                if((isDecimal(curE) && i == 5 ||
                        curE.substring(curE.length()-1).compareTo(")") == 0 && i == 6 ||
                        curE.substring(curE.length()-1).compareTo(")") == 0 && i == 4 ||
                        curE.substring(curE.length()-1).compareTo(")") == 0 && i == 5))
                {

                }
                else
                    {
                        Fifo.add(move(i, curE));
                    }
            }
            //remove head
            Fifo.remove();
            //get next head value
            value = Fifo.getHeadValue();
            //Fifo.dump();
            //System.out.println("--");
        }
        System.out.println("---");
        System.out.println(Fifo.getHeadExpression() + " = " + Fifo.getHeadValue() + " is found for " + n);
   }

//    //Creates concatenation for the Expressions
    public static String move(int index, String s)
    {
        String[] expressions = new String[]{"+4", "-4", "*4", "/4", "4", ".4", "()"};
        if(expressions[index].compareTo("()") == 0)
        {
            return  "(" + s + ")";
        }
        else
            {
                return s + expressions[index];
            }
    }

    //Checks if the last expression was a decimal ".4"
    public static boolean isDecimal(String s)
    {
        for(int i=s.length()-1; i >= 0; i--)
        {
            if(s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/' ||
                    s.charAt(i) == '*' || s.charAt(i) == ')')
            {
                break;
            }
            else if (s.charAt(i) == '.')
            {
                return true;
            }
        }
        return false;
    }
}
