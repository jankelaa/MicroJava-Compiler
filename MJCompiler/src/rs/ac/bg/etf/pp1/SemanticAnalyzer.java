package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	int nVars;
	boolean errorDetected = false;

	// dodavanje boolean
	private Struct boolType;
	// public static Struct boolType = Tab.insert(Obj.Type, "bool", new
	// Struct(Struct.Bool)).getType();

	public SemanticAnalyzer(Struct bool) {
		this.boolType = bool;
	}

	Logger log = Logger.getLogger(getClass());
	private Obj myProgram;
	private Obj currentMethod = null;
	private Struct currentType;
	private Struct constantType;
	private int constant = 0;
	private int currentLevelMyVar = 0;
	private boolean mainFound;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());

		// MJParserTest.dodajGresku(new CompilerError(line, message ,
		// CompilerError.CompilerErrorType.SYNTAX_ERROR));
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	// ************** POCETAK PROGRAMA **************
	@Override
	public void visit(ProgName progName) {
		myProgram = progName.obj = Tab.insert(Obj.Prog, progName.getI1(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(myProgram);
		Tab.closeScope();
		if (!mainFound)
			report_error("U programu nije pronadjena MAIN metoda!", null);
	}

	// ************** VARIJABLE **************
	@Override
	public void visit(VarOne varOne) {
		Obj varObj = Tab.find(varOne.getI1());
		if (varObj != Tab.noObj && varObj.getLevel() == currentLevelMyVar)
			report_error("Dvostruka definicija varijable: " + varOne.getI1(), varOne);
		// varDeclCount++;
		// report_info("Deklarisana promenljiva " + varOne.getI1(), varOne);
		if (varOne.getBracketOption() instanceof NoBracketOption)
			varObj = Tab.insert(Obj.Var, varOne.getI1(), currentType);
		else if (varOne.getBracketOption() instanceof YesBracketOption)
			varObj = Tab.insert(Obj.Var, varOne.getI1(), new Struct(Struct.Array, currentType));
	}

	// ************** KONSTANTE **************
	@Override
	public void visit(ConstOne constOne) {
		Obj conObj = Tab.find(constOne.getI1());
		if (conObj != Tab.noObj)
			report_error("Dvostruka definicija konstante: " + constOne.getI1(), constOne);
		else if (constantType.assignableTo(currentType)) {
			// constDeclCount++;
			// report_info("Deklarisana promenljiva " + constDecl.getI2(), constDecl);
			conObj = Tab.insert(Obj.Con, constOne.getI1(), currentType);
			conObj.setAdr(constant);
		} else {
			report_error("Nekompatibilni tipovi pri kreiranju konstante: " + constOne.getI1(), constOne);
		}
	}

	@Override
	public void visit(ConstNumDecl constNumDecl) {
		constant = constNumDecl.getN1();
		constantType = Tab.intType;
	}

	@Override
	public void visit(ConstCharDecl constCharDecl) {
		constant = constCharDecl.getC1();
		constantType = Tab.charType;
	}

	@Override
	public void visit(ConstBoolDecl constBoolDecl) {
		constant = constBoolDecl.getB1();
		constantType = boolType;
	}

	// ************** METODE **************
	@Override
	public void visit(MethodVoidName methodVoidName) {
		currentMethod = methodVoidName.obj = Tab.insert(Obj.Meth, methodVoidName.getI1(), Tab.noType);
		Tab.openScope();
		currentLevelMyVar++;
		if (methodVoidName.getI1().equals("main"))
			mainFound = true;
		else
			report_error("Metoda " + methodVoidName.getI1() + " mora biti MAIN", methodVoidName);
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		currentMethod = null;
		currentLevelMyVar--;
	}

	// ************** TYPE **************
	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if (typeObj == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getI1() + " u tabeli simbola.", null);
			type.struct = currentType = Tab.noType;
		} else {
			if (Obj.Type == typeObj.getKind()) {
				type.struct = currentType = typeObj.getType();
			} else {
				report_error("Greska: Ime " + type.getI1() + " ne predstavlja tip!", type);
				type.struct = currentType = Tab.noType;
			}
		}
	}

	// ************** DESIGNATOR STATEMENT **************
	@Override
	public void visit(Assign assign) {
		Struct des = assign.getDesignator().obj.getType();
		Struct exp = assign.getExpr().struct;
		if (exp.assignableTo(des)) {
			if (assign.getDesignator().obj.getKind() != Obj.Var && assign.getDesignator().obj.getKind() != Obj.Elem)
				report_error("Vrednost se moze dodeliti jedino promenljivoj ili elementu niza", assign);
		} else
			report_error("Promenljiva i izraz moraju biti istog tipa pri ASSIGN dodeli vrednosti", assign);
	}

	@Override
	public void visit(INC inc) {
		Struct des = inc.getDesignator().obj.getType();
		if (des.equals(Tab.intType)) {
			// if (inc.getDesignator().obj.getKind() != Obj.Var && des.getKind() !=
			// Struct.Array)
			if (inc.getDesignator().obj.getKind() != Obj.Var && inc.getDesignator().obj.getKind() != Obj.Elem)
				report_error("Vrednost se moze inkrementirati jedino promenljivoj ili elementu niza", inc);
		} else
			report_error("Designator u operaciji INC mora biti INT tipa", inc);
	}

	@Override
	public void visit(DEC dec) {
		Struct des = dec.getDesignator().obj.getType();
		if (des.equals(Tab.intType)) {
			// if (dec.getDesignator().obj.getKind() != Obj.Var && des.getKind() !=
			// Struct.Array)
			if (dec.getDesignator().obj.getKind() != Obj.Var && dec.getDesignator().obj.getKind() != Obj.Elem)
				report_error("Vrednost se moze dekrementirati jedino promenljivoj ili elementu niza", dec);

		} else
			report_error("Designator u operaciji DEC mora biti INT tipa", dec);
	}

	// ************** SINGLE STATEMENT **************
	@Override
	public void visit(Read read) {
		Obj des = read.getDesignator().obj;
		if (des.getKind() != Obj.Var && des.getKind() != Obj.Elem)
			report_error("Kod operacije READ Designator mora biti Var ili Elem niza", read);
		else if (des.getType() != Tab.charType && des.getType() != Tab.intType && des.getType() != boolType)
			report_error("Kod operacije READ Designator mora biti INT, CHAR ili BOOL tipa", read);
	}

	@Override
	public void visit(PrintNumConst printNumConst) {
		Struct expr = printNumConst.getExpr().struct;
		if (!(expr.equals(Tab.charType) || expr.equals(Tab.intType) || expr.equals(boolType))) {
			report_error("Expr naredba u operactiji PRINT mora biti INT, CHAR ili BOOL", printNumConst);
		}
	}

	@Override
	public void visit(PrintNoNumConst printNoNumConst) {
		Struct expr = printNoNumConst.getExpr().struct;
		if (!(expr.equals(Tab.charType) || expr.equals(Tab.intType) || expr.equals(boolType))) {
			report_error("Expr naredba u operactiji PRINT mora biti INT, CHAR ili BOOL", printNoNumConst);
		}
	}

	// ************** EXPR **************
	@Override
	public void visit(ExprMore exprMore) {
		Struct exp = exprMore.getExpr().struct;
		Struct term = exprMore.getTerm().struct;

		if (exp.equals(Tab.intType) && term.equals(Tab.intType))
			exprMore.struct = Tab.intType;
		else {
			report_error("Svi clanovi ADDOP operacije moraju biti INT!", exprMore);
			exprMore.struct = Tab.noType;
		}

	}

	@Override
	public void visit(ExprNoMinus exprNoMinus) {
		exprNoMinus.struct = exprNoMinus.getTerm().struct;
	}

	@Override
	public void visit(ExprMinus exprMinus) {
		Struct ter = exprMinus.getTerm().struct;
		if (ter != Tab.intType) {
			report_error("Term tip koji se negira mora biti INT", exprMinus);
			exprMinus.struct = Tab.noType;
		} else
			exprMinus.struct = ter;
	}

	// ************** TERM **************
	@Override
	public void visit(TermFactor termFactor) {
		termFactor.struct = termFactor.getFactor().struct;
	}

	@Override
	public void visit(TermMulopFactor termMulopFactor) {
		Struct ter = termMulopFactor.getTerm().struct;
		Struct fac = termMulopFactor.getFactor().struct;
		if (ter == Tab.intType && fac == Tab.intType)
			termMulopFactor.struct = termMulopFactor.getFactor().struct;
		else {
			report_error("Clanovi Term i Faktor moraju biti tipa INT kod MUL operacija", termMulopFactor);
			termMulopFactor.struct = Tab.noType;
		}
	}

	// ************** FAKTOR **************
	@Override
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	}

	@Override
	public void visit(NUM_CONST numConst) {
		numConst.struct = Tab.intType;
	}

	@Override
	public void visit(CHAR_CONST charConst) {
		charConst.struct = Tab.charType;
	}

	@Override
	public void visit(BOOL_CONST boolConst) {
		boolConst.struct = boolType;
	}

	@Override
	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
	}

	@Override
	public void visit(NewTypeExpr newTypeExpr) {
		Struct expr = newTypeExpr.getExpr().struct;
		if (expr == Tab.intType)
			newTypeExpr.struct = new Struct(Struct.Array, newTypeExpr.getType().struct);
		else {
			report_error("Indekser Expr pri kreiranju niza mora biti INT vrednost.", newTypeExpr);
			newTypeExpr.struct = Tab.noType;
		}
	}

	// ************** DESIGNATOR **************
	@Override
	public void visit(DesignatorIdent designatorIdent) {
		Obj obj = Tab.find(designatorIdent.getI1());
		if (obj == Tab.noObj) {
			report_error("Greska designatorIdent: " + designatorIdent.getI1() + " nije deklarisan!", designatorIdent);
			designatorIdent.obj = Tab.noObj;
		} else if (obj.getKind() == Obj.Var || obj.getKind() == Obj.Con) {
			designatorIdent.obj = obj;
		} else {
			report_error("Greska designatorIdent: " + designatorIdent.getI1() + " nije dobrog tipa!", designatorIdent);
			designatorIdent.obj = Tab.noObj;
		}
	}

	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Obj obj = Tab.find(designatorArrayName.getI1());
		if (obj == Tab.noObj) {
			report_error("Greska designatorArrayName: '" + designatorArrayName.getI1() + "' nije deklarisan!",
					designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		} else if (obj.getKind() == Obj.Var && obj.getType().getKind() == Struct.Array) {
			designatorArrayName.obj = obj;
		} else {
			report_error("Greska DesignatorArrayName: '" + designatorArrayName.getI1() + "' nije dobrog tipa!",
					designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		}
	}

	@Override
	public void visit(DesignatorIdentExpr designatorIdentExpr) {
		Obj obj = designatorIdentExpr.getDesignatorArrayName().obj;
		if (obj == Tab.noObj)
			designatorIdentExpr.obj = Tab.noObj;
		else {
			if (designatorIdentExpr.getExpr().struct.equals(Tab.intType))
				designatorIdentExpr.obj = new Obj(Obj.Elem, "elem", obj.getType().getElemType());
			else {
				report_error("Nevalidan indeks Expr:DesignatorIdentExpr", designatorIdentExpr);
				designatorIdentExpr.obj = Tab.noObj;
			}
		}
	}

	public boolean passed() {
		return !errorDetected;
	}
}
