import java.util.LinkedList;

abstract class SaveBank{
	protected int kind;
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
}