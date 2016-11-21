import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Keyboard extends JFrame implements ActionListener{

	File keyboardfile[];
	File drumfile[];
//	FileInputStream filestream; 
//	AudioStream sound;
	AudioInputStream audioin;
	Clip clip;
	String keyboard[] = {"도.wav","레.wav", "미.wav", "파.wav", "솔.wav","라.wav", "시.wav", "도(높은).wav"};
	String drum[] = {"K.wav", "H.wav", "S.wav", "HH.wav"};
	boolean rec_piano = false;
	boolean rec_drum = false;
	long start = 0, end = 0;
	int rest;
	KeyPanel p;
	LinkedList<Integer> playlist_keyboard;
	LinkedList<Integer> playlist_drum;
	JComboBox<String> comboBox;
	
	Thread thread_keyboard;
	Thread thread_drum;
	
	public Keyboard(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new KeyPanel();
		//start = System.currentTimeMillis();
				
		setContentPane(p); // KeyPanel설정
  
		setSize(500,280); // size
		getContentPane().setLayout(null);
		
		playlist_keyboard = new LinkedList<Integer>();
		playlist_drum = new LinkedList<Integer>();
		
		Playkb pk = new Playkb(playlist_keyboard, keyboardfile);
		Playkb pd = new Playkb(playlist_drum, drumfile);
		
		try{
			keyboardfile = new File[8];
			for(int i=0; i<8; i++)
				keyboardfile[i] = new File(keyboard[i]);
			
			drumfile = new File[4];
			for(int i=0; i<4; i++)
				drumfile[i] = new File(drum[i]);
		}
		catch(Exception e)
		{}

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"피아노", "드럼"}));
		comboBox.setBounds(12, 10, 140, 25);
		getContentPane().add(comboBox);
		comboBox.addActionListener(this);
		JButton recbutton1 = new JButton("1번 뱅크 녹음하기");		
		recbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rec_piano)
					start = System.currentTimeMillis();
				
				rec_piano = !rec_piano;
				System.out.println(rec_piano);
				p.requestFocus();
			}
		});
		recbutton1.setBounds(12, 80, 140, 25);
		getContentPane().add(recbutton1);
		
		JButton playbutton1 = new JButton("1번 뱅크 재생하기");
		playbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pk.getState()==Thread.State.NEW){
					pk.start();
				}
				p.requestFocus();
			}
		});
		playbutton1.setBounds(174, 80, 140, 25);
		getContentPane().add(playbutton1);
		
		JButton bankclear1 = new JButton("1번 뱅크 비우기");
		bankclear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playlist_keyboard.clear();
				p.requestFocus();
			}
		});
		bankclear1.setBounds(340, 80, 140, 25);
		getContentPane().add(bankclear1);
		
		JButton recbutton2 = new JButton("2번 뱅크 녹음하기");
		recbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rec_drum)
					start = System.currentTimeMillis();
				
				rec_drum = !rec_drum;
				System.out.println(rec_drum);
				p.requestFocus();
			}
		});
		recbutton2.setBounds(12, 115, 140, 25);
		getContentPane().add(recbutton2);
		
		JButton playbutton2 = new JButton("2번 뱅크 재생하기");
		playbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pk.getState()==Thread.State.NEW){
					pd.start();
				}
				p.requestFocus();
			}
		});
		playbutton2.setBounds(174, 115, 140, 25);
		getContentPane().add(playbutton2);
		
		JButton bankclear2 = new JButton("2번 뱅크 비우기");
		bankclear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playlist_drum.clear();
				p.requestFocus();
			}
		});
		bankclear2.setBounds(340, 115, 140, 25);
		getContentPane().add(bankclear2);
		
		JButton btnNewButton_3 = new JButton("모두 재생하기");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pk.getState()==Thread.State.NEW){
					pk.start();
					pd.start();
				}
				p.requestFocus();
			}
		});
		btnNewButton_3.setBounds(12, 180, 140, 25);
		getContentPane().add(btnNewButton_3);
		
		setVisible(true); // 표시여부
		p.requestFocus(); // 권한 요청
	}
	
	class KeyPanel extends JPanel {
		JLabel la = new JLabel("키값이 입력될 위치"); // 레이블 생성
		int fileidx = 0, comboboxidx = 0;
		String key;
		public KeyPanel(){ // 패널 생성자
			add(la); // 레이블 추가
			
			this.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e){ // 키가 눌렷을때의 이벤트
					end = System.currentTimeMillis();
					rest = (int)(end - start);
					System.out.println(rest);
					key = KeyEvent.getKeyText(e.getKeyCode()); // 키값
					comboboxidx = comboBox.getSelectedIndex();
					//requestFocus();
					switch(comboboxidx)
					{
					case 0:
						piano();
						break;
					case 1:
						drum();
						break;	
					}
					start = System.currentTimeMillis();
				}
			});
		}
		void piano()
		{
			switch(key)
			{
			case "A":
				System.out.print("도 ");
				fileidx = 0;				
				break;
			case "S":
				System.out.print("레 ");
				fileidx = 1;
				break;
			case "D":
				System.out.print("미 ");
				fileidx = 2;
				break;
			case "F":
				System.out.print("파 ");
				fileidx = 3;
				break;
			case "H":
				System.out.print("솔 ");
				fileidx = 4;
				break;
			case "J":
				System.out.print("라 ");
				fileidx = 5;
				break;
			case "K":
				System.out.print("시 ");
				fileidx = 6;
				break;
			case "L":
				System.out.print("도 ");
				fileidx = 7;
				break;
			
			default :
				fileidx = -1;
			}
			
			try
			{
				if(fileidx == -1)
					return;
				
				audioin = AudioSystem.getAudioInputStream(keyboardfile[fileidx]);
				clip = AudioSystem.getClip();
				clip.open(audioin);
				
//				if(clip.isRunning())
//					clip.stop();
//				clip.setFramePosition(0);
				
				clip.start();
				
				if(rec_piano)
				{
					if(playlist_keyboard.isEmpty())
						playlist_keyboard.add(0);
					else
						playlist_keyboard.add(rest);
					
					playlist_keyboard.add(fileidx);
					
				}
			}
			catch(Exception exp)
			{}
		}
		void drum()
		{
			switch(key)
			{
			case "A":
				System.out.print("킥 ");
				fileidx = 0;				
				break;
			case "S":
				System.out.print("하이햇 ");
				fileidx = 1;
				break;
			case "D":
				System.out.print("스네어 ");
				fileidx = 2;
				break;
			case "F":
				System.out.print("촤아~ ");
				fileidx = 3;
			}
			
			try
			{
				if(fileidx == -1)
					return;
				
				audioin = AudioSystem.getAudioInputStream(drumfile[fileidx]);
				clip = AudioSystem.getClip();
				clip.open(audioin);
				
//				if(clip.isRunning())
//					clip.stop();		
//				clip.setFramePosition(0);
				
				clip.start();
				if(rec_drum)
				{
					if(playlist_drum.isEmpty())
						playlist_drum.add(0);
					else
						playlist_drum.add(rest);

					playlist_drum.add(fileidx);
				}
			}
			catch(Exception exp)
			{}
		}
	}
	
	
	public static void main(String args[]){
		new Keyboard();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p.requestFocus();
	}
}
