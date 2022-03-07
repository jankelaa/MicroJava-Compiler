// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public class VarDeclaration extends VarDecl {

    private Type Type;
    private VarOne VarOne;
    private VarDeclOption VarDeclOption;

    public VarDeclaration (Type Type, VarOne VarOne, VarDeclOption VarDeclOption) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarOne=VarOne;
        if(VarOne!=null) VarOne.setParent(this);
        this.VarDeclOption=VarDeclOption;
        if(VarDeclOption!=null) VarDeclOption.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarOne getVarOne() {
        return VarOne;
    }

    public void setVarOne(VarOne VarOne) {
        this.VarOne=VarOne;
    }

    public VarDeclOption getVarDeclOption() {
        return VarDeclOption;
    }

    public void setVarDeclOption(VarDeclOption VarDeclOption) {
        this.VarDeclOption=VarDeclOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarOne!=null) VarOne.accept(visitor);
        if(VarDeclOption!=null) VarDeclOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarOne!=null) VarOne.traverseTopDown(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarOne!=null) VarOne.traverseBottomUp(visitor);
        if(VarDeclOption!=null) VarDeclOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarOne!=null)
            buffer.append(VarOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOption!=null)
            buffer.append(VarDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclaration]");
        return buffer.toString();
    }
}
