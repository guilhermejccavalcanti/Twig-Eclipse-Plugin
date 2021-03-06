/*******************************************************************************
 * This file is part of the Twig eclipse plugin.
 * 
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.dubture.twig.core.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.php.internal.core.compiler.ast.nodes.ArrayCreation;
import org.eclipse.php.internal.core.compiler.ast.nodes.ArrayElement;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPCallExpression;
import org.eclipse.php.internal.core.compiler.ast.nodes.PHPMethodDeclaration;
import org.eclipse.php.internal.core.compiler.ast.nodes.ReturnStatement;
import org.eclipse.php.internal.core.compiler.ast.nodes.Scalar;
import org.eclipse.php.internal.core.compiler.ast.visitor.PHPASTVisitor;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.PlatformUI;

import com.dubture.twig.core.documentModel.provisional.contenttype.ContentTypeIdForTwig;
import com.dubture.twig.core.log.Logger;

/**
 * 
 * 
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * 
 */
@SuppressWarnings("restriction")
public class TwigModelUtils
{
    
    public static boolean isTwigTemplate(String filename)
    {
        IContentTypeManager manager = Platform.getContentTypeManager();
        IContentType[] contentTypes = manager.findContentTypesFor(filename);
        
        for (IContentType type : contentTypes) {
            if (ContentTypeIdForTwig.CONTENT_TYPE_ID_TWIG.equals(type.getId())) {
                return true;
            }
        }
        
        IFileEditorMapping[] editorMappings = PlatformUI.getWorkbench().getEditorRegistry().getFileEditorMappings();
        String extension = filename.substring(0, filename.lastIndexOf("."));
        
        if (extension == null) {
            return false;
        }
        
        for (IFileEditorMapping mapping : editorMappings) {
            if (mapping.getExtension().equals(extension)) {
                return true;
            }
        }
        
        return false;
    }

    /***
     * 
     * Retrieve the block-endstatements (endif, endblock, etc) from a
     * PHPMethodDeclaration.
     * 
     * 
     * @param method
     * @return
     */
    public static String[] getEndStatements(PHPMethodDeclaration method)
    {

        final List<String> statements = new ArrayList<String>();

        try {
            method.traverse(new PHPASTVisitor()
            {

                public boolean visit(ReturnStatement s) throws Exception
                {

                    if (s.getExpr() instanceof PHPCallExpression) {

                        PHPCallExpression call = (PHPCallExpression) s
                                .getExpr();
                        CallArgumentsList args = call.getArgs();

                        if ("test".equals(call.getName())
                                && args.getChilds().size() == 1) {

                            Object arg = args.getChilds().get(0);

                            if (arg instanceof Scalar) {

                                Scalar scalar = (Scalar) arg;
                                statements.add(scalar.getValue().replaceAll(
                                        "['\"]", ""));

                            } else if (arg instanceof ArrayCreation) {

                                ArrayCreation array = (ArrayCreation) arg;

                                for (ArrayElement key : array.getElements()) {

                                    Expression value = key.getValue();

                                    if (value != null
                                            && value instanceof Scalar) {

                                        Scalar scalar = (Scalar) value;
                                        statements.add(scalar.getValue()
                                                .replaceAll("['\"]", ""));
                                    }
                                }
                            }
                        }
                    }

                    return false;
                };

            });
        } catch (Exception e) {

            Logger.logException(e);
        }

        return (String[]) statements.toArray(new String[statements.size()]);

    }
}
