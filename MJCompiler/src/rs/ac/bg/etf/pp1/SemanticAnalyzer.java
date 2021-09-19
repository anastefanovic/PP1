package rs.ac.bg.etf.pp1;
import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	Obj currentMethod = null;
	boolean returnFound = false;
	int nVars;
	
	Logger log = Logger.getLogger(getClass());
	private Struct currentType;
	private boolean mainParamsFound;
	
	public static final Struct boolType = new Struct(Struct.Bool);

	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		mainParamsFound = false;
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	

//======================================================================= PROGRAM ======================================================================
	
	public void visit(Program program) {
		Obj mainObj = Tab.currentScope.findSymbol("main");
		if(mainObj == null || mainObj.getKind() != Obj.Meth || mainObj.getType() != Tab.noType || mainParamsFound) {
			report_error("Greska: Ne postoji metoda main koja je tipa void i nema parametre", null);
		}
	
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	

	public void visit(ProgName progName) {
		if(Tab.currentScope.findSymbol(progName.getName()) == null) {
			progName.obj = Tab.insert(Obj.Prog, progName.getName(), Tab.noType);
		}
		else {
			progName.obj = Tab.noObj;
			report_error("Greska: Simbol sa imenom " + progName.getName() + " vec postoji u trenutnom opsegu", progName);
		}
		Tab.openScope();
	}
	
	
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getName());
		if (typeObj == Tab.noObj) {
			report_error("Greska: Nije pronadjen simbol sa imenom " + type.getName() + " u tabeli simbola", type);
			type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeObj.getKind()) {
				type.struct = typeObj.getType();
				
			} 
			else {
				report_error("Greska: Simbol sa imenom " + type.getName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}
		currentType = type.struct;
	}

//======================================================================= METHODS =======================================================================
	
	public void visit(MethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Greska: funkcija " + currentMethod.getName() + " nema return iskaz!", methodDecl);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	} 

	
	public void visit(RegularType methodTypeName) {
		Obj methObj = Tab.currentScope.findSymbol(methodTypeName.getName());
		
		if (methObj != null) {
			report_error("Greska: Simbol sa imenom " + methodTypeName.getName() + " vec postoji u tabeli simbola", null);
			currentMethod = Tab.noObj;
		}
		else {
			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getName(), methodTypeName.getType().struct);
			methodTypeName.obj = currentMethod;
		}
		
		Tab.openScope();
	}
	
	
	public void visit(VoidType methodTypeName) {
		Obj methObj = Tab.currentScope.findSymbol(methodTypeName.getName());

		if (methObj != null) {
			report_error("Greska: Simbol sa imenom " + methodTypeName.getName() + " vec postoji u tabeli simbola", null);
			currentMethod = Tab.noObj;
		} 
		else {
			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getName(), Tab.noType);
			methodTypeName.obj = currentMethod;
		}

		Tab.openScope();
	}
	
	
	public void visit(FormalParams formalParams) {
		if(currentMethod.getName().equals("main")) {
			mainParamsFound = true;
		}
	}
	
//====================================================================== STATEMENT ======================================================================
	
	public void visit(ReturnExprStmt returnExpr){
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		
		if (!returnExpr.getExpr().struct.assignableTo(currMethType)) {
			report_error("Greska: Tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), returnExpr);
		}			  	     	
	}
	
	
	public void visit(ReturnStmt returnStmt) {
		if (currentMethod.getType() != Tab.noType) {
			report_error("Greska: Funkcija " + currentMethod.getName() + " mora imati izraz u return naredbi", returnStmt);
		}	
	}
	
	
	public void visit(ReadStmt readStmt) {
		Obj designator = readStmt.getDesignator().obj;
		if(designator.getKind() != Obj.Var && designator.getKind() != Obj.Elem) {
			report_error("Greska: Ne moze se dodeliti vrednost designatoru koji nije promenljiva ili element niza", readStmt);
		}
		Struct designatorType = designator.getType();
		if(designatorType != Tab.intType && designatorType != Tab.charType && designatorType != boolType) {
			report_error("Greska: Argument za read nije odgovarajuceg tipa ", readStmt);
		}
	}
	
	
	public void visit(PrintStmt printStmt) {
		Struct exprType = printStmt.getExpr().struct;
		if(exprType != Tab.intType && exprType != Tab.charType && exprType != boolType) {
			report_error("Greska: Argument za print nije odgovarajuceg tipa ", printStmt);
		}
	}
	
	
	public void visit(PrintConstStmt printConstStmt) {
		Struct exprType = printConstStmt.getExpr().struct;
		if(exprType != Tab.intType && exprType != Tab.charType && exprType != boolType) {
			report_error("Greska: Argument za print nije odgovarajuceg tipa ", printConstStmt);
		}
	}
		
//==================================================================== DECLARATIONS ====================================================================

	public void visit(VarDeclNotArray varDeclSingle) {
		if(Tab.currentScope.findSymbol(varDeclSingle.getName()) != null) {
			report_error("Greska: Simbol sa imenom " + varDeclSingle.getName() + " vec postoji u tabeli simbola", varDeclSingle);
		}
		else {
			Tab.insert(Obj.Var, varDeclSingle.getName(), currentType);
		}
	}
	
	
	public void visit(VarDeclArray varDeclSingle) {
		if(Tab.currentScope.findSymbol(varDeclSingle.getName()) != null) {
			report_error("Greska: Simbol sa imenom " + varDeclSingle.getName() + " vec postoji u tabeli simbola", varDeclSingle);
		}
		else {
			Tab.insert(Obj.Var, varDeclSingle.getName(), new Struct(Struct.Array, currentType));
		}
	}
	
	
	public void visit(NumConstAssign constAssign) {
		if(Tab.currentScope.findSymbol(constAssign.getName()) != null) {
			report_error("Greska: Simbol sa imenom " + constAssign.getName() + " vec postoji u tabeli simbola", constAssign);
		}
		else {
			Tab.insert(Obj.Con, constAssign.getName(), currentType).setAdr(constAssign.getValue());
		}
		
		if(!currentType.equals(Tab.intType)){
			report_error("Greska: Tip dodeljene vrednosti se razlikuje od tipa konstante " + constAssign.getName(), constAssign);
		}	
	}
	
	
	public void visit(CharConstAssign constAssign) {
		if(Tab.currentScope.findSymbol(constAssign.getName()) != null) {
			report_error("Greska: Simbol sa imenom " + constAssign.getName() + " vec postoji u tabeli simbola", constAssign);
		} 
		else {
			Tab.insert(Obj.Con, constAssign.getName(), currentType).setAdr(constAssign.getValue());
		}
		
		if(!currentType.equals(Tab.charType)){
			report_error("Greska: Tip dodeljene vrednosti se razlikuje od tipa konstante " + constAssign.getName(), constAssign);
		}
	}
	 
	
	public void visit(BoolConstAssign constAssign) {
		if(Tab.currentScope.findSymbol(constAssign.getName()) != null) {
			report_error("Greska: Simbol sa imenom " + constAssign.getName() + " vec postoji u tabeli simbola", constAssign);
		} 
		else {
			int address = (constAssign.getValue() == true) ? 1 : 0;
			Tab.insert(Obj.Con, constAssign.getName(), currentType).setAdr(address);
		}
		
		if(!currentType.equals(boolType)){
			report_error("Greska: Tip dodeljene vrednosti se razlikuje od tipa konstante " + constAssign.getName(), constAssign);
		}
	}

//===================================================================== DESIGNATOR =====================================================================	
	
	public void visit(DesignatorBasic designatorBasic){
		Obj obj = Tab.find(designatorBasic.getName());
		if (obj == Tab.noObj) { 
			report_error("Greska: simbol sa imenom " + designatorBasic.getName() + " nije deklarisan! ", designatorBasic);
		}
		else {
			DumpSymbolTableVisitor dump = new DumpSymbolTableVisitor();
			dump.visitObjNode(obj);
			if(obj.getKind() == Obj.Con) {
				report_info("Detektovano koriscenje konstante " + dump.getOutput(), designatorBasic);
			}
			else if(obj.getKind() == Obj.Var) {
				if(obj.getLevel() == 0) {
					report_info("Detektovano koriscenje globalne promenljive " + dump.getOutput(), designatorBasic);
				}
				else {
					report_info("Detektovano koriscenje lokalne promenljive " + dump.getOutput(), designatorBasic);
				}
			}
		}
		designatorBasic.obj = obj;
	}
	
	
	public void visit(DesignatorArrayAccess designatorArrayAccess) {
		Obj designator = designatorArrayAccess.getDesignator().obj;
		Struct expr = designatorArrayAccess.getExpr().struct;
		Struct arrayStruct = designator.getType();
		designatorArrayAccess.obj = new Obj(Obj.Elem, "element", arrayStruct.getElemType());
		
		if(arrayStruct.getKind() != Struct.Array) {
			designatorArrayAccess.obj = Tab.noObj;
			report_error("Greska: Deklarisani simbol sa imenom " + designator.getName() + " nije niz ", designatorArrayAccess);
		}
		if(expr != Tab.intType) {
			designatorArrayAccess.obj = Tab.noObj;
			report_error("Greska: Indeksiranje niza se ne moze vrsiti izrazom koji nije tipa int", designatorArrayAccess);
		}
	}
	
//======================================================================= FACTOR =======================================================================

	public void visit(DesignatorFactor designatorFactor) {
		designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
	}
	
	public void visit(NumConstFactor numConstFactor) {
		numConstFactor.struct = Tab.intType;
	}
	
	public void visit(CharConstFactor charConstFactor) {
		charConstFactor.struct = Tab.charType;
	}
	
	public void visit(BoolConstFactor boolConstFactor) {
		boolConstFactor.struct = boolType;
	}
	
	public void visit(NewArrayFactor newArrayFactor) {
		newArrayFactor.struct = new Struct(Struct.Array, currentType); 
		if(newArrayFactor.getExpr().struct != Tab.intType) {
			newArrayFactor.struct = Tab.noType;
			report_error("Greska: Izraz unutar uglastih zagrada nije tipa int ", newArrayFactor);
		}
	}
	
	public void visit(ExprFactor exprFactor) {
		exprFactor.struct = exprFactor.getExpr().struct;
	}
	
//======================================================================== TERM ========================================================================
	
	public void visit(TermNoMulop termNoMulop) {
		termNoMulop.struct = termNoMulop.getFactor().struct;
	}
	
	
	public void visit(TermMulop termMulop) {
		Struct t1 = termMulop.getTerm().struct;
		Struct t2 = termMulop.getFactor().struct;
		
		if(t1.equals(t2) && t1 == Tab.intType) {
			termMulop.struct = t1;
		}
		else {
			report_error("Greska: Nekompatibilni tipovi simbola u izrazu za mulop ", termMulop);
			termMulop.struct = Tab.noType;
		}
	}
	
	
	public void visit(TermListAddop termListAddop) {
		Struct t1 = termListAddop.getTermList().struct;
		Struct t2 = termListAddop.getTerm().struct;
		
		if (t1.equals(t2) && t1 == Tab.intType) {
			termListAddop.struct = t1;
		}
		else {
			report_error("Greska: Nekompatibilni tipovi simbola u izrazu za addop ", termListAddop);
			termListAddop.struct = Tab.noType;
		} 
	}
	
	
	public void visit(TermListTerm termListTerm) {
		termListTerm.struct = termListTerm.getTerm().struct;
	}
	
	
	public void visit(TermListMinusTerm termListMinusTerm) {
		Struct t = termListMinusTerm.getTerm().struct;
		if(t == Tab.intType) {
			termListMinusTerm.struct = t;
		}
		else {
			report_error("Greska: Nekompatibilan tip simbola u izrazu za minus ", termListMinusTerm);
			termListMinusTerm.struct = Tab.noType;
		}
	}
	
//======================================================================== EXPR ========================================================================
	
	public void visit(ExprNoTernary exprNoTernary) {
		exprNoTernary.struct = exprNoTernary.getTermList().struct;
	}
	
	
	public void visit(ExprTernary exprTernary) {
		Struct condExpr = exprTernary.getTermList().struct;
		Struct trueExpr = exprTernary.getTermList1().struct;
		Struct falseExpr = exprTernary.getTermList2().struct;
		exprTernary.struct = trueExpr;	
		
		if(condExpr != boolType) {
			report_error("Greska: Uslov kod ternarnog operatora nije tipa bool ", exprTernary);
			exprTernary.struct = Tab.noType;
		}
		if(!trueExpr.equals(falseExpr)) {
			report_error("Greska: Tipovi izraza kod ternarnog operatora se razlikuju ", exprTernary);
			exprTernary.struct = Tab.noType;
		}
	}
	
//================================================================ DESIGNATOR STATEMENT ================================================================

	public void visit(AssignDes assignDes) {
		
		Obj designator = assignDes.getDesignator().obj;
		Struct expr = assignDes.getExpr().struct;
		if(designator.getKind() != Obj.Var && designator.getKind() != Obj.Elem) {
			report_error("Greska: Ne moze se dodeliti vrednost designatoru koji nije promenljiva ili element niza", assignDes);
		}
		if(!expr.assignableTo(designator.getType())) {
			report_error("Greska: Nekompatibilni tipovi simbola i izraza pri dodeli vrednosti ", assignDes);
		}
	}
	
	
	public void visit(IncDes incDes) {
		Obj designator = incDes.getDesignator().obj;
		if(designator.getKind() != Obj.Var && designator.getKind() != Obj.Elem) {
			report_error("Greska: Ne moze se dodeliti vrednost designatoru koji nije promenljiva ili element niza", incDes);
		}
		if(designator.getType() != Tab.intType) {
			report_error("Greska: Simbol koji se inkrementira nije tipa int ", incDes);
		}
	}
	
	
	public void visit(DecDes decDes) {
		Obj designator = decDes.getDesignator().obj;
		if(designator.getKind() != Obj.Var && designator.getKind() != Obj.Elem) {
			report_error("Greska: Ne moze se dodeliti vrednost designatoru koji nije promenljiva ili element niza", decDes);
		}
		if(designator.getType() != Tab.intType) {
			report_error("Greska: Simbol koji se dekrementira nije tipa int ", decDes);
		}
	}	
	
//======================================================================= ERRORS =======================================================================
	
	public boolean passed() {
		return !errorDetected;
	}
	
	public void visit(AssignDesError assignDesError) {
		report_info("Uspesan oporavak od sintaksne greske prilikom dodele vrednosti do ; ", assignDesError);
	}
	
	public void visit(VarDeclError varDeclError) {
		report_info("Uspesan oporavak od sintaksne greske prilikom deklaracije promenljivih do ; ", varDeclError);
	}
	
	public void visit(VarDeclarationMultipleError varDeclarationMultipleError) {
		report_info("Uspesan oporavak od sintaksne greske prilikom deklaracije promenljivih do , ", varDeclarationMultipleError);
	}
	
}

