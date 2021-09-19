// generated with ast extension for cup
// version 0.8
// 17/0/2021 13:8:50


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationMultiple extends VarDeclarations {

    private VarDeclarations VarDeclarations;
    private VarDeclSingle VarDeclSingle;

    public VarDeclarationMultiple (VarDeclarations VarDeclarations, VarDeclSingle VarDeclSingle) {
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
        this.VarDeclSingle=VarDeclSingle;
        if(VarDeclSingle!=null) VarDeclSingle.setParent(this);
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public VarDeclSingle getVarDeclSingle() {
        return VarDeclSingle;
    }

    public void setVarDeclSingle(VarDeclSingle VarDeclSingle) {
        this.VarDeclSingle=VarDeclSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
        if(VarDeclSingle!=null) VarDeclSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
        if(VarDeclSingle!=null) VarDeclSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
        if(VarDeclSingle!=null) VarDeclSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationMultiple(\n");

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclSingle!=null)
            buffer.append(VarDeclSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationMultiple]");
        return buffer.toString();
    }
}
