import java.util.StringTokenizer;
import javax.swing.JComponent;
 
/**
 * Analisa os componentes separadamente
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */
 
public class AnalisaJComponent {
    
    /** Obtém o nome completo da classe e devolve apenas o nome da classe
     * @param classe Nome completo da classe
     * @return Nome da classe simples
     */
    public String getClasse(String classe) {
        StringTokenizer strTok = new StringTokenizer(classe,".");
        if (strTok.countTokens() == 2) {
            strTok.nextToken();
            classe = strTok.nextToken();
        }
        return classe;
    }
    
    /** Verifica se o componente foi redimensionado
     * @param cmp Componente
     * @return Lógico
     */
    public boolean resize(JComponent cmp) {
        String classSel = this.getClasse("" + cmp.getClass());
        if (classSel.equals("CafTextField"))
            return (((CafTextField)cmp).getResizing());
        else if (classSel.equals("CafPasswordField"))
            return (((CafPasswordField)cmp).getResizing());
        else if (classSel.equals("CafButton"))
            return (((CafButton)cmp).getResizing());
        else if (classSel.equals("CafList"))
            return (((CafList)cmp).getResizing());
        else if (classSel.equals("CafTextArea"))
            return (((CafTextArea)cmp).getResizing());
        else if (classSel.equals("CafRadioButton"))
            return (((CafRadioButton)cmp).getResizing());
        else if (classSel.equals("CafCheckBox"))
            return (((CafCheckBox)cmp).getResizing());
        else if (classSel.equals("CafLabel"))
            return (((CafLabel)cmp).getResizing());
        else if (classSel.equals("CafComboBox"))
            return (((CafComboBox)cmp).getResizing());
        else
            return false;
    }
    
    /** Obtém o nome do componente e devolve a linha deste para salvar no arquivo do tipo:
     *  classe:nome:posX:posY:posW:posH:text:hint:numEvt
     * @param cmp Componente
     * @return Linha formatada para salvar no arquivo
     */
    public String getAnaComponente(JComponent cmp) {
        String hint = " ";
        String classe = this.getClasse("" + cmp.getClass());
        int posX = cmp.getX();
        int posY = cmp.getY();
        int posW = cmp.getWidth();
        int posH = cmp.getHeight();
        if (cmp.getToolTipText() != null)
            hint = ((cmp.getToolTipText().equals(""))?" ":cmp.getToolTipText()) ;
            String linObj = classe + "|" + cmp.getName() + "|" + posX + "|" + posY +
            "|" + posW + "|" + posH + "|" +
            ((this.getText(cmp).equals(""))?" ":getText(cmp)) + "|" +
            hint + "|" + this.getNumEvento(cmp);
            return linObj;
    }
    
    /** Obtém o nome do componente e devolve a linha deste para gerar no arquivo do tipo:
     * private JClasse nome;
     * @param cmp Componente
     * @return Linha completa para formar o bloco 1
     */
    public String getCriaComponente(JComponent cmp) {
        String classe = "J" + this.getClasse("" + cmp.getClass()).substring(3);
        String txtObj = "";
        txtObj = "\n    private " + classe + " " + cmp.getName() + ";";
        return txtObj;
    }
    
    /** Obtém o nome do componente e devolve as linhas de propriedades deste para gerar no arquivo do tipo:
     *   nome = new JClasse("text");
     *   nome.setToolTipText("hint");
     *   nome.setBounds(new Rectangle(PosX,PosY,PosW,PosH));
     *   this.getContentPane().add(nome, null);
     * @param cmp Componente
     * @return Linha completa para formar o bloco 3
     */
    public String getPropComponente(JComponent cmp) {
        String nome = cmp.getName();
        String classe = "J" + this.getClasse("" + cmp.getClass()).substring(3);
        String txtObj = "\n        " + nome +
        " = new " + classe + "(" + ((this.getText(cmp).equals(""))?"":"\"" +
        getText(cmp) + "\"") + ");";
        int posX = cmp.getX();
        int posY = cmp.getY();
        int posW = cmp.getWidth();
        int posH = cmp.getHeight();
        if (cmp.getToolTipText() != null)
            if (!(cmp.getToolTipText().trim().equals("")))
                txtObj += "\n        " + nome + ".setToolTipText(\"" + cmp.getToolTipText() + "\");";
        txtObj += "\n        " + nome + ".setBounds(new Rectangle(" +
        posX + ", " + posY + ", " +  posW + ", " + posH +  "));";
        txtObj += "\n        this.getContentPane().add(" + nome + ", null);";
        return txtObj;
    }
    
    /** Devolve (se tiver) a propriedade Text do Componente
     * @param cmp Componente
     * @return String contendo o "text" do componente
     */
    public String getText(JComponent cmp) {
        String classSel = this.getClasse("" + cmp.getClass());
        String txtObj = "";
        if (classSel.equals("CafTextField"))
            txtObj = this.devTexto((CafTextField)cmp);
        else if (classSel.equals("CafPasswordField"))
            txtObj = this.devTexto((CafPasswordField)cmp);
        else if (classSel.equals("CafButton"))
            txtObj = this.devTexto((CafButton)cmp);
        else if (classSel.equals("CafTextArea"))
            txtObj = this.devTexto((CafTextArea)cmp);
        else if (classSel.equals("CafRadioButton"))
            txtObj = this.devTexto((CafRadioButton)cmp);
        else if (classSel.equals("CafCheckBox"))
            txtObj = this.devTexto((CafCheckBox)cmp);
        else if (classSel.equals("CafLabel"))
            txtObj = this.devTexto((CafLabel)cmp);
        return txtObj;
    }
    
    /** Envia (se puder) o valor para a propriedade Text do Componente
     * @param cmp Componente
     * @param conteudo String contendo a propriedade "text" para o componente
     */
    public void setText(JComponent cmp, String conteudo) {
        String classSel = this.getClasse("" + cmp.getClass());
        if (classSel.equals("CafTextField"))
            this.setTexto((CafTextField)cmp, conteudo);
        else if (classSel.equals("CafPasswordField"))
            this.setTexto((CafPasswordField)cmp, conteudo);
        else if (classSel.equals("CafButton"))
            this.setTexto((CafButton)cmp, conteudo);
        else if (classSel.equals("CafTextArea"))
            this.setTexto((CafTextArea)cmp, conteudo);
        else if (classSel.equals("CafRadioButton"))
            this.setTexto((CafRadioButton)cmp, conteudo);
        else if (classSel.equals("CafCheckBox"))
            this.setTexto((CafCheckBox)cmp, conteudo);
        else if (classSel.equals("CafLabel"))
            this.setTexto((CafLabel)cmp, conteudo);
    }
    
    private String devTexto(CafTextField jTxF) {
        return jTxF.getText();
    }
    private String devTexto(CafPasswordField jPwF) {
        char[] senha = jPwF.getPassword();
        String retTexto = "";
        for (int i=0; i < senha.length; i++)
            retTexto += senha[i];
        return retTexto;
    }
    private String devTexto(CafButton jBut) {
        return jBut.getText();
    }
    private String devTexto(CafTextArea jTxA) {
        return jTxA.getText();
    }
    private String devTexto(CafRadioButton jRdB) {
        return jRdB.getText();
    }
    private String devTexto(CafCheckBox jCkB) {
        return jCkB.getText();
    }
    private String devTexto(CafLabel jLab) {
        return jLab.getText();
    }
    
    private void setTexto(CafTextField jTxF, String conteudo) {
        jTxF.setText(conteudo);
    }
    private void setTexto(CafPasswordField jPwF, String conteudo) {
        jPwF.setText(conteudo);
    }
    private void setTexto(CafButton jBut, String conteudo) {
        jBut.setText(conteudo);
    }
    private void setTexto(CafTextArea jTxA, String conteudo) {
        jTxA.setText(conteudo);
    }
    private void setTexto(CafRadioButton jRdB, String conteudo) {
        jRdB.setText(conteudo);
    }
    private void setTexto(CafCheckBox jCkB, String conteudo) {
        jCkB.setText(conteudo);
    }
    private void setTexto(CafLabel jLab, String conteudo) {
        jLab.setText(conteudo);
    }
    
    /** Devolve as linhas com o código da criação dos eventos do componente selecionado
     * @param cmp Componente
     * @return String contendo os eventos selecionados deste componente
     */
    public String getCriaEvento(JComponent cmp) {
        String txtObj = "";
        String nome = cmp.getName();
        int numEvento = this.getNumEvento(cmp);
        if ((numEvento - 16) >= 0) { // evento PerdaFoco
            numEvento -= 16;
            txtObj +=
            "\n        " + nome + ".addFocusListener (new FocusAdapter() {" +
            "\n            public void focusLost(FocusEvent e) {" +
            "\n                // Chamada a um método" +
            "\n            }" +
            "\n        });";
        }
        if ((numEvento - 8) >= 0) { // evento GanhoFoco
            numEvento -= 8;
            txtObj +=
            "\n        " + nome + ".addFocusListener (new FocusAdapter() {" +
            "\n            public void focusGained(FocusEvent e) {" +
            "\n                // Chamada a um método" +
            "\n            }" +
            "\n        });";
        }
        if ((numEvento - 4) >= 0) { // evento aoAcionar
            numEvento -= 4;
            txtObj +=
            "\n        " + nome + ".addActionListener (new ActionListener() {" +
            "\n            public void actionPerformed(ActionEvent e) {" +
            "\n                // Chamada a um método" +
            "\n            }" +
            "\n        });";
        }
        if ((numEvento - 2) >= 0) { // evento aoClicar
            numEvento -= 2;
            txtObj +=
            "\n        " + nome + ".addMouseListener (new MouseAdapter() {" +
            "\n            public void mouseClicked(MouseEvent e) {" +
            "\n                // Chamada a um método" +
            "\n            }" +
            "\n        });";
        }
        if ((numEvento - 1) >= 0) {  // evento aoPressionar
            numEvento -= 1;
            txtObj +=
            "\n        " + nome + ".addKeyListener (new KeyAdapter() {" +
            "\n            public void KeyPressed(KeyEvent e) {" +
            "\n                // Chamada a um método" +
            "\n            }" +
            "\n        });";
        }
        return txtObj;
    }
    
    /** Envia o número do Evento para o componente
     * @param cmp Componente
     * @param num inteiro para selecionar os eventos
     */
    public void setNumEvento(JComponent cmp, int num) {
        String classSel = this.getClasse("" + cmp.getClass());
        if (classSel.equals("CafTextField"))
            this.setNumEvento((CafTextField)cmp, num);
        else if (classSel.equals("CafPasswordField"))
            this.setNumEvento((CafPasswordField)cmp, num);
        else if (classSel.equals("CafButton"))
            this.setNumEvento((CafButton)cmp, num);
        else if (classSel.equals("CafTextArea"))
            this.setNumEvento((CafTextArea)cmp, num);
        else if (classSel.equals("CafRadioButton"))
            this.setNumEvento((CafRadioButton)cmp, num);
        else if (classSel.equals("CafCheckBox"))
            this.setNumEvento((CafCheckBox)cmp, num);
        else if (classSel.equals("CafLabel"))
            this.setNumEvento((CafLabel)cmp, num);
        else if (classSel.equals("CafComboBox"))
            this.setNumEvento((CafComboBox)cmp, num);
    }
    
    /** Obtém o número do Evento do componente
     * @param cmp Componente
     * @return Inteiro contendo os eventos selecionados
     */
    public int getNumEvento(JComponent cmp) {
        int num = 0;
        String classSel = this.getClasse("" + cmp.getClass());
        if (classSel.equals("CafTextField"))
            num = this.getNumEvento((CafTextField)cmp);
        else if (classSel.equals("CafPasswordField"))
            num = this.getNumEvento((CafPasswordField)cmp);
        else if (classSel.equals("CafButton"))
            num = this.getNumEvento((CafButton)cmp);
        else if (classSel.equals("CafTextArea"))
            num = this.getNumEvento((CafTextArea)cmp);
        else if (classSel.equals("CafRadioButton"))
            num = this.getNumEvento((CafRadioButton)cmp);
        else if (classSel.equals("CafCheckBox"))
            num = this.getNumEvento((CafCheckBox)cmp);
        else if (classSel.equals("CafLabel"))
            num = this.getNumEvento((CafLabel)cmp);
        else if (classSel.equals("CafComboBox"))
            num = this.getNumEvento((CafComboBox)cmp);
        return num;
    }
    
    private void setNumEvento(CafTextField jTxF, int num) {
        jTxF.setNumEvento(num);
    }
    private void setNumEvento(CafPasswordField jPwF, int num) {
        jPwF.setNumEvento(num);
    }
    private void setNumEvento(CafButton jBut, int num) {
        jBut.setNumEvento(num);
    }
    /*
    private void setNumEvento(CafList jLst, int num) {
        jLst.setNumEvento(num);
    }
    */
    private void setNumEvento(CafTextArea jTxA, int num) {
        jTxA.setNumEvento(num);
    }
    private void setNumEvento(CafRadioButton jRdB, int num) {
        jRdB.setNumEvento(num);
    }
    private void setNumEvento(CafCheckBox jCkB, int num) {
        jCkB.setNumEvento(num);
    }
    private void setNumEvento(CafLabel jLab, int num) {
        jLab.setNumEvento(num);
    }
    private void setNumEvento(CafComboBox jCbx, int num) {
        jCbx.setNumEvento(num);
    }
    
    private int getNumEvento(CafTextField jTxF) {
        return jTxF.getNumEvento();
    }
    private int getNumEvento(CafPasswordField jPwF) {
        return jPwF.getNumEvento();
    }
    private int getNumEvento(CafButton jBut) {
        return jBut.getNumEvento();
    }
    /*
    private int getNumEvento(CafList jLst) {
        return jLst.getNumEvento();
    }
    */
    private int getNumEvento(CafTextArea jTxA) {
        return jTxA.getNumEvento();
    }
    private int getNumEvento(CafRadioButton jRdB) {
        return jRdB.getNumEvento();
    }
    private int getNumEvento(CafCheckBox jCkB) {
        return jCkB.getNumEvento();
    }
    private int getNumEvento(CafLabel jLab) {
        return jLab.getNumEvento();
    }
    private int getNumEvento(CafComboBox jCbx) {
        return jCbx.getNumEvento();
    }
}