/*
 * This file is part of the PDT Extensions eclipse plugin.
 *
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.dubture.twig.core.ast.parser;

public class ExpressionNode implements AstNode {

    public ExpressionNode(Object o) {        
//        System.err.println(o.getClass());
    }
    
    @Override
    public void accept(Visitor visitor) {

    }
}
