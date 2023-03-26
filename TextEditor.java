import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame jframe ;
    JMenuBar menuBar ;

    JMenu file ;
    JMenu edit ;

    //menu items under file

    JMenuItem new_file ;
    JMenuItem open_file ;
    JMenuItem save_file ;

    // Menu items under edit

    JMenuItem cut ;
    JMenuItem copy ;
    JMenuItem paste ;
    JMenuItem select_all ;
    JMenuItem close ;

    JTextArea textArea ;
    TextEditor(){
        jframe = new JFrame() ;

        menuBar = new JMenuBar() ;

        textArea = new JTextArea() ;
        file = new JMenu("File") ;
        open_file = new JMenuItem("open") ;
        save_file = new JMenuItem("save") ;
        new_file = new JMenuItem("NEW File") ;
        open_file.addActionListener(this);
        save_file.addActionListener(this);
        new_file.addActionListener(this);
        file.add(new_file) ;
        file.add(open_file);
        file.add(save_file) ;


        edit = new JMenu("Edit") ;

        cut = new JMenuItem("CUT") ;
        copy = new JMenuItem("Copy") ;
        paste = new JMenuItem("Paste") ;
        select_all = new JMenuItem("Select All") ;
        close = new JMenuItem("Close") ;
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        select_all.addActionListener(this);
        close.addActionListener(this);
        edit.add(copy) ;
        edit.add(cut) ;
        edit.add(paste) ;
        edit.add(select_all) ;
        edit.add(close) ;

        menuBar.add(file) ;
        menuBar.add(edit) ;
        jframe.setJMenuBar(menuBar);
        JPanel jPanel = new JPanel() ;
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setLayout(new BorderLayout(0,0));
        jPanel.add(textArea,BorderLayout.CENTER) ;

        JScrollPane jScrollPane =new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
        jPanel.add(jScrollPane) ;
        jframe.add(jPanel) ;
        jframe.setBounds(100,100,400,400);
        jframe.setVisible(true);
        jframe.setTitle("TEXT EDITOR");
        jframe.setLayout(null);

    }


    public static void main(String[] args) {
       TextEditor te = new TextEditor() ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cut){
            textArea.cut();
        }
        if(e.getSource() == copy){
            textArea.copy();
        }
        if(e.getSource() == paste){
            textArea.paste();
        }
        if(e.getSource() == select_all){
            textArea.selectAll();
        }
        if(e.getSource() == close){
            System.exit(0);
        }
        if(e.getSource() == open_file){
            JFileChooser fileChooser = new JFileChooser("C:") ;
            int chooseoption = fileChooser.showOpenDialog(null) ;

            if(chooseoption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile() ;

                String filepath = file.getPath() ;

                try {
                    FileReader fileReader = new FileReader(filepath) ;

                    BufferedReader bufferedReader = new BufferedReader(fileReader) ;
                    String intermediate = "",output = "" ;

                    while ((intermediate =bufferedReader.readLine()) != null){
                        output += intermediate+"\n" ;
                    }
                    textArea.setText(output);
                }
                catch (IOException fnf){
                    fnf.fillInStackTrace() ;
                }

            }

        }
        if(e.getSource() == save_file){
            JFileChooser fileChooser = new JFileChooser() ;

            int chooseoption = fileChooser.showSaveDialog(null) ;

            if(chooseoption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+" .txt") ;

                try{
                    FileWriter fileWriter = new FileWriter(file) ;
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                catch (IOException io){
                    io.fillInStackTrace() ;
                }
            }

        }
        if(e.getSource() == new_file){
            TextEditor NTE = new TextEditor() ;
         }

    }
}