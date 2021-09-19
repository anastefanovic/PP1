// generated with ast extension for cup
// version 0.8
// 17/0/2021 13:8:50


package rs.ac.bg.etf.pp1.ast;

public class CaseList extends CasesList {

    private CasesList CasesList;
    private Case Case;

    public CaseList (CasesList CasesList, Case Case) {
        this.CasesList=CasesList;
        if(CasesList!=null) CasesList.setParent(this);
        this.Case=Case;
        if(Case!=null) Case.setParent(this);
    }

    public CasesList getCasesList() {
        return CasesList;
    }

    public void setCasesList(CasesList CasesList) {
        this.CasesList=CasesList;
    }

    public Case getCase() {
        return Case;
    }

    public void setCase(Case Case) {
        this.Case=Case;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CasesList!=null) CasesList.accept(visitor);
        if(Case!=null) Case.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CasesList!=null) CasesList.traverseTopDown(visitor);
        if(Case!=null) Case.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CasesList!=null) CasesList.traverseBottomUp(visitor);
        if(Case!=null) Case.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseList(\n");

        if(CasesList!=null)
            buffer.append(CasesList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Case!=null)
            buffer.append(Case.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseList]");
        return buffer.toString();
    }
}
