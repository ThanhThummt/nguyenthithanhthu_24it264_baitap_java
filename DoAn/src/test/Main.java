package test;

import javax.swing.SwingUtilities;
import view.RoleSelectionFrame;

public class Main {
	public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> new RoleSelectionFrame().setVisible(true));
 }
}
