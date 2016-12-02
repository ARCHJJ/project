import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief ���ֱ�ɿ� �����带 �����Ű�� ���� Ŭ����
 * ������Ʈ ����ü�μ� Play Ŭ������ �θ�Ŭ������ �ȴ�. 
 */
abstract class PlayComponents implements Runnable {
	
	//! PlayŬ������ UI�� ������� �����Ѵ�.
	protected Thread thread;
	
	//! �۾������ ���̺�
	protected JTable table_Task;

	//! ��ũ�� ����Ǿ� �ִ� LinkedList
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! ���� ���½ð��� ����Ǿ� �ִ� LinkedList
	protected LinkedList<Note> playlist;
	
	//! �ε巯�� �Ҹ������ ���� ��������
	protected LinkedList<Clip> noise;
	
	//! playlist�� Iterator
	protected Iterator<Note> itNote;
	
	//! playlist.fileidx�� Iterator
	protected Iterator<Integer> itPlay;
	
	//! ���Ұ� ���θ� �Ǵ��ϱ� ���� JCheckBox
	protected JCheckBox mute;
	
	//! ����� �Ҹ��� ����Ǿ� �ִ� Clip[][]
	protected Clip[][] SoundClips;
	
	//! �Ҹ������� ����ϱ� ���� ��ü
	protected Clip clip;
	
	//! ����� ��ũ ��ȣ
	protected int idx;
	
	//! ���� UI
	protected Orpheus ui;
	
	//! ��ũ���� �ַε��||���ֽ����� �����ϴ� ����
	protected boolean singleplay;
}
