package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
//import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private ArrayList<Labela> labele = new ArrayList<Labela>();

	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	// ************** VOID MAIN **************
	@Override
	public void visit(MethodVoidName methodVoidName) {
		// if ("main".equalsIgnoreCase(methodVoidName.getI1())) // nepotrebno, imamo
		// samo jednu, MAIN metodu
		mainPc = Code.pc;
		methodVoidName.obj.setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(0); // main nema argumente (methodVoidName.obj.getLevel() == 0)
		Code.put(methodVoidName.obj.getLocalSymbols().size());
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	// ************** READ **************
	@Override
	public void visit(Read read) {
		if (read.getDesignator().obj.getType() == Tab.charType)
			Code.put(Code.bread);
		else
			Code.put(Code.read);

		Code.store(read.getDesignator().obj);
	}

	// ************** PRINT **************
	@Override
	public void visit(PrintNoNumConst printNoNumConst) {
		if (printNoNumConst.getExpr().struct == Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}

	@Override
	public void visit(PrintNumConst printNumConst) {
		if (printNumConst.getExpr().struct == Tab.charType) {
			Code.loadConst(printNumConst.getN2());
			Code.put(Code.bprint);
		} else {
			Code.loadConst(printNumConst.getN2());
			Code.put(Code.print);
		}
	}

	// ************** DESIGNATOR STATEMENT: ASSIGN INC DEC **************
	@Override
	public void visit(Assign assign) {
		Code.store(assign.getDesignator().obj);
	}

	@Override
	public void visit(INC inc) {
		if (inc.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);
	}

	@Override
	public void visit(DEC dec) {
		if (dec.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dec.getDesignator().obj);
	}

	// ************** EXPR: ADDOP I MULOP **************
	@Override
	public void visit(ExprMore exprMore) {
		if (exprMore.getAddop() instanceof AddopPLUS)
			Code.put(Code.add);
		else if (exprMore.getAddop() instanceof AddopMINUS)
			Code.put(Code.sub);
	}

	@Override
	public void visit(ExprMinus exprMinus) {
		Code.put(Code.neg);
	}

	@Override
	public void visit(TermMulopFactor termMulopFactor) {
		if (termMulopFactor.getMulop() instanceof MulopMUL)
			Code.put(Code.mul);
		else if (termMulopFactor.getMulop() instanceof MulopDIV)
			Code.put(Code.div);
		else if (termMulopFactor.getMulop() instanceof MulopMOD)
			Code.put(Code.rem);
	}

	// ************** FAKTOR **************
	@Override
	public void visit(NUM_CONST numConst) {
		Code.loadConst(numConst.getN1());
	}

	@Override
	public void visit(CHAR_CONST charConst) {
		Code.loadConst(charConst.getC1());
	}

	@Override
	public void visit(BOOL_CONST boolConst) {
		Code.loadConst(boolConst.getB1());
	}

	@Override
	public void visit(NewTypeExpr newTypeExpr) {
		Code.put(Code.newarray);
		if (newTypeExpr.getType().struct == Tab.charType)
			Code.put(0);
		else
			Code.put(1);
	}

	// ************** DESIGNATOR **************
	@Override
	public void visit(DesignatorIdent designatorIdent) {
		SyntaxNode parent = designatorIdent.getParent();

		if (parent.getClass() == FactorDesignator.class)
			Code.load(designatorIdent.obj); // sam radi proveru objektnog fajla, da li se radi o const
											// ili local/ global var
	}

	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Code.load(designatorArrayName.obj);
	}

	@Override
	public void visit(DesignatorIdentExpr designatorIdentExpr) {
		SyntaxNode parent = designatorIdentExpr.getParent();
		if (parent.getClass() == FactorDesignator.class)
			if (designatorIdentExpr.obj.getType() == Tab.charType)
				Code.put(Code.baload);
			else
				Code.put(Code.aload);
	}

	// ************** LABELA **************
	@Override
	public void visit(Label label) {
		String labela = label.getI1();
		Labela tek = null;
		for (int i = 0; i < labele.size(); i++) {
			if (labele.get(i).getIme().equals(labela)) {
				tek = labele.get(i);
				break;
			}
		}

		if (label.getParent() instanceof StatementSingleLabel) {

			if (tek != null) { // *** PRVO GOTO POSLE LABELA ***
				Code.put2(tek.getSrc(), (Code.pc - tek.getSrc() + 1));
//				Code.fixup(tek.getSrc());
			} else {
				tek = new Labela(labela, Code.pc, 0);
				labele.add(tek);
			}

		} else if (label.getParent() instanceof GoTo) {

			if (tek == null) { // *** PRVO GOTO POSLE LABELA ***
				Code.putJump(0);
				tek = new Labela(labela, 0, Code.pc - 2);
				labele.add(tek);
			} else {
				Code.putJump(tek.getDest());
			}
		}
	}

}
