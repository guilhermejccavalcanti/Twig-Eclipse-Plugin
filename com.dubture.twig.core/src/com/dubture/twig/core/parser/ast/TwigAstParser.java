//----------------------------------------------------
package com.dubture.twig.core.parser.ast;

import java.util.LinkedList;
import java.util.List;
import com.dubture.twig.core.parser.ast.node.TwigCallExpression;
import com.dubture.twig.core.parser.ast.node.TwigModuleDeclaration;
import com.dubture.twig.core.parser.ast.node.BlockStatement;
import com.dubture.twig.core.parser.ast.node.Variable;
import com.dubture.twig.core.parser.ast.node.PrintStatement;
import com.dubture.twig.core.parser.ast.node.StringLiteral;
import com.dubture.twig.core.parser.ast.node.BlockName;
import com.dubture.twig.core.parser.ast.node.InLineHtml;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

/** CUP v0.10k generated parser.
  * @version Sat Mar 14 20:56:52 CET 2015
  */
public class TwigAstParser extends AbstractAstParser {

    public TwigAstParser() {
        super();
    }

    public TwigAstParser(java_cup.runtime.Scanner s) {
        super(s);
    }

    /** Production table. */
    protected static final short _production_table[][] = unpackFromStrings(new String[] { "    " + "   " + "    " + "\b \b \t " + "\t  \n \n" + " \n \f" });

    /** Access to production table. */
    public short[][] production_table() {
        return _production_table;
    }

    /** Parse-action table. */
    protected static final short[][] _action_table = unpackFromStrings(new String[] { "  ?  " + "\f\t\b\n\n\f\f" + " ? ? \r" + " ? ? ?" + " ? ? ? " + "\n\n\n\f\f " + "? ? ? \b" + "? ? ? \n" + "\t\n\n\f\f " + "? ? ? \n" + "\n\n\f\f ? " + " " });

    /** Access to parse-action table. */
    public short[][] action_table() {
        return _action_table;
    }

    /** <code>reduce_goto</code> table. */
    protected static final short[][] _reduce_table = unpackFromStrings(new String[] { "   " + " \n\r\f\n\n" + "   \f" + " \b  " + "   " + " \b\t\n " + "   " + "  \b \b" + "\t\n  " + "\b  \b\t" + "\n  " + "" });

    /** Access to <code>reduce_goto</code> table. */
    public short[][] reduce_table() {
        return _reduce_table;
    }

    /** Instance of action encapsulation class. */
    protected CUP$TwigAstParser$actions action_obj;

    /** Action encapsulation object initializer. */
    protected void init_actions() {
        action_obj = new CUP$TwigAstParser$actions(this);
    }

    /** Invoke a user supplied parse action. */
    public java_cup.runtime.Symbol do_action(int act_num, java_cup.runtime.lr_parser parser, java.util.Stack stack, int top) throws java.lang.Exception {
        return action_obj.CUP$TwigAstParser$do_action(act_num, parser, stack, top);
    }

    /** Indicates start state. */
    public int start_state() {
        return 0;
    }

    /** Indicates start production. */
    public int start_production() {
        return 1;
    }

    /** <code>EOF</code> Symbol index. */
    public int EOF_sym() {
        return 0;
    }

    /** <code>error</code> Symbol index. */
    public int error_sym() {
        return 1;
    }

    /* Change the method report_error so it will display the line and
       column of where the error occurred in the input as well as the
       reason for the error which is passed into the method in the
       String 'message'. */
    public void report_error(String message, Object info) {
        StringBuffer m = new StringBuffer("Error");
        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
            if (s.left >= 0) {
                m.append(" in line " + (s.left + 1));
                if (s.right >= 0) {
                    m.append(", column " + (s.right + 1));
                }
            }
        }
        m.append(" : " + message);
    }

    /* Change the method report_fatal_error so when it reports a fatal
       error it will display the line and column number of where the
       fatal error occurred in the input as well as the reason for the
       fatal error which is passed into the method in the object
       'message' and then exit.*/
    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
    }
}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$TwigAstParser$actions {

    private final TwigAstParser parser;

    CUP$TwigAstParser$actions(TwigAstParser parser) {
        this.parser = parser;
    }

    public final java_cup.runtime.Symbol CUP$TwigAstParser$do_action(int CUP$TwigAstParser$act_num, java_cup.runtime.lr_parser CUP$TwigAstParser$parser, java.util.Stack CUP$TwigAstParser$stack, int CUP$TwigAstParser$top) throws java.lang.Exception {
        {
            return CUP$TwigAstParser$do_action1(CUP$TwigAstParser$act_num, CUP$TwigAstParser$parser, CUP$TwigAstParser$stack, CUP$TwigAstParser$top);
        }
    }

    /** Method with the actual generated action code. */
    public final java_cup.runtime.Symbol CUP$TwigAstParser$do_action1(int CUP$TwigAstParser$act_num, java_cup.runtime.lr_parser CUP$TwigAstParser$parser, java.util.Stack CUP$TwigAstParser$stack, int CUP$TwigAstParser$top) throws java.lang.Exception {
        /* Symbol object for return from actions */
        java_cup.runtime.Symbol CUP$TwigAstParser$result;
        /* select the action based on the action number */
        switch(CUP$TwigAstParser$act_num) {
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // module ::= top_statement_list 
            0:
                {
                    TwigModuleDeclaration RESULT = null;
                    int sListleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int sListright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    List sList = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    TwigModuleDeclaration module = parser.getModuleDeclaration();
                    module.setStatements(sList);
                    module.setStart(sListleft);
                    module.setEnd(sListright);
                    RESULT = module;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(1, /*module*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // $START ::= module EOF 
            1:
                {
                    Object RESULT = null;
                    int start_valleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int start_valright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    TwigModuleDeclaration start_val = (TwigModuleDeclaration) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    RESULT = start_val;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(0, /*$START*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                /* ACCEPT */
                CUP$TwigAstParser$parser.done_parsing();
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // top_statement_list ::= top_statement_list top_statement 
            2:
                {
                    List RESULT = null;
                    int sListleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int sListright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    List sList = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    int statementleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int statementright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Statement statement = (Statement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    if (statement != null) {
                        sList.add(statement);
                    }
                    RESULT = sList;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(4, /*top_statement_list*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // top_statement_list ::= 
            3:
                {
                    List RESULT = null;
                    RESULT = new LinkedList();
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(4, /*top_statement_list*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // top_statement ::= statement 
            4:
                {
                    Statement RESULT = null;
                    int statementleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int statementright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Statement statement = (Statement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = statement;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(5, /*top_statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // top_statement ::= var 
            5:
                {
                    Statement RESULT = null;
                    int vleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int vright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    PrintStatement v = (PrintStatement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = v;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(5, /*top_statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // top_statement ::= block 
            6:
                {
                    Statement RESULT = null;
                    int bleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int bright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    BlockStatement b = (BlockStatement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = b;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(5, /*top_statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // var ::= TWIG_VAR_OPEN inner_statement_list TWIG_VAR_CLOSE 
            7:
                {
                    PrintStatement RESULT = null;
                    int startleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).left;
                    int startright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).right;
                    Object start = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).value;
                    int innerleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int innerright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    List inner = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    int endleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int endright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Object end = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    PrintStatement stmt = new PrintStatement(startleft, endright, inner);
                    RESULT = stmt;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(3, /*var*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // block ::= TWIG_STMT_OPEN blockname inner_statement_list TWIG_STMT_CLOSE 
            8:
                {
                    BlockStatement RESULT = null;
                    int startleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).left;
                    int startright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).right;
                    Object start = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).value;
                    int nameleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).left;
                    int nameright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).right;
                    BlockName name = (BlockName) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).value;
                    int innerleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int innerright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    List inner = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    int endleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int endright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Object end = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    BlockStatement stmt = new BlockStatement(startleft, endright, name, inner);
                    RESULT = stmt;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(2, /*block*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // inner_statement_list ::= inner_statement_list inner_statement 
            9:
                {
                    List RESULT = null;
                    int statementListleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int statementListright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    List statementList = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    int statementleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int statementright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Statement statement = (Statement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    // Ignore null statements
                    if (statement != null) {
                        statementList.add(statement);
                    }
                    RESULT = statementList;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(6, /*inner_statement_list*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // inner_statement_list ::= 
            10:
                {
                    List RESULT = null;
                    RESULT = new LinkedList();
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(6, /*inner_statement_list*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // inner_statement ::= statement 
            11:
                {
                    Statement RESULT = null;
                    int statementleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int statementright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Statement statement = (Statement) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = statement;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(7, /*inner_statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // inner_statement ::= function 
            12:
                {
                    Statement RESULT = null;
                    int functionleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int functionright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    TwigCallExpression function = (TwigCallExpression) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = function;
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(7, /*inner_statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // function ::= T_VARIABLE T_OPEN_PARENTHESE inner_statement_list T_CLOSE_PARENTHESE 
            13:
                {
                    TwigCallExpression RESULT = null;
                    int idleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).left;
                    int idright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).right;
                    String id = (String) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).value;
                    int openleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).left;
                    int openright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).right;
                    Object open = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 2)).value;
                    int stmtleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).left;
                    int stmtright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).right;
                    List stmt = (List) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 1)).value;
                    int closeleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int closeright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Object close = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = new TwigCallExpression(idleft, closeright, id, stmt);
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(9, /*function*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 3)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // statement ::= T_VARIABLE 
            14:
                {
                    Statement RESULT = null;
                    int varleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int varright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    String var = (String) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = new Variable(varleft, varright, var);
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(8, /*statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // statement ::= T_CONSTANT_ENCAPSED_STRING 
            15:
                {
                    Statement RESULT = null;
                    int stringleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int stringright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    String string = (String) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = new StringLiteral(stringleft, stringright, string);
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(8, /*statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // statement ::= T_INLINE_HTML 
            16:
                {
                    Statement RESULT = null;
                    int htmlleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int htmlright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    Object html = (Object) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = new InLineHtml(htmlleft, htmlright);
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(8, /*statement*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /*. . . . . . . . . . . . . . . . . . . .*/
            case // blockname ::= T_BLOCK_NAME 
            17:
                {
                    BlockName RESULT = null;
                    int stmtleft = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left;
                    int stmtright = ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right;
                    String stmt = (String) ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).value;
                    RESULT = new BlockName(stmtleft, stmtright, stmt);
                    CUP$TwigAstParser$result = new java_cup.runtime.Symbol(10, /*blockname*/
                    ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).left, ((java_cup.runtime.Symbol) CUP$TwigAstParser$stack.elementAt(CUP$TwigAstParser$top - 0)).right, RESULT);
                }
                return CUP$TwigAstParser$result;
            /* . . . . . .*/
            default:
                throw new Exception("Invalid action number found in internal parse table");
        }
    }
}
