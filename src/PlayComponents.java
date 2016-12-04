import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief 연주기능에 스레드를 적용시키기 위한 클래스
 * 컴포넌트 집합체로서 Play 클래스의 부모클래스가 된다. 
 */
abstract class PlayComponents implements Runnable {
	
	//! Play클래스와 UI는 스레드로 동작한다.
	protected Thread thread;
	
	//! 작업대기줄 테이블
	protected JTable table_Task;

	//! 뱅크가 저장되어 있는 LinkedList
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! 음과 쉬는시간이 저장되어 있는 LinkedList
	protected LinkedList<Note> playlist;
	
	//! 부드러운 소리재생을 위해 잔향제거
	protected LinkedList<Clip> noise;
	
	//! playlist의 Iterator
	protected Iterator<Note> itNote;
	
	//! playlist.fileidx의 Iterator
	protected Iterator<Integer> itPlay;
	
	//! 음소거 여부를 판단하기 위한 JCheckBox
	protected JCheckBox mute;
	
	//! 솔로연주가 끝나면 버튼의 이름을 원래대로 되돌리기 위한 JButton 
	protected JButton btn_play;
	
	//! 연주시작 버튼의 이름을 원래대로 되돌리기 위한 JButton
	protected JButton btn_musicQ;

	//! 솔로연주가 끝나면 버튼의 이름을 원래대로 되돌리기 위한 클래스
	protected Swtch swtch_play;
	
	//! 솔로연주가 끝나면 버튼의 이름을 원래대로 되돌리기 위한 클래스
	protected Swtch swtch_musicQ;
	
	//! 버튼의 이름을 저장하기 위한 변수
	protected String btnName;
	
	//! 재생할 소리가 저장되어 있는 Clip[][]
	protected Clip[][] SoundClips;
	
	//! 소리파일을 재생하기 위한 객체
	protected Clip clip;
	
	//! 재생할 뱅크 번호
	protected int idx;
	
	//! 뱅크듣기와 솔로듣기||연주시작을 구분하는 변수
	protected boolean singleplay;
	
	//! 정지기능을 위한 변수
	protected boolean standby;
}
