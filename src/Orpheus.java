import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JOptionPane;
@SuppressWarnings({ "unchecked" })

/**
 * @brief 메인프로그램의 UI와 재생기능의 스레드, 버튼액션리스너를 설정하는 클래스
 */
public class Orpheus extends OrpheusComponents implements ActionListener, WindowListener{

	private boolean isBeginner;
	private int totalBankCount_before;
	private MainGate mainGate;
	/**
	 * @brief 생성자
	 * 뱅크듣기, 작업대기줄 솔로듣기, 연주시작과 UI의 스레드를 설정한다.
	 * 버튼액션리스너를 추가한다.
	 * @wbp.parser.entryPoint
	 */
	public Orpheus(MainGate mainGate) {
		this.mainGate = mainGate;
		//뱅크듣기
		bankPlay = new Play(this);
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay = new Play[4];

		for(int i=0; i<4; i++)	
		{
			taskPlay[i] = new Play(this);
			taskPlay[i].setDaemon(true);
			taskPlay[i].ThreadStart();
		}
		
		metronome = new Metronome(this);
		metronome.setDaemon(true);
		metronome.ThreadStart();
		
		save = new SaveScore();
		isSave = false;
		totalBankCount = 0;
		totalBankCount_before = 0;
		
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToBase.addActionListener(this);
		btn_start.addActionListener(this);
		btn_erase.addActionListener(this);
		btn_KeyboardPlay.addActionListener(this);
		btn_BankSave.addActionListener(this);
		btn_BankListen.addActionListener(this);
		btn_ChordListen.addActionListener(this);
		btn_ChordInsert.addActionListener(this);
		btn_PianoSolo.addActionListener(this);
		btn_DrumSolo.addActionListener(this);
		btn_GuitarSolo.addActionListener(this);
		btn_BaseSolo.addActionListener(this);
		btn_SaveScore.addActionListener(this);
		
		mainFrame.addWindowListener(this);
		setField(0);
	}
	/**
	 * @brief 메인프레임의 Visible을 설정하는 메소드
	 */
	public void setVisible(boolean tf)
	{
		mainFrame.setVisible(tf);
	}
	/**
	 * @brief 프로그램의 설정이 초보자용인지 숙련자용인지 판단하는 메소드
	 * @param boolean tf : true이면 초보자용, false이면 숙련자용이다.
	 */
	//public void isBeginner(boolean tf, boolean isFirst)
	public void isBeginner(boolean tf)
	{
		BeatSet.setVisible(!tf);
		lbl_SelectBeatSet.setVisible(!tf);
		isBeginner = tf;
	}
	/**
	 * @brief 버튼액션리스너
	 * @param ActionEvent e
	 * 버튼이 눌렸을 때 해당 버튼의 이름에 따라 필요한 메소드를 호출한다.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "피아노" :
			setField(0);
			break;
			
		case "드럼" :
			setField(1);
			break;
			
		case "기타" :
			setField(2);
			break;
			
		case "베이스" :
			setField(3);
			break;
			
		case "뱅크 저장":
			saveBank();
			break;
		
		case "뱅크 듣기":
			ListenBank();
			break;
		
		case "피아노솔로":
			ListenSolo(0);
			break;
			
		case "드럼솔로":
			ListenSolo(1);
			break;
			
		case "기타솔로":
			ListenSolo(2);
			break;
			
		case "베이스솔로":
			ListenSolo(3);
			break;
			
		case "연주시작":
			musicQ();
			break;
			
		case "지우기":
			init();
			break;
			
		case "코드 입력":
			inputCode();
			break;
			
		case "코드 듣기":
			playCode();
			break;
			
		case "키보드연주":
			keyboardPlay.setVisible(true);
			break;
			
		case "파일 저장":
			saveScore();
			break;
		}
	}

	/**
	 * @brief table_Field를 악기에 따라 다르게 세팅한다.
	 * @param int idx : 0번부터 3번까지 차례대로 피아노, 드럼, 기타, 베이스
	 */
	public void setField(int idx)
	{
		table_Field[0].setModel(STK[idx].getModel());
		table_Field[1].setModel(STB[idx].getModel());
		table_Field[2].setModel(STF[idx].getModel());
		
		table_Field[0].getColumnModel().getColumn(0).setPreferredWidth(60);
		STB[idx].setCellOption(table_Field[1]);
		STF[idx].setCellOption(table_Field[2]);
		
		setBankList(STT[idx].getItemList().length-1);
		
		switch(idx)
		{
		case 0:
			RootChord.setEnabled(false);
			ChildChord.setEnabled(false);
			
			RhythmChoice.setEnabled(false);
			btn_RhythmInsert.setEnabled(false);
			btn_RhythmListen.setEnabled(false);
			
			btn_ChordInsert.setEnabled(false);
			btn_ChordListen.setEnabled(false);
		break;

		case 2:
			RootChord.setEnabled(true);
			ChildChord.setEnabled(true);
			
			RhythmChoice.setEnabled(false);
			btn_RhythmInsert.setEnabled(false);
			btn_RhythmListen.setEnabled(false);
			
			btn_ChordInsert.setEnabled(true);
			btn_ChordListen.setEnabled(true);
		break;
	
		case 1:
		case 3:
			RootChord.setEnabled(false);
			ChildChord.setEnabled(false);
			
			RhythmChoice.setEnabled(true);
			btn_RhythmInsert.setEnabled(true);
			btn_RhythmListen.setEnabled(true);
			
			btn_ChordInsert.setEnabled(false);
			btn_ChordListen.setEnabled(false);
		}
		
		IDX = idx;
	}

	/**
	 * @brief table_Field[1], table_Field[2]의 내용을 뱅크에 저장한다.
	 */
	public void saveBank()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		if(!OutofBeat(STB[IDX]))
			return;
		
		STF[IDX].BankList.add(Bank[IDX].getBank(STB[IDX].getBeatResult(), RestTimeSetup.result));
		int num = STF[IDX].BankList.size()-1;
		STT[IDX].reflash(num);
		setBankList(num);
		JOptionPane.showMessageDialog(null, num+"번 뱅크 저장완료.");
		totalBankCount++;
	}

	/**
	 * @brief 뱅크가 추가되면 BankChoice에서 선택할 수 있도록 추가한다.
	 * @param int size : BankChoice의 크기
	 */
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	
	/**
	 * @brief 뱅크를 만들 때 박자규칙을 어겼는지 확인하는 메소드
	 * @param BeatField BeatField : 박자정보를 가지고 있는 클래스의 객체
	 * @return
	 */
	public boolean OutofBeat(BeatField BeatField)
	{
		if(isBeginner)
			return true;
		
		if(BeatField.out_max(table_Field[1]) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			return false;
		}
		if(BeatField.out_max(table_Field[1]) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			return false;
		}
		return true;
	}
	
	/**
	 * @brief BankChoice를 통해 선택된 뱅크를 재생한다.
	 * UI와 스레드로 동작한다.
	 */
	public void ListenBank()
	{
		int BankNum = BankChoice.getSelectedIndex();
		if(BankNum!=0)
		{
			bankPlay.setBank(STF[IDX].BankList.get(BankNum), files.getSoundClips(IDX));
			bankPlay.action();
		}
	}
	
	/**
	 * @brief 작업대기줄에서 특정 악기대기줄만 재생한다.
	 * UI와 스레드로 동작한다.
	 * @param int idx : 0번부터 3번까지 차례대로 피아노, 드럼, 기타, 베이스
	 */
	public void ListenSolo(int idx)
	{
		taskPlay[idx].multySet(table_Task[idx], STF[idx].BankList, files.getSoundClips(idx), Mute[idx]);
		taskPlay[idx].action();
	}
	
	/**
	 * @brief 작업대기줄에 있는 모든 내용을 재생한다.
	 * taskPlay[0], taskPlay[1], taskPlay[2], taskPlay[3], UI가 스레드로 동작한다.
	 */
	public void musicQ()
	{
		int max = 0;

		//연주시작 메소드
		for(int i=0; i<4; i++)
		{
			taskPlay[i].multySet(table_Task[i], STF[i].BankList, files.getSoundClips(i), Mute[i]);
			
			if(max < table_Task[i].getColumnCount())
				max = table_Task[i].getColumnCount();
		}
		
		if(metronome_Check.isSelected())
		{
			metronome.metronomeSet(files.getMetronomeClips(), RestTimeSetup.result, RestTimeSetup.time_signature_denominator, RestTimeSetup.time_signature_numerator, max-2);
			metronome.action();
		}

		for(int i=0; i<4; i++)
			taskPlay[i].action();
	}

	/**
	 * @brief 현재 보고 있는 table_Field[1], table_Field[2]를 지우고 초기상태로 되돌린다.
	 */
	public void init()
	{
		STB[IDX].Init();
		STF[IDX].Init();
		
		STB[IDX].setCellOption(table_Field[1]);
		STF[IDX].setCellOption(table_Field[2]);
	}

	/**
	 * @brief 현재 보고있는 JTable에 미리 만들어진 코드를 삽입한다.
	 */
	public void inputCode()
	{
		Code.addCode(table_Field[2], RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF[2].addColumn(table_Field[2].getModel().getColumnCount()-1);
		STF[2].setCellOption(table_Field[2]);
	}

	/**
	 * @brief RootChord, ChildChord 를 통해 선택된 코드를 재생한다.
	 */
	public void playCode()
	{
		Clip clip;
		String code = files.getGuitarCode()[RootChord.getSelectedIndex()][ChildChord.getSelectedIndex()];
		char ch;
		
		for(int i=0; i<6; i++)
		{
			ch = code.charAt(i);
			if(ch!='x')
			{
				clip = files.getSoundClips(2)[i][(int)ch-48];
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}
	
	/**
	 * @brief 작업 내용을 텍스트 파일 형태로 저장한다.
	 */
	public void saveScore()
	{
		save.setBeat(BPMSet.getText(), BeatSet.getSelectedIndex());
		save.save_Score(STF, table_Task);
		isSave = true;
		totalBankCount_before = totalBankCount;
		
		JOptionPane.showMessageDialog(null, "저장완료!");
	}
	
	/**
	 * @brief 뱅크듣기 버튼을 리턴한다.
	 * 솔로듣기, 연주시작을 눌렀을 때 중복클릭을 방지하기 위해 해당 클래스에서 btn_BankListen을 Enable(false)시킨다. 
	 * @return btn_BankListen
	 */
	public JButton getBankListenButton()
	{
		return btn_BankListen;
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		
		if((!isSave&&totalBankCount>0) || (isSave&&(totalBankCount != totalBankCount_before)))
		{
			int sav;
			sav = JOptionPane.showConfirmDialog(null, "작업하던 내용을 저장하시겠습니까?", "저장", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(sav == 0)
			{
				save.setBeat(BPMSet.getText(), BeatSet.getSelectedIndex());
				save.save_Score(STF, table_Task);
			}
		}
		
		for(int i=0; i<4; i++)
		{
			STF[i].BankListClear();
			STF[i].Init();
			STB[i].Init();
		}
		setField(0);
		isSave = false;
		totalBankCount = totalBankCount_before = 0;
		mainFrame.setVisible(false);
		mainGate.setVisible(true);
	}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
