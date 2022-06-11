

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Rectangle;
//import java.awt.TextArea;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * Mostra a geraçãoo do código
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */
public class MstGerar extends JDialog {

	private static final long serialVersionUID = -4076928844855263290L;
	// Referencia
    private PDialogMaker frmModi;
    // Objetos da Janela
    private JTabbedPane abas = new JTabbedPane();
    private JTextArea txaClasse = new JTextArea();
    private JTextArea txaObjeto = new JTextArea();
    private JScrollPane scrollClasse = new JScrollPane();
    private JScrollPane scrollObjeto = new JScrollPane();
    private JButton btCopiar = new JButton("Copiar p/ Área de Transferência");
    private JButton btSalvar = new JButton("Salvar .prw");
    private Atributo atr = new Atributo();
    // Objetos internos
    //private boolean cancelou;
    
    /** Realiza a geracao da classe
     * @param recModi Formulario Principal
     */
    public MstGerar(PDialogMaker recModi) {
        try {
            frmModi = recModi;
            mostra();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void mostra() throws Exception {
        Font fntCourrier = new Font("Courier New", 0, 11);
        this.getContentPane().setLayout(null);
        this.setSize(652, 507);
        this.setTitle("PDialogMaker - " + atr.CFVERSAO);
        this.setResizable(false);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        // 1a Aba
        abas.setBounds(new Rectangle(3, 11, 630, 420));
        txaClasse.setFont(fntCourrier);
        txaClasse.setEditable(false);
        scrollClasse.getViewport().add(txaClasse, null);
        abas.addTab("Código da Função", scrollClasse);
        // 2a Aba
        txaObjeto.setFont(fntCourrier);
        scrollObjeto.getViewport().add(txaObjeto, null);
        //abas.addTab("Codigo Interno", scrollObjeto);
        this.getContentPane().add(abas, null);
        btCopiar.setBounds(new Rectangle(257, 435, 250, 30));
        this.getContentPane().add(btCopiar, null);
        btCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acaoCopiar();
            }
        });
        btSalvar.setBounds(new Rectangle(531, 435, 100, 30));
        this.getContentPane().add(btSalvar, null);
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                aoFechar(e);
            }
        });
        geraFuncao();
    }
    
    private void geraFuncao() {
    	String variavelObjeto  = "";
    	String variavelConteud = "";
    	String variavelObjSeq  = "";
    	String variavelTexto   = "";
    	String variavelAcoes   = "";
    	int linha;
    	int coluna;
    	int altura;
    	int largura;
    	int alturaJanela;
    	int larguraJanela;
    	
        frmModi.nomJan = JOptionPane.showInputDialog("Informe o Nome da User Function", frmModi.nomJan);
        if (frmModi.nomJan != null) {
            frmModi.nomPac = JOptionPane.showInputDialog("Informe o Nome do Autor", frmModi.nomPac);
            if (frmModi.nomPac == null) frmModi.nomPac = "";
            
            //Ajustes no dimensionamento P12.1.33
            alturaJanela = frmModi.getHeight() - 40;
            larguraJanela = frmModi.getWidth() - 18;
            
            txaClasse.append("//Bibliotecas\n");
            txaClasse.append("#Include 'TOTVS.ch'\n");
            txaClasse.append("\n");
            txaClasse.append("/*/{Protheus.doc} User Function " + frmModi.nomJan + "\n");
            txaClasse.append("Funcao com tela customizada usando a classe TDialog que foi gerado pelo PDialogMaker\n");
            txaClasse.append("@type  Function\n");
            txaClasse.append("@author " + frmModi.nomPac.trim() + "\n");
            txaClasse.append("@since " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " \n");
            txaClasse.append("@see https://atiliosistemas.com/portfolio/pdialogmaker/\n");
            txaClasse.append("@obs Obrigado por usar um aplicativo da Atilio Sistemas\n");
            txaClasse.append("/*/\n");
            txaClasse.append("\n");
            txaClasse.append("User Function " + frmModi.nomJan.trim() + "()\n");
            txaClasse.append("    Local aArea         := FWGetArea()\n");
            txaClasse.append("    Local nCorFundo     := RGB(" + frmModi.getContentPane().getBackground().getRed() + ", " + frmModi.getContentPane().getBackground().getGreen() + ", " + frmModi.getContentPane().getBackground().getBlue() + ")\n");
            txaClasse.append("    Local nJanAltura    := " + alturaJanela + "\n");
            txaClasse.append("    Local nJanLargur    := " + larguraJanela + " \n");
            txaClasse.append("    Local cJanTitulo    := '" + frmModi.getTitle() + "'\n");
            txaClasse.append("    Local lDimPixels    := .T. \n");
            txaClasse.append("    Local lCentraliz    := .T. \n");
            txaClasse.append("    Local nObjLinha     := 0\n");
    		txaClasse.append("    Local nObjColun     := 0\n");
			txaClasse.append("    Local nObjLargu     := 0\n");
			txaClasse.append("    Local nObjAltur     := 0\n");
			txaClasse.append("    Private cFontNome   := 'Tahoma'\n");
			txaClasse.append("    Private oFontPadrao := TFont():New(cFontNome, , -12)\n");
            txaClasse.append("    Private oDialogPvt \n");
            txaClasse.append("    Private bBlocoIni   := {|| /*fSuaFuncao()*/ } //Aqui voce pode acionar funcoes customizadas que irao ser acionadas ao abrir a dialog \n");
            
            //Percorre os componentes e cria as variaveis private
            AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
            Component[] comps = frmModi.getContentPane().getComponents();
            for (int i = 0; i < comps.length; i++) {
            	//pega o nome do objeto gerado pelo fonte (objeto1, objeto2, objeto3, etc)
            	variavelObjSeq  = (String)comps[i].getName();
            	variavelObjSeq  = variavelObjSeq.replace("objeto", "Obj");
            	variavelObjeto  = "";
            	variavelConteud = "";
            	variavelAcoes   = "";
            	linha   = comps[i].getY() / 2;
            	coluna  = comps[i].getX() / 2;
            	largura = comps[i].getWidth() / 2;
            	altura  = comps[i].getHeight() / 2;
            	
            	//Label - TSay
            	if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafLabel")) {
            		variavelObjeto  = "oSay" + variavelObjSeq;
            		variavelConteud = "cSay" + variavelObjSeq;
            		
                    variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TSay\n";
                    variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";
                    variavelTexto += "        " + variavelObjeto + "  := TSay():New(nObjLinha, nObjColun, {|| " + variavelConteud + "}, oDialogPvt, /*cPicture*/, oFontPadrao, , , , lDimPixels, /*nClrText*/, /*nClrBack*/, nObjLargu, nObjAltur, , , , , , /*lHTML*/)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := 'Label' ";
            	}
            	//Text - TGet
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafTextField")) {
            		variavelObjeto  = "oGet" + variavelObjSeq;
            		variavelConteud = "xGet" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TGet\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TGet():New(nObjLinha, nObjColun, {|u| Iif(PCount() > 0 , " + variavelConteud + " := u, " + variavelConteud + ")}, oDialogPvt, nObjLargu, nObjAltur, /*cPict*/, /*bValid*/, /*nClrFore*/, /*nClrBack*/, oFontPadrao, , , lDimPixels)\n";
                    variavelTexto += "        //" + variavelObjeto + ":cPlaceHold := 'Digite aqui um texto...'   //Texto que sera exibido no campo antes de ter conteudo\n";
                    variavelTexto += "        //" + variavelObjeto + ":cF3        := 'Codigo da consulta padrao' //Codigo da consulta padrao / F3 que sera habilitada\n";
            		variavelTexto += "        //" + variavelObjeto + ":bValid     := {|| fFuncaoVld()}           //Funcao para validar o que foi digitado\n";
    				variavelTexto += "        //" + variavelObjeto + ":lReadOnly  := .T.                         //Para permitir o usuario clicar mas nao editar o campo\n";
					variavelTexto += "        //" + variavelObjeto + ":lActive    := .F.                         //Para deixar o campo inativo e o usuario nao conseguir nem clicar\n";
					variavelTexto += "        //" + variavelObjeto + ":Picture    := '@!'                        //Mascara / Picture do campo\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := Space(10) //Se o get for data para inicilizar use dToS(''), se for numerico inicie com 0 ";
            	}
            	//Button - TButton
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafButton")) {
            		variavelObjeto  = "oBtn" + variavelObjSeq;
            		variavelConteud = "cBtn" + variavelObjSeq;
            		variavelAcoes   = "bBtn" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TButton\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TButton():New(nObjLinha, nObjColun, " + variavelConteud + ", oDialogPvt, " + variavelAcoes + ", nObjLargu, nObjAltur, , oFontPadrao, , lDimPixels)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := 'Button' ";
            		variavelAcoes   += "    := {|| MsgInfo('Coloque sua funcao aqui', 'Atencao " + (String)comps[i].getName() + "')} ";
            	}
            	//List - TListBox
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafList")) {
            		variavelObjeto  = "oLis" + variavelObjSeq;
            		variavelConteud = "nLis" + variavelObjSeq;
            		variavelAcoes   = "aLis" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TListBox\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TListBox():New(nObjLinha, nObjColun, {|u| Iif(PCount() > 0 , " + variavelConteud + " := u, " + variavelConteud + ")}, " + variavelAcoes + ", nObjLargu, nObjAltur, /*bChange*/, oDialogPvt, /*bValid*/, , , lDimPixels, , /*bLDBLClick*/, oFontPadrao)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := 0 ";
            		variavelAcoes   += "    := {'Item 1', 'Item 2', 'Item 3', 'Item 4', 'etc'} ";
            	}
            	//TextArea - TMultiGet
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafTextArea")) {
            		variavelObjeto  = "oMul" + variavelObjSeq;
            		variavelConteud = "cMul" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TMultiGet\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TMultiGet():New(nObjLinha, nObjColun, {|u| Iif(PCount() > 0 , " + variavelConteud + " := u, " + variavelConteud + ")}, oDialogPvt, nObjLargu, nObjAltur, oFontPadrao, , , , , lDimPixels, , , /*bWhen*/, , , /*lReadOnly*/, /*bValid*/, , , /*lNoBorder*/, .T.)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := '' ";
            	}
            	//CheckBox - TCheckBox
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafCheckBox")) {
            		variavelObjeto  = "oChk" + variavelObjSeq;
            		variavelConteud = "lChk" + variavelObjSeq;
            		variavelAcoes   = "cChk" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TCheckBox\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TCheckBox():New(nObjLinha, nObjColun, " + variavelAcoes + ", {|u| Iif(PCount() > 0 , " + variavelConteud + " := u, " + variavelConteud + ")}, oDialogPvt, nObjLargu, nObjAltur, , /*bLClicked*/, oFontPadrao, /*bValid*/, /*nClrText*/, /*nClrPane*/, , lDimPixels)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := .F. ";
            		variavelAcoes   += "    := 'CheckBox' ";
            	}
            	//ComboBox - TComboBox
            	else if (cmpAnalisado.getCriaComponente((JComponent)comps[i]).contains("CafComboBox")) {
            		variavelObjeto  = "oCmb" + variavelObjSeq;
            		variavelConteud = "cCmb" + variavelObjSeq;
            		variavelAcoes   = "aCmb" + variavelObjSeq;
            		
            		variavelTexto += "        //" + (String)comps[i].getName() + " - usando a classe TComboBox\n";
            		variavelTexto += "        nObjLinha := " + linha + "\n";
                    variavelTexto += "        nObjColun := " + coluna + "\n";
                    variavelTexto += "        nObjLargu := " + largura + "\n";
                    variavelTexto += "        nObjAltur := " + altura + "\n";                    
                    variavelTexto += "        " + variavelObjeto + "  := TComboBox():New(nObjLinha, nObjColun, {|u| Iif(PCount() > 0 , " + variavelConteud + " := u, " + variavelConteud + ")}, " + variavelAcoes + ", nObjLargu, nObjAltur, oDialogPvt, , /*bChange*/, /*bValid*/, /*nClrText*/, /*nClrBack*/, lDimPixels, oFontPadrao)\n";
            		variavelTexto += "\n";
            		
            		variavelConteud += "    := '01' ";
            		variavelAcoes   += "    := {'01=Sim', '02=Nao', '03=Talvez', 'ZZ=Etc'} ";
            	}
            	
            	//Somente se houver variaveis
            	if (! variavelObjeto.isEmpty() && ! variavelConteud.isEmpty()) {
            		txaClasse.append("    //" + (String)comps[i].getName() + " \n");
	            	txaClasse.append("    Private " + variavelObjeto + " \n");
	            	txaClasse.append("    Private " + variavelConteud + " \n");
            	}
            	if (! variavelAcoes.isEmpty()) {
            		txaClasse.append("    Private " + variavelAcoes + " \n");
            	}
            	
            }
            
            txaClasse.append("    \n");
            txaClasse.append("    //Cria a dialog\n");
            txaClasse.append("    oDialogPvt := TDialog():New(0, 0, nJanAltura, nJanLargur, cJanTitulo, , , , , , nCorFundo, , , lDimPixels)\n");
            txaClasse.append("    \n");
            txaClasse.append(variavelTexto);
            txaClasse.append("    \n");
            txaClasse.append("    //Ativa e exibe a janela\n");
            txaClasse.append("    oDialogPvt:Activate(, , , lCentraliz, , , bBlocoIni)\n");
            txaClasse.append("    \n");
            txaClasse.append("    FWRestArea(aArea)\n");
            txaClasse.append("Return\n");
            
            /*
            txaClasse.append(
            "import java.awt.*;" +
            "\nimport java.awt.event.*;" +
            "\nimport javax.swing.*;" +
            "\n" +
            "\n /* * " + frmModi.nomJan +
            ((frmModi.nomPac.equals(""))?"":"\n * Projeto..: " + frmModi.nomPac) +
            "\n * [ coloque aqui a descricao da classe ]" +
            "\n * @author Fernando Anselmo em " +
            (new SimpleDateFormat("MMM - yyyy")).format(new Date()) +
            "\n * @version 1.0" +
            "\n * /" +
            "\npublic class " + frmModi.nomJan + " extends " +
            //((frmModi.ckbModal.isSelected())?"JDialog":"JFrame") + " {" +
            "\n" +
            "\n    // Bloco 1 - Dados dos Objetos da Janela" +
            "\n" +
            "\n    public " + frmModi.nomJan + "() {" +
            "\n        // Bloco 2 - Dados da Criacao da Janela" +
            "\n" +
            "\n        // Bloco 3 - Dados da Criacao dos Controles na Janela" +
            "\n" +
            "\n        this.addWindowListener(new WindowAdapter() {" +
            "\n            public void windowClosing(WindowEvent e) {" +
            "\n                aoFechar();" +
            "\n            }" +
            "\n        });" +
            "\n    }" +
            "\n" +
            "\n    private void aoFechar() {" +
            "\n        System.exit(0);" +
            "\n    }" +
            "\n" +
            "\n    // Insira aqui os metodos para os eventos" +
            "\n" +
            "\n    public static void main(String args[]) {" +
            "\n        new " + frmModi.nomJan + "();" +
            "\n    }" +
            "\n}"
            );
            geraObjeto();
            */
        }
    }
    
    /*
    private void geraObjeto() {
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        txaObjeto.setText("    // Bloco 1 - Objetos da Janela");
        Component[] comps = frmModi.getContentPane().getComponents();
        for (int i = 0; i < comps.length; i++) {
            txaObjeto.append(cmpAnalisado.getCriaComponente((JComponent)comps[i]));
        }
        // ***
        txaObjeto.append("\n\n        // Bloco 2 - Definicao dos dados da Janela");
        txaObjeto.append("\n        this.getContentPane().setLayout(null);");
        txaObjeto.append("\n        this.getContentPane().setBackground(new Color(" +
        frmModi.getContentPane().getBackground().getRed() + ", " +
        frmModi.getContentPane().getBackground().getGreen() + ", " +
        frmModi.getContentPane().getBackground().getBlue() + "));");
        txaObjeto.append("\n        this.setSize(" + frmModi.getWidth() + ", " +
        frmModi.getHeight() + ");");
        txaObjeto.append("\n        this.setLocation(" + frmModi.getX() + ", " +
        frmModi.getY() + ");");
        txaObjeto.append("\n        this.setTitle(\"" + frmModi.getTitle() + "\");");
        /*txaObjeto.append("\n        this.setResizable(" +
        ((frmModi.ckbRedimens.isSelected())?"true":"false") + ");");
        txaObjeto.append(
        ((frmModi.ckbModal.isSelected())?"\n        this.setModal(true);":""));* /
        // ***
        txaObjeto.append("\n\n        // Bloco 3 - Criacao dos Objetos na Janela");
        for (int i = 0; i < comps.length; i++) {
            txaObjeto.append(cmpAnalisado.getPropComponente((JComponent)comps[i]));
            txaObjeto.append(cmpAnalisado.getCriaEvento((JComponent)comps[i]));
        }
        txaObjeto.append("\n        this.visible(true);");
    }*/
    
    private void acaoCopiar() {
        StringSelection contents = new StringSelection(txaClasse.getText());
        Clipboard clipboard = getToolkit().getSystemClipboard();
        clipboard.setContents(contents,null);
        JOptionPane.showMessageDialog(null, "Texto copiado, use Ctrl+V para colar o conteúdo.", "Cópia", 1);
    }
    
    private void acaoSalvar() {
    	FileDialog dig = new FileDialog(frmModi, "Salvar Arquivo", FileDialog.SAVE);
        dig.setDirectory("");
        dig.setFile(frmModi.nomJan.trim() + ".prw");
        //dig.show();
        dig.setVisible(true);
        if (dig.getFile() != null) {
	        String nomArq = dig.getDirectory() + dig.getFile();
	        if (!nomArq.endsWith(".prw"))
	        	nomArq += ".prw";
	        try {
	            PrintWriter arqSaida = new PrintWriter(
	            new FileWriter(nomArq));
	            arqSaida.print(txaClasse.getText());
	            arqSaida.close();
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
        }
    }
    
    private void aoFechar(WindowEvent e) {
        dispose();
    }
}