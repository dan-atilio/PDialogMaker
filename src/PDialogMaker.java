import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Janela Principal do PDialogMaker
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */

public class PDialogMaker extends JFrame {

	private static final long serialVersionUID = 5761458338063148605L;
	
	//Classe generica de Atributos
	private Atributo vari = new Atributo();
	
	//Componentes da tela principal - right click
    private JPopupMenu popMenu;
    private JMenuItem itmTitulo;
    private JMenuItem itmCorFundo;
    private JMenuItem itmCriaObj;
    private JMenuItem itmGerar;
    private JMenuItem itmAbrir;
    private JMenuItem itmSalvar;
    private JMenuItem itmLimpar;
    private JMenuItem itmVersao;
    private JMenuItem itmFechar;

    /** Inteiro contendo o objeto que deve ser criado */    
    protected int objCriar;
    
    /** Inteiro contendo o objeto selecionado para criar */    
    protected int seqObj;
    
    /** Logico para controlar se a janela de Ajustes dos Objetos ja esta aberta */    
    protected boolean ajObjeto;
    
    /** Logico para controlar se a janela de objetos ja esta aberta */    
    protected boolean jnObjeto;
    
    /** Objeto para a janela de Ajustes dos Objetos */    
    protected PalhetaObjeto janPalObj;
    
    /** Objeto para a janela de Criacao dos Objetos */    
    protected AjustaObjeto janAjusta;
    
    /** String contendo o Nome da classe para a geracao */    
    protected String nomJan;
    
    /** String contendo o Nome do Pacote para a geracao */    
    protected String nomPac;
    
    /** Cria a janela dos Objetos */
    public PDialogMaker() {
        try {
            mostra();
        } catch(Exception ex) {
            System.out.println("Problemas para executar a Janela Principal");
            System.out.println(ex.getMessage());
        }
    }
    
    private void mostra() throws Exception {
        //Define as variaveis e busca os atributos
        this.setTitle("PDialogMaker - " + vari.CFVERSAO);
        objCriar = 0;
        seqObj = 0;
        ajObjeto = false;
        jnObjeto = false;
        nomJan = "zFuncao";
        nomPac = System.getProperty("user.name");
        
        /*
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
  		*/
  		
  		//Define alguns atributos da tela
        this.getContentPane().setLayout(null);
        this.setSize(336, 230);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.mntMenu();
        
        //Mostra a palheta dos objetos na esquerda
        acionaCriarObj();
        
        //Adiciona eventos do menu
        itmTitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaColocarLegenda();
            }
        });
        itmCorFundo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaCorFundo();
            }
        });
        itmCriaObj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaCriarObj();
            }
        });
        itmGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaGerar();
            }
        });
        itmAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaAbrir();
            }
        });
        itmSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaSalvar();
            }
        });
        itmLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionaLimpar();
            }
        });
        itmVersao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionarSobre();
            }
        });
        itmFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acionarFechar();
            }
        });
        
        //Adiciona eventos da janela
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                clkMouse(evt);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                acionarFechar();
            }
        });
        
        ImageIcon icone = vari.getImage("logo.png");
		this.setIconImage(icone.getImage());
        
        //Exibe a janela principal
        this.setVisible(true);
    }
    
    /** Coloca o titulo na Janela */
    private void acionaColocarLegenda() {
        String leg = JOptionPane.showInputDialog("Informe o titulo da Dialog:");
        if (leg != null)
            this.setTitle(leg);
    }
    
    /** Coloca cor de fundo na Janela */
    private void acionaCorFundo() {
        Color color = this.getBackground();
        color = JColorChooser.showDialog(null, "Escolha a Cor da Dialog", color);
        if (color != null)
            this.getContentPane().setBackground(color);
    }
    
    /** Chamar a janela para criacao dos objetos */
    private void acionaCriarObj() {
        if (!(jnObjeto))
            janPalObj = new PalhetaObjeto(this);
        janPalObj.setVisible(true);
    }
    
    /** Abrir um projeto salvo */
    private void acionaAbrir() {
        TrataArquivo arq = new TrataArquivo(this);
        if (arq.abreArquivo()) {
            JOptionPane.showMessageDialog(null, "Arquivo Carregado sem problemas.",
            "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
            if (this.ajObjeto)
                this.janAjusta.carrCbComp();
            int h = this.getHeight();
            int w = this.getWidth();
            this.setSize(1, 1);
            this.setSize(w, h);
        } else
            JOptionPane.showMessageDialog(null,
            "Erro ao Carregar arquivo. Verique os dados.",
            "Cafeteira", JOptionPane.ERROR_MESSAGE);
    }
    
    /** Salvar um projeto */
    private void acionaSalvar() {
        TrataArquivo arq = new TrataArquivo(this);
        if (arq.salvaArquivo()) {
            JOptionPane.showMessageDialog(null, "Arquivo salvo sem problemas.",
            "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
            this.getContentPane().repaint();
        } else
            JOptionPane.showMessageDialog(null,
            "Erro ao salvar arquivo. Verique os dados.",
            "Cafeteira", JOptionPane.ERROR_MESSAGE);
    }
    
    /** Remover os objetos da Janela */
    private void acionaLimpar() {
        if (this.ajObjeto) {
            this.janAjusta.aoFechar();
            this.ajObjeto = false;
        }
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }
    
    /** Gerar o codigo da Classe */
    private void acionaGerar() {
        MstGerar gera = new MstGerar(this);
        if (nomJan == null)
            nomJan = "Classe";
        else
            gera.setVisible(true);
    }
    
    /** Mostrar dados sobre o Cafeteira */
    private void acionarSobre() {
        new SobreSistema();
    }
    
    /** Encerrar a aplicacao */
    private void acionarFechar() {
        System.exit(0);
    }
    
    private void clkMouse(MouseEvent e) {
    	//Se vier do botão direito do mouse
    	if (SwingUtilities.isRightMouseButton(e)) {
            popMenu.show(this, e.getX(), e.getY());
        }
    	//Se o cursor estiver em formato de cruz, irá criar o objeto
    	else if (getCursor() == Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)) {
            CriaComponente cria = new CriaComponente(this);
            cria.criaObjeto(e, objCriar);
            this.refresh();
        }
    }
    
    /** Montar o menu */
    private void mntMenu() {
        popMenu = new JPopupMenu();
        itmTitulo = new JMenuItem("Alterar Titulo");
        itmCorFundo = new JMenuItem("Cor da Janela");
        itmCriaObj = new JMenuItem("Criar Objetos");
        itmGerar = new JMenuItem("Gerar");
        itmSalvar = new JMenuItem("Salvar Projeto");
        itmAbrir = new JMenuItem("Abrir Projeto");
        itmLimpar = new JMenuItem("Limpar");
        itmVersao = new JMenuItem("Sobre - " + vari.CFVERSAO);
        itmFechar = new JMenuItem("Fechar");
        
        // Imagens do Menu
        itmTitulo.setIcon(vari.getImage("icone_titulo.png"));
        itmCorFundo.setIcon(vari.getImage("icone_cor.png"));
        itmCriaObj.setIcon(vari.getImage("icone_adicionar.png"));
        itmGerar.setIcon(vari.getImage("icone_gerar.png"));
        itmAbrir.setIcon(vari.getImage("icone_abrir.png"));
        itmSalvar.setIcon(vari.getImage("icone_salvar.png"));
        itmLimpar.setIcon(vari.getImage("icone_limpar.png"));
        itmVersao.setIcon(vari.getImage("icone_sobre.png"));
        itmFechar.setIcon(vari.getImage("icone_fechar.png"));
        
        // Montar o Menu
        popMenu.add(itmTitulo);
        popMenu.add(itmCorFundo);
        popMenu.add(itmCriaObj);
        popMenu.addSeparator();
        popMenu.add(itmGerar);
        popMenu.add(itmAbrir);
        popMenu.add(itmSalvar);
        popMenu.add(itmLimpar);
        popMenu.addSeparator();
        popMenu.add(itmVersao);
        popMenu.addSeparator();
        popMenu.add(itmFechar);
    }
    
    /** Refazer a janela novamente */    
    public void refresh() {
        this.repaint();
    }
    
    /** Faz o Redimensionamento dos Objetos
     * @param resizeIncx Inteiro para a posiï¿½ï¿½o a esquerda
     * @param resizeIncy Inteiro para a posiï¿½ï¿½o do topo
     * @param incx Inteiro para a largura do objeto
     * @param incy Inteiro para a altura do objeto
     */
    protected void resizeSelected(boolean resizeIncx, boolean resizeIncy,
    int incx, int incy) {
        AnalisaJComponent ajc = new AnalisaJComponent();
        Component[] comps = this.getContentPane().getComponents();
        for (int i = 0; i < comps.length; i++) {
            JComponent cmp = (JComponent)comps[i];
            if (!ajc.resize(cmp))
                continue;
            if (resizeIncx)
                cmp.setSize((cmp.getWidth() + incx), cmp.getHeight());
            if (resizeIncy)
                cmp.setSize(cmp.getWidth(), (cmp.getHeight() + incy));
        }
        this.refresh();
    }
    
    /** Metodo pricipal para iniciar a aplicacao
     * @param args Argumentos passados apenas para compor o metodo "main" (nao necessarios)
     */    
    public static void main(String args[]) {
    	//Aciona a Splash Screen
    	Splash sp = new Splash();
    	sp.setVisible(true);
    	sp.dispose();
    	
    	//Aciona a janela principal
        new PDialogMaker();
    }
}