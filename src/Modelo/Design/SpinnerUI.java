package Modelo.Design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class SpinnerUI extends BasicSpinnerUI{
    @Override
    public void installUI(JComponent jc) {
        super.installUI(jc);
        spinner.setEditor(new Editor(spinner));
    }

    public class Editor extends TextField implements ChangeListener {

        /**
         *
         * @param spinner
         */
        public Editor(JSpinner spinner) {
            spinner.addChangeListener(this);
            setEditable(true);
            setText("0");
        }

        @Override
        public void stateChanged(ChangeEvent ce) {
            JSpinner spinner = (JSpinner) ce.getSource();
            setText(spinner.getValue().toString());
        }
    }

    private class ArrowButton extends JButton {

        private final boolean next;

        public ArrowButton(boolean next) {
            this.next = next;
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(new EmptyBorder(5, 7, 5, 7));
            setBackground(new Color(231, 231, 231));
            setForeground(new Color(150, 150, 150));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    setSelected(true);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    setSelected(true);
                }
            });
        }
    }
}
