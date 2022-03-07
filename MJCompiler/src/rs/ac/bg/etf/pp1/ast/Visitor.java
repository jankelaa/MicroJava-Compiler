// generated with ast extension for cup
// version 0.8
// 26/0/2022 21:22:44


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Expr Expr);
    public void visit(ConstVarList ConstVarList);
    public void visit(VarDeclList VarDeclList);
    public void visit(VarDecl VarDecl);
    public void visit(SingleStatement SingleStatement);
    public void visit(VarDeclOption VarDeclOption);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(Addop Addop);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(Term Term);
    public void visit(BracketOption BracketOption);
    public void visit(StatementList StatementList);
    public void visit(ConstDeclType ConstDeclType);
    public void visit(MulopMOD MulopMOD);
    public void visit(MulopDIV MulopDIV);
    public void visit(MulopMUL MulopMUL);
    public void visit(AddopMINUS AddopMINUS);
    public void visit(AddopPLUS AddopPLUS);
    public void visit(Label Label);
    public void visit(DesignatorArrayName DesignatorArrayName);
    public void visit(DesignatorIdentExpr DesignatorIdentExpr);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(NewTypeExpr NewTypeExpr);
    public void visit(FactorExpr FactorExpr);
    public void visit(BOOL_CONST BOOL_CONST);
    public void visit(CHAR_CONST CHAR_CONST);
    public void visit(NUM_CONST NUM_CONST);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(TermMulopFactor TermMulopFactor);
    public void visit(TermFactor TermFactor);
    public void visit(ErrorExpr ErrorExpr);
    public void visit(ExprNoMinus ExprNoMinus);
    public void visit(ExprMinus ExprMinus);
    public void visit(ExprMore ExprMore);
    public void visit(ErrorDesignatorStatement ErrorDesignatorStatement);
    public void visit(DEC DEC);
    public void visit(INC INC);
    public void visit(Assign Assign);
    public void visit(Statements Statements);
    public void visit(GoTo GoTo);
    public void visit(PrintNoNumConst PrintNoNumConst);
    public void visit(PrintNumConst PrintNumConst);
    public void visit(Read Read);
    public void visit(SingleStmDesignator SingleStmDesignator);
    public void visit(StatementMany StatementMany);
    public void visit(StatementSingleNoLabel StatementSingleNoLabel);
    public void visit(StatementSingleLabel StatementSingleLabel);
    public void visit(NoStatementList NoStatementList);
    public void visit(YesStatementList YesStatementList);
    public void visit(Type Type);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(YesMethodDeclList YesMethodDeclList);
    public void visit(NoVarDeclList NoVarDeclList);
    public void visit(YesVarDeclList YesVarDeclList);
    public void visit(NoBracketOption NoBracketOption);
    public void visit(YesBracketOption YesBracketOption);
    public void visit(NoVarDeclOption NoVarDeclOption);
    public void visit(ErrorVarDeclOptionComma ErrorVarDeclOptionComma);
    public void visit(ErrorVarDeclOptionSemi ErrorVarDeclOptionSemi);
    public void visit(YesVarDeclOption YesVarDeclOption);
    public void visit(VarOne VarOne);
    public void visit(ErrorVarDecl ErrorVarDecl);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(NoConstDeclList NoConstDeclList);
    public void visit(YesConstDeclList YesConstDeclList);
    public void visit(ConstBoolDecl ConstBoolDecl);
    public void visit(ConstCharDecl ConstCharDecl);
    public void visit(ConstNumDecl ConstNumDecl);
    public void visit(ConstOne ConstOne);
    public void visit(ConstDecl ConstDecl);
    public void visit(NoConstVarDeclaration NoConstVarDeclaration);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
