// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarations extends ConstVarList {

    private ConstVarList ConstVarList;
    private ConstDecl ConstDecl;

    public ConstDeclarations (ConstVarList ConstVarList, ConstDecl ConstDecl) {
        this.ConstVarList=ConstVarList;
        if(ConstVarList!=null) ConstVarList.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstVarList getConstVarList() {
        return ConstVarList;
    }

    public void setConstVarList(ConstVarList ConstVarList) {
        this.ConstVarList=ConstVarList;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarList!=null) ConstVarList.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarList!=null) ConstVarList.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarList!=null) ConstVarList.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarations(\n");

        if(ConstVarList!=null)
            buffer.append(ConstVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarations]");
        return buffer.toString();
    }
}
