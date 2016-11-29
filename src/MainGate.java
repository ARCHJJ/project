import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
/**
 * @brief �ʺ��ڿ�� �����ڿ��� �����ϴ� GUI �������̽��� ���ǵǴ� ���̴�.
 */
public class MainGate extends JFrame implements ActionListener{
	
	//! GUI���� ������ �Ǵ� �����̳�
	private Container contentPane;
	
	//! �ʺ��ڿ� ���ù�ư
	private JButton Beginner;
	
	//! �����ڿ� ���ù�ư
	private JButton Expert;
	
	//! �������α׷�
	private Orpheus MainProgram;
	//private boolean[] isFirstOpen;
	
	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�.
	 */
	public MainGate() {
		MainProgram = new Orpheus();
		setPreferredSize(new Dimension(400, 400));
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		
		Beginner = new JButton("�ʺ��ڿ�");
		Beginner.addActionListener(this);
		Expert = new JButton("�����ڿ�");
		Expert.addActionListener(this);
		
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
}
