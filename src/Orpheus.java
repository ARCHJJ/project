import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JOptionPane;
@SuppressWarnings({ "unchecked" })

/**
 * @brief �������α׷��� UI�� �������� ������, ��ư�׼Ǹ����ʸ� �����ϴ� Ŭ����
 */
public class Orpheus extends OrpheusComponents implements ActionListener, WindowListener{

	private boolean isBeginner;
	private int totalBankCount_before;
	private String[] tmp;
	private MainGate mainGate;
	/**
	 * @brief ������
	 * ��ũ���, �۾������ �ַε��, ���ֽ��۰� UI�� �����带 �����Ѵ�.
	 * ��ư�׼Ǹ����ʸ� �߰��Ѵ�.
	 * @wbp.parser.entryPoint
	 */
	public Orpheus(MainGate mainGate) {
		this.mainGate = mainGate;

		bankPlay = new Play();
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay = new Play[4];
		tmp = new String[8];
		for(int i=0; i<4; i++)	
		{
			tmp[i] = btn_Solo[i].getText();
			tmp[i+4] = "�� ��("+(i+1)+")";
			taskPlay[i] = new Play();
			taskPlay[i].setDaemon(true);
			taskPlay[i].ThreadStart();
			
			//btn_Solo[i].addActionListener(this);
		}
		
		metronome = new Metronome();
		metronome.setDaemon(true);
		metronome.ThreadStart();
		
		save = new SaveScore();
		open = new OpenScore();
		
		DrumPlay = new PlayDrumRhythm();
		
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
		btn_SaveScore.addActionListener(this);
		btn_OpenScore.addActionListener(this);
		btn_RhythmInsert.addActionListener(this);
		btn_RhythmListen.addActionListener(this);
		btn_Solo[0].addActionListener(this);
		btn_Solo[1].addActionListener(this);
		btn_Solo[2].addActionListener(this);
		btn_Solo[3].addActionListener(this);
		
		mainFrame.addWindowListener(this);
		setField(0);
	}
	/**
	 * @brief ������������ Visible�� �����ϴ� �޼ҵ�
	 */
	public void setVisible(boolean tf)
	{
		mainFrame.setVisible(tf);
	}
	/**
	 * @brief ���α׷��� ������ �ʺ��ڿ����� �����ڿ����� �Ǵ��ϴ� �޼ҵ�
	 * @param boolean tf : true�̸� �ʺ��ڿ�, false�̸� �����ڿ��̴�.
	 */
	//public void isBeginner(boolean tf, boolean isFirst)
	public void isBeginner(boolean tf)
	{
		BeatSet.setVisible(!tf);
		lbl_SelectBeatSet.setVisible(!tf);
		isBeginner = tf;
	}
	/**
	 * @brief ��ư�׼Ǹ�����
	 * @param ActionEvent e
	 * ��ư�� ������ �� �ش� ��ư�� �̸��� ���� �ʿ��� �޼ҵ带 ȣ���Ѵ�.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "�ǾƳ�" :
			setField(0);
			break;
			
		case "�巳" :
			setField(1);
			break;
			
		case "��Ÿ" :
			setField(2);
			break;
			
		case "���̽�" :
			setField(3);
			break;
			
		case "��ũ ����":
			saveBank();
			break;
		
		case "��ũ ���":
		case "��  ��":
			ListenBank();
			break;
		
		case "�ǾƳ�ַ�":
		case "�� ��(1)":
			ListenSolo(0);
			break;
			
		case "�巳�ַ�":
		case "�� ��(2)":
			ListenSolo(1);
			break;
			
		case "��Ÿ�ַ�":
		case "�� ��(3)":
			ListenSolo(2);
			break;
			
		case "���̽��ַ�":
		case "�� ��(4)":
			ListenSolo(3);
			break;
			
		case "���ֽ���":
		case "��������":
			musicQ();
			break;
			
		case "�����":
			init();
			break;
			
		case "�ڵ� �Է�":
			inputCode();
			break;
			
		case "�ڵ� ���":
			playCode();
			break;
			
		case "Ű���忬��":
			keyboardPlay.setVisible(true);
			break;
			
		case "���� ����":
			saveScore();
			break;
			
		case "���� ����":
			openScore();
			break;
			
		case "���� �Է�":
			inputrhythm();
			break;
		case "���� ���":
			playrhythm();
			break;
		}
	}
	
	/**
	 * @throws InterruptedException 
	 * @throws NumberFormatException 
	 * @brief �巳, ���̽��� ������ ����Ѵ�.
	 */
	public void playrhythm()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		
		DrumPlay.setBeat(RestTimeSetup.time_signature_numerator, RestTimeSetup.time_signature_denominator, RestTimeSetup.result);
		DrumPlay.play_Rhythm(files.getSoundClips(1), BeatSet.getSelectedIndex(), Integer.parseInt((String) RhythmChoice.getSelectedItem()));
	}
	
	/**
	 * @brief �巳, ���̽��� ������ �Է��Ѵ�.
	 */
	public void inputrhythm()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		
		DrumRhythm.setBeat(RestTimeSetup.time_signature_numerator, RestTimeSetup.time_signature_denominator);
		DrumRhythm.inputDrum(BeatSet.getSelectedIndex(), Integer.parseInt((String) RhythmChoice.getSelectedItem()), STF[1], STB[1], table_Field[2]);	
	}
	/**
	 * @brief table_Field�� �Ǳ⿡ ���� �ٸ��� �����Ѵ�.
	 * @param int idx : 0������ 3������ ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
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
	 * @brief table_Field[1], table_Field[2]�� ������ ��ũ�� �����Ѵ�.
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
		JOptionPane.showMessageDialog(null, num+"�� ��ũ ����Ϸ�.");
		totalBankCount++;
	}

	/**
	 * @brief ��ũ�� �߰��Ǹ� BankChoice���� ������ �� �ֵ��� �߰��Ѵ�.
	 * @param int size : BankChoice�� ũ��
	 */
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	
	/**
	 * @brief ��ũ�� ���� �� ���ڱ�Ģ�� ������ Ȯ���ϴ� �޼ҵ�
	 * @param BeatField BeatField : ���������� ������ �ִ� Ŭ������ ��ü
	 * @return
	 */
	public boolean OutofBeat(BeatField BeatField)
	{
		if(isBeginner)
			return true;
		
		if(BeatField.out_max(table_Field[1]) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "������ ���ڰ� �����մϴ�.");
			return false;
		}
		if(BeatField.out_max(table_Field[1]) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "������ ���ڰ� �ʰ��Ͽ����ϴ�.");
			return false;
		}
		return true;
	}
	
	/**
	 * @brief BankChoice�� ���� ���õ� ��ũ�� ����Ѵ�.
	 * UI�� ������� �����Ѵ�.
	 */
	public void ListenBank()
	{
		if(stop[4])
		{
			stop[4] = false;
			btn_BankListen.setText("��ũ ���");
			bankPlay.standby();
			return;
		}
		stop[4] = true;
		btn_BankListen.setText("��  ��");
		int BankNum = BankChoice.getSelectedIndex();
		if(BankNum!=0)
		{
			bankPlay.setBank(STF[IDX].BankList.get(BankNum), files.getSoundClips(IDX));
			bankPlay.action();
		}
	}
	
	/**
	 * @brief �۾�����ٿ��� Ư�� �Ǳ����ٸ� ����Ѵ�.
	 * UI�� ������� �����Ѵ�.
	 * @param int idx : 0������ 3������ ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	 */
	public void ListenSolo(int idx)
	{
		if(stop[idx])
		{
			stop[idx] = false;
			btn_Solo[idx].setText(tmp[idx]);
			taskPlay[idx].standby();
			return;
		}
		stop[idx] = true;
		btn_Solo[idx].setText(tmp[idx+4]);
		taskPlay[idx].multySet(table_Task[idx], STF[idx].BankList, files.getSoundClips(idx), Mute[idx]);
		taskPlay[idx].action();
	}
	
	/**
	 * @brief �۾�����ٿ� �ִ� ��� ������ ����Ѵ�.
	 * taskPlay[0], taskPlay[1], taskPlay[2], taskPlay[3], UI�� ������� �����Ѵ�.
	 */
	public void musicQ()
	{
		if(stop[5])
		{
			stop[5] = false;
			btn_start.setText("���ֽ���");
			
			bankPlay.standby();
			for(Play tmp : taskPlay)
				tmp.standby();
			
			return;
		}
		stop[5] = true;
		btn_start.setText("��������");
		int max = 0;
		
		//���ֽ��� �޼ҵ�
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
	 * @brief ���� ���� �ִ� table_Field[1], table_Field[2]�� ����� �ʱ���·� �ǵ�����.
	 */
	public void init()
	{
		STB[IDX].Init();
		STF[IDX].Init();
		
		STB[IDX].setCellOption(table_Field[1]);
		STF[IDX].setCellOption(table_Field[2]);
	}

	/**
	 * @brief ���� �����ִ� JTable�� �̸� ������� �ڵ带 �����Ѵ�.
	 */
	public void inputCode()
	{
		Code.addCode(table_Field[2], RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF[2].addColumn(table_Field[2].getModel().getColumnCount()-1);
		STF[2].setCellOption(table_Field[2]);
	}

	/**
	 * @brief RootChord, ChildChord �� ���� ���õ� �ڵ带 ����Ѵ�.
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
	 * @brief �۾� ������ �ؽ�Ʈ ���� ���·� �����Ѵ�.
	 */
	public void saveScore()
	{
		save.setBeat(BPMSet.getText(), BeatSet.getSelectedIndex());
		save.save_Score(STF, table_Task);
		isSave = true;
		totalBankCount_before = totalBankCount;
		
		JOptionPane.showMessageDialog(null, "����Ϸ�!");
	}
	
	/**
	 * @brief ���� �۾� ������ �ҷ��´�.
	 */
	public void openScore()
	{
			open.open_Score(BeatSet, BPMSet, STF, STT, table_Task);
			RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
	}
	
	/**
	 * @brief ��ũ��� ��ư�� �����Ѵ�.
	 * �ַε��, ���ֽ����� ������ �� �ߺ�Ŭ���� �����ϱ� ���� �ش� Ŭ�������� btn_BankListen�� Enable(false)��Ų��. 
	 * @return btn_BankListen
	 */
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		
		if((!isSave&&totalBankCount>0) || (isSave&&(totalBankCount != totalBankCount_before)))
		{
			int sav;
			sav = JOptionPane.showConfirmDialog(null, "�۾��ϴ� ������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
	public void windowOpened(WindowEvent arg0) {}
}
