package at.int32.sweaty.ui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import at.int32.sweaty.ui.Control;
import at.int32.sweaty.ui.Layout;
import at.int32.sweaty.ui.annotations.OnTextChanged;
import at.int32.sweaty.ui.annotations.OnTextChangedEvent;

public class TextBox extends Widget<Text> {

	public interface IOnValueChangedListener {
		public void onTextChanged(String text);
	}

	public TextBox(Control parent) {
		super(parent);

		ctrl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				events.post(OnTextChanged.class, new OnTextChangedEvent(TextBox.this, text()));
			}
		});
	}

	public TextBox(Control parent, int style) {
		super(parent, style);
	}

	public TextBox text(String text) {
		if (text == null)
			text = "";
		ctrl.setText(text);
		return this;
	}

	public String text() {
		return ctrl.getText();
	}

	@Override
	public TextBox center() {
		return (TextBox) super.center();
	}

	public TextBox background(Color color) {
		ctrl.setBackground(color);
		return this;
	}

	public TextBox foreground(Color color) {
		ctrl.setForeground(color);
		return this;
	}

	public TextBox width(int width) {
		data().widthHint = width;
		return this;
	}

	public TextBox height(int height) {
		data().heightHint = height;
		return this;
	}

	public TextBox changed(Object o) {
		events.register(OnTextChanged.class, o);
		return this;
	}
	
	public TextBox selection(SelectionListener listener){
		
		ctrl.addSelectionListener(listener);
		return this;
	}
	
	public TextBox size(int fontSize) {
		FontData[] fD = ctrl.getFont().getFontData();
		fD[0].setHeight(fontSize);
		ctrl.setFont(new Font(Display.getCurrent(), fD[0]));
		return this;
	}

	@Override
	public Text getBaseControl(Composite parent, int style) {
		Text txt = new Text(parent, SWT.CENTER | style);
		txt.setLayoutData(Layout.Grid.data(0, true, true));
		txt.setEditable(true);
		return txt;
	}

}
