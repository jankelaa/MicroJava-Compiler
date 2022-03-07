// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class VarOne implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private BracketOption BracketOption;

    public VarOne (String I1, BracketOption BracketOption) {
        this.I1=I1;
        this.BracketOption=BracketOption;
        if(BracketOption!=null) BracketOption.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public BracketOption getBracketOption() {
        return BracketOption;
    }

    public void setBracketOption(BracketOption BracketOption) {
        this.BracketOption=BracketOption;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BracketOption!=null) BracketOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BracketOption!=null) BracketOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BracketOption!=null) BracketOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarOne(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(BracketOption!=null)
            buffer.append(BracketOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarOne]");
        return buffer.toString();
    }
}
