// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class YesConstDeclList extends ConstDeclList {

    private ConstDeclList ConstDeclList;
    private ConstOne ConstOne;

    public YesConstDeclList (ConstDeclList ConstDeclList, ConstOne ConstOne) {
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
        this.ConstOne=ConstOne;
        if(ConstOne!=null) ConstOne.setParent(this);
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public ConstOne getConstOne() {
        return ConstOne;
    }

    public void setConstOne(ConstOne ConstOne) {
        this.ConstOne=ConstOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
        if(ConstOne!=null) ConstOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
        if(ConstOne!=null) ConstOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        if(ConstOne!=null) ConstOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesConstDeclList(\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstOne!=null)
            buffer.append(ConstOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesConstDeclList]");
        return buffer.toString();
    }
}
