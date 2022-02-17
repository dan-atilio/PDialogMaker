import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JComponent;

/**
 * Realiza todo o tratamento com arquivos do Projeto
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */
public class TrataArquivo {
    
    // Referência
    private PDialogMaker frmModi;
    
    /** Construtor inicial
     * @param recModi Janela Principal
     */
    public TrataArquivo(PDialogMaker recModi) {
        frmModi = recModi;
    }
    
    /** Abre o arquivo do Projeto
     * @return Lógico informando o sucesso ou fracasso da operação
     */
    public boolean abreArquivo() {
        boolean ret = false;
        FileDialog dig = new FileDialog(frmModi, "Abrir Arquivo", FileDialog.LOAD);
        dig.setDirectory("");
        dig.setFile("projeto.pdm");
        //dig.show();
        dig.setVisible(true);
        if (dig.getFile() != null && dig.getFile().contains(".pdm")) {
	        String nomArq = dig.getDirectory() + dig.getFile();
	        try {
	            if (new File(nomArq).exists()) {
	                BufferedReader arqEntrada = new BufferedReader(
	                	new FileReader(nomArq));
	                String linMnt = "";
	                if ((linMnt = arqEntrada.readLine()) != null) {
	                    separaClasse(linMnt);
	                    while ((linMnt = arqEntrada.readLine()) != null) {
	                    	if (linMnt.contains("BackgroundColor|"))
	                    		separaClasse(linMnt);
	                    	else
	                    		separaLinha(linMnt);
	                    }
	                }
	                arqEntrada.close();
	            }
	            ret = true;
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
        }
        return ret;
    }
    
    /** Salva o arquivo do Projeto
     * @return Lógico informando o sucesso ou fracasso da operação
     */
    public boolean salvaArquivo() {
        boolean ret = false;
        FileDialog dig = new FileDialog(frmModi, "Salvar Arquivo", FileDialog.SAVE);
        dig.setDirectory("");
        dig.setFile("projeto.pdm");
        //dig.show();
        dig.setVisible(true);
        if (dig.getFile() != null) {
	        String nomArq = dig.getDirectory() + dig.getFile();
	        if (!nomArq.endsWith(".pdm"))
	        	nomArq += ".pdm";
	        try {
	            PrintWriter arqSaida = new PrintWriter(
	            new FileWriter(nomArq));
	            TextArea saida = geraSaida();
	            arqSaida.print(saida.getText());
	            arqSaida.close();
	            ret = true;
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
        }
        return ret;
    }
    
    private void separaClasse(String lin) {
        StringTokenizer strTok = new StringTokenizer(lin,"|");
        if (lin.contains("Dialog|")) {
            strTok.nextToken(); //Dialog
            int posX = Integer.parseInt(strTok.nextToken()); //width
            int posY = Integer.parseInt(strTok.nextToken()); //height
            frmModi.setSize(posX, posY);
            int posLT = Integer.parseInt(strTok.nextToken()); //posX
            int posLH = Integer.parseInt(strTok.nextToken()); //posY
            frmModi.setLocation(posLT, posLH);
            frmModi.setTitle(strTok.nextToken()); //Title
            //int ckModal = Integer.parseInt(strTok.nextToken());
            //frmModi.ckbModal.setSelected((ckModal == 1));
            //int ckRedimens = Integer.parseInt(strTok.nextToken());
            //frmModi.ckbRedimens.setSelected((ckRedimens == 1));
        }
        else if (lin.contains("BackgroundColor|")) {
            strTok.nextToken(); //BackgroundColor
            int red   = Integer.parseInt(strTok.nextToken());
            int green = Integer.parseInt(strTok.nextToken());
            int blue  = Integer.parseInt(strTok.nextToken());
            frmModi.getContentPane().setBackground(new Color(red, green, blue));
        }
    }
    
    private void separaLinha(String lin) {
        StringTokenizer strTok = new StringTokenizer(lin,"|");
        if (strTok.countTokens() == 9) {
        	
            int objCriar = devNumObj(strTok.nextToken());
            String nome = strTok.nextToken();
            int posX = Integer.parseInt(strTok.nextToken());
            int posY = Integer.parseInt(strTok.nextToken());
            int posW = Integer.parseInt(strTok.nextToken());
            int posH = Integer.parseInt(strTok.nextToken());
            String texto = strTok.nextToken().trim();
            String hint = strTok.nextToken().trim();
            int nEvt = Integer.parseInt(strTok.nextToken());
            
            // Cria o objeto na Janela
            CriaComponente cria = new CriaComponente(frmModi);
            cria.criaObjetoDoArquivo(objCriar, nome, posX, posY, posW, posH, texto,
            hint, nEvt);
            
            frmModi.seqObj++;
        }
    }
    
    private TextArea geraSaida() {
        TextArea ret = new TextArea();
        
        ret.append(
	        "Dialog|" +
	        frmModi.getWidth() + "|" +
	        frmModi.getHeight() + "|" +
	        frmModi.getX() + "|" +
	        frmModi.getY() + "|" +
	        frmModi.getTitle() + "|" + '\n'
        	);
        
        ret.append(
    		"BackgroundColor|" +
    		frmModi.getContentPane().getBackground().getRed() + "|" +
    		frmModi.getContentPane().getBackground().getGreen() + "|" +
    		frmModi.getContentPane().getBackground().getBlue() + "|" + '\n'
    		);
        
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        Component[] comps = frmModi.getContentPane().getComponents();
        for (int i = 0; i < comps.length; i++) {
            ret.append(cmpAnalisado.getAnaComponente((JComponent)comps[i]) + '\n');
        }
        return ret;
    }
    
    private int devNumObj(String classSel) {
    	classSel = classSel.replace("class ", "");
        int ret = 0;
        if (classSel.equals("CafTextField"))
            ret = 1;
        else if (classSel.equals("CafPasswordField"))
            ret = 2;
        else if (classSel.equals("CafButton"))
            ret = 3;
        else if (classSel.equals("CafList"))
            ret = 4;
        else if (classSel.equals("CafTextArea"))
            ret = 5;
        else if (classSel.equals("CafRadioButton"))
            ret = 6;
        else if (classSel.equals("CafCheckBox"))
            ret = 7;
        else if (classSel.equals("CafLabel"))
            ret = 8;
        else if (classSel.equals("CafComboBox"))
            ret = 9;
        return ret;
    }
}