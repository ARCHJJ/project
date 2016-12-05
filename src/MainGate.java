import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
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
	
	//! 
	private ImageIcon[] icon;
	
	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�.
	 */
	public MainGate() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));
//		try
//		{
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(this);
//		}
//		catch(Exception e) { e.printStackTrace(); }
		MainProgram = new Orpheus(this);
		setTitle("Orpheus / ���̵��� �������ּ���.");
		setSize(970, 360);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		
		icon = new ImageIcon[3];
		for(int i=0; i<3; i++)
			icon[i] = new ImageIcon("image\\icon"+Integer.toString(i+1)+".jpg");
		
		Beginner = new JButton(icon[0]);
		Beginner.setSize(485, 360);
		Beginner.setContentAreaFilled(false);
		Beginner.addActionListener(this);
		Beginner.addMouseListener(this);
		
		Expert = new JButton(icon[1]);
		Expert.setSize(485, 360);
		Expert.setContentAreaFilled(false);
		Expert.addActionListener(this);
		Expert.addMouseListener(this);
		
		contentPane.add(Beginner);
		contentPane.add(Expert);
	}

	/**
	 * @brief main �޼ҵ�
	 * ���� GUI�� �Ҵ��ϰ� �����Ѵ�.
	 */

	public static void main(String[] args)
	{
		MainGate frame = new MainGate();
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
		if(source == Beginner)
			MainProgram.isBeginner(true);
		else
			MainProgram.isBeginner(false);
		
		MainProgram.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JButton tmp = (JButton)arg0.getSource();
		tmp.setIcon(icon[2]);
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		JButton source = (JButton)arg0.getSource();
		if(source == Beginner)
			source.setIcon(icon[0]);
		else
			source.setIcon(icon[1]);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
