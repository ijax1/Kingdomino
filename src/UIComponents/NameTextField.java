package UIComponents;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.StyledDocument;



import resources.Resources;

public class NameTextField extends JTextField {
	public static final int MAX_CHARS = 16;
	public NameTextField(String text) {
		super(text);
		setFont(Resources.getMedievalFont(10));
		selectAll();
		setPreferredSize(new Dimension(200,50));
		AbstractDocument doc = (AbstractDocument) getDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARS));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(getText().length() > MAX_CHARS) {
					e.consume();
				}
			}
		});
	}

	/* Adapted from TextComponentDemo in the Java Tutorials
	 * 
	 * https://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html#document */
	class DocumentSizeFilter extends DocumentFilter {
		private int maxChars;
		public DocumentSizeFilter(int max) {
			maxChars = max;
		}
		@Override
		public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {

			if ((fb.getDocument().getLength() + str.length()) <= maxChars) {
				super.insertString(fb, offs, str, a);
			} else {
				//Toolkit.getDefaultToolkit().beep();
			}
		}

		public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
			//This rejects the entire replacement if it would make
			//the contents too long. Another option would be
			//to truncate the replacement string so the contents
			//would be exactly maxCharacters in length.
			if ((fb.getDocument().getLength() + str.length() - length) <= maxChars) {
				super.replace(fb, offs, length, str, a);
			} else {
				//Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}
