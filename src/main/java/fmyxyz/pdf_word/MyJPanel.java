package fmyxyz.pdf_word;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	private static final long serialVersionUID = -5611387080777857235L;
	private Map<String, Component> componentContext = new HashMap<String, Component>();

	public Map<String, Component> getComponentContext() {
		return componentContext;
	}

	/**
	 * 需要获取上下文组件
	 */
	@Override
	public Component add(String name, Component comp) {
		componentContext.put(name, comp);
		return super.add(name, comp);
	}
}
