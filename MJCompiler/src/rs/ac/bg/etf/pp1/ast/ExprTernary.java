// generated with ast extension for cup
// version 0.8
// 17/0/2021 13:8:50


package rs.ac.bg.etf.pp1.ast;

public class ExprTernary extends Expr {

    private TermList TermList;
    private Qmark Qmark;
    private TermList TermList1;
    private Colon Colon;
    private TermList TermList2;

    public ExprTernary (TermList TermList, Qmark Qmark, TermList TermList1, Colon Colon, TermList TermList2) {
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
        this.Qmark=Qmark;
        if(Qmark!=null) Qmark.setParent(this);
        this.TermList1=TermList1;
        if(TermList1!=null) TermList1.setParent(this);
        this.Colon=Colon;
        if(Colon!=null) Colon.setParent(this);
        this.TermList2=TermList2;
        if(TermList2!=null) TermList2.setParent(this);
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public Qmark getQmark() {
        return Qmark;
    }

    public void setQmark(Qmark Qmark) {
        this.Qmark=Qmark;
    }

    public TermList getTermList1() {
        return TermList1;
    }

    public void setTermList1(TermList TermList1) {
        this.TermList1=TermList1;
    }

    public Colon getColon() {
        return Colon;
    }

    public void setColon(Colon Colon) {
        this.Colon=Colon;
    }

    public TermList getTermList2() {
        return TermList2;
    }

    public void setTermList2(TermList TermList2) {
        this.TermList2=TermList2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermList!=null) TermList.accept(visitor);
        if(Qmark!=null) Qmark.accept(visitor);
        if(TermList1!=null) TermList1.accept(visitor);
        if(Colon!=null) Colon.accept(visitor);
        if(TermList2!=null) TermList2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
        if(Qmark!=null) Qmark.traverseTopDown(visitor);
        if(TermList1!=null) TermList1.traverseTopDown(visitor);
        if(Colon!=null) Colon.traverseTopDown(visitor);
        if(TermList2!=null) TermList2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        if(Qmark!=null) Qmark.traverseBottomUp(visitor);
        if(TermList1!=null) TermList1.traverseBottomUp(visitor);
        if(Colon!=null) Colon.traverseBottomUp(visitor);
        if(TermList2!=null) TermList2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTernary(\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Qmark!=null)
            buffer.append(Qmark.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList1!=null)
            buffer.append(TermList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Colon!=null)
            buffer.append(Colon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList2!=null)
            buffer.append(TermList2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTernary]");
        return buffer.toString();
    }
}
