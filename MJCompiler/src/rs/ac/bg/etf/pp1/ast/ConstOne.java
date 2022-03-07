// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class ConstOne implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private ConstDeclType ConstDeclType;

    public ConstOne (String I1, ConstDeclType ConstDeclType) {
        this.I1=I1;
        this.ConstDeclType=ConstDeclType;
        if(ConstDeclType!=null) ConstDeclType.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ConstDeclType getConstDeclType() {
        return ConstDeclType;
    }

    public void setConstDeclType(ConstDeclType ConstDeclType) {
        this.ConstDeclType=ConstDeclType;
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
        if(ConstDeclType!=null) ConstDeclType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclType!=null) ConstDeclType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclType!=null) ConstDeclType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstOne(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ConstDeclType!=null)
            buffer.append(ConstDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstOne]");
        return buffer.toString();
    }
}
