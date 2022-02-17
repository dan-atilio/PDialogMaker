import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Mostra uma tela de propriedades do Objeto
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */

public class AjustaObjeto extends JFrame {
	private static final long serialVersionUID = -788693612223682188L;
	// Referência
    private PDialogMaker frmModi;
    private boolean clickCheck = true;
    // Objetos da Janela
    private JComponent cmpAtual;
    private JLabel JLabel1;
    private JLabel JLabel2;
    private JLabel JLabel3;
    private JLabel JLabel4;
    private JLabel JLabel5;
    private JLabel JLabel6;
    private JLabel JLabel7;
    private JTextField proEsquerda;
    private JTextField proTopo;
    private JTextField proLargura;
    private JTextField proAltura;
    private JTextField proLegenda;
    private JTextField proNome;
    private JTextField proSugestao;
    private JCheckBox evento1;
    private JCheckBox evento2;
    private JCheckBox evento4;
    private JCheckBox evento8;
    private JCheckBox evento16;
    private JTabbedPane abas;
    private JPanel panPropriedade;
    private JPanel panEvento;
    @SuppressWarnings("rawtypes")
	private JComboBox cbComponente;
    
    //Classe generica de Atributos
  	private Atributo vari = new Atributo();
    
    /** Cria e organiza os objetos na janela de propriedade dos objetos
     * @param cmp Nome do componente
     * @param recModi Janela Principal
     */    
    public AjustaObjeto(JComponent cmp, PDialogMaker recModi) {
        try {
            frmModi = recModi;
            cmpAtual = cmp;
            mostra();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("rawtypes")
	private void mostra() throws Exception {
        this.getContentPane().setLayout(null);
        this.setTitle("Propriedades");
        this.setResizable(false);
        this.setLocation(0, 345); //esquerda, topo
        // Objeto das Abas
        abas = new JTabbedPane();
        abas.setBounds(new Rectangle(2, 34, 250, 230));
        this.getContentPane().add(abas, null);
        // 1ª Aba - Propriedades
        panPropriedade = new JPanel();
        abas.addTab("Propriedades", panPropriedade);
        panPropriedade.setLayout(null);
        JLabel1 = new JLabel("Esquerda:");
        JLabel1.setBounds(new Rectangle(5, 15, 57, 13));
        panPropriedade.add(JLabel1, null);
        JLabel2 = new JLabel("Topo:");
        JLabel2.setBounds(new Rectangle(5, 45, 57, 13));
        panPropriedade.add(JLabel2, null);
        JLabel3 = new JLabel("Largura:");
        JLabel3.setBounds(new Rectangle(5, 75, 57, 13));
        panPropriedade.add(JLabel3, null);
        JLabel4 = new JLabel("Altura:");
        JLabel4.setBounds(new Rectangle(5, 105, 57, 13));
        panPropriedade.add(JLabel4, null);
        JLabel5 = new JLabel("Legenda");
        JLabel5.setBounds(new Rectangle(5, 135, 57, 13));
        //panPropriedade.add(JLabel5, null);
        JLabel6 = new JLabel("Nome:");
        JLabel6.setBounds(new Rectangle(5, 135, 57, 13));
        panPropriedade.add(JLabel6, null);
        JLabel7 = new JLabel("Sugestão:");
        JLabel7.setBounds(new Rectangle(5, 135, 57, 13));
        //panPropriedade.add(JLabel7, null);
        
        proEsquerda = new JTextField("");
        proEsquerda.setBounds(new Rectangle(74, 10, 60, 30));
        proEsquerda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        panPropriedade.add(proEsquerda, null);
        proTopo = new JTextField("");
        proTopo.setBounds(new Rectangle(74, 40, 60, 30));
        proTopo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        panPropriedade.add(proTopo, null);
        proLargura = new JTextField("");
        proLargura.setBounds(new Rectangle(74, 70, 60, 30));
        proLargura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        panPropriedade.add(proLargura, null);
        proAltura = new JTextField("");
        proAltura.setBounds(new Rectangle(74, 100, 60, 30));
        proAltura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        panPropriedade.add(proAltura, null);
        proLegenda = new JTextField("");
        proLegenda.setBounds(new Rectangle(74, 130, 100, 30));
        proLegenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        //panPropriedade.add(proLegenda, null);
        proNome = new JTextField("");
        proNome.setBounds(new Rectangle(74, 130, 100, 30));
        proNome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setNomComp();
            }
        });
        panPropriedade.add(proNome, null);
        proSugestao = new JTextField("");
        proSugestao.setBounds(new Rectangle(73, 130, 100, 30));
        proSugestao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAjustes();
            }
        });
        //panPropriedade.add(proSugestao, null);
        
        // 2ª Aba - Eventos
        panEvento = new JPanel();
        //abas.addTab("Eventos", panEvento);
        panEvento.setLayout(null);
        evento1 = new JCheckBox("ao Pressionar", false);
        evento1.setBounds(new Rectangle(30, 25, 120, 17));
        evento1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setEventos(1, (e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panEvento.add(evento1, null);
        evento2 = new JCheckBox("ao Clicar", false);
        evento2.setBounds(new Rectangle(30, 51, 120, 17));
        evento2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setEventos(2, (e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panEvento.add(evento2, null);
        evento4 = new JCheckBox("ao Acionar", false);
        evento4.setBounds(new Rectangle(30, 77, 120, 17));
        evento4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setEventos(4, (e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panEvento.add(evento4, null);
        evento8 = new JCheckBox("ao Ganhar Foco", false);
        evento8.setBounds(new Rectangle(30, 104, 120, 17));
        evento8.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setEventos(8, (e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panEvento.add(evento8, null);
        evento16 = new JCheckBox("ao Perder Foco", false);
        evento16.setBounds(new Rectangle(30, 131, 120, 17));
        evento16.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setEventos(16, (e.getStateChange() == ItemEvent.SELECTED));
            }
        });
        panEvento.add(evento16, null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                aoFechar();
            }
        });
        getAjustes();
        getEventos();
        // Carrega Combo - Tem que ser a última
        cbComponente = new JComboBox();
        carrCbComp();
        
        this.pack();
        this.setSize(260, 282);
        
        ImageIcon icone = vari.getImage("logo.png");
		this.setIconImage(icone.getImage());
    }
    
    /** Coloca o componente recebido como padrão na janela
     * @param cmp Componente
     */    
    public void setComponente(JComponent cmp) {
        cmpAtual = cmp;
        cbComponente.setSelectedItem(cmpAtual.getName());
        getAjustes();
        getEventos();
    }
    
    /** Carrega o JComboBox de componentes */    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void carrCbComp() {
        if (!(cbComponente == null))
            this.getContentPane().remove(cbComponente);
        Component[] comps = frmModi.getContentPane().getComponents();
        String[] nomComps = new String[comps.length];
        JComponent cmp;
        for (int i = 0; i < comps.length; i++) {
            cmp = (JComponent)comps[i];
            nomComps[i] = cmp.getName();
        }
        // Cria Combo
        cbComponente = new JComboBox(nomComps);
        cbComponente.setBounds(new Rectangle(2, 5, 220, 21));
        this.getContentPane().add(cbComponente, null);
        cbComponente.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setComponenteCombo();
            }
        });
        cbComponente.setSelectedItem(cmpAtual.getName());
    }
    
    private void setComponenteCombo() {
        if (!(cmpAtual.getName().equals(cbComponente.getSelectedItem()))) {
            Component[] comps = frmModi.getContentPane().getComponents();
            String nomComp = "";
            for (int i = 0; i < comps.length; i++) {
                nomComp = comps[i].getName();
                if (nomComp.equals(cbComponente.getSelectedItem())) {
                    cmpAtual = (JComponent)comps[i];
                    break;
                }
            }
            getAjustes();
            getEventos();
        }
    }
    
    private void getAjustes() {
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        proEsquerda.setText(""+cmpAtual.getX());
        proTopo.setText(""+cmpAtual.getY());
        proLargura.setText(""+cmpAtual.getWidth());
        proAltura.setText(""+cmpAtual.getHeight());
        proLegenda.setText(cmpAnalisado.getText(cmpAtual));
        proNome.setText(cmpAtual.getName());
        proSugestao.setText(cmpAtual.getToolTipText());
    }
    
    private void setAjustes() {
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        cmpAtual.setBounds(
        Integer.parseInt(proEsquerda.getText()),
        Integer.parseInt(proTopo.getText()),
        Integer.parseInt(proLargura.getText()),
        Integer.parseInt(proAltura.getText())
        );
        cmpAnalisado.setText(cmpAtual, proLegenda.getText());
        cmpAtual.setToolTipText(proSugestao.getText());
    }
    
    private void setNomComp() {
        cmpAtual.setName(proNome.getText());
        carrCbComp();
    }
    
    private void getEventos() {
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        clickCheck = false;
        int numEvento = cmpAnalisado.getNumEvento(cmpAtual);
        if (numEvento - 16 >= 0) {
            evento16.setSelected(true);
            numEvento -= 16;
        } else
            evento16.setSelected(false);
        if (numEvento - 8 >= 0) {
            evento8.setSelected(true);
            numEvento -= 8;
        } else
            evento8.setSelected(false);
        if (numEvento - 4 >= 0) {
            evento4.setSelected(true);
            numEvento -= 4;
        } else
            evento4.setSelected(false);
        if (numEvento - 2 >= 0) {
            evento2.setSelected(true);
            numEvento -= 2;
        } else
            evento2.setSelected(false);
        if (numEvento - 1 >= 0) {
            evento1.setSelected(true);
            numEvento -= 1;
        } else
            evento1.setSelected(false);
        clickCheck = true;
    }
    
    private void setEventos(int num, boolean selec) {
        if (clickCheck) {
            AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
            int numEvento = cmpAnalisado.getNumEvento(cmpAtual);
            if (selec)
                cmpAnalisado.setNumEvento(cmpAtual, numEvento + num);
            else
                cmpAnalisado.setNumEvento(cmpAtual, numEvento - num);
            numEvento = cmpAnalisado.getNumEvento(cmpAtual);
        }
    }
    
    /** Encerra a janela */    
    public void aoFechar() {
        frmModi.ajObjeto = false;
        dispose();
    }
}