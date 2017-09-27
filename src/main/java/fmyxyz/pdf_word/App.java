package fmyxyz.pdf_word;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		JButton ob = new JButton("打开文件...");
		JButton sb = new JButton("保存到...");
		JButton zb = new JButton("转换");

		final JTextField ot = new JTextField("打开文件...");
		final JTextField st = new JTextField("保存到...");
		ot.setSize(200, 30);
		st.setSize(200, 30);
		final JFrame a = new JFrame("pdf<-->word");
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setSize(500, 300);
		LayoutManager manager = new FlowLayout();
		a.setLayout(manager);
		MyJPanel cp = new MyJPanel();

		cp.add("ot", ot);
		cp.add(ob);
		ob.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser jchooser1 = new JFileChooser(); // JFileChooser控件
				jchooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jchooser1.setDialogTitle("打开文件...");
				jchooser1.setCurrentDirectory(new File(""));
				int index = jchooser1.showOpenDialog(a.getContentPane());
				if (index == JFileChooser.APPROVE_OPTION) {
					@SuppressWarnings("unused")
					File selectedFile = jchooser1.getSelectedFile();
					ot.setText(jchooser1.getSelectedFile().getAbsolutePath());
				}
			}
		});
		cp.add("st", st);
		cp.add(sb);
		sb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser jchooser1 = new JFileChooser(); // JFileChooser控件
				jchooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jchooser1.setDialogTitle("保存到...");
				jchooser1.setCurrentDirectory(new File(""));
				int index = jchooser1.showOpenDialog(a.getContentPane());
				if (index == JFileChooser.APPROVE_OPTION) {
					@SuppressWarnings("unused")
					File selectedFile = jchooser1.getSelectedFile();
					st.setText(jchooser1.getSelectedFile().getAbsolutePath());
				}
			}
		});
		cp.add(zb);
		ActionListener abl = new MyActionListener(cp.getComponentContext());
		zb.addActionListener(abl);
		a.setContentPane(cp);
		a.setVisible(true);
	}

}

class MyActionListener implements ActionListener {
	private Map<String, Component> context;

	public MyActionListener(Map<String, Component> map) {
		this.context = map;
	}

	@SuppressWarnings({ "resource" })
	public void actionPerformed(ActionEvent e) {
		try {
			JTextField ot = (JTextField) context.get("ot");
			System.out.println(ot.getText());
			// FileInputStream istream = new FileInputStream("test.docx");
			FileInputStream istream = new FileInputStream(ot.getText());
			XWPFDocument xd = new XWPFDocument(istream);
			System.out.println(xd.getLastParagraph().getText());
		} catch (IOException e1) {
			System.out.println("请输入正确的文件路径。");
			e1.printStackTrace();
		}
	}

}
