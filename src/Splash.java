import java.awt.Color;
import java.awt.Font;

import javax.swing.*; 

/**
 * SplashScreen exibida ao abrir o PDialogMaker
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */

public class Splash extends JFrame
{
	private static final long serialVersionUID = -124836471550170691L;
	
	//Label por contar a imagem
	private static JLabel lbSplash;
	private ImageIcon imSplash;
	private JLabel lbTitulo;
	private JLabel lbVersao;
	private JLabel lbSite;
	
	//Classe generica de Atributos
	private Atributo vari = new Atributo();

	//Monta a Splash Screen
	public Splash() {
		Font labelFont = new JLabel().getFont();
		
	    //Definindo o tipo de fechamento, o tamanho, tirando a barra de títulos, deixando no centro, definindo um icone e mostrando a janela
		setLayout(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setSize(700,300);
	    setUndecorated(true);
	    setLocationRelativeTo(null);
	    this.getContentPane().setBackground(Color.white);
	    
	    ImageIcon icone = vari.getImage("logo.png");
		this.setIconImage(icone.getImage());
	    setVisible(true);
	 
	    //Setando a imagem de splash
	    imSplash = new ImageIcon(icone.getImage());
	 
	    //Adicionando a imagem no label e mudando o tamanho
	    lbSplash = new JLabel(imSplash);
	    lbSplash.setBounds(0,0,900,500);
	 
	    //Adicionando componentes na janela
	    add(lbSplash);
	    
	    lbTitulo = new JLabel("PDialogMaker");
        lbTitulo.setForeground(Color.RED);
        lbTitulo.setFont(new Font(labelFont.getName(), Font.BOLD, 30));
        lbTitulo.setBounds(3, 5, 300, 40);
        lbSplash.add(lbTitulo);
        
        lbVersao = new JLabel(vari.CFVERSAO + " - " + vari.REVISAO);
        lbVersao.setForeground(Color.RED);
        lbVersao.setBounds(3, 35, 300, 30);
        lbSplash.add(lbVersao);
        
        lbSite = new JLabel("https://atiliosistemas.com");
        lbSite.setForeground(Color.RED);
        lbSite.setBounds(3, 55, 300, 30);
        lbSplash.add(lbSite);
	    
	    //Forçando a espera de 1500 milissegundos (1,5 segundos)
	    try {
	      Thread.sleep (1500);
	    }
	    catch (InterruptedException ex) {}
	}
 
}