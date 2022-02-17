import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Cria a janela com os objetos que podem ser criados
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */

public class PalhetaObjeto extends JFrame {
	private static final long serialVersionUID = 7871989599221208489L;
	
	// Botões com os Objetos a criar
    private JButton btJTextField;
    private JButton btJButton;
    private JButton btJList;
    private JButton btJTextArea;
    private JButton btJCheckBox;
    private JButton btJLabel;
    private JButton btJCombo;
    private JButton btNormal;
    
    // Labels exibido na direita dos botões
    private JLabel lbJTextField;
    private JLabel lbJButton;
    private JLabel lbJList;
    private JLabel lbJTextArea;
    private JLabel lbJCheckBox;
    private JLabel lbJLabel;
    private JLabel lbJCombo;
    private JLabel lbNormal;
    
    // Referência
    private PDialogMaker frmModi;
    private Atributo vari;
    
    // Cursor
    private final Cursor CROSSHAIR_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    private final Cursor DEFAULT_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    
    /** Cria a janela dos Objetos
     * @param recModi Janela principal
     */
    public PalhetaObjeto(PDialogMaker recModi) {
        try {
            frmModi = recModi;
            mostra();
            frmModi.jnObjeto = true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void mostra() throws Exception {
    	
    	//Define o tema da janela
  		try {
  			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
  				if ("Nimbus".equals(info.getName())) {
  					UIManager.setLookAndFeel(info.getClassName());
  					SwingUtilities.updateComponentTreeUI(this);		
  					this.pack();
  					break;
  				}
  			}
  		} catch (Exception e) {
  		}
    	
        this.getContentPane().setLayout(null);
        this.setSize(260, 330);
        this.setTitle("Objetos");
        this.setResizable(false);
        
        vari = new Atributo();
        btJLabel = new JButton("");
        btJLabel.setToolTipText("JLabel");
        btJLabel.setIcon(vari.getImage("objeto_label.png"));
        btJLabel.setBounds(new Rectangle(3, 7, 60, 32));
        this.getContentPane().add(btJLabel, null);
        btJLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(8);
            }
        });
        
        lbJLabel = new JLabel("TSay (Label)");
        lbJLabel.setBounds(65, 7, 200, 30);
        add(lbJLabel);
        
        btJTextField = new JButton("");
        btJTextField.setToolTipText("JTextField");
        btJTextField.setIcon(vari.getImage("objeto_get.png"));
        btJTextField.setBounds(new Rectangle(3, 37, 60, 32));
        this.getContentPane().add(btJTextField, null);
        btJTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(1);
            }
        });
        
        lbJTextField = new JLabel("TGet (Campo)");
        lbJTextField.setBounds(65, 37, 200, 30);
        add(lbJTextField);
        
        btJButton = new JButton("");
        btJButton.setToolTipText("JButton");
        btJButton.setIcon(vari.getImage("objeto_botao.png"));
        btJButton.setBounds(new Rectangle(3, 67, 60, 32));
        this.getContentPane().add(btJButton, null);
        btJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(3);
            }
        });
        
        lbJButton = new JLabel("TButton (Botão)");
        lbJButton.setBounds(65, 67, 200, 30);
        add(lbJButton);
        
        btJList = new JButton("");
        btJList.setToolTipText("JList");
        btJList.setIcon(vari.getImage("objeto_list.png"));
        btJList.setBounds(new Rectangle(3, 97, 60, 32));
        this.getContentPane().add(btJList, null);
        btJList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(4);
            }
        });
        
        lbJList = new JLabel("TListBox (Lista de Itens)");
        lbJList.setBounds(65, 97, 200, 30);
        add(lbJList);
        
        btJTextArea = new JButton("");
        btJTextArea.setToolTipText("JTextArea");
        btJTextArea.setIcon(vari.getImage("objeto_multiget.png"));
        btJTextArea.setBounds(new Rectangle(3, 127, 60, 32));
        this.getContentPane().add(btJTextArea, null);
        btJTextArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(5);
            }
        });
        
        lbJTextArea = new JLabel("TMultiGet (Campo MEMO)");
        lbJTextArea.setBounds(65, 127, 200, 30);
        add(lbJTextArea);
        
        btJCheckBox = new JButton("");
        btJCheckBox.setToolTipText("JCheckBox");
        btJCheckBox.setIcon(vari.getImage("objeto_check.png"));
        btJCheckBox.setBounds(new Rectangle(3, 157, 60, 32));
        this.getContentPane().add(btJCheckBox, null);
        btJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(7);
            }
        });
        
        lbJCheckBox = new JLabel("TCheckBox (Caixa p/ Marcar)");
        lbJCheckBox.setBounds(65, 157, 200, 30);
        add(lbJCheckBox);
        
        btJCombo = new JButton("");
        btJCombo.setToolTipText("JComboBox");
        btJCombo.setIcon(vari.getImage("objeto_combo.png"));
        btJCombo.setBounds(new Rectangle(3, 187, 60, 32));
        this.getContentPane().add(btJCombo, null);
        btJCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criaObj(9);
            }
        });
        
        lbJCombo = new JLabel("TComboBox (Caixa p/ Selecao)");
        lbJCombo.setBounds(65, 187, 200, 30);
        add(lbJCombo);
        
        btNormal = new JButton("");
        btNormal.setToolTipText("Termina");
        btNormal.setIcon(vari.getImage("cursor.png"));
        btNormal.setBounds(new Rectangle(3, 247, 60, 38));
        this.getContentPane().add(btNormal, null);
        btNormal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                termina();
            }
        });
        
        lbNormal = new JLabel("Encerra criacao de objetos");
        lbNormal.setBounds(65, 251, 200, 30);
        add(lbNormal);
        
        ImageIcon icone = vari.getImage("logo.png");
		this.setIconImage(icone.getImage());
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                aoFechar(e);
            }
        });
    }
    
    private void termina() {
        frmModi.setCursor(DEFAULT_CURSOR);
        this.setCursor(DEFAULT_CURSOR);
    }
    
    private void criaObj(int tipObj) {
        frmModi.objCriar = tipObj;
        frmModi.setCursor(CROSSHAIR_CURSOR);
        this.setCursor(CROSSHAIR_CURSOR);
    }
    
    private void aoFechar(WindowEvent e) {
        frmModi.jnObjeto = false;
        this.dispose();
    }
}