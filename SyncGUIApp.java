package Syncs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class SyncGUIApp implements ActionListener
{	
	String src,trg;
	JFrame f ;
	JButton btn1,btn2,btnSyn,btnClose;
	JTextField txtSrc,txtDes;
	JLabel lblSrc,lblDes,lblSyn;
    JFileChooser fc;
	Icon ic,icExit;
	ImageIcon gifImage;
    JTabbedPane t1;
	JPanel pan1,pan2;
	
	
	public SyncGUIApp()
	{
		  try 
		 {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
		catch(Exception e) 
		{
            e.printStackTrace();
        }
		try{
        f= new JFrame("Sync App");
		
	
		ic=new ImageIcon(getClass().getResource("/img/ficn.png"));
		//URL bg=getClass().getResource("/img/bg1.jpg");
		//Icon ibg=new ImageIcon(bg);
		pan1=new JPanel(new FlowLayout());
		gifImage = new ImageIcon(getClass().getResource("/img/b.gif"));
		icExit=new ImageIcon(getClass().getResource("/img/exit.png"));
        
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pan2=new JPanel(new FlowLayout());
		//f.setLayout(new FlowLayout());
        btn1=new JButton("Select");
		txtSrc=new JTextField(30);
		txtDes=new JTextField(30);
		lblSrc=new JLabel("Source");
		lblDes=new JLabel("Target");
		lblSyn=new JLabel();
		btn2=new JButton("Select");
		btnSyn=new JButton("Start Sync",ic);
		btnClose=new JButton("Close Sync",icExit);
		f.setSize(500,180);
	//	pan.setContentPane(new JLabel(ibg));
	    txtSrc.setEditable(false);
		txtDes.setEditable(false);
	    pan1.add(lblSrc);
		pan1.add(txtSrc);
		pan1.add(btn1);
		pan1.add(lblDes);
		pan1.add(txtDes);
		pan1.add(btn2);
		
		pan1.add(btnSyn);
		pan1.add(btnClose);
		pan1.add(lblSyn);
		fc=new JFileChooser();
		fc.setDialogTitle("Select Directory");
		fc.setMultiSelectionEnabled(false);
		fc.setApproveButtonText("Select");
		//fc.setIcon(new ImageIcon("img/Sync.png"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setApproveButtonToolTipText("Select Directory");
        f.setLocationByPlatform(true);
        f.setDefaultLookAndFeelDecorated(true);
		f.setResizable(false);
       // f.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icn.png"));
       
	
	   f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icn.png")));
// f.setIconImage(new ImageIcon(getClass().getResource("/img/icn.png")));
     //   f.setIconImage(new ImageIcon(icn));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
			
		t1=new JTabbedPane();
		t1.addTab("Sync App",pan1);
		t1.addTab("Information",new Information());
		f.add(t1);
		
		//f.setBounds(250,300,500,150);
		f.setVisible(true);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btnClose.addActionListener(this);
		txtSrc.addActionListener(this);
		txtDes.addActionListener(this);
		btnSyn.addActionListener(this);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	  public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn1)
		{
			int returnVal = fc.showOpenDialog(f);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			  /* System.out.println("You chose to open this directory: " +
					fc.getSelectedFile().getAbsolutePath());*/
					src=fc.getSelectedFile().getAbsolutePath();
					txtSrc.setText(fc.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource()==btn2)
		{
			int returnVal = fc.showOpenDialog(f);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			   /*System.out.println("You chose to open this directory: " +
					fc.getSelectedFile().getAbsolutePath());*/
					trg=fc.getSelectedFile().getAbsolutePath();
					txtDes.setText(fc.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource()==btnClose)
		{
			System.exit(0);
		}
		if(e.getSource()==btnSyn)
		{	
			try
			{
				new SyncDrive(src,trg);
				lblSyn.setIcon(gifImage);
				btn1.setEnabled(false);
				btn2.setEnabled(false);
				btnSyn.setEnabled(false);
				txtSrc.setEnabled(false);
				txtDes.setEnabled(false);
			}
			catch(Exception ex)
			{
				String err=ex.getMessage();
				JOptionPane.showMessageDialog(f,
				"Please  Select Valid Path", "Error",
				JOptionPane.ERROR_MESSAGE);
			}
		}
		
	
		
	}

}

class Information extends JPanel implements ActionListener {
    
	JLabel lblDev;
	JLabel lblVersion;
	JButton btnHow;
	JPanel panBtn,panVer,panDev;
	 JLabel lblDocs,lblStep;
	JButton btns1,btns2,btns3;
	 JDialog df;
	 Icon ic1,ic2,ic3;
	public Information(){
	
		try
	{
		setLayout(new BorderLayout());
		Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/my.TTF")); 
		
		lblDev=new JLabel("Core Member: Mohammad Uzaif Nilger");
		
		lblVersion=new JLabel("Sync App Version : 1.0");
		
		btnHow=new JButton("How To Use?");
		panDev=new JPanel(new FlowLayout());
		panDev.add(lblDev);
		panVer=new JPanel(new FlowLayout());
		panVer.add(lblVersion);
		panBtn=new JPanel(new FlowLayout());
		panBtn.add(btnHow);
		lblVersion.setFont(new Font("Roman",Font.BOLD,17));
		lblDev.setFont(new Font("Script", Font.PLAIN, 14));
		lblVersion.setFont(font.deriveFont(Font.BOLD, 17f));
		add(panDev,BorderLayout.SOUTH);
		add(panBtn);
		add(panVer,BorderLayout.NORTH);
		btnHow.addActionListener(this);
	}
	catch(Exception ex)
	{
	
	}
	}
	 JButton btn1,btn2,btn3;
	 public void actionPerformed(ActionEvent e)
	 {
		if(e.getSource()==btnHow)
		{ new Dmain();}
	 
	
}
}


	 
    

