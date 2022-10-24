import java.io.*;   



class Calculator {
    private final InputStream in;

    private int lookahead;

    public Calculator(InputStream in) throws IOException {
        this.in = in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException, ParseError {
        if (lookahead == symbol)
            lookahead = in.read();
        else
            throw new ParseError();
    }

    private boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c) {
        return c - '0';
    }

    public int eval() throws IOException, ParseError {
        int value = Expr();

        if (lookahead != -1 && lookahead != '\n' && lookahead != 13)
            throw new ParseError();

        return value;
    }

    private int Expr() throws IOException, ParseError {
        int term = Term();
        return Expr2(term);
    }

    private int Expr2(int left) throws IOException, ParseError {
        switch(lookahead) {
            case '^':
                consume(lookahead);
                int term = Term();
                int right = left ^ term;
                return Expr2(right);
            case 13:
            case '\n':
            case ')':
                return left;
        }
        throw new ParseError();
    }

    private int Term() throws IOException, ParseError {
        int factor = Factor();
        return Term2(factor);
    }

    private int Term2(int left) throws IOException, ParseError {
        switch(lookahead) {
            case '&':
                consume(lookahead);
                int factor = Factor();
                int right = left & factor;
                return Term2(right);
            case 13:
            case '\n':
            case '^':
            case ')':
                return left;
        }
        throw new ParseError();
    }

    private int Factor() throws IOException, ParseError {
        if (isDigit(lookahead)) {
            int cond = evalDigit(lookahead);

            consume(lookahead);
            return cond; 
        } else if (lookahead == '(') {
            consume(lookahead);
            int value = Expr();
            if (lookahead == ')')
                consume(lookahead);
            else
                throw new ParseError();
            return value; 
        }
        throw new ParseError();
    }


}
