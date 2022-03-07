// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class YesVarDeclOption extends VarDeclOption {

    private VarDeclOption VarDeclOption;
    private VarOne VarOne;

    public YesVarDeclOption (VarDeclOption VarDeclOption, VarOne VarOne) {
        this.VarDeclOption=VarDeclOption;
        if(VarDeclOption!=null) VarDeclOption.setParent(this);
        this.VarOne=VarOne;
        if(VarOne!=null) VarOne.setParent(this);
    }

    public VarDeclOption getVarDeclOption() {
        return VarDeclOption;
    }

    public void setVarDeclOption(VarDeclOption VarDeclOption) {
        this.VarDeclOption=VarDeclOption;
    }

    public VarOne getVarOne() {
        return VarOne;
    }

    public void setVarOne(VarOne VarOne) {
        this.VarOne=VarOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclOption!=null) VarDeclOption.accept(visitor);
        if(VarOne!=null) VarOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseTopDown(visitor);
        if(VarOne!=null) VarOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclOption!=null) VarDeclOption.traverseBottomUp(visitor);
        if(VarOne!=null) VarOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesVarDeclOption(\n");

        if(VarDeclOption!=null)
            buffer.append(VarDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarOne!=null)
            buffer.append(VarOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesVarDeclOption]");
        return buffer.toString();
    }
}
