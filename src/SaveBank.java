import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

abstract class SaveBank{
	protected int kind, restTime;
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList);
}