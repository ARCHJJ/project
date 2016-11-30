import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
/**
 * @brief 초보자용과 숙련자용을 선택하는 GUI 인터페이스가 정의되는 곳이다.
 */
public class MainGate extends JFrame implements ActionListener, MouseListener{
	
	//! GUI에서 메인이 되는 컨테이너
	private Container contentPane;
	
	//! 초보자용 선택버튼
	private JButton Beginner;
	
	//! 숙련자용 선택버튼
	private JButton Expert;
	
	//! 메인프로그램
	private Orpheus MainProgram;
	//private boolean[] isFirstOpen;
	
	ImageIcon[] icon;
	/**
	 * @brief 생성자
	 * 사용할 컴포넌트를 할당하고 위치를 지정한다.
	 */
	public MainGate() {
		MainProgram = new Orpheus(this);
		setTitle("난이도를 선택해주세요.");
		setPreferredSize(new Dimension(970, 360));
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		
		icon = new ImageIcon[3];
		for(int i=0; i<3; i++)
			icon[i] = new ImageIcon("image\\icon"+Integer.toString(i+1)+".jpg");
		
		Beginner = new JButton(icon[0]);
		Beginner.setText("초보자용");
		Beginner.addActionListener(this);
		Beginner.addMouseListener(this);
		
		Expert = new JButton(icon[1]);
		Expert.setText("숙련자용");
		Expert.addActionListener(this);
		Expert.addMouseListener(this);
		
		contentPane.add(Beginner);
		contentPane.add(Expert);
		
		//isFirstOpen = new boolean[]{true, true};
	}

	/**
	 * @brief main 메소드
	 * 시작 GUI를 할당하고 실행한다.
	 */

	public static void main(String[] args)
	{
		MainGate frame = new MainGate();
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * @brief 버튼액션리스너
	 * @param ActionEvent e
	 * 버튼이 눌렸을 때 해당 버튼의 이름에 따라 필요한 메소드를 호출한다.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		JButton source = (JButton)e.getSource();
		switch(source.getText())
		{
		case "초보자용":
			MainProgram.isBeginner(true);
			//isFirstOpen[0] = false;
			break;
		case "숙련자용":
			MainProgram.isBeginner(false);
			//isFirstOpen[1] = false;
		}
		MainProgram.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("마우스가 들어왔다");
		JButton tmp = (JButton) arg0.getSource();
		tmp.setIcon(icon[2]);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JButton tmp = (JButton) arg0.getSource();
		switch(tmp.getText())
		{
		case "초보자용":
			tmp.setIcon(icon[0]);
			break;
		case "숙련자용":
			tmp.setIcon(icon[1]);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
