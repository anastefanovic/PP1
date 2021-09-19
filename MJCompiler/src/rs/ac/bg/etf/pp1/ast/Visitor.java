// generated with ast extension for cup
// version 0.8
// 17/0/2021 13:8:51


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(CasesList CasesList);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(FormParsOptional FormParsOptional);
    public void visit(VarDeclSingle VarDeclSingle);
    public void visit(ActualParamList ActualParamList);
    public void visit(FormalParam FormalParam);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(ActPars ActPars);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Decl Decl);
    public void visit(ConstAssignList ConstAssignList);
    public void visit(ConstAssign ConstAssign);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ExtendsOptional ExtendsOptional);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclListOptional MethodDeclListOptional);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(FormPars FormPars);
    public void visit(Lequal Lequal);
    public void visit(Less Less);
    public void visit(Gequal Gequal);
    public void visit(Greater Greater);
    public void visit(NotEqual NotEqual);
    public void visit(IsEqual IsEqual);
    public void visit(Assignop Assignop);
    public void visit(Mod Mod);
    public void visit(Div Div);
    public void visit(Mul Mul);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(DesignatorBasic DesignatorBasic);
    public void visit(DesignatorArrayAccess DesignatorArrayAccess);
    public void visit(DesignatorClassMemberAccess DesignatorClassMemberAccess);
    public void visit(ExprFactor ExprFactor);
    public void visit(NewArrayFactor NewArrayFactor);
    public void visit(NewFactor NewFactor);
    public void visit(BoolConstFactor BoolConstFactor);
    public void visit(CharConstFactor CharConstFactor);
    public void visit(NumConstFactor NumConstFactor);
    public void visit(DesignatorNoParamsFactor DesignatorNoParamsFactor);
    public void visit(DesignatorParamsFactor DesignatorParamsFactor);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(TermNoMulop TermNoMulop);
    public void visit(TermMulop TermMulop);
    public void visit(TermListMinusTerm TermListMinusTerm);
    public void visit(TermListTerm TermListTerm);
    public void visit(TermListAddop TermListAddop);
    public void visit(Colon Colon);
    public void visit(Qmark Qmark);
    public void visit(ExprNoTernary ExprNoTernary);
    public void visit(ExprTernary ExprTernary);
    public void visit(ActParSingle ActParSingle);
    public void visit(ActParMultiple ActParMultiple);
    public void visit(CondFactNoRelop CondFactNoRelop);
    public void visit(CondFactRelop CondFactRelop);
    public void visit(CondTermNoAnd CondTermNoAnd);
    public void visit(CondTermAnd CondTermAnd);
    public void visit(ConditionNoOr ConditionNoOr);
    public void visit(ConditionOr ConditionOr);
    public void visit(DecDes DecDes);
    public void visit(IncDes IncDes);
    public void visit(ParamDes ParamDes);
    public void visit(NoParamDes NoParamDes);
    public void visit(AssignDesError AssignDesError);
    public void visit(AssignDes AssignDes);
    public void visit(Case Case);
    public void visit(NoCaseList NoCaseList);
    public void visit(CaseList CaseList);
    public void visit(NoStatList NoStatList);
    public void visit(StatList StatList);
    public void visit(ListStmt ListStmt);
    public void visit(PrintConstStmt PrintConstStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnExprStmt ReturnExprStmt);
    public void visit(ReturnStmt ReturnStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(SwitchStmt SwitchStmt);
    public void visit(DoWhileStmt DoWhileStmt);
    public void visit(IfElseStmt IfElseStmt);
    public void visit(IfStmt IfStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(FormParArray FormParArray);
    public void visit(FormParNotArray FormParNotArray);
    public void visit(FormParSingle FormParSingle);
    public void visit(FormParMultiple FormParMultiple);
    public void visit(NoFormalParams NoFormalParams);
    public void visit(FormalParams FormalParams);
    public void visit(VoidType VoidType);
    public void visit(RegularType RegularType);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoClassMethods NoClassMethods);
    public void visit(ClassMethods ClassMethods);
    public void visit(NoExtendedClass NoExtendedClass);
    public void visit(ExtendedClass ExtendedClass);
    public void visit(ClassDecl ClassDecl);
    public void visit(NoVarDeclarationList NoVarDeclarationList);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(VarDeclArray VarDeclArray);
    public void visit(VarDeclNotArray VarDeclNotArray);
    public void visit(VarDeclarationSingle VarDeclarationSingle);
    public void visit(VarDeclarationMultipleError VarDeclarationMultipleError);
    public void visit(VarDeclarationMultiple VarDeclarationMultiple);
    public void visit(VarDeclError VarDeclError);
    public void visit(VarDeclr VarDeclr);
    public void visit(BoolConstAssign BoolConstAssign);
    public void visit(CharConstAssign CharConstAssign);
    public void visit(NumConstAssign NumConstAssign);
    public void visit(ConstAssignSingle ConstAssignSingle);
    public void visit(ConstAssignMultiple ConstAssignMultiple);
    public void visit(ConstDecl ConstDecl);
    public void visit(NoMethodDeclarationList NoMethodDeclarationList);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(NoDeclarationList NoDeclarationList);
    public void visit(DeclarationList DeclarationList);
    public void visit(DeclDerived3 DeclDerived3);
    public void visit(DeclDerived2 DeclDerived2);
    public void visit(DeclDerived1 DeclDerived1);
    public void visit(Type Type);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
