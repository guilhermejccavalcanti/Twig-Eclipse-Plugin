/*******************************************************************************
 * This file is part of the Twig eclipse plugin.
 *
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.dubture.twig.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISources;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.sse.ui.internal.Logger;
import org.eclipse.wst.sse.ui.internal.SSEUIMessages;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.sse.ui.internal.handlers.AbstractCommentHandler;
import org.eclipse.wst.sse.ui.internal.handlers.ToggleLineCommentHandler;

@SuppressWarnings("restriction")
public class TwigToggleLineCommentHandler extends AbstractCommentHandler {

	static final String OPEN_COMMENT = "{#"; //$NON-NLS-1$
	static final String CLOSE_COMMENT = "#}"; //$NON-NLS-1$

	/** if toggling more then this many lines then use a busy indicator */
	private static final int TOGGLE_LINES_MAX_NO_BUSY_INDICATOR = 20;

	private static final ToggleLineCommentHandler toggleLineCommentHandler = new ToggleLineCommentHandler();

	@Override
	protected void processAction(final ITextEditor textEditor, final IStructuredDocument document,
			ITextSelection textSelection) {

		IStructuredModel model = null;
		DocumentRewriteSession session = null;
		boolean changed = false;

		try {
			// get text selection lines info
			int selectionStartLine = textSelection.getStartLine();
			int selectionEndLine = textSelection.getEndLine();

			int selectionEndLineOffset = document.getLineOffset(selectionEndLine);
			int selectionEndOffset = textSelection.getOffset() + textSelection.getLength();

			// adjust selection end line
			if ((selectionEndLine > selectionStartLine) && (selectionEndLineOffset == selectionEndOffset)) {
				selectionEndLine--;
				selectionEndLineOffset = document.getLineInformation(selectionEndLine).getOffset();
			}

			int selectionStartLineOffset = document.getLineOffset(selectionStartLine);
			ITypedRegion[] lineTypedRegions = document.computePartitioning(selectionStartLineOffset,
					selectionEndLineOffset - selectionStartLineOffset);

			if (lineTypedRegions != null && lineTypedRegions.length >= 1
					&& (lineTypedRegions[0].getType().equals("org.eclipse.wst.html.HTML_DEFAULT")
							|| lineTypedRegions[0].getType().equals("com.dubture.twig.TWIG_DEFAULT"))) {

				// save the selection position since it will be changing
				Position selectionPosition = null;
				selectionPosition = new Position(textSelection.getOffset(), textSelection.getLength());
				document.addPosition(selectionPosition);

				model = StructuredModelManager.getModelManager().getModelForEdit(document);
				if (model != null) {
					// makes it so one undo will undo all the edits to the
					// document
					model.beginRecording(this, SSEUIMessages.ToggleComment_label,
							SSEUIMessages.ToggleComment_description);

					// keeps listeners from doing anything until updates are all
					// done
					model.aboutToChangeModel();
					if (document instanceof IDocumentExtension4) {
						session = ((IDocumentExtension4) document)
								.startRewriteSession(DocumentRewriteSessionType.UNRESTRICTED);
					}
					changed = true;

					// get the display for the editor if we can
					Display display = null;
					if (textEditor instanceof StructuredTextEditor) {
						StructuredTextViewer viewer = ((StructuredTextEditor) textEditor).getTextViewer();
						if (viewer != null) {
							display = viewer.getControl().getDisplay();
						}
					}

					// create the toggling operation
					IRunnableWithProgress toggleCommentsRunnable = new ToggleLinesRunnable(
							model.getContentTypeIdentifier(), document, selectionStartLine, selectionEndLine, display);

					// if toggling lots of lines then use progress monitor else
					// just
					// run the operation
					if ((selectionEndLine - selectionStartLine) > TOGGLE_LINES_MAX_NO_BUSY_INDICATOR
							&& display != null) {
						ProgressMonitorDialog dialog = new ProgressMonitorDialog(display.getActiveShell());
						dialog.run(false, true, toggleCommentsRunnable);
					} else {
						toggleCommentsRunnable.run(new NullProgressMonitor());
					}
				}
			} else {
				org.eclipse.core.expressions.EvaluationContext evaluationContext = new org.eclipse.core.expressions.EvaluationContext(
						null, "") {
					@Override
					public Object getVariable(String name) {
						if (ISources.ACTIVE_EDITOR_NAME.equals(name)) {
							return textEditor;
						}
						return null;
					}
				};
				org.eclipse.core.commands.ExecutionEvent executionEvent = new org.eclipse.core.commands.ExecutionEvent(
						null, Collections.EMPTY_MAP, new Event(), evaluationContext);
				toggleLineCommentHandler.execute(executionEvent);
			}

		} catch (InvocationTargetException e) {
			Logger.logException("Problem running toggle comment progess dialog.", e); //$NON-NLS-1$
		} catch (InterruptedException e) {
			Logger.logException("Problem running toggle comment progess dialog.", e); //$NON-NLS-1$
		} catch (BadLocationException e) {
			Logger.logException("The given selection " + textSelection + " must be invalid", e); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (ExecutionException e) {
		} finally {
			// clean everything up
			if (session != null && document instanceof IDocumentExtension4) {
				((IDocumentExtension4) document).stopRewriteSession(session);
			}

			if (model != null) {
				model.endRecording(this);
				if (changed) {
					model.changedModel();
				}
				model.releaseFromEdit();
			}
		}

	}

	private static class ToggleLinesRunnable implements IRunnableWithProgress {
		private IStructuredDocument fDocument;
		private int fSelectionStartLine;
		private int fSelectionEndLine;
		private Display fDisplay;

		protected ToggleLinesRunnable(String contentTypeIdentifier, IStructuredDocument document,
				int selectionStartLine, int selectionEndLine, Display display) {
			this.fDocument = document;
			this.fSelectionStartLine = selectionStartLine;
			this.fSelectionEndLine = selectionEndLine;
			this.fDisplay = display;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

			monitor.beginTask(SSEUIMessages.ToggleComment_progress, this.fSelectionEndLine - this.fSelectionStartLine);
			try {
				boolean allLinesCommented = true;
				for (int i = fSelectionStartLine; i <= fSelectionEndLine; i++) {
					try {
						if (fDocument.getLineLength(i) > 0) {
							if (!isCommentLine(fDocument, i)) {
								allLinesCommented = false;
								break;
							}
						}
					} catch (BadLocationException e) {
						Logger.log(Logger.WARNING_DEBUG, e.getMessage(), e);
					}
				}
				// toggle each line so long as task not canceled
				for (int line = this.fSelectionStartLine; line <= this.fSelectionEndLine
						&& !monitor.isCanceled(); ++line) {

					// allows the user to be able to click the cancel button
					readAndDispatch(this.fDisplay);

					// get the line region
					IRegion lineRegion = this.fDocument.getLineInformation(line);

					// don't toggle empty lines
					String content = this.fDocument.get(lineRegion.getOffset(), lineRegion.getLength());
					if (content.trim().length() > 0) {
						// try to get a line comment type

						// toggle the comment on the line
						if (allLinesCommented) {
							remove(this.fDocument, lineRegion.getOffset(), lineRegion.getLength(), true);
						} else {
							apply(this.fDocument, lineRegion.getOffset(), lineRegion.getLength());
						}
					}
					monitor.worked(1);
				}
			} catch (BadLocationException e) {
				Logger.logException("Bad location while toggling comments.", e); //$NON-NLS-1$
			}
			// done work
			monitor.done();

		}

		/**
		 * <p>
		 * Assumes that the given offset is at the begining of a line and adds
		 * the line comment prefix there
		 * </p>
		 *
		 */
		public void apply(IStructuredDocument document, int offset, int length) throws BadLocationException {

			document.replace(offset, 0, OPEN_COMMENT + " ");
			document.replace(offset + length + 3, 0, CLOSE_COMMENT);

		}

		/**
		 * <p>
		 * This method modifies the given document to remove the given comment
		 * prefix at the given comment prefix offset and the given comment
		 * suffix at the given comment suffix offset. In the case of removing a
		 * line comment that does not have a suffix, pass <code>null</code> for
		 * the comment suffix and it and its associated offset will be ignored.
		 * </p>
		 *
		 * <p>
		 * <b>NOTE:</b> it is a good idea if a model is at hand when calling
		 * this to warn the model of an impending update
		 * </p>
		 *
		 * @param document
		 *            the document to remove the comment from
		 * @param commentPrefixOffset
		 *            the offset of the comment prefix
		 * @param commentSuffixOffset
		 *            the offset of the comment suffix (ignored if
		 *            <code>commentSuffix</code> is <code>null</code>)
		 * @param commentPrefix
		 *            the prefix of the comment to remove from its associated
		 *            given offset
		 * @param commentSuffix
		 *            the suffix of the comment to remove from its associated
		 *            given offset, or null if there is not suffix to remove for
		 *            this comment
		 */
		protected static void uncomment(IDocument document, int commentPrefixOffset, String commentPrefix,
				int commentSuffixOffset, String commentSuffix) {

			try {
				// determine if there is a space after the comment prefix that
				// should also be removed
				int commentPrefixLength = commentPrefix.length();
				String postCommentPrefixChar = document.get(commentPrefixOffset + commentPrefix.length(), 1);
				if (postCommentPrefixChar.equals(" ")) {
					commentPrefixLength++;
				}

				// remove the comment prefix
				document.replace(commentPrefixOffset, commentPrefixLength, ""); //$NON-NLS-1$

				if (commentSuffix != null) {
					commentSuffixOffset -= commentPrefixLength;

					// determine if there is a space before the comment suffix
					// that should also be removed
					int commentSuffixLength = commentSuffix.length();
					String preCommentSuffixChar = document.get(commentSuffixOffset - 1, 1);
					if (preCommentSuffixChar.equals(" ")) {
						commentSuffixLength++;
						commentSuffixOffset--;
					}

					// remove the comment suffix
					document.replace(commentSuffixOffset, commentSuffixLength, ""); //$NON-NLS-1$
				}
			} catch (BadLocationException e) {
				Logger.log(Logger.WARNING_DEBUG, e.getMessage(), e);
			}
		}

		/**
		 * <p>
		 * Assumes that the given offset is at the beginning of a line that is
		 * commented and removes the comment prefix from the beginning of the
		 * line, leading whitespace on the line will not prevent this method
		 * from finishing correctly
		 * </p>
		 *
		 */
		public void remove(IStructuredDocument document, int offset, int length, boolean removeEnclosing)
				throws BadLocationException {

			String content = document.get(offset, length);

			int origOffset = offset;

			int innerOffset = content.indexOf(OPEN_COMMENT);
			if (innerOffset > 0) {
				offset += innerOffset;
			}

			// remove the opening tag
			uncomment(document, offset, OPEN_COMMENT, -1, null);

			innerOffset = content.indexOf(CLOSE_COMMENT);
			if (innerOffset > 0) {
				origOffset += innerOffset;
			}

			// take into account the removed opening comment tag
			origOffset -= 3;

			// remove the closing tag
			uncomment(document, origOffset, CLOSE_COMMENT, -1, null);

		}

		/**
		 * <p>
		 * When calling {@link Display#readAndDispatch()} the game is off as to
		 * whose code you maybe calling into because of event
		 * handling/listeners/etc. The only important thing is that the UI has
		 * been given a chance to react to user clicks. Thus the logging of most
		 * {@link Exception}s and {@link Error}s as caused by
		 * {@link Display#readAndDispatch()} because they are not caused by this
		 * code and do not effect it.
		 * </p>
		 *
		 * @param display
		 *            the {@link Display} to call <code>readAndDispatch</code>
		 *            on with exception/error handling.
		 */
		private void readAndDispatch(Display display) {
			try {
				display.readAndDispatch();
			} catch (Exception e) {
				Logger.log(Logger.WARNING, "Exception caused by readAndDispatch, not caused by or fatal to caller", e);
			} catch (LinkageError e) {
				Logger.log(Logger.WARNING, "LinkageError caused by readAndDispatch, not caused by or fatal to caller",
						e);
			} catch (VirtualMachineError e) {
				// re-throw these
				throw e;
			} catch (ThreadDeath e) {
				// re-throw these
				throw e;
			} catch (Error e) {
				// catch every error, except for a few that we don't want to
				// handle
				Logger.log(Logger.WARNING, "Error caused by readAndDispatch, not caused by or fatal to caller", e);
			}
		}

	}

	public static boolean isCommentLine(IDocument document, int line) {
		boolean isComment = false;

		try {
			IRegion region = document.getLineInformation(line);
			String string = document.get(region.getOffset(), region.getLength()).trim();

			// empty line
			if (string.trim().length() == 0)
				return true;

			isComment = (string.length() >= OPEN_COMMENT.length() && string.startsWith(OPEN_COMMENT))
					|| (string.length() >= OPEN_COMMENT.length() && string.startsWith(OPEN_COMMENT));

		} catch (BadLocationException e) {
			Logger.log(Logger.WARNING_DEBUG, e.getMessage(), e);
		}
		return isComment;
	}
}
