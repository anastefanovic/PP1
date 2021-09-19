package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	private int adrElse;
	private int adrEndIf;
	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(VoidType methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(methodTypeName.getName())) {
			mainPc = Code.pc;
		}
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(0);
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(RegularType methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(methodTypeName.getName())) {
			mainPc = Code.pc;
		}
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(0);
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReturnExprStmt ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReturnStmt ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(NumConstFactor numConstFactor) {
		Code.loadConst(numConstFactor.getValue());
	}
	
	@Override
	public void visit(CharConstFactor charConstFactor) {
		Code.loadConst(charConstFactor.getValue());
	}
	
	@Override
	public void visit(BoolConstFactor boolConstFactor) {
		Code.loadConst(boolConstFactor.getValue() ? 1 : 0);
	}
	
	@Override
	public void visit(DesignatorFactor designatorFactor) {
		Code.load(designatorFactor.getDesignator().obj);
	}
	
	@Override
	public void visit(NewArrayFactor newArrayFactor) {
		Code.put(Code.newarray);
		if(newArrayFactor.getType().struct == Tab.charType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
	
	@Override
	public void visit(TermListAddop termListAddop) {
		if(termListAddop.getAddop() instanceof Plus) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	@Override
	public void visit(TermMulop termMulop) {
		if(termMulop.getMulop() instanceof Mul) {
			Code.put(Code.mul);
		}
		else if(termMulop.getMulop() instanceof Div) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
	}
	
	@Override
	public void visit(TermListMinusTerm termListMinusTerm) {
		Code.put(Code.neg);
	}
	
	@Override
	public void visit(AssignDes assignDes) {
		Code.store(assignDes.getDesignator().obj);
	}
	
	@Override
	public void visit(IncDes incDes) {
		Obj designator = incDes.getDesignator().obj;
		if(designator.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designator);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designator);
	}
	
	@Override
	public void visit(DecDes decDes) {
		Obj designator = decDes.getDesignator().obj;
		if(designator.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designator);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designator);
	}
	
	@Override
	public void visit(PrintStmt printStmt) {
		Code.loadConst(1);
		if(printStmt.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(PrintConstStmt printConstStmt) {
		Code.loadConst(printConstStmt.getLen());
		if(printConstStmt.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(ReadStmt readStmt) {
		Obj designator = readStmt.getDesignator().obj;
		if(designator.getType() == Tab.charType) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		Code.store(designator);
	}
	
	@Override
	public void visit(DesignatorArrayAccess designatorArrayAccess) {
		Code.load(designatorArrayAccess.getDesignator().obj); // 0 2
		Code.put(Code.dup_x1); // 2 0 2
		Code.put(Code.pop); // 2 0
	}
	
	@Override
	public void visit(Qmark qmark) {
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		adrElse = Code.pc - 2;
	}
	
	@Override
	public void visit(Colon colon) {
		//kraj then grane
		Code.putJump(0);
		adrEndIf = Code.pc - 2;
		//pocetak else grane
		Code.fixup(adrElse);
	}
	
	@Override
	public void visit(ExprTernary exprTernary) {
		Code.fixup(adrEndIf);
	}
	
	
}





