//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.util.LinkedList;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.swing.*;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//
//public class _DEAD_Keyboard extends JFrame implements ActionListener{
//
//	//!
//	File keyboardfile[];
//	//!
//	File drumfile[];
////	FileInputStream filestream; 
////	AudioStream sound;
//	//!
//	AudioInputStream audioin;
//	//!
//	Clip clip;
//	//!
//	private String[][] PianoFnames;
//	//!
//	private String[] PianoFolder;
//	//!
//	private File[][] PianoFiles;
//
//	//!
//	String keyboard[] = {"��.wav","��.wav", "��.wav", "��.wav", "��.wav","��.wav", "��.wav", "��(����).wav"};
//	//!
//	String drum[] = {"K.wav", "H.wav", "S.wav", "HH.wav"};
//	//!
//	boolean rec_piano = false;
//	//!
//	boolean rec_drum = false;
//	//!
//	long start = 0, end = 0;
//	//!
//	int rest;
//	//!
//	KeyPanel p;
//	//!
//	LinkedList<Integer> playlist_keyboard;
//	//!
//	LinkedList<Integer> playlist_drum;
//	//!
//	JComboBox<String> comboBox;
//	
//	//!
//	Thread thread_keyboard;
//	//!
//	Thread thread_drum;
//	
//	/**
//	 * @brief ������
//	 */
//	public _DEAD_Keyboard(){
//		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		p = new KeyPanel();
//		//start = System.currentTimeMillis();
//				
//		setContentPane(p); // KeyPanel����
//  
//		setSize(500,280); // size
//		getContentPane().setLayout(null);
//		
//		playlist_keyboard = new LinkedList<Integer>();
//		playlist_drum = new LinkedList<Integer>();
//		
//		
//		
//	    PianoFolder = new String[]{
//	              "piano\\0\\"   
//	            , "piano\\1\\"
//	            , "piano\\2\\"
//	    };
//	    
//	    PianoFiles = new File[12][3];
//        PianoFnames = new String[][]{
//              {"C_4.wav" , "C_5.wav" , "C_6.wav" }
//             , {"C#_4.wav", "C#_5.wav", "C#_6.wav"}
//             , {"D_4.wav" , "D_5.wav" , "D_6.wav" }
//             , {"D#_4.wav", "D#_5.wav", "D#_6.wav"}
//             , {"E_4.wav" , "E_5.wav" , "E_6.wav" }
//             , {"F_4.wav" , "F_5.wav" , "F_6.wav" }
//             , {"F#_4.wav", "F#_5.wav", "F#_6.wav"}
//             , {"G_4.wav" , "G_5.wav" , "G_6.wav" }
//             , {"G#_4.wav", "G#_5.wav", "G#_6.wav"}
//             , {"A_4.wav" , "A_5.wav" , "A_6.wav" }
//             , {"A#_4.wav", "A#_5.wav", "A#_6.wav"}
//             , {"B_4.wav" , "B_5.wav" , "B_6.wav" }
//        };
//        for(int i=0; i<12; i++)
//           for(int j=0; j<3; j++)
//              PianoFiles[i][j] = new File(PianoFolder[j]+PianoFnames[i][j]); 
//        
//        Playkb pk = new Playkb(playlist_keyboard, PianoFiles);
//		//Playkb pd = new Playkb(playlist_drum, drumfile);
//		
//		//try{
//		//	keyboardfile = new File[8];
//		//	for(int i=0; i<8; i++)
//		//		keyboardfile[i] = new File(keyboard[i]);
//			
//		//	drumfile = new File[4];
//		//	for(int i=0; i<4; i++)
//		//		drumfile[i] = new File(drum[i]);
//		//}
//		//catch(Exception e)
//		//{}
//
//		comboBox = new JComboBox<String>();
//		comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"�ǾƳ�", "�巳"}));
//		comboBox.setBounds(12, 10, 140, 25);
//		getContentPane().add(comboBox);
//		comboBox.addActionListener(this);
//		JButton recbutton1 = new JButton("1�� ��ũ �����ϱ�");		
//		recbutton1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(!rec_piano)
//					start = System.currentTimeMillis();
//				
//				rec_piano = !rec_piano;
//				System.out.println(rec_piano);
//				p.requestFocus();
//			}
//		});
//		recbutton1.setBounds(12, 80, 140, 25);
//		getContentPane().add(recbutton1);
//		
//		JButton playbutton1 = new JButton("1�� ��ũ ����ϱ�");
//		playbutton1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(pk.getState()==Thread.State.NEW){
//					pk.start();
//				}
//				p.requestFocus();
//			}
//		});
//		playbutton1.setBounds(174, 80, 140, 25);
//		getContentPane().add(playbutton1);
//		
//		JButton bankclear1 = new JButton("1�� ��ũ ����");
//		bankclear1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				playlist_keyboard.clear();
//				p.requestFocus();
//			}
//		});
//		bankclear1.setBounds(340, 80, 140, 25);
//		getContentPane().add(bankclear1);
//		
//		JButton recbutton2 = new JButton("2�� ��ũ �����ϱ�");
//		recbutton2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(!rec_drum)
//					start = System.currentTimeMillis();
//				
//				rec_drum = !rec_drum;
//				System.out.println(rec_drum);
//				p.requestFocus();
//			}
//		});
//		recbutton2.setBounds(12, 115, 140, 25);
//		getContentPane().add(recbutton2);
//		
//		JButton playbutton2 = new JButton("2�� ��ũ ����ϱ�");
//		playbutton2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(pk.getState()==Thread.State.NEW){
//					//pd.start();
//				}
//				p.requestFocus();
//			}
//		});
//		playbutton2.setBounds(174, 115, 140, 25);
//		getContentPane().add(playbutton2);
//		
//		JButton bankclear2 = new JButton("2�� ��ũ ����");
//		bankclear2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				playlist_drum.clear();
//				p.requestFocus();
//			}
//		});
//		bankclear2.setBounds(340, 115, 140, 25);
//		getContentPane().add(bankclear2);
//		
//		JButton btnNewButton_3 = new JButton("��� ����ϱ�");
//		btnNewButton_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(pk.getState()==Thread.State.NEW){
//					pk.start();
//					//pd.start();
//				}
//				p.requestFocus();
//			}
//		});
//		btnNewButton_3.setBounds(12, 180, 140, 25);
//		getContentPane().add(btnNewButton_3);
//		
//		setVisible(true); // ǥ�ÿ���
//		p.requestFocus(); // ���� ��û
//	}
//	
//	/**
//	 * @brief 
//	 */
//	class KeyPanel extends JPanel {
//		JLabel la = new JLabel("Ű���� �Էµ� ��ġ"); // ���̺� ����
//		int fileidx = 0,fileidy = 0, comboboxidx = 0;
//		String key;
//		int mode;
//		public KeyPanel(){ // �г� ������
//			add(la); // ���̺� �߰�
//			
//			this.addKeyListener(new KeyAdapter() {
//				public void keyPressed(KeyEvent e){ // Ű�� ���������� �̺�Ʈ
//					end = System.currentTimeMillis();
//					rest = (int)(end - start);
//					System.out.println(rest);
//					
//					key = KeyEvent.getKeyText(e.getKeyCode());// Ű��
//					switch(key)
//					{
//					case "1":
//						mode = 1;
//						break;
//					case "2":
//						mode = 2;
//						break;
//					case "3":
//						mode = 3;
//						break;
//					}
//					
//					key = KeyEvent.getKeyText(e.getKeyCode());
//					comboboxidx = comboBox.getSelectedIndex();
//					if(comboboxidx==0)
//					{
//						switch(mode)
//						{
//						case 1:
//							piano1();
//							break;
//						case 2:
//							piano2();
//							break;
//						case 3:
//							piano3();
//							break;
//						}
//					}
//					else if(comboboxidx==1)
//					{
//						drum();
//					}
//					start = System.currentTimeMillis();
//				}
//			});
//		}
//		/**
//		 * @brief 
//		 */
//		
//		void piano1()
//		{
//			switch(key)
//			{
//			case "A":
//				System.out.print("C_4");
//				fileidx = 0;
//	            fileidy = 0;				
//				break;
//			case "W":
//				System.out.print("C_#4");
//				fileidx = 1;
//		        fileidy = 0;
//				break;
//			case "S":
//				System.out.print("D_4");
//				fileidx = 2;
//	            fileidy = 0;
//				break;
//			case "E":
//				System.out.print("D#_4");
//				fileidx = 3;
//		        fileidy = 0;
//				break;
//			case "D":
//				System.out.print("E_4");
//				fileidx = 4;
//	            fileidy = 0;
//				break;
//			case "F":
//				System.out.print("F_4");
//				fileidx = 5;
//	            fileidy = 0;
//				break;
//			case "T":
//				System.out.print("F#_4");
//				fileidx = 6;
//	            fileidy = 0;
//				break;
//			case "G":
//				System.out.print("G_4");
//				fileidx = 7;
//	            fileidy = 0;
//				break;
//			case "Y":
//				System.out.print("G_#4");
//				fileidx = 8;
//	            fileidy = 0;
//				break;
//			case "H":
//				System.out.print("A_4");
//				fileidx = 9;
//	            fileidy = 0;
//				break;
//			case "U":
//				System.out.print("A#_4");
//				fileidx = 10;
//	            fileidy = 0;
//				break;
//			case "J":
//				System.out.print("B_4");
//				fileidx = 11;
//	            fileidy = 0;
//				break;
//			default:
//				fileidx = -1;		
//			}
//			pianoPlay(fileidx,fileidy);
//		}
//			
//		void piano2()
//		{
//			switch(key)
//			{
//			case "A":
//				System.out.print("C_5");
//				fileidx = 0;
//	            fileidy = 1;				
//				break;
//			case "W":
//				System.out.print("C_#5");
//				fileidx = 1;
//		        fileidy = 1;
//				break;
//			case "S":
//				System.out.print("D_5");
//				fileidx = 2;
//	            fileidy = 1;
//				break;
//			case "E":
//				System.out.print("D#_5");
//				fileidx = 3;
//		        fileidy = 1;
//				break;
//			case "D":
//				System.out.print("E_5");
//				fileidx = 4;
//	            fileidy = 1;
//				break;
//			case "F":
//				System.out.print("F_5");
//				fileidx = 5;
//	            fileidy = 1;
//				break;
//			case "T":
//				System.out.print("F#_5");
//				fileidx = 6;
//	            fileidy = 1;
//				break;
//			case "G":
//				System.out.print("G_5");
//				fileidx = 7;
//	            fileidy = 1;
//				break;
//			case "Y":
//				System.out.print("G_#5");
//				fileidx = 8;
//	            fileidy = 1;
//				break;
//			case "H":
//				System.out.print("A_5");
//				fileidx = 9;
//	            fileidy = 1;
//				break;
//			case "U":
//				System.out.print("A#_5");
//				fileidx = 10;
//	            fileidy = 1;
//				break;
//			case "J":
//				System.out.print("B_5");
//				fileidx = 11;
//	            fileidy = 1;
//				break;
//			default:
//				fileidx = -1;	
//			}
//			pianoPlay(fileidx,fileidy);
//		}
//		
//		void piano3()
//		{
//			switch(key)
//			{
//			case "A":
//				System.out.print("C_6");
//				fileidx = 0;
//	            fileidy = 2;				
//				break;
//			case "W":
//				System.out.print("C_#6");
//				fileidx = 1;
//		        fileidy = 2;
//				break;
//			case "S":
//				System.out.print("D_6");
//				fileidx = 2;
//	            fileidy = 2;
//				break;
//			case "E":
//				System.out.print("D#_6");
//				fileidx = 3;
//		        fileidy = 2;
//				break;
//			case "D":
//				System.out.print("E_6");
//				fileidx = 4;
//	            fileidy = 2;
//				break;
//			case "F":
//				System.out.print("F_6");
//				fileidx = 5;
//	            fileidy = 2;
//				break;
//			case "T":
//				System.out.print("F#_6");
//				fileidx = 6;
//	            fileidy = 2;
//				break;
//			case "G":
//				System.out.print("G_6");
//				fileidx = 7;
//	            fileidy = 2;
//				break;
//			case "Y":
//				System.out.print("G_#6");
//				fileidx = 8;
//	            fileidy = 2;
//				break;
//			case "H":
//				System.out.print("A_6");
//				fileidx = 9;
//	            fileidy = 2;
//				break;
//			case "U":
//				System.out.print("A#_6");
//				fileidx = 10;
//	            fileidy = 2;
//				break;
//			case "J":
//				System.out.print("B_6");
//				fileidx = 11;
//	            fileidy = 2;
//				break;
//			default:
//				fileidx = -1;	
//			}
//			pianoPlay(fileidx,fileidy);
//		}
//		
//		void pianoPlay(int fileidx, int fileidy)
//		{
//			try
//			{
//				if(fileidx == -1)
//					return;
//				
//				audioin = AudioSystem.getAudioInputStream(PianoFiles[fileidx][fileidy]);
//				clip = AudioSystem.getClip();
//				clip.open(audioin);
//				clip.start();
//				
//				if(rec_piano)
//				{
//					if(playlist_keyboard.isEmpty())
//						playlist_keyboard.add(0);
//					else
//						playlist_keyboard.add(rest);
//					
//					playlist_keyboard.add(fileidx);
//					
//				}
//			}
//			catch(Exception exp)
//			{}
//		}
//		
//		/**
//		 * @brief 
//		 */
//		void drum()
//		{
//			switch(key)
//			{
//			case "A":
//				System.out.print("ű ");
//				fileidx = 0;				
//				break;
//			case "S":
//				System.out.print("������ ");
//				fileidx = 1;
//				break;
//			case "D":
//				System.out.print("���׾� ");
//				fileidx = 2;
//				break;
//			case "F":
//				System.out.print("�Ҿ�~ ");
//				fileidx = 3;
//			}
//			
//			try
//			{
//				if(fileidx == -1)
//					return;
//				
//				audioin = AudioSystem.getAudioInputStream(drumfile[fileidx]);
//				clip = AudioSystem.getClip();
//				clip.open(audioin);
//				clip.start();
//				
//				if(rec_drum)
//				{
//					if(playlist_drum.isEmpty())
//						playlist_drum.add(0);
//					else
//						playlist_drum.add(rest);
//
//					playlist_drum.add(fileidx);
//				}
//			}
//			catch(Exception exp)
//			{}
//		}
//	}
//	
//	/**
//	 * @brief 
//	 */
//	public static void main(String args[]){
//		new _DEAD_Keyboard();
//	}
//
//	/**
//	 * @brief 
//	 */
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		p.requestFocus();
//	}
//}
