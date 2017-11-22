/**
 * Class to show messages inside a text area
 */
package utilities;

import javax.swing.JTextArea;

public class TextAreaWriter {

	final String ENDLINE = System.getProperty("line.separator");

	JTextArea msg;

	public TextAreaWriter(JTextArea msgField) {
		msg = msgField;
	}

	public void write(String text) {
		msg.append(text);
		msg.setCaretPosition(msg.getDocument().getLength());
	}

	public void writeLine(String text) {
		msg.append(text + ENDLINE);
		msg.setCaretPosition(msg.getDocument().getLength());
	}

	public void writeLine(long text) {
		writeLine(Long.toString(text));
	}

	public void writeLine(int text) {
		writeLine(Integer.toString(text));
	}

	public void writeLine(boolean text) {
		writeLine(Boolean.toString(text));
	}

	public void writeLine(double text) {
		writeLine(Double.toString(text));
	}

	public void writeLine(Float text) {
		writeLine(Float.toString(text));
	}

	public void writeEmptyLine() {
		msg.append(ENDLINE);
		msg.setCaretPosition(msg.getDocument().getLength());
	}

	public void clear() {
		msg.setText("");
	}

	public void removeLine() {
		msg.setText(msg.getText().substring(0, msg.getText().lastIndexOf(ENDLINE)));
		msg.setCaretPosition(msg.getDocument().getLength());
	}
}
