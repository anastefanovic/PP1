// generated with ast extension for cup
// version 0.8
// 17/0/2021 13:8:50


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String name;
    private ExtendsOptional ExtendsOptional;
    private VarDeclList VarDeclList;
    private MethodDeclListOptional MethodDeclListOptional;

    public ClassDecl (String name, ExtendsOptional ExtendsOptional, VarDeclList VarDeclList, MethodDeclListOptional MethodDeclListOptional) {
        this.name=name;
        this.ExtendsOptional=ExtendsOptional;
        if(ExtendsOptional!=null) ExtendsOptional.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MethodDeclListOptional=MethodDeclListOptional;
        if(MethodDeclListOptional!=null) MethodDeclListOptional.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public ExtendsOptional getExtendsOptional() {
        return ExtendsOptional;
    }

    public void setExtendsOptional(ExtendsOptional ExtendsOptional) {
        this.ExtendsOptional=ExtendsOptional;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MethodDeclListOptional getMethodDeclListOptional() {
        return MethodDeclListOptional;
    }

    public void setMethodDeclListOptional(MethodDeclListOptional MethodDeclListOptional) {
        this.MethodDeclListOptional=MethodDeclListOptional;
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
        if(ExtendsOptional!=null) ExtendsOptional.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MethodDeclListOptional!=null) MethodDeclListOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsOptional!=null) ExtendsOptional.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MethodDeclListOptional!=null) MethodDeclListOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsOptional!=null) ExtendsOptional.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MethodDeclListOptional!=null) MethodDeclListOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(ExtendsOptional!=null)
            buffer.append(ExtendsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclListOptional!=null)
            buffer.append(MethodDeclListOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
