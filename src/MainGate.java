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
 * @brief �ʺ��ڿ�� �����ڿ��� �����ϴ� GUI �������̽��� ���ǵǴ� ���̴�.
 */
public class MainGate extends JFrame implements ActionListener, MouseListener{
	
	//! GUI���� ������ �Ǵ� �����̳�
	private Container contentPane;
	
	//! �ʺ��ڿ� ���ù�ư
	private JButton Beginner;
	
	//! �����ڿ� ���ù�ư
	private JButton Expert;
	
	//! �������α׷�
	private Orpheus MainProgram;
	//private boolean[] isFirstOpen;
	
	ImageIcon[] icon;
	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�.
	 */
	public MainGate() {
		MainProgram = new Orpheus(this);
		setTitle("���̵��� �������ּ���.");
		setPreferredSize(new Dimension(970, 360));
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		
		icon = new ImageIcon[3];
		for(int i=0; i<3; i++)
			icon[i] = new ImageIcon("image\\icon"+Integer.toString(i+1)+".jpg");
		
		Beginner = new JButton(icon[0]);
		Beginner.setText("�ʺ��ڿ�");
		Beginner.addActionListener(this);
		Beginner.addMouseListener(this);
		
		Expert = new JButton(icon[1]);
		Expert.setText("�����ڿ�");
		Expert.addActionListener(this);
		Expert.addMouseListener(this);
		
		contentPane.add(Beginner);
		contentPane.add(Expert);
		
		//isFirstOpen = new boolean[]{true, true};
	}

	/**
	 * @brief main �޼ҵ�
	 * ���� GUI�� �Ҵ��ϰ� �����Ѵ�.
	 */

	public static void main(String[] args)
	{
		MainGate frame = new MainGate();
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * @brief ��ư�׼Ǹ�����
	 * @param ActionEvent e
	 * ��ư�� ������ �� �ش� ��ư�� �̸��� ���� �ʿ��� �޼ҵ带 ȣ���Ѵ�.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		JButton source = (JButton)e.getSource();
		switch(source.getText())
		{
		case "�ʺ��ڿ�":
			MainProgram.isBeginner(true);
			//isFirstOpen[0] = false;
			break;
		case "�����ڿ�":
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
		//System.out.println("���콺�� ���Դ�");
		JButton tmp = (JButton) arg0.getSource();
		tmp.setIcon(icon[2]);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JButton tmp = (JButton) arg0.getSource();
		switch(tmp.getText())
		{
		case "�ʺ��ڿ�":
			tmp.setIcon(icon[0]);
			break;
		case "�����ڿ�":
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
